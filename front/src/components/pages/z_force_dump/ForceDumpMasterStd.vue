<script setup lang="ts">
import { ref, watch, type Ref } from 'vue';
import type ForceDumpCapsuleInterface from '../../../dto/z_force_dump/forceDumpCapsuleDto';
import ForceDumpCapsuleDto from '../../../dto/z_force_dump/forceDumpCapsuleDto';
import type FrameworkResultInterface from '../../../dto/frameworkResultDto';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';

// 実行条件
const capsuleDto: Ref<ForceDumpCapsuleInterface> = ref(new ForceDumpCapsuleDto());

const isExecuteAll: Ref<boolean> = ref(true);

const isDisabledCorp: Ref<boolean> = ref(true);
const isDisabledPerson: Ref<boolean> = ref(true);
const isDisabledPoliOrg: Ref<boolean> = ref(true);
const now: Date = new Date();
const limitDate: Ref<string> = ref(now.getFullYear() + "-01-01");

// 関連者選択制御
watch(isExecuteAll, () => {
    if (isExecuteAll.value) {
        capsuleDto.value.isExecuteCorp = true;
        capsuleDto.value.isExecutePerson = true;
        capsuleDto.value.isExecutePoliOrg = true;
        isDisabledCorp.value = true;
        isDisabledPerson.value = true;
        isDisabledPoliOrg.value = true;
    } else {
        isDisabledCorp.value = false;
        isDisabledPerson.value = false;
        isDisabledPoliOrg.value = false;
    }
});

function onCancel() {
    alert("キャンセル");
    history.back();
}
function onSave() {
    getAuthorizedPromiseArea().then(token => {
        // 処理条件再設定なしでそのまま
        const url = "http://localhost:6080/dump-master-std/execute";
        const method = "POST";
        const body = JSON.stringify(capsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: FrameworkResultInterface = await response.json();
                alert(resultDto.message);
            })
            .catch((error) => { alert(error); });
    });

}
</script>
<template>
    <h1>関連者マスタ強制csvダンプ</h1>
    <div class="left-area">
        実施全選択
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="isExecuteAll">全選択
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        関連者個別選択
    </div>
    <div class="right-area">
        <span class="left-space"><input type="checkbox" v-model="capsuleDto.isExecuteCorp"
                :disabled=isDisabledCorp>関連者企業・団体</span>
        <span class="left-space"><input type="checkbox" v-model="capsuleDto.isExecutePerson"
                :disabled=isDisabledPerson>関連者個人</span>
        <span class="left-space"><input type="checkbox" v-model="capsuleDto.isExecutePoliOrg"
                :disabled=isDisabledPoliOrg>関連者政治団体</span>
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        指定期間
    </div>
    <div class="right-area">
        <input type="date" v-model="limitDate" /><span class="left-space">まで</span>
    </div>
    <div class="clear-both"></div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>
</template>
<style scoped></style>
