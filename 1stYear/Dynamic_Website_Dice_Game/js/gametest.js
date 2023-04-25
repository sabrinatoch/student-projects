let user = new User(
  "Sabrina",
  "Tochkov",
  "stochkov",
  "613-266-0175",
  "Gatineau",
  "2056856@cegep-heritage.qc.ca"
); // user instantiation

let game = new Game(); // game (and dice) instantiation

console.log(`Instantiated User object:`);
console.log(`Full Name: ${user.getFullName()}`);
console.log(`Username: ${user.username}`);
console.log(`Phone number: ${user.phoneNumber}`);
console.log(`City: ${user.city}`);
console.log(`Email address: ${user.email}`);
console.log(`Bank Amount: ${user.bank}$`);
console.log(``); // test user

console.log(`Instantiated Dice object:`);
console.log(`Default dice 1: ${game.dice.die1}`);
console.log(`Default dice 2: ${game.dice.die2}`);
console.log(""); // test dice

console.log(`Instantiated Game object:`);
console.log(`Default main: ${game.main}`);
console.log(`Default chance: ${game.chance}`);
console.log(``); // test game

console.log(`Setting invalid data:`);
user.bet = -48;
console.log(`Setting a bet of -48$, the bet is set to ${user.bet}$`);
user.bet = "hello";
console.log(`Setting a bet of "hello", the bet is set to ${user.bet}$`);
game.main = 4;
console.log(`Setting a main of 4, the main is set to ${game.main}`);
game.main = "hello";
console.log(`Setting a main of "hello", the main is set to ${game.main}`);
game.main = 8.8;
console.log(`Setting a main of 8.8, the main is set to ${game.main}`);
console.log(""); // invalid data

// gameplay

console.log(`Gameplay:`);
user.bet = 20;
game.main = 7;
console.log(`1. Setting the bet to ${user.bet}$.`);
console.log(`2. Setting the main to ${game.main}.`);
game.roll();
console.log(`3. Random dice roll...`);
console.log(`   Dice 1: ${game.dice.die1}`);
console.log(`   Dice 2: ${game.dice.die2}`);

if (game.hasWon()) {
  user.getResults();
  console.log(
    `Congratulations! You won and your bank account now has ${user.bank}$.`
  );
} // won at first roll
else if (game.hasWon() == null) {
  while (game.hasWon() == null) {
    console.log(
      `You neither won, nor lost. You rolled a chance of ${game.chance}.`
    );
    console.log(`Let's roll again...`);
    game.rollAgain();
    console.log(`   Dice 1: ${game.dice.die1}`);
    console.log(`   Dice 2: ${game.dice.die2}`);
  } // while no win or loss
  if (game.hasWon()) {
    user.getResults();
    console.log(
      `Congratulations! You rolled the chance and won! Your bank account now has ${user.bank}$.`
    );
  } // if chance is rolled
  else {
    user.getResults();
    console.log(
      `Oh no! You rolled the main and lost. Your bank account now has ${user.bank}$.`
    );
  } // else main is rolled
} else {
  user.getResults();
  console.log(`Oh no! You lost, and your bank account now has ${user.bank}$.`);
} // lost at first roll
