import './Slot.css'
import React from "react";

class Slot extends React.Component{
    constructor(props){
        super(props); 
    }

    render(){

        let buttonText, pid = this.props.pid;
        if(pid == 'unavailable'){
            buttonText = 'UNAVAIBLE';
        }
        else{
            buttonText = pid ? 'PID: '+pid : 'FREE'; 
            buttonText += this.props.weight ? '\nName: ' + this.props.name : '';
            buttonText += this.props.weight ? '\nW: ' + this.props.weight + 'kg' : '';
        }
            
        return(
            <div>
                <label>Slot n.{this.props.number}</label>
                <button className={this.props.state} disabled="true">{buttonText}</button>
            </div>
        )
    }
}
export default Slot;