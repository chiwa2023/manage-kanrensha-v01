<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import ReadCsv from '../../common/read_csv/ReadCsv.vue';
import EditWkTblCombineOrg from '../../common/wktbl_combine/EditWkTblCombineOrg.vue';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import type StorageFileInterface from '../../../dto/storage_file/storageFileDto';
import type RegistDataByCsvFileCapsuleInterface from '../../../dto/storage_file/registDataByCsvFileCapsuleDto';
import RegistDataByCsvFileCapsuleDto from '../../../dto/storage_file/registDataByCsvFileCapsuleDto';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type FrameworkMessageAndResultInterface from '../../../dto/frameworkMessageAndResultDto';
import StorageFileDto from '../../../dto/storage_file/storageFileDto';
import type RetryWktblBatchCapsuleInterface from '../../../dto/add_xml/retryWktblBatchCapsuleDto';
import RetryWktblBatchCapsuleDto from '../../../dto/add_xml/retryWktblBatchCapsuleDto';
import RoutePathConstants from '../../../routePathConstants';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// サンプル表示
const templateViewButtonText: ComputedRef<String> = computed(() => isVisibleTemplate.value ? "CSVサンプルを隠す" : "CSVサンプルを表示する");
const isVisibleTemplate: Ref<boolean> = ref(false);
function viewSample() {
    isVisibleTemplate.value = !isVisibleTemplate.value;
}
const capsuleDto: Ref<RegistDataByCsvFileCapsuleInterface> = ref(new RegistDataByCsvFileCapsuleDto());
const sessionStorage = window["sessionStorage"];
const userDtoText: string | null = sessionStorage.getItem("userDto");
const userDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
if (userDtoText !== null) {
    userDto.value = JSON.parse(userDtoText);
}
capsuleDto.value.userPersonLeastDto = userDto.value;

// 再処理起動条件(ユーザ)
const retryCapsuleDto: Ref<RetryWktblBatchCapsuleInterface> = ref(new RetryWktblBatchCapsuleDto());
retryCapsuleDto.value.userDto = userDto.value;


function onCancel() {
    alert("キャンセル");
    history.back();
}
function onSave() {
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-combine/retry";
        const method = "POST";
        const body = JSON.stringify(retryCapsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: FrameworkMessageAndResultInterface = await response.json();
                alert(resultDto.message);
                // 処理が成功したら再登録できないようにアップロードファイル情報を初期化
                if (response.status === 200) {
                    capsuleDto.value.storageFileDto = new StorageFileDto();
                }
            })
            .catch((error) => { alert(error); });
    });
}

function onBatchByFile() {
    getAuthorizedPromiseArea().then(token => {

        const url = urlBack + "/regist-combine/execute-corp";
        const method = "POST";
        const body = JSON.stringify(capsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: FrameworkMessageAndResultInterface = await response.json();
                alert(resultDto.message);
            })
            .catch((error) => { alert(error); });
    });
}

// ファイル保全情報受信
function recieveStorageFileInterface(storageFileDto: StorageFileInterface) {
    capsuleDto.value.storageFileDto = storageFileDto;
}

</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo></ManagerInfo>

    <h1>関連者個人－企業／団体紐づけ登録</h1>

    <!-- csv読み出し10行 -->
    <ReadCsv @send-storage-file-interface="recieveStorageFileInterface"></ReadCsv>

    <div class="one-line">
        <button @click="onBatchByFile">頭出ししたcsvファイルで一括処理</button>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        <button @click="viewSample">{{ templateViewButtonText }}</button>
        <div v-if="isVisibleTemplate">
            ヘッダ必須。1行目は読み飛ばすので、ないと1行目が登録されません<br>
            最初の1列は不要です。(ファイル内は4列)
            <table>
                <tbody>
                    <tr>
                        <th class="hojo">要件</th>
                        <th>個人関連者コード</th>
                        <th>個人名称</th>
                        <th>企業・団体関連者コード</th>
                        <th>企業・団体名称</th>
                        <th>紐づけ開始年</th>
                        <th>紐づけ終了年</th>
                    </tr>
                    <tr>
                        <th class="hojo">説明</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>12-34567-8901-2345-67890</td>
                        <td>迂回献金　太郎</td>
                        <td>1-2345-67-890123-4567890</td>
                        <td>ふんだくり企業</td>
                        <td>2021</td>
                        <td>2024</td>
                    </tr>
                </tbody>
            </table>
            <a href="sample_csv/sample_combine_corp.csv">上記内容サンプルcsvをダウンロード</a>
        </div>
        <!-- 登録結果と編集 -->
        <EditWkTblCombineOrg org-type="corp"></EditWkTblCombineOrg>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>
</template>
<style scoped>
:root {
    --cell_width: 200 px;
}

table {
    border-style: solid;
    border-width: 1px;
}

table.std {
    border-style: solid;
    border-width: 1px;
    width: calc(200px * 26);
}

td {
    border-style: solid;
    border-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
}

th.hojo {
    background-color: lightgray;
    width: --cell_width px;
}

th.explain {
    background-color: lightcyan;
    width: --cell_width px;
}
</style>
