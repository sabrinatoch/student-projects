let $$ = (sel) => document.querySelector(sel);
let tn = (txt) => document.createTextNode(txt);

let user = new User(
  localStorage.firstName,
  localStorage.lastName,
  localStorage.username,
  localStorage.phoneNumber,
  localStorage.city,
  localStorage.email,
  parseInt(localStorage.bank)
);

let game = new Game();

let userInfo = $$("#userInfo");
let loginInfo = $$("#loginInfo");
let stat = $$("#status");
// user info
let bankP = document.createElement("p");
let betP = document.createElement("p");
let chanceP = document.createElement("p");
let mainP = document.createElement("p");
bankP.textContent = `Bank: $${localStorage.bank}`;
bankP.style.color = "pink";
bankP.style.fontWeight = "bold";

userInfo.appendChild(bankP);

$$(
  "#p1"
).textContent = `Welcome back, ${localStorage.firstName} ${localStorage.lastName}!`;
$$(
  "#p2"
).textContent = `Username: ${localStorage.username} | City: ${localStorage.city}`;
$$("#p3").textContent = `Email: ${localStorage.email}`;
$$("#p4").textContent = `Phone #: ${localStorage.phoneNumber}`;
$$("#p5").textContent = `Last Visit: ${localStorage.date}`;
$$(
  "#p6"
).innerHTML = `Not ${localStorage.firstName} ${localStorage.lastName}? <a id="change" href="#">Change credentials</a>`;
$$("#p1").style.color = "pink";

// status
let winP = document.createElement("p");
let againP = document.createElement("p");
let diceP = document.createElement("p");

let yes = document.createElement("button");
yes.innerHTML = "Yes";
yes.classList.add("otherBtns");
let no = document.createElement("button");
no.innerHTML = "No";
no.classList.add("otherBtns");

// form variables
let bet = $$("#bet");
let main = $$("#main");
let playForm = $$("#playForm");

// buttons
let roll = document.createElement("button");
roll.innerHTML = "ROLL";
roll.classList.add("btns");
$$("#rollDiv").classList.add("rollBtn");

let btnBet = $$("#btnBet");
let btnClear = $$("#betClear");

stat.classList.add("vanish");
againP.appendChild(tn("Play again?"));

// form validation //
function validBet() {
  let valid = true;
  let regex = /^[0-9]*$/;
  if (bet.value === "") {
    bet.classList.add("errRed");
    $$("#betErr").textContent = "⚠ You must enter a bet";
    valid = false;
  } else if (!regex.test(bet.value)) {
    bet.classList.add("errRed");
    $$("#betErr").textContent = "⚠ Invalid bet";
    valid = false;
  } else if (regex.test(bet.value)) {
    let num = parseInt(bet.value);
    if (num < 1 || num > localStorage.getItem("bank")) {
      bet.classList.add("errRed");
      $$("#betErr").textContent = "⚠ Invalid bet";
      valid = false;
    } // if improper range
    else {
      bet.classList.remove("errRed");
      $$("#betErr").textContent = "";
    } // valid
  }
  return valid;
} // validBet()

function validMain() {
  let valid = true;
  let regex = /^[0-9]*$/;
  if (main.value === "") {
    main.classList.add("errRed");
    $$("#mainErr").textContent = "⚠ You must enter a main";
    valid = false;
  } else if (!regex.test(main.value)) {
    main.classList.add("errRed");
    $$("#mainErr").textContent = "⚠ Invalid main";
    valid = false;
  } else if (regex.test(main.value)) {
    let num = parseInt(main.value);
    if (num < 5 || num > 9) {
      main.classList.add("errRed");
      $$("#mainErr").textContent = "⚠ Invalid main: must be between 5 and 9";
      valid = false;
    } // if improper range
    else {
      main.classList.remove("errRed");
      $$("#mainErr").textContent = "";
    } // valid
  }
  return valid;
} // validMain()

function validBoth() {
  let valid = true;
  if (!validBet()) valid = false;
  if (!validMain()) valid = false;
  if (valid) {
    // remove errors
    $$("#betErr").textContent = "";
    bet.classList.remove("errRed");
    $$("#mainErr").textContent = "";
    main.classList.remove("errRed");

    betP.appendChild(tn(``));
    userInfo.appendChild(betP);
    mainP.appendChild(tn(``));
    userInfo.appendChild(mainP);
    diceP.appendChild(tn(``));

    setupGame();
  } // if (valid)
  return valid;
} // validBoth()

function clearBet() {
  $$("#betErr").textContent = "";
  bet.classList.remove("errRed");
  $$("#mainErr").textContent = "";
  main.classList.remove("errRed");
} // clearBet()

// game setup/reset //
function setupGame() {
  playForm.classList.add("vanish");
  // userInfo.style.margin = "auto";
  userInfo.style.marginTop = "10px";
  $$("#rollDiv").appendChild(roll);
  $$("#rollDiv").classList.add("fadeIn");
  $$("#d1").classList.remove("vanish");
  $$("#d1").classList.add("fadeIn");
  $$("#d2").classList.remove("vanish");
  $$("#d2").classList.add("fadeIn");

  roll.addEventListener("click", rollDice);

  game.main = parseInt(main.value);
  user.bet = parseInt(bet.value);

  betP.textContent = `Bet: $${user.bet}`;
  mainP.textContent = `Main: ${game.main}`;
} // setupGame()

function resetGame() {
  diceP.textContent = "";
  winP.textContent = "";
  stat.removeChild(againP);
  stat.removeChild(yes);
  stat.removeChild(no);

  bet.value = "";
  main.value = "";
  betP.textContent = "";
  mainP.textContent = "";

  $$(".dice").classList.add("vanish");

  location.reload();
} // resetGame()

