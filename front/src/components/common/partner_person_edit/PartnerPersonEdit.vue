<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import InputAddressDto from '../../../dto/Input_address/inputAddressDto';
import ViewInputAddress from '../input_address/ViewInputAddress.vue';
import ViewInputPersonName from '../input_person_name/ViewInputPersonName.vue';
import InputPersonNameInterface from '../../../dto/input_person_name/inputPersonNameDto';
import InputPersonNameDto from '../../../dto/input_person_name/inputPersonNameDto';
import type PersonNoInterface from '../../../dto/partner_person/personNoDto';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type FrameworkResultInterface from '../../../dto/frameworkResultDto';
import router from '../../../router';
import RoutePathConstants from '../../../routePathConstants';
import InputShokugyou from '../input_shokugyou/InputShokugyou.vue';
import type InputShokugyouInterface from '../../../dto/input_shokugyou/inputShokugyouDto';
import InputShokugyouDto from '../../../dto/input_shokugyou/inputShokugyouDto';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserRoleConstants from '../../../dto/user/userRoleConstants';
import InputAccess from '../input_access/InputAccess.vue';
import type SaveKanrenshaPersonCapsuleInterface from '../../../dto/partner_person/saveKanrenshaPersonCapsuleDto';
import SaveKanrenshaPersonCapsuleDto from '../../../dto/partner_person/saveKanrenshaPersonCapsuleDto';

// props,emmits
const props = defineProps<{ editDto: PersonNoInterface, isEditNew: boolean, isCombineUser: boolean, userDto: UserPersonLeastInterface }>();
const inputPersonNoDto: ComputedRef<PersonNoInterface> = computed(() => props.editDto);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// よく使う定数
const BLANK: string = "";
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

/**
 *住所編集受信
 */
function recieveInputAddressInterface(sendDto: InputAddressDto) {
    inputPersonNoDto.value.inputAddressDto = sendDto;
    inputPersonNoDto.value.inputAddressDto.addressAll = sendDto.addressPostal;
}

function resetData() {
    // コードのリセット
    inputPersonNoDto.value.personKanrenshaCode = BLANK;
    // 名前情報のリセット
    inputPersonNoDto.value.inputPersonNameDto = new InputPersonNameDto();
    // 住所情報のリセット   
    inputPersonNoDto.value.inputAddressDto = new InputAddressDto();
    // 職業情報のリセット   
    inputPersonNoDto.value.inputShokugyouDto = new InputShokugyouDto();

}

function recieveInputPersonNameInterface(sendDto: InputPersonNameInterface) {

    inputPersonNoDto.value.inputPersonNameDto = sendDto;
}

/**
 * すでに同じ法人番号で登録されているかチェック
 */
function onCheckAlreadyRegist() {
    if (inputPersonNoDto.value.personKanrenshaCode !== BLANK) {
        alert("現在既存または新規と確定したデータを編集中です");
    } else {
        // 仮で時効の秒数基準で既存だったり新規だったり動作を変更する
        // TOD Back側で同一判定処理ができたら連結する
        const date: Date = new Date();
        if (date.getSeconds() % 2 == 0) {
            alert("新規データでした");
            inputPersonNoDto.value.personKanrenshaCode = "新規";
        } else {
            alert("既存データが存在します。変更が必要な場合はデータ検索からやり直してください");
            inputPersonNoDto.value.personKanrenshaCode = "12-tye12er";
        }
    }
}

/**国籍を確認する */
function nationarityConfirm() {

    // チェックされた対象だけに絞る
    // const list: PersonNoInterface[] = ref([]);
    // list.push(inputPersonNoDto);

    // // API接続時には不要な回答リスト初期処理
    // listInquireAnswer.value.splice(0);

    // // 外部APIに国籍情報問い合わせ
    // // TODO (現在はRelationPersonNoを送付しているが、PersonNoDtoを送付する形に変更)
    // // 国籍問い合わせInquireNationality.vueも編集
    // const url = "http://localhost:7080/inquire-nationarity";
    // const method = "POST";
    // const body = JSON.stringify(list.value);
    // const headers = {
    //     'Accept': 'application/json',
    //     'Content-Type': 'application/json'
    // };
    // fetch(url, { method, headers, body })
    //     .then(async (response) => {
    //         listInquireAnswer.value = await response.json();
    //         listInquireAnswer.value[0];
    //     })
    //     .catch((error) => { alert(error); });

    // 国籍確認mock実装
    switch (parseInt(inputPersonNoDto.value.inputAddressDto.tel3) % 3) {
        case 0:
            alert("日本国籍保持");
            break;

        case 1:
            alert("外国人籍");
            break;

        case 2:
            alert("国籍不明");
            break;

        default:
            break;
    }
}

