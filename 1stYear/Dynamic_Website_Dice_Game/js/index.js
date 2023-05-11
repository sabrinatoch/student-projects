const section = document.querySelector("section");
const logo = document.querySelector("svg");
const fps = 60;
let canvas = document.querySelector("#blob");
let randomColor = () => Math.floor(Math.random() * 16777215).toString(16);

section.style.height = `${window.innerHeight}px`;
section.style.width = `${window.innerWidth}px`;

let xPosition = 10;
let yPosition = 10;
let xSpeed = 3;
let ySpeed = 3;

function move() {
  logo.style.left = `${xPosition}px`;
  logo.style.top = `${yPosition}px`;
}

setInterval(() => {
  if (xPosition + logo.clientWidth >= window.innerWidth || xPosition <= 0) {
    xSpeed = -xSpeed;
    logo.style.fill = `#${randomColor()}`;
  }
  if (yPosition + logo.clientHeight >= window.innerHeight || yPosition <= 0) {
    ySpeed = -ySpeed;
    logo.style.fill = `#${randomColor()}`;
  }
  xPosition += xSpeed;
  yPosition += ySpeed;
  move();
}, 1000 / fps);

function draw() {
  canvas.width = 400;
  canvas.height = 400;
  let ctx = canvas.getContext("2d");

  ctx.fillStyle = "black";
  ctx.strokeStyle = "white";
  ctx.lineWidth = 4;

  ctx.rect(105, 105, 180, 180);
  ctx.clip();
  ctx.fillRect(0, 0, 150, 100);
  ctx.fill();
  ctx.stroke();

  ctx.fillStyle = "white";
  ctx.beginPath();
  ctx.arc(140, 140, 10, 0, Math.PI * 2, true);
  ctx.fill();
  ctx.arc(250, 140, 10, 0, Math.PI * 2, true);
  ctx.fill();
  ctx.moveTo(250, 140);
  ctx.arc(250, 250, 10, 0, Math.PI * 2, true);
  ctx.fill();
  ctx.moveTo(250, 250);
  ctx.arc(140, 250, 10, 0, Math.PI * 2, true);
  ctx.fill();
  ctx.moveTo(140, 250);
  ctx.arc(195, 195, 10, 0, Math.PI * 2, true);
  ctx.fill();
  ctx.closePath();

  setTimeout(function() {
    document.querySelector("canvas").classList.add("roller");
  }, 2000);
  setTimeout(function () {
    document.querySelector("p").classList.remove("hidden");
    setTimeout(() => {location.href = "intro.html";}, 4000)
  }, 8000)
} // draw

window.onresize = () => {
  xPosition = 10;
  yPosition = 10;
  section.style.height = `${window.innerHeight}px`;
  section.style.width = `${window.innerWidth}px`;
};

window.onload = draw;

document.querySelector("input[type='button']").onclick = function () {
  location.href = "intro.html";
};