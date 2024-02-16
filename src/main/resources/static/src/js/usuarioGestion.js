document.addEventListener("DOMContentLoaded", async function () {
    // Endpoint para obtener la lista de usuarios desde el servidor
    const url = "https://qdart-qd0y.onrender.com/admin/users";

    try {
        const response = await fetch(url);
        const data = await response.json();

        if (response.ok) {
            // Llenar la tabla con los datos obtenidos
            llenarTabla(data);
        } else {
            console.error("Error al obtener los datos del servidor");
        }
    } catch (error) {
        console.error("Error al realizar la solicitud:", error);
    }

    // Función para llenar la tabla con los datos obtenidos
    function llenarTabla(data) {
        const tablaBody = document.querySelector(".table tbody");

        // Limpiar la tabla antes de llenarla con nuevos datos
        tablaBody.innerHTML = "";

        // Recorrer los datos y agregar filas a la tabla
        data.forEach(usuario => {
            const fila = document.createElement("tr");
            fila.innerHTML = `
                <td>${usuario.nombre}</td>
                <td>${usuario.email}</td>
                <td>
                    <small class="d-inline-flex mb-3 px-2 py-1 fw-semibold text-white bg-${getColor(usuario.rol)} rounded-2">${usuario.rol}</small>
                </td>
                <td>
                    <small class="d-inline-flex mb-3 px-2 py-1 fw-semibold text-${getStatusColor(usuario.estatus)}-emphasis bg-${getStatusColor(usuario.estatus)}-subtle border border-${getStatusColor(usuario.estatus)}-subtle rounded-2">${usuario.estatus}</small>
                </td>
                <td>
                    <button type="button" class="btn btn-success btn-sm" href="#">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button type="button" class="btn btn-danger btn-sm" href="#">
                        <i class="bi bi-trash-fill"></i>
                    </button>
                </td>
            `;

            tablaBody.appendChild(fila);
        });
    }

    // Función para asignar un color basado en el rol del usuario
    function getColor(rol) {
        switch (rol) {
            case "Administrador":
                return "success";
            case "Editor":
                return "secondary";
            case "Fotografo":
                return "primary";
            case "Cliente":
                return "info";
            default:
                return "dark";
        }
    }

    // Función para asignar un color basado en el estado del usuario
    function getStatusColor(estatus) {
        return estatus === "activo" ? "success" : "danger";
    }
});