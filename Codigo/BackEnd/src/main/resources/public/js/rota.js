var map = L.map('map').setView([-19.917, -43.934], 13);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '© OpenStreetMap'
}).addTo(map);

var routingControl = null;

document.querySelector('.search-box button').addEventListener('click', function () {
    const startPoint = document.querySelectorAll('.search-box input')[0].value;
    const endPoint = document.querySelectorAll('.search-box input')[1].value;
    console.log(startPoint, endPoint);

    if (startPoint && endPoint) {
        // Using a geocoding service to get coordinates from addresses (example: Nominatim)
        const startGeocodeUrl = `https://nominatim.openstreetmap.org/search?format=json&q=${startPoint}`;
        const endGeocodeUrl = `https://nominatim.openstreetmap.org/search?format=json&q=${endPoint}`;

        axios.all([
            axios.get(startGeocodeUrl),
            axios.get(endGeocodeUrl)
        ]).then(axios.spread((startResponse, endResponse) => {
            const startCoords = [startResponse.data[0].lat, startResponse.data[0].lon];
            const endCoords = [endResponse.data[0].lat, endResponse.data[0].lon];

            // Remove the existing routing control if it exists
            if (routingControl) {
                map.removeControl(routingControl);
            }

            // Add routing control to the map
            routingControl = L.Routing.control({
                waypoints: [
                    L.latLng(startCoords[0], startCoords[1]),
                    L.latLng(endCoords[0], endCoords[1])
                ],
                routeWhileDragging: true,
                createMarker: function (i, waypoint, n) {
                    var markerOptions = {
                        draggable: true
                    };
                    if (i === 0) { // Start point
                        markerOptions.icon = L.icon({
                            iconUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/images/marker-icon.png',
                            iconAnchor: [12, 41],
                            popupAnchor: [1, -34],
                            shadowSize: [41, 41]
                        });
                    } else if (i === n - 1) { // End point
                        markerOptions.icon = L.icon({
                            iconUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/images/marker-icon.png',
                            iconAnchor: [12, 41],
                            popupAnchor: [1, -34],
                            shadowSize: [41, 41]
                        });
                    }
                    return L.marker(waypoint.latLng, markerOptions).addTo(map);
                }
            }).on('routesfound', function (e) {
                var routes = e.routes;
                console.log(routes);
            }).addTo(map);
        })).catch(error => {
            console.error('Error fetching geocode data:', error);
            alert('Erro ao obter dados de geocodificação. Verifique os endereços fornecidos.');
        });
    } else {
        alert('Por favor, preencha os campos de origem e destino.');
    }
});

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
