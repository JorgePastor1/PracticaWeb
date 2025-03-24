const equipos = new Map();  //Estructura de mapa para los equipos
const partidos = new Map(); //Igual para los partidos

document.getElementById("form-equipo").addEventListener("submit", function (e) {
  e.preventDefault();

    //Atributos a guardar en la base de datos sobre los equipos
  const id = document.getElementById("idEquipo").value;
  const nombre = document.getElementById("nombreEquipo").value;
  const deporte = document.getElementById("deporte").value;
  const ciudad = document.getElementById("ciudad").value;
  const numJugadores = parseInt(document.getElementById("numJugadores").value);

  const jugadores = [];
  for (let i = 0; i < numJugadores; i++) {
    jugadores.push({
      nombre: `Jugador${i + 1}`,
      edad: 20 + i,
      posicion: `Posición${i + 1}`
    });
  }

  equipos.set(id, {
    id,
    nombre,
    deporte,
    ciudad,
    numJugadores,
    jugadores
  });

  mostrarDatos();   //Funcion para mostrar los datos guardados
});

document.getElementById("form-partido").addEventListener("submit", function (e) {
  e.preventDefault();

    //Atributos a guardar en la base de datos sobre los partidos

  const id = document.getElementById("idPartido").value;
  const equipoA = document.getElementById("equipoA").value;
  const equipoB = document.getElementById("equipoB").value;
  const fechaHora = document.getElementById("fechaHora").value;
  const resultado = document.getElementById("resultado").value;

  partidos.set(id, {
    id,
    equipoA,
    equipoB,
    fechaHora,
    resultado
  });

  mostrarDatos();
});

function mostrarDatos() {   //Guarda los datos en un documento salida
  const salida = document.getElementById("salida");
  salida.textContent = `Equipos:\n${JSON.stringify(Object.fromEntries(equipos), null, 2)}\n\nPartidos:\n${JSON.stringify(Object.fromEntries(partidos), null, 2)}`;
}
window.addEventListener("DOMContentLoaded", () => {
  fetch("data.xml")
    .then(response => response.text())
    .then(str => (new window.DOMParser()).parseFromString(str, "text/xml"))
    .then(xml => {
      const equiposXML = xml.getElementsByTagName("equipo");
      for (let equipo of equiposXML) {
        const id = equipo.getAttribute("id");
        const nombre = equipo.getElementsByTagName("nombre")[0].textContent;
        const deporte = equipo.getElementsByTagName("deporte")[0].textContent;
        const ciudad = equipo.getElementsByTagName("ciudad")[0].textContent;

        const jugadoresXML = equipo.getElementsByTagName("jugador");
        const jugadores = [];
        for (let jugador of jugadoresXML) {
          jugadores.push({
            nombre: jugador.getElementsByTagName("nombre")[0].textContent,
            edad: parseInt(jugador.getElementsByTagName("edad")[0].textContent),
            posicion: jugador.getElementsByTagName("posicion")[0].textContent
          });
        }

        equipos.set(id, {
          id,
          nombre,
          deporte,
          ciudad,
          numJugadores: jugadores.length,
          jugadores
        });
      }

      const partidosXML = xml.getElementsByTagName("partido");
      for (let partido of partidosXML) {
        const id = partido.getAttribute("id");
        const equipoA = partido.getElementsByTagName("equipoA")[0].textContent;
        const equipoB = partido.getElementsByTagName("equipoB")[0].textContent;
        const fechaHora = partido.getElementsByTagName("fechaHora")[0].textContent;
        const resultado = partido.getElementsByTagName("resultado")[0].textContent;

        partidos.set(id, {
          id,
          equipoA,
          equipoB,
          fechaHora,
          resultado
        });
      }

      mostrarDatos();
    })
    .catch(err => console.error("Error al cargar el XML:", err));
});
