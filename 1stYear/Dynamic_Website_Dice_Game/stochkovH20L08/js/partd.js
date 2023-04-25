let currentHero = 0;

function changeImage() {
    if (currentHero == 4)
        currentHero = 0;
    else
        ++currentHero;

    document.querySelector("img").src = `./images/xmen/hero${currentHero}.png`;
} // changeImage()

setInterval(changeImage, 4000);