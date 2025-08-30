<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import InputAddressDto from '../../../dto/Input_address/inputAddressDto';
import ViewInputAddress from '../input_address/ViewInputAddress.vue';
import ViewInputPersonName from '../input_person_name/ViewInputPersonName.vue';
import InputPersonNameInterface from '../../../dto/input_person_name/inputPersonNameDto';
import InputPersonNameDto from '../../../dto/input_person_name/inputPersonNameDto';
import type PersonNoInterface from '../../../dto/partner_person/personNoDto';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type FrameworkCapsuleInterface from '../../../dto/frameworkCapsuleDto';
import FrameworkCapsuleDto from '../../../dto/frameworkCapsuleDto';
import type FrameworkResultInterface from '../../../dto/frameworkResultDto';
import router from '../../../router';
import RoutePathConstants from '../../../routePathConstants';
import InputShokugyou from '../input_shokugyou/InputShokugyou.vue';
import type InputShokugyouInterface from '../../../dto/input_shokugyou/inputShokugyouDto';
import InputShokugyouDto from '../../../dto/input_shokugyou/inputShokugyouDto';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserRoleConstants from '../../../dto/user/userRoleConstants';

// props,emmits
const props = defineProps<{ editDto: PersonNoInterface, isEditNew: boolean, userDto: UserPersonLeastInterface }>();
const inputPersonNoDto: ComputedRef<PersonNoInterface> = computed(() => props.editDto);

const BLANK: string = "";

/**
 *住所編集受信
 */
function recieveInputAddressInterface(sendDto: InputAddressDto) {
    inputPersonNoDto.value.inputAddress = sendDto;
}


function resetData() {
    // コードのリセット
    inputPersonNoDto.value.personNo = BLANK;
    // 名前情報のリセット
    inputPersonNoDto.value.inputName = new InputPersonNameDto();
    // 住所情報のリセット   
    inputPersonNoDto.value.inputAddress = new InputAddressDto();
    // 職業情報のリセット   
    inputPersonNoDto.value.allShokugyou = BLANK;
    inputPersonNoDto.value.inputShokugyou = new InputShokugyouDto();

}

function recieveInputPersonNameInterface(sendDto: InputPersonNameInterface) {

    inputPersonNoDto.value.inputName = sendDto;
}

/**
 * すでに同じ法人番号で登録されているかチェック
 */
function onCheckAlreadyRegist() {
    if (inputPersonNoDto.value.personNo !== BLANK) {
        alert("現在既存または新規と確定したデータを編集中です");
    } else {
        // 仮で時効の秒数基準で既存だったり新規だったり動作を変更する
        // TOD Back側で同一判定処理ができたら連結する
        const date: Date = new Date();
        if (date.getSeconds() % 2 == 0) {
            alert("新規データでした");
            inputPersonNoDto.value.personNo = "新規";
        } else {
            alert("既存データが存在します。変更が必要な場合はデータ検索からやり直してください");
            inputPersonNoDto.value.personNo = "12-tye12er";
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
    switch (parseInt(inputPersonNoDto.value.inputAddress.tel3) % 3) {
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
    getAuthorizedPromiseArea().then(token => {
        const capsuleDto: Ref<FrameworkCapsuleInterface> = ref(new FrameworkCapsuleDto());
        capsuleDto.value.userPersonLeastDto = props.userDto;
        if (token !== "") {
            // パスワード更新
            const url = "http://localhost:6080/add-user/partner-person";
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
    inputPersonNoDto.value.inputShokugyou = sendDto;
    inputPersonNoDto.value.allShokugyou = sendDto.allShokugyou;
}

</script>
<template>

    <h3>収支報告書公開情報</h3>

    <div class="left-area">
        姓名
    </div>
    <div class="right-area">
        <input type="text" v-model="inputPersonNoDto.inputName.allName" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        住所
    </div>
    <div class="right-area">
        <input type="text" v-model="inputPersonNoDto.inputAddress.addressPostal" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        職業
    </div>
    <div class="right-area">
        <input type="text" disabled="true" v-model="inputPersonNoDto.inputShokugyou.allShokugyou" class="max-input">
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
        <input type="text" v-model="inputPersonNoDto.personNo" disabled="true"><button class="left-space"
            @click="onCheckAlreadyRegist">重複確認</button>
    </div>
    <div class="clear-both"></div>

    <!-- 姓名入力 -->
    <ViewInputPersonName :edit-dto="inputPersonNoDto.inputName" :is-raise-edit-view="true"
        @send-input-person-name-interface="recieveInputPersonNameInterface"></ViewInputPersonName>

    <!-- 住所入力 -->
    <ViewInputAddress :edit-dto="inputPersonNoDto.inputAddress" :is-raise-edit-view="true"
        @send-input-address-interface="recieveInputAddressInterface"></ViewInputAddress>

    <!-- 職業入力 -->
    <InputShokugyou :isfooter="false" :edit-dto="inputPersonNoDto.inputShokugyou"
        @send-input-shokugyou-interface="recieveInputShokugyouInterface"></InputShokugyou>

    <hr>

    <h3>連絡先(情報確認のため使用、非公開)</h3>
    <div class="left-area">
        メールアドレス
    </div>
    <div class="right-area">
        <input type="email">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        SNSアカウント
    </div>
    <div class="right-area">
        <input type="email">
    </div>
    <div class="clear-both"></div>

    <h3>編集内容(違反判定情報)</h3>

    <div class="left-area">
        国籍
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="inputPersonNoDto.isGaikoujin" disabled="true">外国人である<span
            class="left-space"><button @click="nationarityConfirm">確認する</button></span>
    </div>
    <div class="clear-both"></div>

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
