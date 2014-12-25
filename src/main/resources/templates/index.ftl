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
    <form method="post" action="" name="grub">
        <div class="form-group">
            <label>Select site:</label>
            <select name="site" class="form-control">
                <#list sites as site>
                    <option>${site}</option>
                </#list>
            </select>
        </div>
        <input type="submit" value="Submit" class="btn btn-default">
    </form>
    <hr>

</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>