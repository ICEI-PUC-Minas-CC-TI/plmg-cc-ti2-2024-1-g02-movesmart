function validateEmail(email) {
    var verificando = /^[a-zA-Z\s]+$/;

    if (email.indexOf('@') === -1) {
        return false;
    }

    return verificando.test(email);
}
function validadeNames(name) {
    var verificando = /^[a-zA-Z]+$/;
    return verificando.test(name.trim());
}

document.getElementById('myForm').addEventListener('submit', function(e) {
    e.preventDefault();

    var name = document.getElementById('name').value;
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;
    var confirmPassword = document.getElementById('confirmPassword').value;

    // validação de senha
    if (password !== confirmPassword) {
        alert('Senhas não conferem');
        return;
    }

    // validação de nome
    if (!validateEmail(email)) {
        alert('Email inválido');
        return;
    }

    // validação de nome
    if (!validadeNames(name)) {
        alert('caracteres inválidos no nome');
        return;
    }

    var obj = {
        name: name.trim(),
        email: email.trim(),
        password: password.trim(),
        confirmPassword: confirmPassword
    };

    var json = JSON.stringify(obj);

    console.log(json);
});