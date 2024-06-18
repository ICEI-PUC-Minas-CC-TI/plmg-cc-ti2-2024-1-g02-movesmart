//cadastro de usuário
const btn = document.querySelector('.btn-nav');

btn.addEventListener('click', () => {
    const nome = document.querySelector('#inputNome').value;
    const email = document.querySelector('#inputEmail').value;
    const telefone = document.querySelector('#inputTelefone').value;
    const login = document.querySelector('#inputLogin').value;
    const senha = document.querySelector('#inputSenha').value;

    const usuario = {
        nome: nome,
        email: email,
        telefone: telefone,
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