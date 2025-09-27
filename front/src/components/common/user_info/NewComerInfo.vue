<script setup lang="ts">
import { watch } from 'vue';
import router from '../../../router';
import RoutePathConstants from '../../../routePathConstants';
//props,emit
const props = defineProps<{ registCode: String }>();

//コードチェック
const regiCode: string | null = sessionStorage.getItem("regist-code");
watch(props, () => {
    if (props.registCode !== regiCode) {
        alert("登録コードが異なります。ログインページに戻ります");
        router.push(RoutePathConstants.PAGE_LOGIN);
    }
});

</script>
<template>
    <div style="background-color: palevioletred;padding-left: 0.7%;">
        <div style=" background-color: white;z-index: 2;padding-left: 1.3%;opacity: 1;text-align: left;">
            <div style="padding-right: 2.5%;">
                新規ユーザ登録作業中・・・
            </div>
        </div>
    </div>
</template>
<style scoped></style>
