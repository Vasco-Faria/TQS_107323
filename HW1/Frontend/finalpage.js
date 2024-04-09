function gotohome(event) {
    event.preventDefault(); 

    window.location.href = 'index.html';
}


function fetchTicketInfo(ticketId) {
    return fetch(`http://localhost:8080/ticketinfo/${ticketId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch ticket information');
            }
            return response.json();
        });
}


const ticketId = localStorage.getItem('ticketId');


if (ticketId) {
    fetchTicketInfo(ticketId)
        .then(ticketInfo => {
            console.log(ticketInfo);
            const ticketNameElement = document.getElementById('ticketName');
            ticketNameElement.textContent = `${ticketInfo.name} thanks for trusting us. Here is your ticket info`;
        document.getElementById('ticketOrigin').textContent = ticketInfo.origin;
        document.getElementById('ticketDestination').textContent = ticketInfo.destiny;
        document.getElementById('ticketDate').textContent = ticketInfo.date;
        document.getElementById('ticketSeat').textContent = ticketInfo.seat;
        document.getElementById('ticketTripId').textContent = ticketInfo.trip.id || "Not provided";
        document.getElementById('ticketDtime').textContent = ticketInfo.trip.departureTime || "Not provided";
        document.getElementById('ticketAtime').textContent = ticketInfo.trip.arrivalTime || "Not provided";
        document.getElementById('ticketCompany').textContent = ticketInfo.trip.company || "Not provided";
        document.getElementById('ticketJson').textContent = JSON.stringify(ticketInfo, null, 2);
    })
        .catch(error => {
            console.error('Error fetching ticket information:', error);
        });
} else {
    console.error('Ticket ID not found in localStorage');
}

function gotos(event){
    event.preventDefault(); 

    window.location.href = 'ticketschedule.html';
}