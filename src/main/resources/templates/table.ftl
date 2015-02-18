<thead>
<tr>
    <th data-type="string">Script</th>
    <th>Args</th>
    <th>Status</th>
    <th></th>
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
            <#if script.status??>
                ${script.status}
            </#if>
        </td>
        <td>
            <span class="glyphicon glyphicon-remove"></span>
            <a href="/delete/${script.id}">Delete</a>
        </td>
        <td>
            <a href="/${script.id}">Details</a>
        </td>
        <td>
            <#if script.errorMessage??>
                <p><font color="red">${script.errorMessage}</font></p>
            </#if>
        </td>
    </tr>
</#list>
</tbody>