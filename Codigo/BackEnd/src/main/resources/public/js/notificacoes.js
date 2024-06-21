/* const btn = document.querySelector('.btn-nav');

btn.addEventListener('click', () => {
    const onibus = document.querySelector('#onibus').value;
    const origem = document.querySelector('#origem').value;
    const destino = document.querySelector('#destino').value;
    const horario = document.querySelector('#horario').value;

    if (!onibus || !origem || !destino || !horario) {
        alert('Por favor, preencha todos os campos.');
        return;
    }

    const Od = {
        linha: onibus,
        origem: origem,
        destino: destino,
        horario: horario
    };
    
    axios.post('http://localhost:6796/od/insert', Od)
    .then(response => {
        console.log(response);
        alert('Notificação de Origem-Destino criada com sucesso!');
    })
    .catch(error => {
        console.log(error);
        alert('Erro! Não foi possível criar sua Notificação de Origem-Destino. Tente novamente');
    });
}); */

// Dados predefinidos para os dropdowns
const onibusOptions = ["Ônibus 1", "Ônibus 2", "Ônibus 3"];
const origemOptions = ["Origem 1", "Origem 2", "Origem 3"];
const destinoOptions = ["Destino 1", "Destino 2", "Destino 3"];
const horarioOptions = ["08:00", "09:00", "10:00"];

// Função para preencher um dropdown com as opções fornecidas
function preencherDropdown(id, options) {
    const dropdown = document.getElementById(id);
    options.forEach(option => {
        const opt = document.createElement("option");
        opt.value = option;
        opt.text = option;
        dropdown.add(opt);
    });
}

// Função chamada quando a página é carregada
function onLoad() {
    preencherDropdown("onibus", onibusOptions);
    preencherDropdown("origem", origemOptions);
    preencherDropdown("destino", destinoOptions);
    preencherDropdown("horario", horarioOptions);
    if (document.getElementById("msg").value != "") {
        alert(document.getElementById("msg").value);
    }
}

// Função para deletar uma origem-destino
function deletarOd(id, origem, destino, horario) {
    if (confirm('Deseja realmente excluir a Origem-Destino (' + origem + ' - ' + destino + ' - ' + horario + ')' + ' ?')) {
        location.href = '/od/delete/' + id;
    }
}

// Variável para armazenar os origens e destinos adicionados
let odList = [];

// Função para adicionar uma origem e destino à lista e exibir na tabela
function adicionarOD() {
    const onibus = document.getElementById("onibus").value;
    const origem = document.getElementById("origem").value;
    const destino = document.getElementById("destino").value;
    const horario = document.getElementById("horario").value;
    // Validar se todos os campos foram selecionados
    if (onibus && origem && destino && horario) {
        // Criar objeto com os dados selecionados
        const od = {
            onibus: onibus,
            origem: origem,
            destino: destino,
            horario: horario
        };
        // Adicionar o objeto à lista
        odList.push(od);
        // Limpar os campos de seleção
        document.getElementById("onibus").value = "";
        document.getElementById("origem").value = "";
        document.getElementById("destino").value = "";
        document.getElementById("horario").value = "";
        // Atualizar a tabela de listagem
        atualizarTabelaOD();
    } else {
        alert("Por favor, selecione todos os campos.");
    }
}

// Função para atualizar a tabela de listagem de origens e destinos
function atualizarTabelaOD() {
    const listaOD = document.getElementById("lista-od");
    listaOD.innerHTML = ""; // Limpar a tabela antes de adicionar novamente
    // Iterar sobre a lista de origens e destinos
    odList.forEach((od, index) => {
        const row = document.createElement("tr");
        row.classList.add("od-item"); // Adicionar a classe dinâmica
        row.innerHTML = `
            <td>${od.origem}</td>
            <td>${od.destino}</td>
            <td>
                <button type="button" class="btn btn-sm btn-info" onclick="editarOD(${index})">
                    <i class="bi bi-pencil-square"></i> Editar
                </button>
                <button type="button" class="btn btn-sm btn-danger ms-1" onclick="deletarOD(${index})">
                    <i class="bi bi-trash"></i> Deletar
                </button>
            </td>
        `;
        listaOD.appendChild(row);
    });
    // Aplicar estilos alternados para linhas da tabela
    const rows = document.querySelectorAll('.od-item');
    rows.forEach((row, index) => {
        if (index % 2 === 0) {
            row.classList.add('table-striped');
        }
    });
}

// Função para deletar uma origem e destino da lista
function deletarOD(index) {
    if (confirm(`Deseja realmente excluir a Origem-Destino (${odList[index].origem} - ${odList[index].destino})?`)) {
        odList.splice(index, 1); // Remove o elemento da lista pelo índice
        atualizarTabelaOD(); // Atualiza a tabela após a remoção
    }
}

// Função para editar uma origem e destino da lista
function editarOD(index) {
    // Preencher os campos de seleção com os valores atuais para edição
    const od = odList[index];
    document.getElementById("onibus").value = od.onibus;
    document.getElementById("origem").value = od.origem;
    document.getElementById("destino").value = od.destino;
    document.getElementById("horario").value = od.horario;

    // Remover o item da lista após editar
    odList.splice(index, 1);

    // Atualizar a tabela de listagem
    atualizarTabelaOD();
}

/* Funções de Teste */
function linhaSelecionada() {
    var dropdown = document.getElementById("onibus");
    var onibus = dropdown.value;
    console.log("onibus:", onibus);
}
function origemSelecionada() {
    var dropdown = document.getElementById("origem");
    var origem = dropdown.value;
    console.log("origem:", origem);
}
function destinoSelecionada() {
    var dropdown = document.getElementById("destino");
    var destino = dropdown.value;
    console.log("destino:", destino);
}
function horaSelecionada() {
    var dropdown = document.getElementById("horario");
    var horario = dropdown.value;
    console.log("horario:", horario);
}
