<script setup lang="ts">
import InputAddressDto from '../dto/Input_address/inputAddressDto';
import sendInputAddressInterface from '../dto/Input_address/inputAddressDto';
import InputAddress from './common/input_address/InputAddress.vue';
import ViewInputAddress from './common/input_address/ViewInputAddress.vue';
import PartnerPersonEdit from './common/partner_person_edit/PartnerPersonEdit.vue';
import PartnerPoliOrgEdit from './common/partner_poli_org_edit/PartnerPoliOrgEdit.vue';
import SearchCorpNo from './common/search_houjin_no/SearchHoujinNo.vue';
import PartnerCorpEdit from './common/partner_corp_edit/PartnerCorpEdit.vue';
import InputPersonName from './common/input_person_name/InputPersonName.vue';
import ViewInputPersonName from './common/input_person_name/ViewInputPersonName.vue';


const editDto:sendInputAddressInterface = new InputAddressDto();
</script>
<template>
    <h1>コンポーネントをページと関係なく作成するための台紙</h1>

    <!-- 
    <SearchCorpNo></SearchCorpNo>
    <PartnerCorpEdit></PartnerCorpEdit>
    <PartnerPoliOrgEdit></PartnerPoliOrgEdit>
    <ViewInputPersonName></ViewInputPersonName>

    -->

    <PartnerPersonEdit></PartnerPersonEdit>

</template>
<style scoped></style>
