import { useEffect, useState } from "react";
import "../styles/App.css";
import List from "./List";
import Movie from "./Movie";
import ActorSelect from "./ActorSelect";
import YearSelect from "./YearSelect";
import MovieAdd from "./MovieAdd";
import TitleSelect from "./TitleSelect";

function App() {
  const [selectedMovie, setSelectedMovie] = useState({});
  const [movies, setMovies] = useState([]);
  const [isHidden, setHidden] = useState(true);
  const [addPage, setAddPage] = useState(false);

  useEffect(() => {
    const fetchMovies = async () => {
      setMovies(
        await fetch("/movies")
          .then((res) => res.json())
          .then((data) => data)
      );
    };
    fetchMovies();
  }, []); // useEffect()

  const searchMovie = (id) => {
    async function fetchMov() {
      setSelectedMovie(
        await fetch("/movies/" + id)
          .then((res) => res.json())
          .then((data) => data)
      );
    }
    fetchMov();
    setHidden(false);
  }; // searchMovie()

  const closePopup = () => {
    setHidden(true);
  }; // closePopup()

  const searchActorMovie = (name) => {
    if (name.length > 0) {
      async function fetchActorMovies() {
        setMovies(
          await fetch("/actors/" + name)
            .then((res) => res.json())
            .then((data) => data)
        );
      }
      fetchActorMovies();
    }
  }; // searchActorMovie()

  const searchYearMovie = (year) => {
    if (year.length > 0) {
      async function fetchYearMovies() {
        setMovies(
          await fetch("/years/" + year)
            .then((res) => res.json())
            .then((data) => data)
        );
      }
      fetchYearMovies();
    }
  }; // searchYearMovie()

  const searchTitleMovie = (title) => {
    if (title.length > 0) {
      async function fetchTitleMovies() {
        setMovies(
          await fetch("/title/" + title)
            .then((res) => res.json())
            .then((data) => data)
        );
      }
      fetchTitleMovies();
    }
  }; // searchTitleMovie()

  const addMoviePage = () => {
    setAddPage(!addPage);
  }; // addMoviePage()

  const listAll = () => {
    const fetchMovies = async () => {
      setMovies(
        await fetch("/movies")
          .then((res) => res.json())
          .then((data) => data)
      );
    };
    fetchMovies();
  }; // listAll()

  return (
    <div className="App">
      {addPage ? (
        <div className="newMovie">
          <header className="App-header">New Movie</header>
          <button className="App-btn add-btn" onClick={addMoviePage}>
            Back
          </button>
          <MovieAdd changePage={addMoviePage} />
        </div>
      ) : (
        <div>
          {" "}
          <header className="App-header">Movie List</header>
          <button className="App-btn add-btn" onClick={addMoviePage}>
            Add Movie
          </button>
          <button className="App-btn list-btn" onClick={listAll}>
            List All
          </button>
          <div id="apart">
            <div id="search">
              <ActorSelect search={searchActorMovie} />
              <YearSelect search={searchYearMovie} />
              <TitleSelect search={searchTitleMovie} />
            </div>
            {/* <MovieAdd/> */}
            {selectedMovie.Key ? (
              <Movie
                mov={selectedMovie}
                onClose={closePopup}
                hidden={isHidden}
              />
            ) : (
              <div></div>
            )}
            <List movies={movies} onMovieClick={searchMovie} />
          </div>
        </div>
      )}
    </div>
  ); // return
} // App()

export default App;
