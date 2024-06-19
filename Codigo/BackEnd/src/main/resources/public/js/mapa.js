/* CONSTRUIR O MAPA */
const map = L.map("map");
// Initializes map
map.setView([51.505, -0.05], 13);
// Sets initial coordinates and zoom level
L.tileLayer("https://tile.openstreetmap.org/{z}/{x}/{y}.png", {
  maxZoom: 18,
  attribution:
    '© <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
}).addTo(map);
// Sets map data source and associates with map
let marker, circle, zoomed;
navigator.geolocation.watchPosition(success, error);
function success(pos) {
  const lat = pos.coords.latitude;
  const lng = pos.coords.longitude;
  const accuracy = pos.coords.accuracy;
  if (marker) {
    map.removeLayer(marker);
    map.removeLayer(circle);
  }
  // Removes any existing marker and circule (new ones about to be set)
  marker = L.marker([lat, lng]).addTo(map);
  circle = L.circle([lat, lng], { radius: accuracy }).addTo(map);
  // Adds marker to the map and a circle for accuracy
  if (!zoomed) {
    zoomed = map.fitBounds(circle.getBounds());
  }
  // Set zoom to boundaries of accuracy circle
  map.setView([lat, lng]);
  // Set map focus to current user position
}
function error(err) {
  if (err.code === 1) {
    alert("Please allow geolocation access");
  } else {
    alert("Cannot get current location");
  }
}

/* PESQUISAR E CHATBOT */
document.querySelector('.btn-chat').addEventListener('click', function () {
  const chatboxWrapper = document.querySelector('.chatbox-wrapper');
  if (chatboxWrapper.style.display === 'block') {
      chatboxWrapper.style.display = 'none';
  } else {
      chatboxWrapper.style.display = 'block';
  }
});

document.querySelector('.btn-lupa').addEventListener('click', function () {
  const searchBox = document.querySelector('.search-box');
  if (searchBox.style.display === 'block') {
      searchBox.style.display = 'none';
  } else {
      searchBox.style.display = 'block';
  }
});

/* NÃO FUNCIONA */
const btnNoti = document.querySelector('.btn-noti');
btnNoti.addEventListener('click', () => {
    axios.post('http://localhost:6796/od/list/1')
    .then(response => {
        console.log(response);
        // alert('Você foi autenticado com sucesso!');
        window.location.href = 'notificacoes.html';
    })
    .catch(error => {
        console.log(error);
        // alert('Falha na autenticação. Tente novamente');
    });
});