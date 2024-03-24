document.addEventListener("DOMContentLoaded", function() {
    setTimeout(function() {
        var loader = document.querySelector('.Loader');
        var container = document.querySelector('.homepage-container');

        loader.style.transition = "opacity 1s";
        loader.style.opacity = 0;

        setTimeout(function() {
            loader.style.display = "none";
            container.style.display = "block";
            fadeIn(container);
        }, 1000);
    }, 2000); 

    function fadeIn(element) {
        var opacity = 0;
        var timer = setInterval(function() {
            if (opacity >= 1) {
                clearInterval(timer); 
            }
            element.style.opacity = opacity;
            opacity += 0.1; 
        }, 100); 
    }
});

function toggleCalendar() {
    var idaRadio = document.getElementById("ida");
    var endDateInput = document.getElementById("end_date");
    
    if (idaRadio.checked) {
        
        endDateInput.disabled = true;
    } else {
        
        endDateInput.disabled = false;
    }
}
