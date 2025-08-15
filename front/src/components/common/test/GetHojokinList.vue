<script setup lang="ts">
import getMockCountoryHojokinList from './mock/getMockCountoryHojokinList';

//仮
const jsonText: string = getMockCountoryHojokinList();
const data = JSON.parse(jsonText);

</script>
<template>
    <h1>補助金リスト</h1>

    <hr>
    {{ data.id }}<br>
    {{ data.message }}<br>
    <hr>
    法人番号:{{ data["hojin-infos"][0].corporate_number }}<br>
    法人名かな:{{ data["hojin-infos"][0].kana }}<br>
    法人名:{{ data["hojin-infos"][0].name }}<br>
    法人名所在地:{{ data["hojin-infos"][0].location }}<br>
    情報更新日:{{ data["hojin-infos"][0].update_date }}<br>
    <hr>
    <table>
        <tbody>
            <tr>
                <th>承認日</th>
                <th>補助金名</th>
                <th>金額</th>
                <th>発行元</th>
                <th>補助金の目的</th>
            </tr>
            <tr>
                <td>{{ data["hojin-infos"][0].subsidy[0].date_of_approval }}</td>
                <td>{{ data["hojin-infos"][0].subsidy[0].title }}</td>
                <td>{{ data["hojin-infos"][0].subsidy[0].amount }}</td>
                <td>{{ data["hojin-infos"][0].subsidy[0].government_departments }}</td>
                <td><select> <option>調査・研究</option><option>災害</option></select></td>
            </tr>
            <tr>
                <td>{{ data["hojin-infos"][0].subsidy[1].date_of_approval }}</td>
                <td>{{ data["hojin-infos"][0].subsidy[1].title }}</td>
                <td>{{ data["hojin-infos"][0].subsidy[1].amount }}</td>
                <td>{{ data["hojin-infos"][0].subsidy[1].government_departments }}</td>
                <td><select> <option>調査・研究</option><option>災害</option></select></td>
            </tr>

        </tbody>
    </table>
    <hr>

</template>
<style scoped>
table {
    border-style: solid;
    border-width: 1px;
}

td {
    border-style: solid;
    border-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
}
</style>
