const express = require("express");
const app = express();
const port = 8888;
const fs = require("fs");
const path = require("path");
const movies = path.join(__dirname, "data/movies.json");

app.use(express.static(__dirname + "../client/public"));

app.get("/movies", (req, res) => {
  fs.readFile(movies, (err, data) => {
    if (err) {
      res.status(500).json({ error: "Error reading file" });
      return;
    } // if (err)
    else {
      let movs = JSON.parse(data);
      movs = movs.map((mov) => ({
        Key: mov.Key,
        Title: mov.Title,
        Year: mov.Year,
      }));
      movs.sort((a, b) => {
        a = a.Title.toLowerCase();
        b = b.Title.toLowerCase();
        return a < b ? -1 : a > b ? 1 : 0;
      }); // sort
      res.json(movs);
    } // else
  }); // readFile
}); // movies

app.get("/movies/:id", (req, res) => {
  let id = req.params.id;
  fs.readFile(movies, (err, data) => {
    if (err) {
      res.status(500).json({ error: "Error reading file" });
      return;
    } // if (err)
    else {
      let movs = JSON.parse(data);
      let result = movs.find((mov) => mov.Key == id);
      if (result) res.json(result);
      else res.end("No movie found");
    } // else
  }); // readFile
}); // id

app.get("/actors/:name", (req, res) => {
  let name = req.params.name;
  fs.readFile(movies, (err, data) => {
    if (err) {
      res.status(500).json({ error: "Error reading file" });
      return;
    } // if (err)
    else {
      let movs = JSON.parse(data);
      let moviesWithActor = movs.filter((mov) =>
        mov.Actors.some((actor) =>
          actor.toLowerCase().includes(name.toLowerCase())
        )
      );
      if (moviesWithActor.length > 0) {
        moviesWithActor = moviesWithActor.map((mov) => ({
          Key: mov.Key,
          Title: mov.Title,
          Year: mov.Year,
        }));
        moviesWithActor.sort((a, b) => {
          a = a.Title.toLowerCase();
          b = b.Title.toLowerCase();
          return a < b ? -1 : a > b ? 1 : 0;
        }); // sort
        res.json(moviesWithActor);
      } else res.json({});
    } // else
  }); // readFile
}); // name

app.get("/years/:year", (req, res) => {
  let year = req.params.year;
  fs.readFile(movies, (err, data) => {
    if (err) {
      res.status(500).json({ error: "Error reading file" });
      return;
    } // if (err)
    else {
      let movs = JSON.parse(data);
      let moviesWithYear = movs.filter((mov) => mov.Year == year);
      if (moviesWithYear.length > 0) {
        moviesWithYear = moviesWithYear.map((mov) => ({
          Key: mov.Key,
          Title: mov.Title,
          Year: mov.Year,
        }));
        moviesWithYear.sort((a, b) => {
          a = a.Title.toLowerCase();
          b = b.Title.toLowerCase();
          return a < b ? -1 : a > b ? 1 : 0;
        }); // sort
        res.json(moviesWithYear);
      } else res.json({});
    } // else
  }); // readFile
}); // year

app.get("/title/:name", (req, res) => {
  let name = req.params.name;
  fs.readFile(movies, (err, data) => {
    if (err) {
      res.status(500).json({ error: "Error reading file" });
      return;
    } // if (err)
    else {
      let movs = JSON.parse(data);
      let moviesWithTitle = movs.filter((mov) =>
        mov.Title.toLowerCase().includes(name.toLowerCase())
      );
      if (moviesWithTitle.length > 0) {
        moviesWithTitle = moviesWithTitle.map((mov) => ({
          Key: mov.Key,
          Title: mov.Title,
          Year: mov.Year,
        }));
        moviesWithTitle.sort((a, b) => {
          a = a.Title.toLowerCase();
          b = b.Title.toLowerCase();
          return a < b ? -1 : a > b ? 1 : 0;
        }); // sort
        res.json(moviesWithTitle);
      } else res.json({});
    } // else
  }); // readFile
}); // title

app.use(express.json());

app.post("/movies", (req, res) => {
  const newMovie = req.body;
  fs.readFile(movies, (err, data) => {
    if (err) {
      res.status(500).json({ error: "Error reading file" });
      return;
    }
    try {
      let movs = JSON.parse(data);
      let highestID = 0;
      movs.map((mov) => {
        if (mov.Key > highestID) highestID = mov.Key;
      }); // map
      newMovie.Key = highestID + 1;
      movs.push(newMovie);

      fs.writeFile(movies, JSON.stringify(movs, null, 2), (err) => {
        if (err) {
          res.status(500).json({ error: "Error writing file" });
          return;
        }
        res.status(200).json({ message: "Movie added successfully" });
      });
    } catch (error) {
      res.status(500).json({ error: "Error parsing JSON data" });
    } // catch
  }); // readFile
}); // post (add movie)

app.listen(port, () => {
  console.log(`Listening on port ${port}`);
}); // listen
