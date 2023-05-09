
function editar() {
    var guardar = document.getElementById('j_idt5:guardar');
    var cancelar = document.getElementById('j_idt5:cancelar');
    var editar = document.getElementById('j_idt5:editar');
    guardar.setAttribute("style","display: block");
    cancelar.setAttribute("style","display: block"); 
    editar.setAttribute("disabled", "disabled");
}   

function guardar() {
    var guardar = document.getElementById('j_idt5:guardar');
    var cancelar = document.getElementById('j_idt5:cancelar');
    guardar.setAttribute("style","display: none");
    cancelar.setAttribute("style","display: none");
}

