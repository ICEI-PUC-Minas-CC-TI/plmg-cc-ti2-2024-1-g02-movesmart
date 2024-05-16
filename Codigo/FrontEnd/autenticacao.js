const btnLogin = document.querySelector('.btn-login');

btnLogin.addEventListener('click', () => {
    const login = document.querySelector('#inputLogin').value;
    const senha = document.querySelector('#inputSenha').value;

    const usuario = {
        login: login,
        senha: senha
    };

    axios.post('http://localhost:6796/login', usuario)
    .then(response => {
        console.log(response);
        alert('Você foi autenticado com sucesso!');
        window.location.href = '/Codigo/FrontEnd/mapa.html';
    })
    .catch(error => {
        console.log(error);
        alert('Falha na autenticação. Tente novamente');
    });
});