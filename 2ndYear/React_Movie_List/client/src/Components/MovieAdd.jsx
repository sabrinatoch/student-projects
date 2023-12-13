import React, { useState } from "react";

export default function MovieAdd({ changePage }) {
  const [title, setTitle] = useState("");
  const [year, setYear] = useState(0);
  const [runtime, setRuntime] = useState(0);
  const [revenue, setRevenue] = useState(0);
  const [actor, setActor] = useState("");
  const [genre, setGenre] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    let genreArr = genre.split(", ");
    let actorArr = actor.split(", ");
    let newMovie = {
      Key: -1,
      Title: title,
      Genre: genreArr,
      Actors: actorArr,
      Year: parseInt(year),
      Runtime: parseInt(runtime),
      Revenue: parseFloat(revenue),
    }; // newMovie
    try {
      const response = await fetch("/movies", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newMovie),
      });
      if (response.ok) {
        alert("Movie added successfully!");
        changePage();
      } else alert("Failed to add movie.");
    } catch (err) {
      alert("Something went wrong..." + err);
    } // catch
  };

  return (
    <div id="form">
      <form onSubmit={handleSubmit}>
        <label htmlFor="title">Title:</label>
        <br></br>
        <input
          type="text"
          id="title"
          required={true}
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        ></input>
        <br></br>
        <label htmlFor="year">Year:</label>
        <br></br>
        <input
          type="number"
          id="year"
          required={true}
          value={year}
          min="0"
          onChange={(e) => setYear(e.target.value)}
        ></input>
        <br></br>
        <label htmlFor="runtime">Runtime (in minutes):</label>
        <br></br>
        <input
          type="number"
          id="runtime"
          required={true}
          value={runtime}
          min="0"
          onChange={(e) => setRuntime(e.target.value)}
        ></input>
        <br></br>
        <label htmlFor="revenue">Revenue (in millions):</label>
        <br></br>
        <input
          type="number"
          id="revenue"
          required={true}
          value={revenue}
          min="0"
          step="0.01"
          onChange={(e) => setRevenue(e.target.value)}
        ></input>
        <br></br>
        <label htmlFor="actors">Actors (separated by commas):</label>
        <br></br>
        <input
          type="text"
          id="actors"
          required={true}
          value={actor}
          onChange={(e) => setActor(e.target.value)}
        ></input>
        <br></br>
        <label htmlFor="genres">Genres (separated by commas):</label>
        <br></br>
        <input
          type="text"
          id="genres"
          required={true}
          value={genre}
          onChange={(e) => setGenre(e.target.value)}
        ></input>
        <br></br>
        <input type="submit" value="Submit" className="App-btn"></input>
      </form>
    </div>
  );
} // MovieAdd()
