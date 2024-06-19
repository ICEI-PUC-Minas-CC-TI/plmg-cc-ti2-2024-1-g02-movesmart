const messageBar = document.querySelector(".bar-wrapper input");
const sendBtn = document.querySelector(".bar-wrapper button");
const messageBox = document.querySelector(".message-box");

const chatResponses = {
    "greetings": {
        "welcomeMessage": "Como posso ajudar você hoje?",
        "options": [
            "1. Consultar horários de ônibus",
            "2. Ver rotas disponíveis",
            "3. Informações sobre tarifas",
            "4. Relatar um problema"
        ]
    },
    "consultBusSchedule": {
        "prompt": "Por favor, informe a linha de ônibus ou a parada de interesse.",
        "responses": {
            "lineNotFound": "Desculpe, não encontramos essa linha de ônibus. Por favor, tente novamente.",
            "stopNotFound": "Desculpe, não encontramos essa parada. Por favor, tente novamente."
        }
    },
    "viewRoutes": {
        "prompt": "Por favor, informe o número da linha de ônibus que deseja consultar.",
        "responses": {
            "routeDetails": "Aqui estão os detalhes da linha {lineNumber}:",
            "routeNotFound": "Desculpe, não encontramos informações para essa linha. Por favor, tente novamente."
        }
    },
    "fareInformation": {
        "message": "A tarifa atual para o transporte público é de R$ 4,50. Para mais informações sobre tarifas, visite nosso site."
    },
    "reportProblem": {
        "prompt": "Por favor, descreva o problema que você está enfrentando.",
        "responses": {
            "thankYouMessage": "Obrigado por relatar o problema. Vamos investigar e resolver o mais rápido possível.",
            "problemReported": "Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente."
        }
    },
    "fallback": {
        "message": "Desculpe, não entendi sua solicitação. Por favor, escolha uma das opções disponíveis ou reformule sua pergunta."
    }
};

let currentStep = null;

sendBtn.onclick = function () {
    if (messageBar.value.length > 0) {
        const UserTypedMessage = messageBar.value;
        messageBar.value = "";

        let userMessage =
            `<div class="chat message">
        <img src="img/user logo.png">
        <span>
          ${UserTypedMessage}
        </span>
      </div>`;

        let botTyping =
            `<div class="chat response">
        <img src="img/bus.png">
        <span class="new">...
        </span>
      </div>`;

        messageBox.insertAdjacentHTML("beforeend", userMessage);
        messageBox.insertAdjacentHTML("beforeend", botTyping);

        setTimeout(() => {
            let botReply = chatResponses.fallback.message; // Default to fallback message

            if (currentStep === "consultBusSchedule") {
                botReply = `Os horários da linha ${UserTypedMessage} são: 06:00, 07:00, 08:00, 09:00, 10:00...`;
                currentStep = null;
            } else if (currentStep === "viewRoutes") {
                botReply = `Aqui estão os detalhes da linha ${UserTypedMessage}: Rota principal - Centro, Parada A, Parada B, Parada C...`;
                currentStep = null;
            } else if (currentStep === "reportProblem") {
                botReply = chatResponses.reportProblem.responses.thankYouMessage;
                currentStep = null;
            } else {
                // Initial question handling
                if (UserTypedMessage.includes("1")) {
                    botReply = chatResponses.consultBusSchedule.prompt;
                    currentStep = "consultBusSchedule";
                } else if (UserTypedMessage.includes("2")) {
                    botReply = chatResponses.viewRoutes.prompt;
                    currentStep = "viewRoutes";
                } else if (UserTypedMessage.includes("3")) {
                    botReply = chatResponses.fareInformation.message;
                    currentStep = null;
                } else if (UserTypedMessage.includes("4")) {
                    botReply = chatResponses.reportProblem.prompt;
                    currentStep = "reportProblem";
                } else if (UserTypedMessage.toLowerCase().includes("olá") || UserTypedMessage.toLowerCase().includes("oi") || UserTypedMessage.toLowerCase().includes("ola")) {
                    botReply = chatResponses.greetings.welcomeMessage + "<br> " + chatResponses.greetings.options.join("<br> ");
                    currentStep = null;
                }
            }

            const ChatBotResponse = document.querySelector(".response .new");
            ChatBotResponse.innerHTML = botReply;
            ChatBotResponse.classList.remove("new");
        }, 100);
    }
}
