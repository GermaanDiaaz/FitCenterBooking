(() => {
  'use strict'

  const forms = document.querySelectorAll('.needs-validation')

  Array.from(forms).forEach(form => {
	
    form.addEventListener('submit', event => {
		
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }

      form.classList.add('was-validated')
    }, false)
  })
})()

const inputPrecio = document.getElementById('precio');

if (inputPrecio) {
  inputPrecio.addEventListener('input', (event) => {
    let valor = event.target.value;
    event.target.value = valor.replace(/[^0-9.,]/g, '');
  });
}