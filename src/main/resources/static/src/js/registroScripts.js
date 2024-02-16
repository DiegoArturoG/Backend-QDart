document.addEventListener("DOMContentLoaded", function () {
    const registrar = document.getElementById('submit');

    registrar.addEventListener('click', async () => {
        // Obtener los valores de los campos
        const usuario = document.getElementById('usuario').value;
        const correo = document.getElementById('correo').value;
        const contrasena = document.getElementById('contrasena').value;
        const rol = document.getElementById('rol').value;
        const estatus = document.getElementById('estatus').value;

        // Validar los campos
        if (!usuario || !correo || !contrasena) {
            mostrarMensaje("Complete todos los campos");
        } else {
            const user = {
                nombre: usuario,
                email: correo,
                password: contrasena,
                rol: rol,
                estatus: estatus
            };

            const url = `https://qdart-qd0y.onrender.com/admin/users`;

            try {
                const response = await fetch(url, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(user)
                });

                const data = await response.json();

                // Verificar la respuesta del servidor y mostrar el mensaje correspondiente
                if (response.ok) {
                    mostrarMensaje("Usuario registrado con éxito", "success");
                } else {
                    mostrarMensaje(data.message || "Error al registrar usuario");
                }
            } catch (error) {
                console.log(error);
                mostrarMensaje("Error al registrar usuario");
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