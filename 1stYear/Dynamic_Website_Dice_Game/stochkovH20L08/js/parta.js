let rows = document.querySelectorAll('tr');

function mouseOverTr(e) {
    this.style.fontSize = "1.2em";
    this.style.backgroundColor = "navy";
    this.style.color = "white";

    e.target.style.backgroundColor = "yellow";
    e.target.style.fontStyle = "italic";
    e.target.style.color = "black";
} // mouseOverTr(e)

function mouseOutTr(e) {
    this.style.removeProperty('font-size');
    this.style.removeProperty('background-color');
    this.style.removeProperty('color');

    e.target.style.removeProperty('background-color');
    e.target.style.removeProperty('font-style');
    e.target.style.removeProperty('color');
} // mouseOutTr(e)

for (row of rows) {
    row.addEventListener('mouseover', mouseOverTr)
    row.addEventListener('mouseout', mouseOutTr)
}