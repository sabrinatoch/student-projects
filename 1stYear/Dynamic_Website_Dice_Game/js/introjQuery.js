$(() => {
  jQuery.validator.addMethod(
    "divisble",
    function (value, element) {
      return this.optional(element) || parseFloat(value) % 3 == 0;
    },
    "⚠ Amount must be divisible by 3"
  );

  $("#form").validate({
    rules: {
      firstName: {
        required: true,
        pattern: /(^[A-Z][A-Z\'\`\-\s]*?(?<!\`)$){1,20}/i,
        maxlength: 20,
      },
      lastName: {
        required: true,
        pattern: /(^[A-Z][A-Z\'\`\-\s]*?(?<!\`)$){1,30}/i,
        maxlength: 30,
      },
      user: {
        required: true,
        pattern: /^[A-Z][a-z]{3}[0-5]$/,
      },
      email: {
        required: true,
        pattern: /^[A-Za-z0-9_\-.]*@[A-Za-z0-9_]*.(ca|org)$/,
      },
      phoneNumber: {
        required: true,
        pattern: /^\([0-9]{3}\)\s[0-9]{3}\-[0-9]{4}$/,
      },
      city: {
        required: true,
        pattern: /^[A-Za-z\s]{1,42}$/,
      },
      amt: {
        required: true,
        digits: true,
        range: [5, 5000],
        divisble: true,
      },
    }, // rules
    messages: {
      firstName: {
        required: "⚠ Please enter your first name",
        pattern: "⚠ Invalid first name",
      },
      lastName: {
        required: "⚠ Please enter your last name",
        pattern: "⚠ Invalid last name",
      },
      user: {
        required: "⚠ Please enter your username",
        pattern: "⚠ Invalid username",
      },
      email: {
        required: "⚠ Please enter your email",
        pattern: "⚠ Invalid email",
      },
      phoneNumber: {
        required: "⚠ Please enter your phone #",
        pattern: "⚠ Invalid phone #",
      },
      city: {
        required: "⚠ Please enter your city",
        pattern: "⚠ Invalid city",
      },
      amt: {
        required: "⚠ Please enter your starting amount",
        pattern: "⚠ Invalid starting amount",
      },
    }, // messages
  }); // validate
}); // fct

function store() {
  if ($("#form").valid()) {
    let myDate = new Date();
    localStorage.firstName = $("#firstName").val();
    localStorage.lastName = $("#lastName").val();
    localStorage.username = $("#user").val();
    localStorage.email = $("#email").val();
    localStorage.phoneNumber = $("#phoneNumber").val();
    localStorage.city = $("#city").val();
    localStorage.bank = $("#amt").val();
    localStorage.date = myDate.toUTCString();
    return true;
  } else return false;
} // validForm()

$("#form").on("submit", store);

$(window).on("load", () => {
  if (localStorage.length != 0) location.href = "game.html";
}); // check localStorage

$("#clear").on("click", () => {
  location.reload();
});
