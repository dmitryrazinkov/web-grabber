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
                <select id="select" name="site1" class="form-control" onchange="change(this)">
                    <#list sites as site1>
                        <option>${site1.getName()}</option>
                    </#list>
                </select>
                <script src="/assets/js/select-script.js">
                </script>
            </div>
            <div>
                <textarea id="area" name="args" class="form-control"></textarea>
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
            <th>Status</th>
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
                <td>
                    <#if script.changed==true>Data changed!!<#else>Data don't changed</#if>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="/assets/bootstrap/js/bootstrap.min.js"></script>
<script src="/assets/autosize-master/jquery.autosize.min.js"></script>
<script>
    $('#area').autosize();
</script>
<script src="/assets/js/reload.js"></script>
</body>
</html>