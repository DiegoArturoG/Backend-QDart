document.addEventListener("DOMContentLoaded", function () {
    const registrar = document.getElementById('submit');

    registrar.addEventListener('click', async () => {
        // Obtener los valores de los campos
        const nombreCliente = document.getElementById('nombreCliente').value;
        const telefono = document.getElementById('telefono').value;
        const direccionEvento = document.getElementById('direccionEvento').value;
        const fechaEvento = document.getElementById('fechaEvento').value;
        const horarioEventoInicial = document.getElementById('horarioEventoInicial').value;
        const horarioEventoFinal = document.getElementById('horarioEventoFinal').value;
        const comentarioServicio = document.getElementById('comentarioServicio').value;
        const idServicio = document.getElementById('idServicio').value;
        const idEstado = document.getElementById('idEstado').value;

        // Validar los campos
        if (!nombreCliente || !telefono || !direccionEvento || !fechaEvento || !horarioEventoInicial || !horarioEventoFinal || !comentarioServicio || !idServicio || !idEstado) {
            mostrarMensaje("Complete todos los campos");
        } else {
            const agenda = {
                nombreCliente: nombreCliente,
                telefono: telefono,
                direccionEvento: direccionEvento,
                fechaEvento: fechaEvento,
                horarioEventoInicial: horarioEventoInicial,
                horarioEventoFinal: horarioEventoFinal,
                comentarioServicio: comentarioServicio,
                idServicio: idServicio,
                idEstado: idEstado
            };

            const url = `https://qdart-qd0y.onrender.com/admin/agendas`;

            try {
                const response = await fetch(url, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(agenda)
                });

                const data = await response.json();

                // Verificar la respuesta del servidor y mostrar el mensaje correspondiente
                if (response.ok) {
                    mostrarMensaje("Evento registrado con éxito", "success");
                } else {
                    mostrarMensaje(data.message || "Error al registrar evento");
                }
            } catch (error) {
                console.log(error);
                mostrarMensaje("Error al registrar evento");
            }
        }
    });

    // Función para mostrar mensajes en el div creado
    function mostrarMensaje(mensaje, tipo = "danger") {
        var mensajeDiv = document.getElementById("mensaje");
        mensajeDiv.innerHTML = mensaje;
        mensajeDiv.classList.add("alert-" + tipo);
        mensajeDiv.style.display = "block";

        // Limpiar el mensaje después de 3 segundos (3000 milisegundos)
        setTimeout(function () {
            mensajeDiv.innerHTML = "";
            mensajeDiv.classList.remove("alert-" + tipo);
            mensajeDiv.style.display = "none";
        }, 3000);
    }
});