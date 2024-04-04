document.getElementById('email-form').addEventListener('submit', function(event) {
    event.preventDefault();
    var email = document.getElementById('email-input').value;
    if (email.trim() !== '') {
        alert('Obrigado por se inscrever para receber notificações por email!');
    }
});
