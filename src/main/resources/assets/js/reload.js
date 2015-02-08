function reload() {
    $.ajax({
        url: "/tableReload",
        success: function(html) {
            $('#table').html(html);
        }
    });
}

$(document).ready( function() {
    reload();
    setInterval("reload()",10000);
    }
)