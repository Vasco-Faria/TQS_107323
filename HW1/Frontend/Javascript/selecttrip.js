



document.addEventListener('DOMContentLoaded', function() {

    getTicketInfo();
   
    const select = document.getElementById('currency-select');
    select.addEventListener('change', function() {
        const baseCurrency = select.value;
        fetch('http://localhost:8080/prices')
        .then(response => response.json())
        .then(data => {
            if (data.hasOwnProperty(baseCurrency)) {
                const selectedCurrencyRate = data[baseCurrency];
                
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

    window.location.href = 'Homepage.html';
}


function getTicketInfo() {
    var ticketId = localStorage.getItem('ticketId');

    if (!ticketId) {
        console.error('Ticket ID not found in localStorage');
        return;
    }

    fetch(`http://localhost:8080/ticketinfo/${ticketId}`)
    .then(response => response.json())
    .then(ticket => {
        const title = document.querySelector('.title-trip');
        title.textContent = `Trip from ${ticket.origin} to ${ticket.destiny}`;
    })
    .catch(error => {
        console.error('Erro ao obter informações do ticket:', error);
    });
}
