<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import ReadCsv from '../../common/read_csv/ReadCsv.vue';
import EditWkTblMinPoliOrg from '../../common/wktbl_edit_min/EditWkTblMinPoliOrg.vue';
import EditWkTblStdPoliOrg from '../../common/wktbl_edit_std/EditWkTblStdPoliOrg.vue';
import type StorageFileInterface from '../../../dto/storage_file/storageFileDto';
import type RegistDataByCsvFileCapsuleInterface from '../../../dto/storage_file/registDataByCsvFileCapsuleDto';
import RegistDataByCsvFileCapsuleDto from '../../../dto/storage_file/registDataByCsvFileCapsuleDto';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type FrameworkMessageAndResultInterface from '../../../dto/frameworkMessageAndResultDto';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import type RetryWktblBatchCapsuleInterface from '../../../dto/add_xml/retryWktblBatchCapsuleDto';
import RetryWktblBatchCapsuleDto from '../../../dto/add_xml/retryWktblBatchCapsuleDto';


// サンプル表示
const templateViewButtonText: ComputedRef<String> = computed(() => isVisibleTemplate.value ? "CSVサンプルを隠す" : "CSVサンプルを表示する");
const isVisibleTemplate: Ref<boolean> = ref(false);
function viewSample() {
    isVisibleTemplate.value = !isVisibleTemplate.value;
}

// 初期表示データフォーマットは最小
const formatMin: string = "min";
const formatStd: string = "std";
const isVisibleFormat: Ref<string> = ref(formatMin);

// ファイルからバッチ起動条件
const capsuleDto: Ref<RegistDataByCsvFileCapsuleInterface> = ref(new RegistDataByCsvFileCapsuleDto());
const sessionStorage = window["sessionStorage"];
const userDtoText: string | null = sessionStorage.getItem("userDto");
const userDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
if (userDtoText !== null) {
    userDto.value = JSON.parse(userDtoText);
}
capsuleDto.value.userPersonLeastDto = userDto.value;

// 再処理起動条件(ユーザ)
const retryCapsuleDto:Ref<RetryWktblBatchCapsuleInterface> = ref(new RetryWktblBatchCapsuleDto());
retryCapsuleDto.value.userDto = userDto.value;

// ファイル保全情報受信
function recieveStorageFileInterface(storageFileDto: StorageFileInterface) {
    capsuleDto.value.storageFileDto = storageFileDto;
}

function onCancel() {
    alert("キャンセル");
    history.back();
}

let url = "";
function onSave() {
    getAuthorizedPromiseArea().then(token => {
        if (isVisibleFormat.value === formatMin) {
            url = "http://localhost:6080/regist-bulk-master-min/retry-poli-org";
        }
        if (isVisibleFormat.value === formatStd) {
            url = "http://localhost:6080/regist-bulk-master-std/retry-poli-org";
        }

        const method = "POST";
        const body = JSON.stringify(retryCapsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto:FrameworkMessageAndResultInterface = await response.json();
                alert(resultDto.message);
            })
            .catch((error) => { alert(error); });
    });
}

