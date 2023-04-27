function draw() {
    let canvas = document.querySelector("#myCanvas");
    let ctx = canvas.getContext("2d");
    ctx.fillStyle = "#ff9";
    ctx.strokeStyle = "#999";
    ctx.lineWidth = 4;

    ctx.beginPath();
    ctx.arc(120, 40, 25, 0, 2 * Math.PI, false);
    ctx.stroke();
    ctx.fill();

    ctx.beginPath();
    ctx.moveTo(120, 65);
    ctx.lineTo(120, 165); // body
    ctx.stroke();
    ctx.lineTo(160, 205); // right leg

    ctx.moveTo(120, 165);
    ctx.lineTo(80, 205); // left leg

    ctx.moveTo(120, 105);
    ctx.lineTo(80, 65); // left arm

    ctx.moveTo(120, 105);
    ctx.lineTo(160, 65) // right arm

    ctx.stroke();
    ctx.closePath();
} //draw()

window.onload = draw;