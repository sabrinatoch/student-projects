import React, { useState } from "react";

export default function TitleSelect({ search }) {
  const [inputTitle, setInputTitle] = useState("");
  const handleChange = (e) => {
    setInputTitle(e.target.value);
  };
  const handleClick = () => {
    search(inputTitle);
    clear();
  }; // handleClick
  const clear = () => {
    setInputTitle("");
  }; // clear
  return (
    <div>
      <label htmlFor="title">Search by Title:</label>
      <br></br>
      <input
        type="text"
        id="title"
        onChange={handleChange}
        value={inputTitle}
      ></input>
      <br></br>
      <button className="App-btn" onClick={handleClick}>
        Submit
      </button>
    </div>
  );
} // ActorSelect()
