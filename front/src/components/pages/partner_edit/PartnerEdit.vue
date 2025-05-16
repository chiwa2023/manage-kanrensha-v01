<script setup lang="ts">
import { ref, type Ref } from 'vue';
import PartnerCorpEdit from '../../common/partner_corp_edit/PartnerCorpEdit.vue';
import PartnerPersonEdit from '../../common/partner_person_edit/PartnerPersonEdit.vue';
import PartnerPoliOrgEdit from '../../common/partner_poli_org_edit/PartnerPoliOrgEdit.vue';
import type PersonNoInterface from '../../../dto/partner_person/personNoDto';
import PersonNoDto from '../../../dto/partner_person/personNoDto';
import type PoliOrgNoInterface from '../../../dto/partner_poli_org/poliOrgNoDto';
import PoliOrgNoDto from '../../../dto/partner_poli_org/poliOrgNoDto';
import mockGetPersonList from '../../common/search_person_no/mock/mockGetPersonList';
import mockGetPoliOrgNoList from '../../common/search_poli_org/mock/mockGetPoliOrgNoList';
import CorpNoInterface from '../../../dto/partner_corp/corpNoDto';
import CorpNoDto from '../../../dto/partner_corp/corpNoDto';
import mockGetCorpList from '../../common/search_corp_no/mock/mockGetCorpList';

// ログインユーザの属性により、どのデータを作成するか決定されている
// 個人の想定
const viewStatus: number = 1;

const inputPersonDto: Ref<PersonNoInterface> = ref(new PersonNoDto());
const inputPoliOrgDto: Ref<PoliOrgNoInterface> = ref(new PoliOrgNoDto());
const inputCorpNoDto: Ref<CorpNoInterface> = ref(new CorpNoDto());

switch (viewStatus) {
    case 1:
        inputPersonDto.value = mockGetPersonList()[0];
        break;
    case 2:
        inputCorpNoDto.value = mockGetCorpList()[0];
        break;
    case 3:
        inputPoliOrgDto.value = mockGetPoliOrgNoList()[0];
        break;
    default:
        break;
}

</script>
<template>
    <h1>関連者編集</h1>
    <!-- 編集対象が法人／個人 -->
    <div v-if="viewStatus == 1">
        <PartnerPersonEdit :edit-dto="inputPersonDto" :is-edit-new="false"></PartnerPersonEdit>
    </div>
    <!-- 編集対象が法人／団体 -->
    <div v-if="viewStatus == 2">
        <PartnerCorpEdit :edit-dto="inputCorpNoDto" :is-edit-new="false"></PartnerCorpEdit>
    </div>
    <!-- 編集対象が政治団体 -->
    <div v-if="viewStatus == 3">
        <PartnerPoliOrgEdit :edit-dto="inputPoliOrgDto" :is-edit-new="false"></PartnerPoliOrgEdit>
    </div>
</template>
<style scoped></style>
