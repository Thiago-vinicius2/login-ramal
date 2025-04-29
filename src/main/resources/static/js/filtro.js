const inputPesquisa = document.querySelector(".filtro");

inputPesquisa.addEventListener("input", filtraLinhas);

// FUNÇÃO PARA BUSCAR DADOS NA TABELA
function filtraLinhas() {
    const valor = inputPesquisa.value.toLowerCase();
    const linhas = document.querySelectorAll("#tabela-ramais tr");

    linhas.forEach(linha => {  
        const textoLinha = linha.textContent.toLowerCase();

        if (textoLinha.includes(valor)) {
            linha.style.display = ""
        }
        else {
            linha.style.display = "none"
        }

    }); 
};

