let imgArray = new Array(30);
let current = 0;
let currentPos = -125;
let width = window.innerWidth;
let height = window.innerHeight;

function walk() {
    if (current == 29)
        current = 0;
    else
        ++current;

    document.querySelector("img").src = imgArray[current].src;

    if (currentPos > width)
        currentPos = -125;
    else
        currentPos += 4;

    document.querySelector("img").style.left = `${currentPos}px`;
} // walk()

function setup() {
    for (let img = 0; img < imgArray.length; ++img) {
        imgArray[img] = new Image();
        imgArray[img].src = `../images/skeletonwalkacross/skeletonAcross${img}.png`;
    }
    setInterval(walk, 40);
} // setup()

window.onload = setup;

document.documentElement.style.overflow = 'hidden';
document.querySelector("img").style.left = `${currentPos}px`;
document.querySelector("img").style.top = `${height - 185}px`;