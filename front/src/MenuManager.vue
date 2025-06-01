<script setup lang="ts">
import ManagerInfo from './components/common/user_info/ManagerInfo.vue';


// function getLoginInfo() {

//     const jwtText: string | null = sessionStorage.getItem("jwtToken");
//     let jwtToken: JwtTokenInterface = new JwtTokenDto();
//     if (jwtText === null) {
//         alert("jwtを取得できませんでした");
//         return;
//     } else {
//         jwtToken = JSON.parse(jwtText);
//     }
//     alert("アクセストークン" + jwtToken.accessToken);
//     alert("アクセストークン有効期限" + jwtToken.expiresAt);


//     const dto = sessionStorage.getItem("userDto");
//     const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
//     if (dto !== null) {
//         userLeastDto.value = JSON.parse(dto);
//     }
//     alert("ユーザ名" + userLeastDto.value.userPersonName);
// }


// function doAnything() {

//     getAuthorizedPromiseArea().then(token => {
//         if (token !== "") {
//             const conditionDto: PostalCodeCapsuleInterface = new PostalCodeCapsuleDto();
//             conditionDto.selectedPostal = 33;
//             conditionDto.isGyouseikuData = true;

//             const url = "http://localhost:6080/postal-search/block";
//             const method = "POST";
//             const body = JSON.stringify(conditionDto);
//             const headers = {
//                 'Accept': 'application/json',
//                 'Content-Type': 'application/json',
//                 'X-AUTH-TOKEN': 'Bearer ' + token
//             };
//             fetch(url, { method, headers, body })
//                 .then(async (response) => {
//                     const resultDto: PostalCodeBlockResultInterface = await response.json();

//                     alert(resultDto.listOptions[0].text);
//                 })
//                 .catch((e) => { alert(e); });
//         } else {
//             alert("エラーのつもり");
//         }
//     });

// }

</script>
<template>
    <!-- ユーザメニュー兼チェック -->
    <ManagerInfo></ManagerInfo>
    <hr>

    <h2>管理者用メニュー</h2>

    <RouterLink to="/partner_manage">関連者管理</RouterLink><br>
    <RouterLink to="/">ログインページ</RouterLink><br>

</template>
<style scoped></style>