function playAgain() {
  localStorage.bank = user.bank;
  bankP.textContent = `Bank: $${localStorage.bank}`;

  $$("#rollDiv").removeChild(roll);
  if (localStorage.bank > 0) {
    stat.appendChild(againP);
    stat.appendChild(yes);
    stat.appendChild(no);
  } else finishGame();
} // playAgain()

function finishGame() {
  stat.removeChild(diceP);
  $$("#d1").classList.add("vanish");
  $$("#d2").classList.add("vanish");
  winP.classList.remove("blink");
  chanceP.classList.add("vanish");
  if (localStorage.bank > 0) {
    $$(".win").classList.add("vanish");
    $$(".fall").classList.add("vanish");
    stat.removeChild(againP);
    stat.removeChild(yes);
    stat.removeChild(no);
    winP.textContent = "Thanks for playing Hazard! See you next time :)";
    winP.style.color = "pink";
  } else {
    winP.textContent =
      "You lost every last penny. Come back soon with more money :)";
    winP.style.color = "pink";
    $$(".win").classList.add("vanish");
    $$(".fall").classList.add("vanish");
  } // lost

  bet.value = "";
  main.value = "";
  betP.textContent = "";
  mainP.textContent = "";
} // finishGame()

// rolling the dice //
function rollDice() {
  game.roll();
  rollAnimation();
  $$("button").disabled = true;
  setTimeout(setDice1, 550);
} // rollDice()

function rollAgain() {
  game.rollAgain();
  rollAnimation();
  $$("button").disabled = true;
  setTimeout(setDice2, 550);
} // rollAgain()

function setDice1() {
  for (i = 1; i < 7; ++i) {
    if (game.dice.die1 == i) $$("#d1").src = `./media/d${i}.jpg`;
  }

  for (i = 1; i < 7; ++i) {
    if (game.dice.die2 == i) $$("#d2").src = `./media/d${i}.jpg`;
  }
  $$("button").disabled = false;
  diceP.textContent = `You rolled ${game.dice.getOutcome()}.`;
  stat.appendChild(diceP);
  stat.classList.remove("vanish");

  stat.appendChild(winP);

  if (game.hasWon() == null) {
    winP.textContent = `Chance: ${game.chance}. Let's roll again!`;
    chanceP.appendChild(tn(`Chance: ${game.chance}`));
    userInfo.appendChild(chanceP);
    roll.removeEventListener("click", rollDice);
    roll.addEventListener("click", rollAgain);
  } // else if no win or loss
  else if (game.hasWon()) {
    win();
  } // won at first roll
  else {
    lose();
  } // lost at first roll
} // setDice1()

function setDice2() {
  for (i = 1; i < 7; ++i) {
    if (game.dice.die1 == i) $$("#d1").src = `./media/d${i}.jpg`;
  }

  for (i = 1; i < 7; ++i) {
    if (game.dice.die2 == i) $$("#d2").src = `./media/d${i}.jpg`;
  }
  $$("button").disabled = false;
  diceP.textContent = `You rolled ${game.dice.getOutcome()}.`;
  if (game.hasWon() == null) {
    winP.textContent = `Let's roll again!`;
  } // if no win or loss
  else if (game.hasWon()) {
    win();
  } // else if chance is rolled
  else {
    lose();
  } // else main is rolled
} // setDice2()

function style() {
  winP.classList.add("blink");
  winP.style.fontSize = "1.3em";
  winP.style.fontFamily = "Courier New";
  winP.style.backgroundColor = "black";
  winP.style.fontWeight = "bold";
  winP.style.padding = "2px";
} // style()

function win() {
  user.getResults();
  winP.textContent = `WIN! +$${user.bet}`;
  winP.style.color = "greenyellow";
  style();
  $$(".win").classList.remove("vanish");
  playAgain();
} // win()

function lose() {
  user.getResults();
  winP.textContent = `GAME OVER! -$${user.bet}`;
  winP.style.color = "rgb(255, 52, 52)";
  style();
  $$(".fall").classList.remove("vanish");
  playAgain();
} // lose()

function rollAnimation() {
  let d = 0;
  function changeImg() {
    if (d == 6) d = 0;
    else ++d;
    $$("#d1").src = `./media/d${d}.jpg`;
    $$("#d2").src = `./media/d${d == 6 ? d : d + 1}.jpg`;
  } // changeImg()
  var n = setInterval(changeImg, 70);
  setTimeout(function () {
    clearInterval(n);
  }, 480);
} // rollAnimation()

// event handlers //
bet.onblur = validBet;
main.onblur = validMain;

yes.onclick = resetGame;
no.onclick = finishGame;
$$("#exit").onclick = function () {
  location.href = "index.html";
};
btnBet.onclick = validBoth;
betClear.onclick = clearBet;

window.onload = () => {
  let myDate = new Date();
  localStorage.date = myDate.toUTCString();
};

$$("#change").onclick = () => {
  localStorage.removeItem("firstName");
  localStorage.removeItem("lastName");
  localStorage.removeItem("username");
  localStorage.removeItem("email");
  localStorage.removeItem("phoneNumber");
  localStorage.removeItem("city");
  localStorage.removeItem("bank");
  localStorage.removeItem("date");
  location.href = "intro.html";
};

// error handler
const modalWindow = $(".modal");
const closeModal = $("#close-btn");
const modalDetails = $(".modalDetails");

const toggleModal = () => modalWindow.classList.toggle("showModal");
const checkClose = (e) => (e.target === modalWindow ? toggleModal() : null);

const processErrors = function (errMessage) {
  modalDetails.innerHTML = `Error: ${errMessage} occurred`;
  toggleModal();
};

$(window).on("error", processErrors);
closeModal.addEventListener("click", toggleModal);
window.addEventListener("click", checkClose, true);
