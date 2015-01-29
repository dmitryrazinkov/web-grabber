<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Grub</title>
    <!-- Bootstrap -->
    <link href="/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <#if sites??>
        <form method="post" action="" style="margin:60px 0px 0px 0px;">
            <div class="form-group">
                <label>Select site:</label>
                <select name="site1" class="form-control" onchange="change(this)">
                    <#list sites as site1>
                        <option>${site1.getName()}</option>
                    </#list>
                </select>
                <script>
                    function change(sc) {
                        $.ajax({
                            url: '/ajaxArgs',
                            data: {script: sc.value},
                            dataType: 'text',
                            success: function(text){
                                document.getElementById('area').value = text;
                            }
                        })
                    }
                </script>
            </div>
            <div>
                <textarea id="area" name="args" class="form-control"></textarea>
                <script>
                    moveCaretToEnd(document.getElementsByName("args"));
                    function moveCaretToEnd(inputObject) {
                        if (inputObject.createTextRange) {
                            var r = inputObject.createTextRange();
                            r.collapse(false);
                            r.select();
                        }
                    }
                </script>
            </div>
            <p></p>
            <div>
            <input type="submit" value="Add" class="btn btn-default">
            </div>
        </form>
    </#if>



    <hr>
    <label>Current scripts:</label>
    <table class="table table-striped" id="table">
        <thead>
        <tr>
            <th data-type="string">Script</th>
            <th>Args</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list onTaskScripts as script>
            <tr>
                <td>${script.script.getName()}</td>
                <td>${script.getArgs()}</td>
                <td>
                    <span class="glyphicon glyphicon-remove"></span>
                    <a href="/delete/${script.id}">Delete</a>
                </td>
                <td>
                    <a href="/${script.id}">Details</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>