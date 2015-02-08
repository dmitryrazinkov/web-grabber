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