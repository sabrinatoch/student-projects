import React, { useState } from "react";

export default function YearSelect({ search }) {
  const [inputYear, setInputYear] = useState("");
  const handleChange = (e) => {
    setInputYear(e.target.value);
  };
  const handleClick = () => {
    search(inputYear);
    clear();
  }; // handleClick
  const clear = () => {
    setInputYear("");
  }; // clear
  return (
    <div>
      <label htmlFor="year">Search by Year:</label>
      <br></br>
      <input
        type="number"
        id="year"
        onChange={handleChange}
        value={inputYear}
      ></input>
      <br></br>
      <button className="App-btn" onClick={handleClick}>
        Submit
      </button>
    </div>
  );
} // YearSelect()
