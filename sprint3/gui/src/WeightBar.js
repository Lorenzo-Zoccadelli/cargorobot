// src/WeightBar.js
import React from 'react';
import './WeightBar.css';

class WeightBar extends React.Component {
  render() {
    const { totalWeight, maxWeight } = this.props;
    const percentage = Math.min((totalWeight / maxWeight) * 100, 100);

    // colore dinamico in base alla percentuale
    let color = '#0aed34ff'; // verde
    if (percentage >= 60 && percentage < 80) color = '#f2cb08ff'; // arancione
    if (percentage >= 80) color = '#ff0400ff'; // rosso

    return (
      <div className="weight-bar-container">
        <h3>⚖️ Carico Stiva</h3>
        <div className="bar-outer">
          <div
            className="bar-inner"
            style={{
              width: `${percentage}%`,
              backgroundColor: color,
            }}
          />
        </div>
        <p className="weight-text">
          {totalWeight} kg / {maxWeight} kg
        </p>
        {percentage >= 100 && (
          <p className="warning-text">⚠ Capacità massima raggiunta!</p>
        )}
      </div>
    );
  }
}

export default WeightBar;
