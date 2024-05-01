//cadastro de usuário
const btn = document.querySelector('.btn-nav');

btn.addEventListener('click', () => {
    const nome = document.querySelector('#inputNome').value;
    const email = document.querySelector('#inputEmail').value;
    // const telefone = document.querySelector('#inputTelefone').value;
    const login = document.querySelector('#inputLogin').value;
    const senha = document.querySelector('#inputSenha').value;

    const usuario = {
        nome: nome,
        email: email,
        // telefone:  telefone,
        login: login,
        senha: senha
    };

    axios.post('http://localhost:6796/usuario', usuario)
    .then(response => {
        console.log(response);
        alert('Sua conta foi criada com sucesso!');
        window.location.href = '/Codigo/FrontEnd/login.html';
    })
    .catch(error => {
        console.log(error);
        alert('Sua conta não foi criada. Tente novamente');
    });
});

// function validateEmail(email) {

//     const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
//     return regex.test(email);
// }
// function validadeNames(name) {
//     var verificando = /^[a-zA-Z]+$/;
//     return verificando.test(name.trim());
// }

// document.getElementById('myForm').addEventListener('submit', function(e) {
//     e.preventDefault();

//     var name = document.getElementById('name').value;
//     var email = document.getElementById('email').value;
//     var password = document.getElementById('password').value;
//     var confirmPassword = document.getElementById('confirmPassword').value;

//     // validação de senha
//     if (password !== confirmPassword) {
//         alert('Senhas não conferem');
//         return;
//     }

//     // validação de nome
//     if (!validateEmail(email)) {
//         alert('Email inválido');
//         return;
//     }

//     // validação de nome
//     if (!validadeNames(name)) {
//         alert('caracteres inválidos no nome');
//         return;
//     }

//     var obj = {
//         name: name.trim(),
//         email: email.trim(),
//         password: password.trim(),
//         confirmPassword: confirmPassword
//     };

//     var json = JSON.stringify(obj);

//     console.log(json);
// });