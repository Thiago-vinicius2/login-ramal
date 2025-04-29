// CARREGAR NAV E SIDE BAR
function carregarHtml(containerId, url) {
    fetch(url)
        .then(response => response.text())
        .then(data => {
            document.getElementById(containerId).innerHTML = data;
        })
        .catch(error => console.error("Erro ao carregar html: ", error));
}

carregarHtml('navbar-container', 'layout.html');
carregarHtml('sidebar-container', 'layout.html');

