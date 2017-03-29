function saveLaw() {
    if(!isLawValid()){
        BootstrapDialog.alert('Todos los campos son obligatorios');
    }else{
        $.ajax({
            type: 'POST',
            url: "/api/saveLaw",
            data: {
                "title" : $('#lawTitle').val(),
                "number" : $('#lawNumber').val(),
                "content" : $('#lawContent').val()
            },
            success: function(data){
                if(data == "created"){
                    BootstrapDialog.alert('Se ha creado exitosamente la ley');
                }else{
                    BootstrapDialog.alert('Se ha producido un error');
                }
            },
            async:false
        });
    }
}

function isLawValid() {
    var mandatoryFields = ["lawTitle","lawNumber", "lawContent"];
    var isValid = true;
    $(mandatoryFields).each(
        function(){
            isValid = ($('#'+this).val() != "") && isValid;
        }
    );
    return isValid;
}