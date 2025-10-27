import logo from './logo.svg';
import './App.css';

import React from 'react';
import Slot from './Slot';
import WeightBar from './WeightBar';
import StatsPanel from "./StatsPanel";


const url =          "ws://guibackend:9094/ws/stiva";
const apiPathCarico = "/api/cargoservice/richiesta-carico";



class App extends React.Component{
  constructor(props){
    super(props);
    this.state = {
      tot_slots: 5,
      occupied: [],
      newProductId: "",
      maxLoad:0
    }
    this.onUpdate = this.onUpdate.bind(this);
    this.handleSendRequest = this.handleSendRequest.bind(this);
  }

  componentDidMount() {
    const ws = new WebSocket(url);
    this.ws = ws;

    ws.onopen = () => {console.log("websocket connection has been opened");
                        this.setState({ connected: true });
    }
    ws.onclose = () => {console.log("websocket connection has been closed");
                         this.setState({ connected: false });
    }
    ws.onerror = (error) => {console.log("error has occured" + error);
                            this.setState({ connected: true });
    }
    ws.onmessage = (event) => {

      const data = JSON.parse(event.data);
      console.log("Received:", data);
      
      const slotMap = data.slotMap;
      const maxLoad = data.maxLoad;

      this.setState({ maxLoad: maxLoad });
      this.onUpdate(slotMap);
    }
  }



  onUpdate(slotMap) {
    const updated = [];
    
    for(let i = 1; i<this.state.tot_slots; i++){
      let slotName = "Slot"+i
      if(slotMap[slotName]){
        updated.push({
        slot_number: i,
        pid: slotMap[slotName].productId,
        productName: slotMap[slotName].name,
        weight: slotMap[slotName].weight,
      });
      }
    }

    updated.sort((a, b) => a.slot_number - b.slot_number)

    this.setState({ 
      tot_slots: this.state.tot_slots,
      occupied: updated 
    });
  }

  //INVIO RICHIESTA IN INPUT
  //------------------------------------------------------
  handleInputChange(e) {
    this.setState({ newProductId: e.target.value });
  }

  handleSendRequest() {
  const { newProductId } = this.state;

  if (newProductId.trim() === "") {
    alert("⚠️ Inserisci un ID prodotto valido!");
    return;
  }

  fetch(apiPathCarico, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ productId: parseInt(newProductId) }),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Errore nella richiesta REST");
      }
      return response.json().catch(() => ({})); // gestisce risposte vuote o non-JSON
    })
    .then((data) => {
      console.log("✅ Risposta server:", data);
      if(data.tipoRisposta == 'richiestaCaricoAccettata'){
        alert("✅ Richiesta di carico accettata!");
      
      }
      else if(data.tipoRisposta == 'richiestaCaricoRifiutata'){
        alert("❌ Richiesta di carico rifiutata: "+ data.messaggio);
      }
      this.setState({ newProductId: "" });
    })
    .catch((error) => {
      console.error("❌ Errore REST:", error);
      alert("❌ Errore durante l'invio della richiesta al server");
    });
}
  //------------------------------------------------------


render() {
    const tot = this.state.tot_slots;
    const occupied = this.state.occupied;
    const slots = [];

    // primi 4 slot
    for (let i = 0; i < tot - 1; i++) {
      const prod = occupied.find((s) => s.slot_number === i + 1);
      const current_slot = prod ? (
        <Slot
          key={i}
          number={i + 1}
          state="occupied"
          pid={prod.pid}
          name={prod.productName}
          weight={prod.weight}
        />
      ) : (
        <Slot key={i} number={i + 1} state="free" pid="" weight="" />
      );

      slots.push(current_slot);
    }

    // slot 5 unavailable
    const bottomSlot = (
      <Slot
        key={tot}
        number={tot}
        state="unavailable"
        pid="unavailable"
        weight=""
      />
    );

    return (
      <div className="main-container">
        {/*LEFT SIDE BAR*/}
         <div className="left-sidebar">
          <h2>📦 Richiesta Carico</h2>
          <p>
            <strong>Connessione:</strong> {this.state.connected ? "🟢 Attiva" : "🔴 Disconnessa"}
          </p>
          <div className="input-group">
            <label htmlFor="productId">ID Prodotto:</label>
            <input
              id="productId"
              type="number"
              placeholder="Inserisci ID prodotto"
              value={this.state.newProductId}
              onChange={(e) => this.setState({ newProductId: e.target.value })}
              className="input-field"
            />
          </div>
          <button className="send-btn" onClick={this.handleSendRequest}>
            Invia Richiesta
          </button>
        </div>
        
        {/*CORPO CENTRALE*/}
        <div className="box">
          <h2>⚓ Stato stiva</h2>
          <div className="slot-grid">{slots}</div>
          <div className="bottom-slot">{bottomSlot}</div>
        </div>

        {/*PANNELLO DI CONTROLLO*/}
        <div className="side-panel">
          <WeightBar
            totalWeight={occupied.reduce((acc, s) => acc + (s.weight || 0), 0)}
            maxWeight={this.state.maxLoad}
          />

          <StatsPanel
            totalSlots={tot-1}
            occupiedSlots={occupied.length}
            avgWeight={
              occupied.length > 0
                ? occupied.reduce((acc, s) => acc + s.weight, 0) / occupied.length
                : 0
            }
            heaviestProduct={
              occupied.length > 0
                ? occupied.reduce((max, s) =>
                    s.weight > (max.weight || 0) ? s : max
                  )
                : null
            }
          />
        </div>
      </div>
    );
  }
}


// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }

export default App;
