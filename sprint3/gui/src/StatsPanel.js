import React from "react";
import "./StatsPanel.css";

class StatsPanel extends React.Component {
  render() {
    const { totalSlots, occupiedSlots, avgWeight, heaviestProduct } = this.props;

    return (
      <div className="stats-panel">
        <h3>📊 Statistiche in tempo reale</h3>
        <div className="stats-grid">
          <div className="stat-item">
            <span className="stat-label">Slot occupati:</span>
            <span className="stat-value">
              {occupiedSlots} / {totalSlots}
            </span>
          </div>

          <div className="stat-item">
            <span className="stat-label">Peso medio per slot:</span>
            <span className="stat-value">{avgWeight.toFixed(2)} kg</span>
          </div>

          <div className="stat-item">
            <span className="stat-label">Prodotto più pesante:</span>
            <span className="stat-value">
              {heaviestProduct
                ? `${heaviestProduct.productName} (${heaviestProduct.weight} kg)`
                : "Nessuno"}
            </span>
          </div>
        </div>
      </div>
    );
  }
}

export default StatsPanel;
