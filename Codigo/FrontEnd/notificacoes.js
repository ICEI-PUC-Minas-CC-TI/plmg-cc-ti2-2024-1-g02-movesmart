//cadastro de usuário
const btn = document.querySelector('btn-nav');

btn.addEventListener('click', () => {
    const onibus = document.querySelector('#onibus').value;
    const origem = document.querySelector('#origem').value;
    const destino = document.querySelector('#destino').value;
    const horario = document.querySelector('#horario').value;

    const OD = {
        onibus: onibus,
        origem: origem,
        destino: destino,
        horario: horario
    };

    axios.post('http://localhost:6796/od', od)
    .then(response => {
        console.log(response);
        alert('Origem-Destino criada com sucesso!');
    })
    .catch(error => {
        console.log(error);
        alert('Erro! Não foi possível criar sua Origem-Destino. Tente novamente');
    });
});