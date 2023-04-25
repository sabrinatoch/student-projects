let imageArray = new Array(31);
let currentImage = 0;

function startSkipping() {
    if (currentImage == 30)
        currentImage = 0;
    else
        ++currentImage;

    document.querySelector("img").src = `./images/jumprope/jumprope${currentImage}.gif`
} // startSkipping()

function setUp() {
    for (let img = 0; img < imageArray.length; ++img) {
        imageArray[img] = new Image();
        imageArray[img].src=`./images/jumprope/jumprope${img}.gif`;
    }

    setInterval(startSkipping, 50);
} // setUp()

window.onload = setUp;