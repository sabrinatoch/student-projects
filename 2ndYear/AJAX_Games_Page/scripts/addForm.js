const form = document.querySelector("#form");
const boxes = document.querySelector("#boxes");
const main = document.querySelector("main");
const successDiv = document.querySelector("#success");
let jsonObj;
let highestID = 0;

window.addEventListener("load", () => {
  document
    .querySelector("body")
    .removeChild(document.querySelector("#forest-ext-shadow-host"));

  fetch("../files/games.json", {
    method: "get",
  })
    .then((response) => {
      return response.json();
    })
    .then((json) => {
      json.map((game) => {
        let box = document.createElement("input");
        box.type = "checkbox";
        let label = document.createElement("label");
        box.name = `${game.game}`;
        box.value = `${game.game.charAt(0).toUpperCase() + game.game.slice(1)}`;
        label.for = `${game.game}`;
        label.innerHTML = `${
          game.game.charAt(0).toUpperCase() + game.game.slice(1)
        }`;

        boxes.appendChild(box);
        boxes.appendChild(label);
        boxes.innerHTML += "&emsp;";
      }); // map
    })
    .catch((err) => {
      main.innerHTML = `Error occurred: ${err}`;
      main.style.fontSize = "1.4em";
      main.style.color = "white";
    }); // catch

  fetch("../files/players.json", {
    method: "get",
  })
    .then((response) => {
      return response.json();
    })
    .then((json) => {
      json.map((player) => {
        if (player.id > highestID) highestID = player.id;
      });
    })
    .catch((err) => {
      main.innerHTML = `Error occurred: ${err}`;
      main.style.fontSize = "1.4em";
      main.style.color = "white";
    }); // catch

  document.querySelector("button").addEventListener("click", () => {
    window.location.href = "../gamePlayers.html";
  });
}); // window event listener

document.querySelector("#submit").addEventListener("click", (event) => {
  event.preventDefault();
  addToJSON();
}); // submit listener

function addToJSON() {
  let id = ++highestID;
  let fName = document.querySelector("#firstName").value;
  let lName = document.querySelector("#lastName").value;
  let user = document.querySelector("#user").value;
  if (user == "") {
    user =
      fName.charAt(0).toLowerCase() +
      lName.charAt(0).toLowerCase() +
      lName.slice(1);
  } // if player left username blank
  let avatar = `https://robohash.org/${user}.png?size=60x60&set=set5`;
  let email = document.querySelector("#email").value;
  let wins = document.querySelector("#wins").value;
  let losses = document.querySelector("#losses").value;
  let checkboxes = document.querySelectorAll("input[type='checkbox']");
  let date = new Date();
  let year = date.toLocaleString("default", { year: "numeric" });
  let month = date.toLocaleString("default", { month: "2-digit" });
  let day = date.toLocaleString("default", { day: "2-digit" });
  let enrolled = year + "/" + month + "/" + day;

  let checked = [];
  let games_played = [];

  checkboxes.forEach((box) => {
    if (box.checked) checked.push(box);
  }); // for each checkbox

  checked.forEach((box) => {
    let obj = {
      game: box.name,
      date: enrolled,
    }; // obj
    games_played.push(obj);
  }); // for each checked

  if (validateForm(fName, lName, email, wins, losses)) {
    jsonObj = {
      id: id,
      first_name: fName,
      last_name: lName,
      username: user,
      email: email,
      enrolled: enrolled,
      avatar: avatar,
      wins: Number(wins),
      losses: Number(losses),
      games_played: games_played,
    }; // jsonObj

    fetch("../php/addPlayer.php", {
      method: "POST",
      body: JSON.stringify(jsonObj),
    }).catch((err) => {
      main.innerHTML = `Error occurred: ${err}`;
      main.style.fontSize = "1.4em";
      main.style.color = "white";
    }); // catch

    displayNewCard();
  } // if valid
} // addToJSON()

function displayNewCard() {
  form.style.display = "none";
  successDiv.style.display = "block";

  let avatar = document.createElement("img");
  let pPlayer = document.createElement("p");
  let pInfo = document.createElement("p");
  let pWins = document.createElement("p");
  let pLosses = document.createElement("p");
  let pGames = document.createElement("p");
  let pMail = document.createElement("p");

  avatar.src = `${jsonObj.avatar}`;

  pPlayer.innerHTML = `${jsonObj.first_name} ${jsonObj.last_name}<br>`;
  pInfo.innerHTML = `
        Username: ${jsonObj.username}<br>
        Enrolled: ${jsonObj.enrolled}<br>
        `;
  pWins.innerHTML = `Wins: ${jsonObj.wins}`;
  pLosses.innerHTML = `Losses: ${jsonObj.losses}`;
  pLosses.style.marginBottom = `5px`;

  if (Array.isArray(jsonObj.games_played)) {
    pGames.innerHTML = `Games Played:<br>`;
    jsonObj.games_played.map((game) => {
      pGames.innerHTML += `&#127918; ${game.game} (${game.date})<br>`;
    });
  } // if array
  else {
    pGames.innerHTML = `Games Played:<br> &#127918; ${jsonObj.games_played.game} (${jsonObj.games_played.date})`;
  } // else

  pMail.innerHTML = `&#128231;<a href = "mailto: ${jsonObj.email}">Email Player</a>`;

  successDiv.appendChild(avatar);
  successDiv.appendChild(pPlayer);
  successDiv.appendChild(pInfo);
  successDiv.appendChild(pWins);
  successDiv.appendChild(pLosses);
  successDiv.appendChild(pGames);
  successDiv.appendChild(pMail);
} // displayNewCard()

function validateForm(first, last, email, wins, losses) {
  let valid = true;

  let regexName = /(^[A-Z][A-Z\'\`\-\s]*?(?<!\`)$){1,20}/i;
  let regexMail = /^[A-Za-z0-9_\-.]*@[A-Za-z0-9_]*.(ca|org|com)$/;
  let regexNumber = /^[0-9]+$/;

  if (!regexName.test(first)) {
    document.querySelector("#errFirst").innerHTML = `Invalid first name`;
    document.querySelector("#errFirst").style.display = "block";
    valid = false;
  } else {
    document.querySelector("#errFirst").style.display = "none";
  }
  if (!regexName.test(last)) {
    document.querySelector("#errLast").innerHTML = `Invalid last name`;
    document.querySelector("#errLast").style.display = "block";
    valid = false;
  } else {
    document.querySelector("#errLast").style.display = "none";
  }
  if (!regexMail.test(email)) {
    document.querySelector("#errEmail").innerHTML = `Invalid email`;
    document.querySelector("#errEmail").style.display = "block";
    valid = false;
  } else {
    document.querySelector("#errEmail").style.display = "none";
  }
  if (!regexNumber.test(wins)) {
    document.querySelector("#errWins").innerHTML = `Invalid number`;
    document.querySelector("#errWins").style.display = "block";
    valid = false;
  } else {
    document.querySelector("#errWins").style.display = "none";
  }
  if (!regexNumber.test(losses)) {
    document.querySelector("#errLosses").innerHTML = `Invalid number`;
    document.querySelector("#errLosses").style.display = "block";
    valid = false;
  } else {
    document.querySelector("#errLosses").style.display = "none";
  }
  return valid;
} // validateForm()

document.querySelector("#clear").addEventListener("click", () => {
  location.reload();
});
