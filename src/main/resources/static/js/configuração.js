// VARIAVEIS GLOBAIS: ARMAZENA RANGE DOS RAMAIS E O VALOR MAXIMO DO RAMAL
let rangeInicio = null;
let rangeFim = null;
let ramalMaxGlobal = null;

// PEGANDO OS DADOS DO HTML
const selectInicio = document.getElementById("ramalInicio");
const selectFim = document.getElementById("ramalFim");
const btnFiltrar = document.getElementById("filtrar");

// (SE HOUVER VALORES) RECUPERA SALVO NO LOCALSTORAGE
const savedInicio = localStorage.getItem("rangeInicio");
const savedFim = localStorage.getItem("rangeFim");

if (!savedInicio || !savedFim) {
    fetch("http://localhost:8080/ramais/range/atual")
        .then(res => res.json())
        .then(data => {
            if (data.inicio !== null && data.fim !== null) {
                rangeInicio = data.inicio;
                rangeFim = data.fim;
            }
            atualizaRangeInicio(3601, 3625);
        });
} else {
    rangeInicio = parseInt(savedInicio);
    rangeFim = parseInt(savedFim);
    atualizaRangeInicio(3601, 3625);
}

// PREENCHE O SELECT INICIO 
function atualizaRangeInicio(ramalMin, ramalMax) {
    ramalMaxGlobal = ramalMax;
    selectInicio.innerHTML = "";
    selectFim.innerHTML = "";

    // CRIA AS OPÇÕES DO SELECT INICIO
    for (let i = ramalMin; i <= ramalMax; i++) {
        const optionInicio = document.createElement("option");
        optionInicio.value = i;
        optionInicio.textContent = i;

        // PARA MOSTRAR QUAL VALOR SELECIONADO AO ABRIR A PAGINA
        if (i === rangeInicio) {
            optionInicio.selected = true;
        }

        selectInicio.appendChild(optionInicio);
    };
    atualizaRangeFim();
}

// PREENCHE O SELECT FIM (COM BASE NO INICIO SELECIONADO)
function atualizaRangeFim() {
    const inicioSelecionado = parseInt(selectInicio.value);
    selectFim.innerHTML = "";

    // CRIA AS OPÇÕES DO SELECT FIM
    for (let i = inicioSelecionado + 1; i <= ramalMaxGlobal; i++) {
        const optionFim = document.createElement("option");
        optionFim.value = i;
        optionFim.textContent = i;

        // PARA MOSTRAR QUAL VALOR SELECIONADO AO ABRIR A PAGINA
        if (i === rangeFim) {
            optionFim.selected = true;
        }

        selectFim.appendChild(optionFim);
    }
};

// ATUALIZA O VALOR INICAL DO INTERVALO QUANDO O USUARIO MUDAR O SELECT 
selectInicio.addEventListener("change", (e) => {
    rangeInicio = parseInt(e.target.value);
    atualizaRangeFim();
})

// ATUALIZA O VALOR FINAL DO INTERVALO QUANDO O USUARIO MUDAR O SELECT 
selectFim.addEventListener("change", (e) => {
    rangeFim = parseInt(e.target.value);
})

// SALVA O INTERVALO E REDIRECIONA PARA PAGINA PRINCIPAL
btnFiltrar.addEventListener("click", () => {
    if (rangeInicio === null || rangeFim === null || rangeInicio >= rangeFim) {
        alert("Selecione um intervalo válido.");
        return;
    }

    // SALVA NO LOCALSTORAGE
    localStorage.setItem("rangeInicio", rangeInicio);
    localStorage.setItem("rangeFim", rangeFim);

    fetch(`http://localhost:8080/ramais/range?inicio=${rangeInicio}&fim=${rangeFim}`, {
    }).then(() => {
        window.location.href = "index.html";
    });
});

