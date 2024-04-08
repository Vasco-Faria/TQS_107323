
document.addEventListener('DOMContentLoaded', function() {

    getTicketInfo();
   
    const select = document.getElementById('currency-select');
    select.addEventListener('change', function() {
        const baseCurrency = select.value;
        localStorage.setItem('selectedCurrency', baseCurrency);
        fetch('http://localhost:8080/prices')
        .then(response => response.json())
        .then(data => {
            if (data.hasOwnProperty(baseCurrency)) {
                const selectedCurrencyRate = data[baseCurrency];
                localStorage.setItem('rate',selectedCurrencyRate);
                
                const valuesToMultiply = [10, 20, 30, 40, 50];
                
                const multipliedValues = valuesToMultiply.map(value => value * selectedCurrencyRate);

               
                const tableCells = document.querySelectorAll('tbody td:nth-child(6)');
                tableCells.forEach((cell, index) => {
                    cell.textContent = multipliedValues[index].toFixed(2) + ' ' + baseCurrency;
                });
            } else {
                console.error('Moeda selecionada não encontrada nas taxas de câmbio recebidas.');
            }
        })
        .catch(error => {
            console.error('Erro ao obter as taxas de câmbio:', error);
        });
    });

    select.dispatchEvent(new Event('change'));
});



function gotohome(event) {
    event.preventDefault(); 

    window.location.href = 'index.html';
}


function getTicketInfo() {
    const ticketId = localStorage.getItem('ticketId');
    if (!ticketId) {
        console.error('Ticket ID not found in localStorage');
        return;
    }


    fetch(`http://localhost:8080/ticketinfo/${ticketId}`)
    .then(response => response.json())
    .then(ticket => {
        const title = document.querySelector('.title-trip');
        title.textContent = `Trip from ${ticket.origin} to ${ticket.destiny}`;


        
        fetch(`http://localhost:8080/trips?origin=${ticket.origin}&destination=${ticket.destiny}&date=${ticket.date}`)
        .then(response => response.json())
        .then(trips => {
           
            const tbody = document.querySelector('tbody');
            tbody.innerHTML = '';

            
            trips.forEach((trip, index) => {
                let buttonOrText = trip.availableSeats > 0 ?
                    `<button class="button-select" onclick="selectTrip(${trip.id})">Select</button>` :
                    '<p>No seats available</p>';
                const newRow = `<tr>
                                    <td>${buttonOrText}</td>
                                    <td>${trip.id}</td>
                                    <td>${trip.company}</td>
                                    <td>${trip.departureTime}</td>
                                    <td>${trip.arrivalTime}</td>
                                    <td>${trip.price}.00 USD</td>
                                </tr>`;
                tbody.innerHTML += newRow;
            });
        })
        .catch(error => {
            console.error('Erro ao obter informações das viagens:', error);
        });
    })
    .catch(error => {
        console.error('Erro ao obter informações do ticket:', error);
    });
}


function selectTrip(tripId) {
    const ticketId = localStorage.getItem('ticketId');
    if (!ticketId) {
        console.error('Ticket ID not found in localStorage');
        return;
    }

    const data = {
        ticketId: ticketId.toString(),
        tripId: tripId.toString()
    };

    fetch('http://localhost:8080/ticket/addtrip', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            console.log('Trip added to ticket successfully.');
            window.location.href = 'PersonalInfo.html';
        } else {
            console.error('Failed to add trip to ticket.');
        }
    })
    .catch(error => {
        console.error('Error adding trip to ticket:', error);
    });
}
