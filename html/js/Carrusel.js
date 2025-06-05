var carruselScroll = document.querySelector('.carrusel-scroll');


if (carruselScroll == null) {
    console.warn("Error: No se encontró el elemento con la clase .carrusel-scroll. El carrusel no funcionará.");
} else {
    var velocidadScroll = 3000;
    var posicionActualScroll = 0;

    setInterval(function() {
   
        var primerItem = carruselScroll.querySelector('.item');
        if (primerItem == null) {
            console.warn("No se encontraron items dentro del carrusel. El desplazamiento se detendrá.");
            return; 
        }

        var anchoItem = primerItem.offsetWidth;
        var espacioEntreItems = 15;


        posicionActualScroll += (anchoItem + espacioEntreItems);

        if (posicionActualScroll >= (carruselScroll.scrollWidth - carruselScroll.clientWidth)) {
            posicionActualScroll = 0;
        }

        carruselScroll.scrollLeft = posicionActualScroll;

    }, velocidadScroll);
}