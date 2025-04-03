// Mapa para almacenar los datos en memoria
let datos = {
  equipos: [],
  torneos: [],
  inscripciones: []
};

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

  // Agregar el equipo al mapa
  datos.equipos.push({ equipo, capitan });

  alert('Equipo registrado correctamente.');
  this.reset();
  actualizarBotonDescarga();
});

// Crear inscripción
document.getElementById('formInscripcion').addEventListener('submit', function(e) {
  e.preventDefault();
  const equipo = this.equipoInscripcion.value;
  const torneo = this.torneoInscripcion.value;

  // Agregar inscripción al mapa
  datos.inscripciones.push({ equipo, torneo });

  alert('Inscripción realizada correctamente.');
  this.reset();
  actualizarBotonDescarga();
});

// Función para actualizar el botón de descarga JSON
function actualizarBotonDescarga() {
  const jsonStr = JSON.stringify(datos, null, 2); // Convertir datos a JSON
  const blob = new Blob([jsonStr], { type: 'application/json' });
  const url = URL.createObjectURL(blob);

  let botonDescarga = document.getElementById('descargarJSON');
  if (!botonDescarga) {
    botonDescarga = document.createElement('a');
    botonDescarga.id = 'descargarJSON';
    botonDescarga.textContent = 'Descargar JSON';
    botonDescarga.style.display = 'inline-block';
    botonDescarga.style.margin = '20px';
    botonDescarga.style.background = '#28a745';
    botonDescarga.style.color = 'white';
    botonDescarga.style.padding = '10px';
    botonDescarga.style.textDecoration = 'none';
    document.body.appendChild(botonDescarga);
  }
  botonDescarga.href = url;
  botonDescarga.download = 'datos_torneos.json';
}

// Ver inscripciones de un equipo
function verInscripcionesEquipo() {
  const equipoBuscado = document.getElementById('equipoConsulta').value.trim();
  const lista = document.getElementById('listaInscripciones');
  lista.innerHTML = ''; // Limpiar lista

  // Filtrar las inscripciones que corresponden al equipo buscado
  const inscripciones = datos.inscripciones.filter(inscripcion => inscripcion.equipo.toLowerCase() === equipoBuscado.toLowerCase());

  if (inscripciones.length > 0) {
    inscripciones.forEach((inscripcion, index) => {
      const li = document.createElement('li');
      li.textContent = `Torneo: ${inscripcion.torneo}`;
      
      // Crear botón de eliminar
      const eliminarBtn = document.createElement('button');
      eliminarBtn.textContent = 'Eliminar';
      eliminarBtn.style.backgroundColor = '#e8590c';
      eliminarBtn.style.color = 'white';
      eliminarBtn.style.border = 'none';
      eliminarBtn.style.cursor = 'pointer';
      eliminarBtn.style.marginLeft = '10px';
      
      // Agregar el evento para eliminar la inscripción
      eliminarBtn.addEventListener('click', () => eliminarInscripcion(index));

      li.appendChild(eliminarBtn);
      lista.appendChild(li);
    });
  } else {
    const li = document.createElement('li');
    li.textContent = 'No hay inscripciones encontradas para este equipo.';
    lista.appendChild(li);
  }
}

// Eliminar inscripción de la lista
function eliminarInscripcion(index) {
  if (confirm('¿Estás seguro de que quieres eliminar esta inscripción?')) {
    datos.inscripciones.splice(index, 1);  // Eliminar inscripción por índice
    actualizarBotonDescarga();  // Actualizar el botón de descarga JSON
    verInscripcionesEquipo();  // Volver a mostrar las inscripciones actualizadas
    alert('Inscripción eliminada correctamente.');
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

  // Buscar la inscripción por su ID
  const inscripcionIndex = datos.inscripciones.findIndex(inscripcion => inscripcion.id === idInscripcion);
  if (inscripcionIndex !== -1) {
    datos.inscripciones[inscripcionIndex].equipo = equipo;
    datos.inscripciones[inscripcionIndex].torneo = torneo;
    alert('Inscripción actualizada correctamente.');
    this.reset();
    actualizarBotonDescarga();
  } else {
    alert('Inscripción no encontrada.');
  }
});

// Mostrar el formulario de contacto cuando se hace clic en el correo electrónico
function mostrarFormularioContacto() {
  const form = document.getElementById('formularioContacto');
  form.style.display = 'block'; // Mostrar el formulario
}

// Función para cerrar el formulario de contacto
function cerrarFormulario() {
  const form = document.getElementById('formularioContacto');
  form.style.display = 'none'; // Ocultar el formulario
}

// Función para enviar el mensaje (simulado)
function enviarMensaje() {
  const mensaje = document.getElementById('mensaje').value;
  if (mensaje.trim()) {
    alert('Mensaje enviado: ' + mensaje);
    document.getElementById('formularioContacto').style.display = 'none'; // Ocultar el formulario después de enviar
    document.getElementById('mensaje').value = ''; // Limpiar el campo de mensaje
  } else {
    alert('Por favor, escribe un mensaje.');
  }
}