function onCancel() {
    const role: string = props.userDto.listRoles[0];
    if (UserRoleConstants.ROLE_ADMIN === role) {
        router.push(RoutePathConstants.PAGE_MENU_ADMIN);
        return;
    }
    if (UserRoleConstants.ROLE_MANAGER === role) {
        router.push(RoutePathConstants.PAGE_MENU_MANAGER);
        return;
    }
    router.push(RoutePathConstants.PAGE_MENU_PARTNER);
}

function onSave() {

    // 編集か新規作成かでアクセス先を変えるだけ
    let url = BLANK;
    if (props.isEditNew) {
        url = urlBack + "/add-user/partner-person";
    } else {
        url = urlBack + "/user-kanrensha/edit-person";
    }

    getAuthorizedPromiseArea().then(token => {
        const capsuleDto: Ref<SaveKanrenshaPersonCapsuleInterface> = ref(new SaveKanrenshaPersonCapsuleDto());
        inputPersonNoDto.value.isCombineUser = props.isCombineUser;
        capsuleDto.value.userPersonLeastDto = props.userDto;
        capsuleDto.value.kanrenshaPersonDto = inputPersonNoDto.value;
        if (token !== BLANK) {
            // 保存処理
            const method = "POST";
            const body = JSON.stringify(capsuleDto.value);
            const headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-AUTH-TOKEN': 'Bearer ' + token
            };
            fetch(url, { method, headers, body })
                .then(async (response) => {
                    // 結果を受け取ってメッセージ表示
                    const resultDto: FrameworkResultInterface = await response.json();
                    alert(resultDto.message);
                })
                .catch((e) => { alert(e); });
        } else {
            alert("エラーのつもり");
        }
    });
}

function recieveInputShokugyouInterface(sendDto: InputShokugyouInterface) {
    inputPersonNoDto.value.inputShokugyouDto = sendDto;
}

</script>
<template>

    <h3>収支報告書公開情報</h3>

    <div class="left-area">
        姓名
    </div>
    <div class="right-area">
        <input type="text" v-model="inputPersonNoDto.inputPersonNameDto.allName" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        住所
    </div>
    <div class="right-area">
        <input type="text" v-model="inputPersonNoDto.inputAddressDto.addressAll" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        職業
    </div>
    <div class="right-area">
        <input type="text" disabled="true" v-model="inputPersonNoDto.inputShokugyouDto.allShokugyou" class="max-input">
    </div>
    <div class="clear-both"></div>

    <hr>

    <h3>収支報告書公開入力</h3>

    <div class="left-area">
        (編集→)新規作成
    </div>
    <div class="right-area">
        <button @click="resetData" :disabled="!isEditNew">入力情報のリセット</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        政治資金関連者コード(個人)
    </div>
    <div class="right-area">
        <input type="text" v-model="inputPersonNoDto.personKanrenshaCode" disabled="true"><button class="left-space"
            @click="onCheckAlreadyRegist">重複確認</button>
    </div>
    <div class="clear-both"></div>

    <!-- 姓名入力 -->
    <ViewInputPersonName :edit-dto="inputPersonNoDto.inputPersonNameDto" :is-raise-edit-view="true"
        @send-input-person-name-interface="recieveInputPersonNameInterface"></ViewInputPersonName>

    <!-- 住所入力 -->
    <ViewInputAddress :edit-dto="inputPersonNoDto.inputAddressDto" :is-raise-edit-view="true"
        @send-input-address-interface="recieveInputAddressInterface"></ViewInputAddress>

    <!-- 職業入力 -->
    <InputShokugyou :isfooter="false" :edit-dto="inputPersonNoDto.inputShokugyouDto"
        @send-input-shokugyou-interface="recieveInputShokugyouInterface"></InputShokugyou>
    <hr>

    <h3>編集内容(違反判定情報)</h3>

    <div class="left-area">
        国籍
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="inputPersonNoDto.isForeign">外国人である<span class="left-space"><button
                @click="nationarityConfirm">確認する</button></span>
    </div>
    <div class="clear-both"></div>

    <!-- 連絡先入力 -->
    <InputAccess :edit-dto="editDto.inputAccessDto"></InputAccess>

    <hr>

    <h3>変更履歴</h3>

    <div class="left-area">
        履歴表示
    </div>
    <div class="right-area">
        <button>展開</button>
    </div>
    <div class="clear-both"></div>

    <hr>
    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

</template>

<style scoped></style>
