document.addEventListener('DOMContentLoaded', function() {
    const ticketId = localStorage.getItem('ticketId');
    if (!ticketId) {
        console.error('Ticket ID not found in localStorage');
    } else {
        fetchTicketInfo(ticketId)
            .then(ticketInfo => {
                updateBusInfo(ticketInfo);
            })
            .catch(error => {
                console.error('Error fetching ticket information:', error);
            });
    }
});

function fetchTicketInfo(ticketId) {
    return fetch(`http://localhost:8080/ticketinfo/${ticketId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch ticket information');
            }
            return response.json();
        });
}

function updateBusInfo(ticketInfo) {
    const busInfoContainer = document.querySelector('.bus-info');
    if (busInfoContainer) {
        const { origin, destiny, trip, seat, date} = ticketInfo;
        const { company, price, id: tripId } = trip;

       
        const preferredCurrency = localStorage.getItem('selectedCurrency');
        const exchangeRate = localStorage.getItem('rate');

       
        const priceInPreferredCurrency = (price * exchangeRate).toFixed(2);

       
        busInfoContainer.querySelector('h2').textContent = `Bus trip from ${origin} to ${destiny}`;
        const busDetails = busInfoContainer.querySelector('.bus-details');
        busDetails.querySelector('p:nth-child(1)').textContent = `Company: ${company}`;
        busDetails.querySelector('p:nth-child(2)').textContent = `Trip: #${tripId}`;
        busDetails.querySelector('p:nth-child(3)').textContent = `Seat: ${seat}`;
        busDetails.querySelector('p:nth-child(4)').textContent = `Date: ${date}`;
        busDetails.querySelector('p:nth-child(5)').textContent = `Price: ${priceInPreferredCurrency} ${preferredCurrency}`;
    } else {
        console.error('Bus info container not found');
    }
}


document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('purchase-form');
    if (form) {
        form.addEventListener('submit', function(event) {
            event.preventDefault(); 

            // Get ticketId from localStorage
            const ticketId = localStorage.getItem('ticketId');
            if (!ticketId) {
                console.error('Ticket ID not found in localStorage');
                return;
            }

            const cardType = document.getElementById('cardType').value;

    
            const formData = new FormData(form);

          
            let isValid = true;
            for (const value of formData.values()) {
                if (!value) {
                    isValid = false;
                    break;
                }
            }

            if (!isValid) {
                console.error('All inputs must be filled.');
                return;
            }

           
            formData.append('ticketId', ticketId);
            formData.append('cardType', cardType);

            
            const jsonData = {};
            formData.forEach((value, key) => {
                jsonData[key] = value;
            });

            fetch('http://localhost:8080/ticket/purchase', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(jsonData)
            })
            .then(response => {
                if (response.ok) {
                    console.log('Ticket purchased successfully.');
                    window.location.href = 'finalpage.html';
                } else {
                    console.error('Failed to purchase ticket.');
                }
            })
            .catch(error => {
                console.error('Error purchasing ticket:', error);
            });
        });
    } else {
        console.error('Form not found.');
    }
});


function gotohome(event) {
    event.preventDefault(); 

    window.location.href = 'index.html';
}

 