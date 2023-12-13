const main = document.querySelector("main");
const addBtn = document.querySelector("#add");
addBtn.addEventListener("click", () => {
  window.location.href = "../addForm.html";
}); // click

window.addEventListener("load", () => {
  document
    .querySelector("body")
    .removeChild(document.querySelector("#forest-ext-shadow-host"));

  // fetching players /

  fetch("../files/players.json", {
    method: "get",
  })
    .then((response) => {
      return response.json();
    })
    .then((json) => {
      players = json;

      sortLast(players);
      mapJSON(players); // function below window event handler
    })
    .catch((err) => {
      main.innerHTML = `Error occurred: ${err}`;
      main.style.fontSize = "1.4em";
      main.style.color = "white";
    }); // catch

  // fetching games //

  fetch("../files/games.json", {
    method: "get",
  })
    .then((response) => {
      return response.json();
    })
    .then((json) => {
      
      json.map((game) => {
        let opt = document.createElement("option");
        opt.value = `${game.game}`;
        opt.innerHTML = `${
          game.game.charAt(0).toUpperCase() + game.game.slice(1)
        }`;

        filterGame.appendChild(opt);
      }); // map
    })
    .catch((err) => {
      main.innerHTML = `Error occurred: ${err}`;
      main.style.fontSize = "1.4em";
      main.style.color = "white";
    }); // catch
}); // window.onload

// functions //

function sortLast() {
  players = players.sort((a, b) => {
    let val = 0;
    if (a.last_name > b.last_name) val = 1;
    if (a.last_name < b.last_name) val = -1;
    return val;
  }); // sort
} // sortLast()

function sortFirst() {
  players = players.sort((a, b) => {
    let val = 0;
    if (a.first_name > b.first_name) val = 1;
    if (a.first_name < b.first_name) val = -1;
    return val;
  }); // sort
} // sortFirst()

function mapJSON() {
  removePlayers();

  if (players.length === 0) {
    main.innerHTML = `No players are playing this game :)`;
  } else {
    players.map((player) => {
      let container = document.createElement("div");
      let avatar = document.createElement("img");
      let pPlayer = document.createElement("p");
      let pInfo = document.createElement("p");
      let pWins = document.createElement("p");
      let pLosses = document.createElement("p");
      let pGames = document.createElement("p");
      let pMail = document.createElement("p");

      avatar.src = `${player.avatar}`;

      pPlayer.innerHTML = `${player.first_name} ${player.last_name}<br>`;
      pInfo.innerHTML = `
        Username: ${player.username}<br>
        Enrolled: ${player.enrolled}<br>
        `;
      pWins.innerHTML = `Wins: ${player.wins}`;
      pLosses.innerHTML = `Losses: ${player.losses}`;
      pLosses.style.marginBottom = `5px`;

      if (Array.isArray(player.games_played)) {
        pGames.innerHTML = `Games Played:<br>`;
        player.games_played.map((game) => {
          pGames.innerHTML += `&#127918; ${game.game} (${game.date})<br>`;
        });
      } // if array
      else {
        pGames.innerHTML = `Games Played:<br> &#127918; ${player.games_played.game} (${player.games_played.date})`;
      } // else

      pMail.innerHTML = `&#128231;<a href = "mailto: ${player.email}">Email Player</a>`;

      container.appendChild(avatar);
      container.appendChild(pPlayer);
      container.appendChild(pInfo);
      container.appendChild(pWins);
      container.appendChild(pLosses);
      container.appendChild(pGames);
      container.appendChild(pMail);

      main.appendChild(container);
    }); // map
  } // else
} // mapJSON(json)

function removePlayers() {
  let count = main.childElementCount;
  for (let i = 0; i < count; ++i)
    main.removeChild(document.querySelector("main div:first-child"));
} // removePlayers()
