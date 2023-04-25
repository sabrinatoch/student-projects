let hasWon;

class User {
  #firstName;
  #lastName;
  #username;
  #phoneNumber;
  #city;
  #email;
  #bank;
  #bet;

  constructor(first, last, user, phone, city, email, bank) {
    this.#firstName = first;
    this.#lastName = last;
    this.#username = user;
    this.#phoneNumber = phone;
    this.#city = city;
    this.#email = email;
    this.#bank = bank;
    this.#bet = 0;
  } // User constructor

  // accessors and mutators

  set firstName(first) {
    this.#firstName = first;
  }
  get firstName() {
    return this.#firstName;
  }
  set lastName(last) {
    this.#lastName = last;
  }
  get lastName() {
    return this.#lastName;
  }
  set username(user) {
    this.#username = user;
  }
  get username() {
    return this.#username;
  }
  set phoneNumber(phone) {
    this.#phoneNumber = phone;
  }
  get phoneNumber() {
    return this.#phoneNumber;
  }
  set city(city) {
    this.#city = city;
  }
  get city() {
    return this.#city;
  }
  set email(email) {
    this.#email = email;
  }
  get email() {
    return this.#email;
  }
  set bank(bank) {
    this.#bank = bank;
  }
  get bank() {
    return this.#bank;
  }
  set bet(bet) {
    bet > 0 && !isNaN(bet) ? (this.#bet = bet) : (this.#bet = 0);
  }
  get bet() {
    return this.#bet;
  }

  // other methods

  getFullName() {
    return `${this.#firstName} ${this.#lastName}`;
  } // getFullName()

  addMoney() {
    this.#bank += this.#bet;
  } // addMoney()

  deductMoney() {
    this.#bank -= this.#bet;
  } // deductMoney()

  getResults() {
    if (hasWon) this.addMoney(this.#bet);
    else this.deductMoney(this.#bet);

    hasWon = null;
  } // getResults()
} // User class

class Dice {
  #die1;
  #die2;

  constructor() {
    this.#die1 = 1;
    this.#die2 = 1;
  } // Dice constructor

  // accessors and mutators

  set die1(die1) {
    this.#die1 = die1;
  }
  set die2(die2) {
    this.#die2 = die2;
  }
  get die1() {
    return this.#die1;
  }
  get die2() {
    return this.#die2;
  }

  // other methods

  randomRoll() {
    this.#die1 = Math.floor(Math.random() * 6) + 1;
    this.#die2 = Math.floor(Math.random() * 6) + 1;
  } // randomRoll()

  getOutcome() {
    return this.#die1 + this.#die2;
  } // getOutcome()
} // Dice class

class Game {
  #main;
  #chance;

  constructor() {
    this.#main = 0;
    this.#chance = 0;
    this.dice = new Dice();
  } // Game constructor

  // accessors and mutators

  set main(main) {
    main >= 5 && main <= 9 && !isNaN(main)
      ? (this.#main = parseInt(main))
      : (this.#main = 5);
  }
  set chance(chance) {
    chance == 4 || chance == 10 ? (this.#chance = chance) : (this.#chance = -1);
  }
  get main() {
    return this.#main;
  }
  get chance() {
    return this.#chance;
  }

  // other methods

  hasWon() {
    return hasWon;
  } // hasWon()

  roll() {
    this.dice.randomRoll();
    if (this.dice.getOutcome() == this.#main) hasWon = true;
    else if (this.dice.getOutcome() == 2 || this.dice.getOutcome() == 3)
      hasWon = false;
    else if (this.dice.getOutcome() == 11) {
      if (this.#main == 5 || this.#main == 9) hasWon = false;
      else if (this.#main == 6 || this.#main == 8) hasWon = false;
      else if (this.#main == 7) hasWon = true;
    } else if (this.dice.getOutcome() == 12) {
      if (this.#main == 5 || this.#main == 9) hasWon = true;
      else if (this.#main == 6 || this.#main == 8) hasWon = true;
      else if (this.#main == 7) hasWon = false;
    } else {
      this.#chance = this.dice.getOutcome();
    }
  } // roll()

  rollAgain() {
    this.dice.randomRoll();
    if (this.dice.getOutcome() == this.#chance) hasWon = true;
    else if (this.dice.getOutcome() == this.#main) hasWon = false;
  } // rollAgain();
} // Game class
