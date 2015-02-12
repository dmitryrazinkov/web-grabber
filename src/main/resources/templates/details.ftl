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

    <h2 align="center">Details</h2>
    <table class="table table-striped" id="table">
        <thead>
        <tr>

            <th>Date</th>
            <th>Script result</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list resultList as result>

            <tr>
                <td>${result.date}</td>
                <td>${result.stringScriptOutput.stringResult}</td>
                <td><p><font color="red">${result.stringScriptOutput.errorMessage}</font></p></td>
            </tr>

        </#list>
        </tbody>
    </table>

    <div>
        <a href="/" class="btn btn-default" role="button"> Back</a>
    </div>


</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>