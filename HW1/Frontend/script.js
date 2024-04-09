var ticketId;


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

window.onload = function() {
    updateOriginOptions();
    updateDestinationOptions();
}

function updateDestinationOptions() {
    var originSelect = document.getElementById("origin");
    var destinationSelect = document.getElementById("destination");

    for (var i = 0; i < destinationSelect.options.length; i++) {
        destinationSelect.options[i].disabled = false;
    }

  
    var selectedIndex = originSelect.selectedIndex;
    destinationSelect.options[selectedIndex].disabled = true;
}

function updateOriginOptions() {
    var originSelect = document.getElementById("origin");
    var destinationSelect = document.getElementById("destination");

    for (var i = 0; i < originSelect.options.length; i++) {
        originSelect.options[i].disabled = false;
    }

    
    var selectedIndex = destinationSelect.selectedIndex;
    originSelect.options[selectedIndex].disabled = true;
}



document.getElementById('searchForm').addEventListener('submit', function(event) {
    event.preventDefault();
});


function createTicket() {
    
    var origin = document.querySelector('#origin').value;
    var destination = document.querySelector('#destination').value;
    var startDate = document.querySelector('#start_date').value;

    if (!origin || !destination || !startDate) {
        
        return; 
    }

    var ticketData = {
        origin: origin,
        destiny: destination,
        date: startDate,
    };


    fetch('http://localhost:8080/createtickets', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(ticketData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to create ticket');
        }
        
        return response.text();
    })
    .then(id => {
        var ticketId = parseInt(id, 10);
        localStorage.setItem('ticketId', ticketId); 
        window.location.href = 'SelectTrip.html';
    })
    .catch(error => {
        console.error('Error:', error);
        
    });
}


function gotohome(event) {
    event.preventDefault(); 

    window.location.href = 'index.html';
}


var today = new Date();
var endDateLimit = new Date(today);
endDateLimit.setDate(endDateLimit.getDate() + 5); 

var startDateInput = document.getElementById("start_date");
var endDateInput = document.getElementById("end_date");

startDateInput.setAttribute("min", today.toISOString().split('T')[0]); 
startDateInput.setAttribute("max", endDateLimit.toISOString().split('T')[0]); 

startDateInput.addEventListener("change", function() {
    if (startDateInput.value) {
        endDateInput.setAttribute("min", startDateInput.value);
    } else {
        endDateInput.removeAttribute("min");
    }
    endDateInput.value = ""; 
});

endDateInput.addEventListener("change", function() {
    if (endDateInput.value && startDateInput.value) {
        if (endDateInput.value < startDateInput.value) {
            endDateInput.value = "";
        }
    }
});