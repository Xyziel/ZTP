import React from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faDog, faCat } from "@fortawesome/free-solid-svg-icons";
import "../../styles/css/mainLabel.css"

export function MainLabel() {
    return (
      <div className="mainLabelContainer">
          <FontAwesomeIcon icon={faDog} size="2x"/>
          <p className="mainLabel">Schronisko</p>
          <FontAwesomeIcon icon={faCat} flip="horizontal" size="2x"/>
      </div>
    );
}