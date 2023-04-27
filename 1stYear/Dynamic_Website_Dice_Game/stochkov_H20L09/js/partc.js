let randomColor = () => Math.floor(Math.random() * 16777215).toString(16);
let canvas = document.querySelector("#canv");
let ctx = canvas.getContext("2d");

function setup() {
    ctx.translate(100, 100);
    setInterval(drawFlower, 750);
} // setup()

function drawFlower() {
    ctx.moveTo(0, 0);
    ctx.bezierCurveTo(0, 30, 10, 90, 0, 150);
    ctx.fillStyle = "DarkSeaGreen";
    ctx.strokeStyle = "DarkOliveGreen";
    ctx.lineWidth = 2;
    ctx.fill();
    ctx.stroke();
    for (let i = 0; i < 24; ++i) {
        ctx.beginPath();
        ctx.moveTo(0, 0);
        ctx.bezierCurveTo(75, -15, 75, 15, 0, 0);
        ctx.fillStyle = "#" + randomColor();
        ctx.strokeStyle = "#" + randomColor();
        ctx.lineWidth = 1;
        ctx.fill();
        ctx.stroke();
        ctx.rotate(Math.PI / 12);
    }
} // drawFlower()

window.onload = setup;