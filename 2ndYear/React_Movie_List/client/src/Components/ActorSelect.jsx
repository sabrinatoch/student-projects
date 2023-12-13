import React, { useState } from "react";

export default function ActorSelect({ search }) {
  const [inputName, setInputName] = useState("");
  const handleChange = (e) => {
    setInputName(e.target.value);
  };
  const handleClick = () => {
    search(inputName);
    clear();
  }; // handleClick
  const clear = () => {
    setInputName("");
  }; // clear
  return (
    <div>
      <label htmlFor="name">Search by Actor:</label>
      <br></br>
      <input
        type="text"
        id="name"
        onChange={handleChange}
        value={inputName}
      ></input>
      <br></br>
      <button className="App-btn" onClick={handleClick}>
        Submit
      </button>
    </div>
  );
} // ActorSelect()
