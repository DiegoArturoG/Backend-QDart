document.addEventListener("DOMContentLoaded", async function () {
    const baseUrl = "https://qdart-qd0y.onrender.com/admin/agendas";
    let data = [];

    try {
        const response = await fetch(baseUrl);

        if (!response.ok) {
            console.error("Error al obtener los datos del servidor");
            return;
        }

        data = await response.json();
    } catch (error) {
        console.error("Error al realizar la solicitud:", error);
    }

    function llenarTabla() {
        const tablaBody = document.querySelector(".table tbody");
        tablaBody.innerHTML = "";

        data.forEach(agenda => {
            const fila = document.createElement("tr");
            fila.innerHTML = `
                <td>${agenda.nombreCliente}</td>
                <td>${agenda.telefono}</td>
                <td>${agenda.direccionEvento}</td>
                <td>${agenda.fechaEvento}</td>
                <td>${agenda.horarioEventoInicial}</td>
                <td>${agenda.horarioEventoFinal}</td>
                <td>${agenda.comentarioServicio}</td>
                <td>${agenda.idServicio}</td>
                <td>${agenda.idEstado}</td>
                <td>
                    <button type="button" class="btn btn-success btn-sm" data-bs-toggle="modal" data-bs-target="#editarModal" data-id="${agenda.id}">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#confirmarEliminarModal" data-id="${agenda.id}">
                        <i class="bi bi-trash-fill"></i>
                    </button>
                </td>
            `;

            tablaBody.appendChild(fila);
        });
    }

    document.querySelector('.table tbody').addEventListener('click', async function (event) {
        const target = event.target;
        if (target.tagName === 'BUTTON') {
            const accion = target.classList.contains('btn-success') ? 'editar' : 'eliminar';
            const id = target.getAttribute('data-id');
            abrirModal(accion, id);
        }
    });

    window.abrirModal = async function (accion, id) {
        try {
            const agendaResponse = await fetch(`${baseUrl}/${id}`);

            if (!agendaResponse.ok) {
                console.error("Error al obtener la información de la agenda con ID:", id);
                return;
            }

            const agendaSeleccionada = await agendaResponse.json();
            const modal = accion === 'editar' ? $("#editarModal") : $("#confirmarEliminarModal");

            modal.find("#nombreClienteEditar").val(agendaSeleccionada.nombreCliente);
            modal.find("#telefonoEditar").val(agendaSeleccionada.telefono);
            modal.find("#direccionEventoEditar").val(agendaSeleccionada.direccionEvento);
            modal.find("#fechaEventoEditar").val(agendaSeleccionada.fechaEvento);
            modal.find("#horarioEventoInicialEditar").val(agendaSeleccionada.horarioEventoInicial);
            modal.find("#horarioEventoFinalEditar").val(agendaSeleccionada.horarioEventoFinal);
            modal.find("#comentarioServicioEditar").val(agendaSeleccionada.comentarioServicio);
            modal.find("#idServicioEditar").val(agendaSeleccionada.idServicio);
            modal.find("#idEstadoEditar").val(agendaSeleccionada.idEstado);

            if (accion === 'eliminar') {
                modal.find("#confirmarEliminar").off().on("click", async function () {
                    try {
                        const eliminacionResponse = await fetch(`${baseUrl}/${id}`, {
                            method: 'DELETE'
                        });

                        if (!eliminacionResponse.ok) {
                            console.error("Error al eliminar la agenda con ID:", id);
                            return;
                        }

                        alert("La agenda se ha eliminado correctamente");
                        modal.modal("hide");
                        location.reload();
                    } catch (error) {
                        console.error("Error al eliminar la agenda:", error);
                    }
                });
            } else if (accion === 'editar') {
                modal.find("#actualizarInformacion").off().on("click", async function () {
                    try {
                        const datosActualizados = {
                            nombreCliente: modal.find("#nombreClienteEditar").val(),
                            telefono: modal.find("#telefonoEditar").val(),
                            direccionEvento: modal.find("#direccionEventoEditar").val(),
                            fechaEvento: modal.find("#fechaEventoEditar").val(),
                            horarioEventoInicial: modal.find("#horarioEventoInicialEditar").val(),
                            horarioEventoFinal: modal.find("#horarioEventoFinalEditar").val(),
                            comentarioServicio: modal.find("#comentarioServicioEditar").val(),
                            idServicio: modal.find("#idServicioEditar").val(),
                            idEstado: modal.find("#idEstadoEditar").val()
                        };

                        const actualizacionResponse = await fetch(`${baseUrl}/${id}`, {
                            method: 'PUT',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(datosActualizados)
                        });

                        if (!actualizacionResponse.ok) {
                            console.error("Error al actualizar la información de la agenda con ID:", id);
                            return;
                        }

                        alert("La información se ha actualizado correctamente");
                        modal.modal("hide");
                        location.reload();
                    } catch (error) {
                        console.error("Error al actualizar la información de la agenda:", error);
                    }
                });
            }

            modal.modal("show");
        } catch (error) {
            console.error("Error al obtener la información de la agenda:", error);
        }
    };

    llenarTabla();
});
