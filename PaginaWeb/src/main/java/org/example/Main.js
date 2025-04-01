let xmlDocGlobal = document.implementation.createDocument('', 'datos', null);
let rootGlobal = xmlDocGlobal.documentElement;

// Función para mostrar la ventana activa
function mostrarVentana(id) {
  document.querySelectorAll('.ventana').forEach(section => {
    section.classList.remove('activa');
  });
  document.getElementById(id).classList.add('activa');
}

// Crear un equipo
document.getElementById('formRegistro').addEventListener('submit', function(e) {
  e.preventDefault();
  const equipo = this.equipo.value;
  const capitan = this.capitan.value;

  const registro = xmlDocGlobal.createElement('registroEquipo');
  const equipoNode = xmlDocGlobal.createElement('equipo');
  const capitanNode = xmlDocGlobal.createElement('capitan');
  equipoNode.textContent = equipo;
  capitanNode.textContent = capitan;
  registro.appendChild(equipoNode);
  registro.appendChild(capitanNode);
  rootGlobal.appendChild(registro);

  alert('Equipo registrado correctamente.');
  this.reset();
  actualizarBotonDescarga();
});

// Crear inscripción
document.getElementById('formInscripcion').addEventListener('submit', function(e) {
  e.preventDefault();
  const equipo = this.equipoInscripcion.value;
  const torneo = this.torneoInscripcion.value;

  const inscripcion = xmlDocGlobal.createElement('inscripcionTorneo');
  const equipoNode = xmlDocGlobal.createElement('equipo');
  const torneoNode = xmlDocGlobal.createElement('torneo');
  equipoNode.textContent = equipo;
  torneoNode.textContent = torneo;
  inscripcion.appendChild(equipoNode);
  inscripcion.appendChild(torneoNode);
  rootGlobal.appendChild(inscripcion);

  alert('Inscripción realizada correctamente.');
  this.reset();
  actualizarBotonDescarga();
});

// Función para actualizar el botón de descarga XML
function actualizarBotonDescarga() {
  const serializer = new XMLSerializer();
  const xmlStr = serializer.serializeToString(xmlDocGlobal);
  const blob = new Blob([xmlStr], { type: 'application/xml' });
  const url = URL.createObjectURL(blob);

  let botonDescarga = document.getElementById('descargarXML');
  if (!botonDescarga) {
    botonDescarga = document.createElement('a');
    botonDescarga.id = 'descargarXML';
    botonDescarga.textContent = 'Descargar XML';
    botonDescarga.style.display = 'inline-block';
    botonDescarga.style.margin = '20px';
    botonDescarga.style.background = '#28a745';
    botonDescarga.style.color = 'white';
    botonDescarga.style.padding = '10px';
    botonDescarga.style.textDecoration = 'none';
    document.body.appendChild(botonDescarga);
  }
  botonDescarga.href = url;
  botonDescarga.download = 'datos_torneos.xml';
}

// Ver inscripciones de un equipo
function verInscripcionesEquipo() {
  const equipoBuscado = document.getElementById('equipoConsulta').value.trim();
  const lista = document.getElementById('listaInscripciones');
  lista.innerHTML = ''; // Limpiar lista

  const inscripciones = xmlDocGlobal.getElementsByTagName('inscripcionTorneo');

  for (let i = 0; i < inscripciones.length; i++) {
    const equipo = inscripciones[i].getElementsByTagName('equipo')[0].textContent;
    const torneo = inscripciones[i].getElementsByTagName('torneo')[0].textContent;

    if (equipo.toLowerCase() === equipoBuscado.toLowerCase()) {
      const li = document.createElement('li');
      li.textContent = torneo;
      lista.appendChild(li);
    }
  }

  if (lista.childElementCount === 0) {
    const li = document.createElement('li');
    li.textContent = 'No hay inscripciones encontradas para este equipo.';
    lista.appendChild(li);
  }
}

// Función para actualizar las ciudades dependiendo del país seleccionado
const ciudadesPorPais = {
  espana: ['Madrid', 'Barcelona', 'Valencia', 'Sevilla', 'Bilbao'],
  francia: ['París', 'Lyon', 'Marsella', 'Niza', 'Toulouse'],
  italia: ['Roma', 'Milán', 'Nápoles', 'Turín', 'Florencia']
};

function actualizarCiudades() {
  const pais = document.getElementById('paisSelect').value;
  const ciudadSelect = document.getElementById('ciudadSelect');
  ciudadSelect.innerHTML = '';
  const ciudades = ciudadesPorPais[pais];
  ciudades.forEach(ciudad => {
    const option = document.createElement('option');
    option.value = ciudad.toLowerCase();
    option.textContent = ciudad;
    ciudadSelect.appendChild(option);
  });

  const ciudadSelectTorneo = document.getElementById('ciudadSelectTorneo');
  ciudadSelectTorneo.innerHTML = '';
  ciudades.forEach(ciudad => {
    const option = document.createElement('option');
    option.value = ciudad.toLowerCase();
    option.textContent = ciudad;
    ciudadSelectTorneo.appendChild(option);
  });
}

// Inicializa la función de actualización de ciudades al seleccionar un país
document.getElementById('paisSelectTorneo').addEventListener('change', actualizarCiudades);

// Operación PATCH para actualizar una inscripción
document.getElementById('formActualizarInscripcion').addEventListener('submit', function(e) {
  e.preventDefault();
  const idInscripcion = this.idInscripcion.value;
  const equipo = this.nuevoEquipo.value;
  const torneo = this.nuevoTorneo.value;

  const inscripciones = xmlDocGlobal.getElementsByTagName('inscripcionTorneo');
  for (let i = 0; i < inscripciones.length; i++) {
    const inscripcion = inscripciones[i];
    const idNode = inscripcion.getElementsByTagName('id')[0];
    if (idNode && idNode.textContent === idInscripcion) {
      inscripcion.getElementsByTagName('equipo')[0].textContent = equipo;
      inscripcion.getElementsByTagName('torneo')[0].textContent = torneo;
      alert('Inscripción actualizada correctamente.');
      this.reset();
      actualizarBotonDescarga();
      return;
    }
  }
  alert('Inscripción no encontrada.');
});
