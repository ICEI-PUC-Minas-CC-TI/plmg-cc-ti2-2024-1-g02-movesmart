//DATE: 2021-09-29
const chatMessages = document.getElementById("chat-messages");
const userInput = document.getElementById("user-input");
const sendButton = document.getElementById("send-button");

sendButton.addEventListener("click", sendMessage);

function sendMessage() {
    const userMessage = userInput.value;
    if (userMessage.trim() !== "") {
        displayMessage("user", userMessage);
        setTimeout(function () {
            displayMessage("bot", "Random Response.");
        }, 1000);
        userInput.value = "";
    }
}

function displayMessage(sender, message) {
    const messageDiv = document.createElement("div");
    messageDiv.classList.add("message");
    if (sender === "user") {
        messageDiv.innerHTML = `<div class="user-message">${message}</div>`;
    } else if (sender === "bot") {
        messageDiv.innerHTML = `<div class="bot-message">${message}</div>`;
    }
    chatMessages.appendChild(messageDiv);
    // Scroll down to the latest message
    chatMessages.scrollTop = chatMessages.scrollHeight;
}