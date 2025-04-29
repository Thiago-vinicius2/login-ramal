const savedInicio = localStorage.getItem("rangeInicio");
const savedFim = localStorage.getItem("rangeFim");

// SE OS VALORES EXISTIREM, CONVERTE PARA NUMEROS E CARREGA OS RAMAIS
if (savedInicio && savedFim) {
    rangeInicio = parseInt(savedInicio);
    rangeFim = parseInt(savedFim);
    carregarRangeRamais();
}

// FUNÇÃO QUE PEGA OS DADOS DO RAMAL DO BACK END APÓS DEFINIR O INTERVALO
function carregarRangeRamais() {

    let url = `http://localhost:8080/ramais/ocupados`;

    // SE O VALOR FINAL DO INTERVALO NÃO FOR DEFINIDO, USARÁ O VALOR MÁXIMO
    const fim = (rangeFim !== null) ? rangeFim : ramalMaxGlobal;

    // SE O VALOR INICIAL FOR VALIDO, SERÁ PASSADO PARAMETRO PARA URL
    if (rangeInicio !== null) {
        url += `?inicio=${rangeInicio}&fim=${fim}`;
    }

    fetch(url)
        .then(response => {
            if (!response.ok) {
                Swal.fire({
                    icon: "error",
                    title: "Oops...",
                    text: "Não foi possivel carregar os ramais",
                });
            }
            return response.json()
        })
        .then(ramais => {
            preencherTabela(ramais);
        })
        .catch(error => {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Ocorreu um erro ao carregar os ramais.",
            });
        });
};