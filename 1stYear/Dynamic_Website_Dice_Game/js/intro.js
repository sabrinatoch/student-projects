let $$ = (sel) => document.querySelector(sel);

const fName = $$("#firstName");
const lName = $$("#lastName");
const user = $$("#user");
const email = $$("#email");
const tel = $$("#phoneNumber");
const city = $$("#city");
const amt = $$("#amt");

function validFirst() {
  let valid = true;
  let regex = /(^[A-Z][A-Z\'\`\-\s]*?(?<!\`)$){1,20}/i;
  if (fName.value === "") {
    fName.classList.add("errRed");
    $$("#firstErr").classList.add("errDisplay");
    $$("#firstErr").textContent = "⚠ You must enter a first name";
    valid = false;
  } else if (!regex.test(fName.value)) {
    fName.classList.add("errRed");
    $$("#firstErr").classList.add("errDisplay");
    $$("#firstErr").textContent = "⚠ Invalid first name";
    valid = false;
  } else {
    fName.classList.remove("errRed");
    $$("#firstErr").classList.remove("errDisplay");
    $$("#firstErr").textContent = "";
  }
  return valid;
} // validFirst()

function validLast() {
  let valid = true;
  let regex = /(^[A-Z][A-Z\'\`\-\s]*?(?<!\`)$){1,30}/i;
  if (lName.value === "") {
    lName.classList.add("errRed");
    $$("#lastErr").classList.add("errDisplay");
    $$("#lastErr").textContent = "⚠ You must enter a last name";
    valid = false;
  } else if (!regex.test(lName.value)) {
    lName.classList.add("errRed");
    $$("#lastErr").classList.add("errDisplay");
    $$("#lastErr").textContent = "⚠ Invalid last name";
    valid = false;
  } else {
    lName.classList.remove("errRed");
    $$("#lastErr").classList.remove("errDisplay");
    $$("#lastErr").textContent = "";
  }
  return valid;
} // validLast()

function validUser() {
  let valid = true;
  let regex = /^[A-Z][a-z]{3}[0-5]$/;
  if (user.value === "") {
    user.classList.add("errRed");
    $$("#userErr").classList.add("errDisplay");
    $$("#userErr").textContent = "⚠ You must enter a username";
    valid = false;
  } else if (!regex.test(user.value)) {
    user.classList.add("errRed");
    $$("#userErr").classList.add("errDisplay");
    $$("#userErr").textContent = "⚠ Invalid username";
    valid = false;
  } else {
    user.classList.remove("errRed");
    $$("#userErr").classList.remove("errDisplay");
    $$("#userErr").textContent = "";
  }
  return valid;
} // validUser()

function validEmail() {
  let valid = true;
  let regex = /^[A-Za-z0-9_\-.]*@[A-Za-z0-9_]*.(ca|org)$/;
  if (email.value === "") {
    email.classList.add("errRed");
    $$("#emailErr").classList.add("errDisplay");
    $$("#emailErr").textContent = "⚠ You must enter an email";
    valid = false;
  } else if (!regex.test(email.value)) {
    email.classList.add("errRed");
    $$("#emailErr").classList.add("errDisplay");
    $$("#emailErr").textContent = "⚠ Invalid email";
    valid = false;
  } else {
    email.classList.remove("errRed");
    $$("#emailErr").classList.remove("errDisplay");
    $$("#emailErr").textContent = "";
  }
  return valid;
} // validEmail()

function validTel() {
  let valid = true;
  let regex = /^\([0-9]{3}\)\s[0-9]{3}\-[0-9]{4}$/;
  if (tel.value === "") {
    tel.classList.add("errRed");
    $$("#phoneErr").classList.add("errDisplay");
    $$("#phoneErr").textContent = "⚠ You must enter a phone #";
    valid = false;
  } else if (!regex.test(tel.value)) {
    tel.classList.add("errRed");
    $$("#phoneErr").classList.add("errDisplay");
    $$("#phoneErr").textContent = "⚠ Invalid phone #";
    valid = false;
  } else {
    tel.classList.remove("errRed");
    $$("#phoneErr").classList.remove("errDisplay");
    $$("#phoneErr").textContent = "";
  }
  return valid;
} // validTel()

function validCity() {
  let valid = true;
  let regex = /^[A-Za-z\s]{1,42}$/;
  if (city.value === "") {
    city.classList.add("errRed");
    $$("#cityErr").classList.add("errDisplay");
    $$("#cityErr").textContent = "⚠ You must enter a city";
    valid = false;
  } else if (!regex.test(city.value)) {
    city.classList.add("errRed");
    $$("#cityErr").classList.add("errDisplay");
    $$("#cityErr").textContent = "⚠ Invalid city";
    valid = false;
  } else {
    city.classList.remove("errRed");
    $$("#cityErr").classList.remove("errDisplay");
    $$("#cityErr").textContent = "";
  }
  return valid;
} // validCity()

function validAmt() {
  let valid = true;
  let regex = /^[0-9]*$/;
  if (amt.value === "") {
    amt.classList.add("errRed");
    $$("#amtErr").textContent = "⚠ You must enter a starting amount";
    valid = false;
  } else if (!regex.test(amt.value)) {
    amt.classList.add("errRed");
    $$("#amtErr").textContent = "⚠ Invalid amount";
    valid = false;
  } else if (regex.test(amt.value)) {
    let num = parseInt(amt.value);
    if (num < 5 || num > 5000 || num % 3 != 0) {
      amt.classList.add("errRed");
      $$("#amtErr").textContent =
        "⚠ Invalid amount (must be between $5 & $5000 and divisble by 3)";
      valid = false;
    } // if improper range
    else {
      amt.classList.remove("errRed");
      $$("#amtErr").textContent = "";
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
    localStorage.setItem("firstName", fName.value);
    localStorage.setItem("lastName", lName.value);
    localStorage.setItem("username", user.value);
    localStorage.setItem("email", email.value);
    localStorage.setItem("phoneNumber", tel.value);
    localStorage.setItem("city", city.value);
    localStorage.setItem("bank", amt.value);
  }
  return valid;
} // validForm()

fName.onblur = validFirst;
lName.onblur = validLast;
user.onblur = validUser;
email.onblur = validEmail;
tel.onblur = validTel;
city.onblur = validCity;
amt.onblur = validAmt;

const form = $$("#form");
form.onsubmit = validForm;
form.onreset = clearForm;

function clearForm() {
  $$("#firstErr").textContent = "";
  $$("#firstErr").classList.remove("errDisplay");
  fName.classList.remove("errRed");
  $$("#lastErr").textContent = "";
  $$("#lastErr").classList.remove("errDisplay");
  lName.classList.remove("errRed");
  $$("#userErr").textContent = "";
  $$("#userErr").classList.remove("errDisplay");
  user.classList.remove("errRed");
  $$("#emailErr").textContent = "";
  $$("#emailErr").classList.remove("errDisplay");
  email.classList.remove("errRed");
  $$("#phoneErr").textContent = "";
  $$("#phoneErr").classList.remove("errDisplay");
  tel.classList.remove("errRed");
  $$("#cityErr").textContent = "";
  $$("#cityErr").classList.remove("errDisplay");
  city.classList.remove("errRed");
  $$("#amtErr").textContent = "";
  $$("#amtErr").classList.remove("errDisplay");
  amt.classList.remove("errRed");
} // clearForm()
