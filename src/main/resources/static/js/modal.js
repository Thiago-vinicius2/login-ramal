function configurarModal() {

    document.addEventListener("click", (event) => {
        const loginBtn = event.target.closest(".btn-login");
        const logoutBtn = event.target.closest(".btn-logout");

        // LOGIN
        if (loginBtn) {
            const ramal = loginBtn.getAttribute("data-ramal");
            document.getElementById("ramal-login").value = ramal;
            document.getElementById("ramalDisplay-login").textContent = `Ramal: ${ramal}`;
        };

        // LOGOUT
        if (logoutBtn) {
            const ramal = logoutBtn.getAttribute("data-ramal");
            document.getElementById("ramal-logout").value = ramal;
            document.getElementById("ramalDisplay-logout").textContent = `Ramal: ${ramal}`;
        };
    });

    // ENVIA DADOS DO LOGIN
    const buttonLogin = document.querySelector("#loginModal .btn-connect-login");
    if (buttonLogin) {
        buttonLogin.addEventListener("click", () => {
            const ramal = document.getElementById("ramal-login").value;
            const email = document.getElementById("email-login").value;
            realizarLogin(ramal, email);
        });
    };

    // ENVIA DADOS DO LOGOUT
    const buttonLogout = document.querySelector("#logoutModal .btn-disconnect-logout");
    if (buttonLogout) {
        buttonLogout.addEventListener("click", () => {
            const ramal = document.getElementById("ramal-logout").value;
            const email = document.getElementById("email-logout").value;
            realizarLogout(ramal, email);
        });
    };
};

// BOTÃO LOGIN DO MODAL 
function realizarLogin(ramal, email) {
    const dadosLogin = {
        numeroRamal: ramal,
        email: email
    };

    fetch("http://localhost:8080/ramais/loginRamal", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(dadosLogin)
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(erro => {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: erro.message
                    });
                });
            };
            return response.json();
        })
        .then(data => {
            if (data && data.numeroRamal) {
                Swal.fire({
                    title: "Login Concluido",
                    icon: "success"
                }).then(() => {
                    // FECHAR O MODAL AUTOMATICAMENTE 
                    const modalElement = document.getElementById('loginModal');
                    const modalInstance = bootstrap.Modal.getInstance(modalElement) || new bootstrap.Modal(modalElement);
                    modalInstance.hide();

                    // RECARREGA A PAGINA APÓS LOGIN
                    location.reload();
                });
            }
        })
        .catch(error => {
            alert(error.message)
        });
};

// BOTÃO LOGOUT DO MODAL
function realizarLogout(ramal, email) {
    const dadosLogout = {
        numeroRamal: ramal,
        email: email
    };

    fetch("http://localhost:8080/ramais/logoutRamal", {
        method: "DELETE",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(dadosLogout)
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(erro => {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: erro.message
                    });
                });
            };
            return response.json()
        })
        .then(data => {
            if (data && data.numeroRamal) {
                Swal.fire({
                    title: "Logout Concluido",
                    icon: "success"
                }).then(() => {
                    // FECHAR O MODEL AUTOMATICAMENTE 
                    const modalElement = document.getElementById('logoutModal');
                    const modalInstance = bootstrap.Modal.getInstance(modalElement) || new bootstrap.Modal(modalElement);
                    modalInstance.hide();

                    // RECARREGA A PAGINA APÓS LOGIN
                    location.reload();
                });
            }
        })
        .catch(error => {
            alert(error.message)
        });
}


