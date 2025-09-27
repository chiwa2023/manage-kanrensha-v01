<script setup lang="ts">
import { ref, type Ref } from 'vue';
import UploadContentCapsuleInterface from '../../../dto/storage_file/uploadContentCapsuleDto';
import UploadContentCapsuleDto from '../../../dto/storage_file/uploadContentCapsuleDto';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type LookAheadPublishXmlResultInterface from '../../../dto/storage_file/lookAheadPublishXmlResultDto';
import LookAheadPublishXmlResultDto from '../../../dto/storage_file/lookAheadPublishXmlResultDto';

//props,emit
const emits = defineEmits(["sendStorageFileInterface"]);

// 文字コード
const capsuleDto: Ref<UploadContentCapsuleInterface> = ref(new UploadContentCapsuleDto());
capsuleDto.value.uploadFileDto.charset = "UTF-8";
const sessionStorage = window["sessionStorage"];
const userDtoText: string | null = sessionStorage.getItem("userDto");
if (userDtoText !== null) {
    capsuleDto.value.userDto = JSON.parse(userDtoText);
}

//ファイル指定ダイアログ
const selectFileInput: Ref<HTMLInputElement | undefined> = ref<HTMLInputElement>();

// 処理結果
const resultDto: Ref<LookAheadPublishXmlResultInterface> = ref(new LookAheadPublishXmlResultDto());

/**
* ファイル選択ダイアログを表示する
*/
function onReadButton() {
    if (selectFileInput.value !== undefined) {
        selectFileInput.value.click();
    }
}


/**
 * ファイル選択ダイアログを表示する
 */
function readXmlFile() {
    if (selectFileInput.value !== null) {
        if (selectFileInput.value !== undefined) {
            if (selectFileInput.value.files !== null) {
                const file: File = selectFileInput.value.files[0];
                capsuleDto.value.uploadFileDto.fileName = file.name;
                // ファイルをバイト取得
                const reader: FileReader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = async () => {
                    if (reader.result !== null) {
                        capsuleDto.value.uploadFileDto.fileContent = String(reader.result);
                        getAuthorizedPromiseArea().then(token => {
                            const url = "http://localhost:6080/xml/look-ahead";
                            const method = "POST";
                            const body = JSON.stringify(capsuleDto.value);
                            const headers = {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json',
                                'X-AUTH-TOKEN': 'Bearer ' + token
                            };
                            fetch(url, { method, headers, body })
                                .then(async (response) => {
                                    if (response.status < 400) {
                                        resultDto.value = await response.json();
                                        if (response.status === 200) {
                                            //保存したファイル情報は親に渡す
                                            emits("sendStorageFileInterface", resultDto.value.storageFileDto);
                                        }
                                        alert(resultDto.value.message);

                                    }
                                })
                                .catch((error) => { alert(error); });
                        });
                    }
                }
            } else {
                alert("ユーザが取れない");
            }
        }
    }
}

</script>
<template>
    <h3>公式XMLファイル選択</h3>
    <div class="left-area">
        読取りファイルの指定<br>
    </div>
    <div class="right-area">
        <input ref="selectFileInput" type="file" accept=".xml" @change="readXmlFile" style="display:none;">
        <button @click="onReadButton">ファイルを指定して読み取り</button>
    </div>
    <div class="clear-both"><br></div>
    <div class="left-area">
        XMLの種類
    </div>
    <div class="right-area">
        {{ resultDto.app }}
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        アプリバージョン
    </div>
    <div class="right-area">
        {{ resultDto.version }}
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        政治団体<br>
    </div>
    <div class="right-area">
        {{ resultDto.dantaiName }}({{ resultDto.houkokuNen }})
    </div>
    <div class="clear-both"></div>



</template>
<style scoped></style>
