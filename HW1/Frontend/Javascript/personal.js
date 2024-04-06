document.addEventListener('DOMContentLoaded', function() {
    const ticketId = localStorage.getItem('ticketId');
    if (!ticketId) {
        console.error('Ticket ID not found in localStorage');
    } else {
        fetchTicketInfo(ticketId)
            .then(ticketInfo => {
                console.log('Ticket information:', ticketInfo);
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

        // Retrieve preferred currency and exchange rate from local storage
        const preferredCurrency = localStorage.getItem('selectedCurrency');
        const exchangeRate = localStorage.getItem('rate');

        // Calculate price in preferred currency
        const priceInPreferredCurrency = (price * exchangeRate).toFixed(2);

        // Update bus information in the DOM
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
