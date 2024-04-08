function loadTickets() {
    fetch("http://localhost:8080/alltickets")
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao carregar os tickets.");
            }
            return response.json();
        })
        .then(tickets => {
            var ticketList = document.getElementById("ticket-list");
            ticketList.innerHTML = ''; 

            
            tickets.forEach(ticket => {
                var ticketItem = document.createElement("div");
                ticketItem.classList.add("ticket-item");
                ticketItem.textContent = "Ticket ID: " + ticket.id + " - Origin: " + ticket.origin +"  Destination: " + ticket.destiny+"   ";

                var deleteBtn = document.createElement("button");
                deleteBtn.classList.add("delete-btn");
                deleteBtn.textContent = "Excluir";
                deleteBtn.addEventListener("click", () => openDeleteModal(ticket.id));

                ticketItem.appendChild(deleteBtn);
                ticketList.appendChild(ticketItem);
            });
        })
        .catch(error => {
            console.error("Erro ao carregar os tickets:", error);
        });
}

function openDeleteModal(ticketId) {
    document.getElementById("confirmDeleteBtn").addEventListener("click", function() {
        deleteTicket(ticketId);
        closeDeleteModal();
    });

    document.getElementById("cancelDeleteBtn").addEventListener("click", function() {
        closeDeleteModal();
    });

    document.getElementById("deleteModal").style.display = "block";
}

function closeDeleteModal() {
    
    document.getElementById("confirmDeleteBtn").removeEventListener("click", deleteTicket);
    document.getElementById("cancelDeleteBtn").removeEventListener("click", closeDeleteModal);

    
    document.getElementById("deleteModal").style.display = "none";
}

function deleteTicket(ticketId) {
    console.log(ticketId);
    fetch("http://localhost:8080/deleteticket/" + ticketId, {
        method: "DELETE"
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Erro ao excluir o ticket.");
        }
        loadTickets(); 
    })
    .catch(error => {
        console.error("Erro ao excluir o ticket:", error);
    });
}

function gotohome(event) {
    event.preventDefault();
    window.location.href = "/"; 
}

document.addEventListener("DOMContentLoaded", loadTickets);
