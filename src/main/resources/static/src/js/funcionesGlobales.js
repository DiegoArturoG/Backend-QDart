document.addEventListener('DOMContentLoaded', function () {
  const storedUsuario = localStorage.getItem('usuario');
  const storedContrasena = localStorage.getItem('contrasena');

  if (!storedUsuario || !storedContrasena) {
      // No hay credenciales almacenadas, redirige a la página de inicio de sesión
      window.location.href = 'login.html'; // Cambia la URL según tu estructura de archivos
  }
});

function cerrarSesion() {
    localStorage.removeItem('usuario');
    localStorage.removeItem('contrasena');
  }