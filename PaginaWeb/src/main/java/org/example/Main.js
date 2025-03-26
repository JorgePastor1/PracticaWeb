let xmlDocGlobal = document.implementation.createDocument('', 'datos', null);
let rootGlobal = xmlDocGlobal.documentElement;

function mostrarVentana(id) {
  document.querySelectorAll('.ventana').forEach(section => {
    section.classList.remove('activa');
  });
  document.getElementById(id).classList.add('activa');
}

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

document.getElementById('formInscripcion').addEventListener('submit', function(e) {
  e.preventDefault();
  const equipo = this.equipo.value;
  const torneo = this.torneo.value;

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

function verInscripcionesEquipo() {
  const equipoBuscado = document.getElementById('equipoConsulta').value.trim();
  const lista = document.getElementById('listaInscripciones');
  lista.innerHTML = '';

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
