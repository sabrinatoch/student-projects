const filterGame = document.querySelector("select[name='game']");
const sortBy = document.querySelector("#sort");
var players;
var sortStatus;

filterGame.addEventListener("change", () => {
  sortStatus = sortBy.value; // check sortStatus

  fetch("../files/players.json", {
    method: "get",
  })
    .then((response) => {
      return response.json();
    })
    .then((json) => {
      players = json;

      let selected = filterGame.value;

      if (selected == "all") {
        if (sortStatus == "first") sortFirst();
        else sortLast();
        mapJSON(players);
      } // if 'all'
      else {
        // filter by selected value
        players = players.filter((player) => {
          let bool = false;
          if (Array.isArray(player.games_played)) {
            player.games_played.map((game) => {
              if (game.game == selected) bool = true;
            });
          } // if multiple games // only one game
          else if (player.games_played.game == selected) bool = true;
          return bool;
        }); // filter

        // display
        if (sortStatus == "first") sortFirst();
        else sortLast();
        mapJSON(players);
      } // else
    })
    .catch((err) => {
      main.innerHTML = `Error occurred: ${err}`;
      main.style.fontSize = "1.4em";
      main.style.color = "white";
    }); // catch
}); // filterByGame event

sortBy.addEventListener("change", () => {
  sortStatus = sortBy.value;
  if (sortStatus == "last") sortLast();
  else sortFirst();
  mapJSON(players);
}); // sortBy event