function onBatchByFile() {
    getAuthorizedPromiseArea().then(token => {
        // 最小と標準で接続先切り替え(起動条件のパラメータ内容は変わらない)
        if (isVisibleFormat.value === formatMin) {
            url = "http://localhost:6080/regist-bulk-master-min/execute-poli-org";
        }
        if (isVisibleFormat.value === formatStd) {
            url = "http://localhost:6080/regist-bulk-master-std/execute-poli-org";
        }
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
</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo></ManagerInfo>

    <h1>関連者政治団体マスタ一括登録</h1>

    <div class="left-area">
        データフォーマット(最小／標準)
    </div>
    <div class="right-area">
        <span><input type="radio" v-model="isVisibleFormat" :value="formatMin" id="dataFormat">最小</span>
        <span class="left-space"><input type="radio" v-model="isVisibleFormat" :value="formatStd"
                id="dataFormat">標準</span>
    </div>
    <div class="clear-both"></div>

    <!-- csv読み出し10行 -->
    <ReadCsv @send-storage-file-interface="recieveStorageFileInterface"></ReadCsv>

    <div class="one-line">
        <button @click="onBatchByFile">頭出ししたcsvファイルで一括処理</button>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line" v-if="isVisibleFormat === formatMin">
        <button @click="viewSample">{{ templateViewButtonText }}</button>
        <div v-if="isVisibleTemplate">
            ヘッダ必須。1行目は読み飛ばすので、ないと1行目が登録されません<br>
            最初の1列は不要です。(ファイル内は4列)
            <table>
                <tbody>
                    <tr>
                        <th class="hojo">要件</th>
                        <th>政治団体名称</th>
                        <th>政治団体住所</th>
                        <th>政治団体代表者</th>
                        <th>団体区分</th>
                    </tr>
                    <tr>
                        <th class="hojo">説明</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須<br>政党:01、政党支部:02、政治資金団体:03<br>18条2項1規定団体:04、その他の政治団体:05、その他の政治団体支部:06
                        </th>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>ちゃらんぽらん政治団体</td>
                        <td>和歌山県実在市山麓町</td>
                        <td>代表者　太郎</td>
                        <td>05</td>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>いいかげん政治団体　架空市支部</td>
                        <td>宮崎県架空市湖畔町</td>
                        <td>代表者　花子</td>
                        <td>06</td>
                    </tr>
                </tbody>
            </table>
            <a href="sample_csv/sample_bulk_master_min_poli_org.csv">上記内容サンプルcsvをダウンロード</a>
        </div>
    </div>
    <!-- 登録結果と編集 -->
    <EditWkTblMinPoliOrg v-if="isVisibleFormat === formatMin"></EditWkTblMinPoliOrg>

    <h3 v-if="isVisibleFormat !== formatMin">標準フォーマット</h3>
    <div class="one-line" v-if="isVisibleFormat !== formatMin">
        <button @click="viewSample">{{ templateViewButtonText }}</button>
        <div v-if="isVisibleTemplate">
            ヘッダ必須。1行目は読み飛ばすので、ないと1行目が登録されません<br>
            最初の1列は不要です。(ファイル内は24列)
            <table class="std">
                <tbody>
                    <tr>
                        <th class="hojo">要件</th>
                        <th>政治団体名称</th>
                        <th>全住所</th>
                        <th>団体代表者名</th>
                        <th>政治団体区分</th>
                        <th>住所郵便番号まで</th>
                        <th>住所番地まで</th>
                        <th>住所建物まで</th>
                        <th>郵便番号1</th>
                        <th>郵便番号2</th>
                        <th>電話番号市外局番</th>
                        <th>電話番号局番</th>
                        <th>電話番号番号</th>
                        <th>メールアドレス</th>
                        <th>自分の公式サイト</th>
                        <th>SNS名称</th>
                        <th>SNSアカウント</th>
                        <th>関連者団体名称かな</th>
                        <th>団体代表者関連者コード</th>
                        <th>会計責任者関連者個人コード</th>
                        <th>会計責任者関連者個人氏名</th>
                        <th>地方公共団体コード</th>
                        <th>町字Id</th>
                        <th>街区Id</th>
                        <th>住居Id</th>
                        <th>住居2Id</th>
                    </tr>
                    <tr>
                        <th class="hojo">説明</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須<br>政党:01、政党支部:02、政治資金団体:03<br>18条2項1規定団体:04、その他の政治団体:05、その他の政治団体支部:06
                        </th>
                        <th class="explain">必須</th>
                        <th class="explain">必須</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意<br>5文字まで</th>
                        <th class="explain">任意<br>5文字まで</th>
                        <th class="explain">必須<br>9文字まで</th>
                        <th class="explain">必須<br>9文字まで</th>
                        <th class="explain">必須<br>9文字まで</th>
                        <th class="explain">必須</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意<br>7文字まで</th>
                        <th class="explain">任意<br>6文字まで</th>
                        <th class="explain">任意<br>8文字まで</th>
                        <th class="explain">任意<br>4文字まで</th>
                        <th class="explain">任意<br>4文字まで</th>
                        <th class="explain">任意<br>6文字まで</th>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>ちゃらんぽらん政治団体</td>
                        <td>和歌山県実在市山麓町</td>
                        <td>代表者　太郎</td>
                        <td>05</td>
                        <td>和歌山県実在市山麓町</td>
                        <td>100番地3</td>
                        <td>三角ビル2F</td>
                        <td>012</td>
                        <td>3456</td>
                        <td>098</td>
                        <td>7654</td>
                        <td>3210</td>
                        <td>info@charan-poran.com</td>
                        <td>https://charan-poran.com</td>
                        <td>弱小SNS</td>
                        <td>@charan-poran</td>
                        <td>ちゃらんぽらんせいじだんたい</td>
                        <td>12-34AB-56cd</td>
                        <td>98-76EF-54gh</td>
                        <td>会計責任者　花子</td>
                        <td>0987654</td>
                        <td>111</td>
                        <td>222</td>
                        <td>333</td>
                        <td>44</td>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>いいかげん政党</td>
                        <td>宮崎県架空市湖畔町</td>
                        <td>寄付上限　花子</td>
                        <td>01</td>
                        <td>宮崎県架空市湖畔町</td>
                        <td>2丁目4番地9</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>098</td>
                        <td>7654</td>
                        <td>3210</td>
                        <td>info@iikagen.net</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
            <a href="sample_csv/sample_bulk_master_std_poli_org.csv">上記内容サンプルcsvをダウンロード</a><br>
        </div>
    </div>
    <!-- 登録結果と編集 -->
    <EditWkTblStdPoliOrg v-if="isVisibleFormat !== formatMin"></EditWkTblStdPoliOrg>


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
    width: calc(200px * 24);
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
