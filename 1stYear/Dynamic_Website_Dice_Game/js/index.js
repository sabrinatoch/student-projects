function draw() {
  let canvas = document.querySelector("#myCanvas");
  let ctx = canvas.getContext("2d");
  ctx.fillStyle = "black";
  ctx.strokeStyle = "white";
  ctx.lineWidth = 4;

  ctx.beginPath();
  ctx.arc(120, 40, 25, 0, 2 * Math.PI, false);
  ctx.stroke();
  ctx.fill();
}

window.onload = draw;
