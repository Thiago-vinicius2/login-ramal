// FUNÇÃO PARA PREENCHER A TABEA COM OS RAMAIS
function preencherTabela(ramais) {
    const tabela = document.getElementById("tabela-ramais");
    tabela.innerHTML = "";

    ramais.forEach((ramal, index) => {
        const linha = document.createElement("tr");

        let html = `
            <td> ${index + 1}</td>
            <td> ${ramal.numeroRamal}</td>
            <td> ${ramal.nomeUsuario ?? ''}</td>
            <td> ${ramal.emailUsuario ?? ''}</td>
            <td> ${ramal.statusRamal}</td>`;

        if (ramal.statusRamal === "Disponivel") {
            html += `
                <td>
                    <button type="button" class="btn btn-login" data-bs-toggle="modal" data-bs-target="#loginModal" data-ramal="${ramal.numeroRamal}">
                        Login
                        <i class="bi bi-box-arrow-in-right"></i>
                    </button>
                </td>`;
        } else {
            html += `
                <td>
                    <button type="button" class="btn btn-logout" data-bs-toggle="modal" data-bs-target="#logoutModal" data-ramal="${ramal.numeroRamal}">
                            Logout
                            <i class="bi bi-box-arrow-left"></i>
                    </button>
                </td>`;
        }

        linha.innerHTML = html;
        tabela.appendChild(linha);
    });

    // GARANTE QUE SÓ SEJA EXECUTADO APÓS PREENCHER A TABELA CORRETAMENTE
    setTimeout(() => { resumoStatus(); }, 0);
    setTimeout(() => { configurarModal(); }, 0)
};

// FUNÇÃO PARA ATUALIZAR OS CARTÕES COM A QUANTIDADE DE RAMAIS DE ACORDO COM STATUS
function resumoStatus() {
    const linhas = document.querySelectorAll("#tabela-ramais tr");

    let disponivel = 0;
    let ocupado = 0;

    linhas.forEach(linha => {
        const status = linha.children[4].textContent.trim().toLowerCase();

        switch (status) {
            case "disponivel":
                disponivel++;
                break;

            case "ocupado":
                ocupado++;
                break;
        }
    });

    document.getElementById("count-disponivel").textContent = disponivel;
    document.getElementById("count-ocupado").textContent = ocupado;
};
