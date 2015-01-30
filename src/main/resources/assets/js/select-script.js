 function change(sc) {
      $.ajax( {
            url: '/ajaxArgs',
            data: {script: sc.value},
            dataType: 'text',
            success: function(text) {
                if(text=="") {
                    document.getElementById('area').disabled=true;
                    $('#area').val("").trigger('autosize.resize');
                } else {
                    document.getElementById('area').disabled=false;
                    $('#area').val(text).trigger('autosize.resize');
                }
            }
      })
 }