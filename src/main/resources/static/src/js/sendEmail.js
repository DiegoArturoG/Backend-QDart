
const form = document.getElementById('form')

async function handleSendEmail(event) {
  event.preventDefault()

  const fd = new FormData(this)

  try {
    const response = await fetch('https://formspree.io/f/mleqgeke', {
      method: 'POST',
      body: fd,
      headers: {
        Accept: 'application/json'
      }
    })

    if (response.ok) {
      this.reset()
      appendAlert('Información enviada!', 'success'); // Llamar a appendAlert cuando la información se envía correctamente
    } else {
      appendAlert('Error al enviar el mensaje', 'danger'); // Llamar a appendAlert cuando hay un error al enviar la información
    }
  } catch (error) {
    console.error('Error inesperado:', error);
    appendAlert('Error inesperado al enviar el mensaje', 'danger'); // Llamar a appendAlert en caso de un error inesperado
  }
}

form.addEventListener('submit', handleSendEmail);

const alertPlaceholder = document.getElementById('liveAlertPlaceholder');

// La función appendAlert permanece sin cambios
function appendAlert(message, type) {
  const wrapper = document.createElement('div');
  wrapper.innerHTML = [
    `<div class="alert alert-${type} alert-dismissible" role="alert">`,
    `   <div>${message}</div>`,
    '   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
    '</div>'
  ].join('');

  alertPlaceholder.append(wrapper);
}

const alertTrigger = document.getElementById('liveAlertBtn');