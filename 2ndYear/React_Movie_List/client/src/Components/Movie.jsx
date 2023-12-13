import React, { useState, useEffect } from "react";
import Actor from "./Actor";
import Genre from "./Genre";

export default function Movie({ mov, onClose, hidden }) {
  return (
    <div className={hidden ? "hidden" : "popup"}>
      <a href="#" className="close" onClick={onClose}></a>
      <p className="title">{mov.Title}</p>
      <p>Year: {mov.Year}</p>
      <p>Runtime: {mov.Runtime} minutes</p>
      <p>Revenue: ${mov.Revenue} million</p>
      <Actor actors={mov.Actors} />
      <Genre genre={mov.Genre} />
    </div>
  );
} // Movie
