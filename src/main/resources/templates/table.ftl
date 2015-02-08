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
            <#if script.changed==true>Data changed!!
                <#else>Data don't changed
            </#if>
        </td>
    </tr>
</#list>
</tbody>