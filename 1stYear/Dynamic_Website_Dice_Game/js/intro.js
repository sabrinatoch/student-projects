const fName = $("#firstName");
const lName = $("#lastName");
const user = $("#user");
const email = $("#email");
const tel = $("#phoneNumber");
const city = $("#city");
const amt = $("#amt");

function validFirst() {
  let valid = true;
  let regex = /(^[A-Z][A-Z\'\`\-\s]*?(?<!\`)$){1,20}/i;
  if (fName.val() === "") {
    fName.addClass("errRed");
    $("#firstErr").addClass("errDisplay");
    $("#firstErr").text("⚠ You must enter a first name");
    valid = false;
  } else if (!regex.test(fName.val())) {
    fName.addClass("errRed");
    $("#firstErr").addClass("errDisplay");
    $("#firstErr").text("⚠ Invalid first name");
    valid = false;
  } else {
    fName.removeClass("errRed");
    $("#firstErr").removeClass("errDisplay");
    $("#firstErr").text("");
  }
  return valid;
} // validFirst()

function validLast() {
  let valid = true;
  let regex = /(^[A-Z][A-Z\'\`\-\s]*?(?<!\`)$){1,30}/i;
  if (lName.val() === "") {
    lName.addClass("errRed");
    $("#lastErr").addClass("errDisplay");
    $("#lastErr").text("⚠ You must enter a last name");
    valid = false;
  } else if (!regex.test(lName.val())) {
    lName.addClass("errRed");
    $("#lastErr").addClass("errDisplay");
    $("#lastErr").text("⚠ Invalid last name");
    valid = false;
  } else {
    lName.removeClass("errRed");
    $("#lastErr").removeClass("errDisplay");
    $("#lastErr").text("");
  }
  return valid;
} // validLast()

function validUser() {
  let valid = true;
  let regex = /^[A-Z][a-z]{3}[0-5]$/;
  if (user.val() === "") {
    user.addClass("errRed");
    $("#userErr").addClass("errDisplay");
    $("#userErr").text("⚠ You must enter a username");
    valid = false;
  } else if (!regex.test(user.val())) {
    user.addClass("errRed");
    $("#userErr").addClass("errDisplay");
    $("#userErr").text("⚠ Invalid username");
    valid = false;
  } else {
    user.removeClass("errRed");
    $("#userErr").removeClass("errDisplay");
    $("#userErr").text("");
  }
  return valid;
} // validUser()

function validEmail() {
  let valid = true;
  let regex = /^[A-Za-z0-9_\-.]*@[A-Za-z0-9_]*.(ca|org)$/;
  if (email.val() === "") {
    email.addClass("errRed");
    $("#emailErr").addClass("errDisplay");
    $("#emailErr").text("⚠ You must enter an email");
    valid = false;
  } else if (!regex.test(email.val())) {
    email.addClass("errRed");
    $("#emailErr").addClass("errDisplay");
    $("#emailErr").text("⚠ Invalid email");
    valid = false;
  } else {
    email.removeClass("errRed");
    $("#emailErr").removeClass("errDisplay");
    $("#emailErr").text("");
  }
  return valid;
} // validEmail()

function validTel() {
  let valid = true;
  let regex = /^\([0-9]{3}\)\s[0-9]{3}\-[0-9]{4}$/;
  if (tel.val() === "") {
    tel.addClass("errRed");
    $("#phoneErr").addClass("errDisplay");
    $("#phoneErr").text("⚠ You must enter a phone #");
    valid = false;
  } else if (!regex.test(tel.val())) {
    tel.addClass("errRed");
    $("#phoneErr").addClass("errDisplay");
    $("#phoneErr").text("⚠ Invalid phone #");
    valid = false;
  } else {
    tel.removeClass("errRed");
    $("#phoneErr").removeClass("errDisplay");
    $("#phoneErr").text("");
  }
  return valid;
} // validTel()

function validCity() {
  let valid = true;
  let regex = /^[A-Za-z\s]{1,42}$/;
  if (city.val() === "") {
    city.addClass("errRed");
    $("#cityErr").addClass("errDisplay");
    $("#cityErr").text("⚠ You must enter a city");
    valid = false;
  } else if (!regex.test(city.val())) {
    city.addClass("errRed");
    $("#cityErr").addClass("errDisplay");
    $("#cityErr").text("⚠ Invalid city");
    valid = false;
  } else {
    city.removeClass("errRed");
    $("#cityErr").removeClass("errDisplay");
    $("#cityErr").text("");
  }
  return valid;
} // validCity()

function validAmt() {
  let valid = true;
  let regex = /^[0-9]*$/;
  if (amt.val() === "") {
    amt.addClass("errRed");
    $("#amtErr").text("⚠ You must enter a starting amount");
    valid = false;
  } else if (!regex.test(amt.val())) {
    amt.addClass("errRed");
    $("#amtErr").text("⚠ Invalid amount");
    valid = false;
  } else if (regex.test(amt.val())) {
    let num = parseInt(amt.val());
    if (num < 5 || num > 5000 || num % 3 != 0) {
      amt.addClass("errRed");
      $("#amtErr").text(
        "⚠ Invalid amount (must be between $5 & $5000 and divisble by 3)"
      );
      valid = false;
    } // if improper range
    else {
      amt.removeClass("errRed");
      $("#amtErr").text("");
    } // valid
  }
  return valid;
} // validAmt()

function validForm() {
  let valid = true;
  if (!validFirst()) valid = false;
  if (!validLast()) valid = false;
  if (!validUser()) valid = false;
  if (!validEmail()) valid = false;
  if (!validTel()) valid = false;
  if (!validCity()) valid = false;
  if (!validAmt()) valid = false;

  if (valid) {
    let myDate = new Date();
    localStorage.setItem("firstName", fName.val());
    localStorage.setItem("lastName", lName.val());
    localStorage.setItem("username", user.val());
    localStorage.setItem("email", email.val());
    localStorage.setItem("phoneNumber", tel.val());
    localStorage.setItem("city", city.val());
    localStorage.setItem("bank", amt.val());
    localStorage.date = myDate.toUTCString();
  }
  return valid;
} // validForm()

function checkStorage() {
  if (localStorage.length != 0) {
    location.href = "game.html";
  }
} // checkStorage

fName.on("blur", validFirst);
lName.on("blur", validLast);
user.on("blur", validUser);
email.on("blur", validEmail);
tel.on("blur", validTel);
city.on("blur", validCity);
amt.on("blur", validAmt);

const form = $("#form");
form.on("submit", validForm);

$("#clear").on("click", () => {
  location.reload();
});

$(window).on("load", checkStorage);
