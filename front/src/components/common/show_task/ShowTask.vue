<script setup lang="ts">
import { computed, onBeforeMount, ref, type ComputedRef, type Ref } from 'vue';
import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
import UserRoleConstants from '../../../dto/user/userRoleConstants';
import TaskPlanBaseEntity from '../../../entity/taskPlanBaseEntity';
import { SearchTaskPlanCapsuleDto, type SearchTaskPlanCapsuleDtoInterface } from '../../../dto/task_plan/searchTaskPlanCapsuleDto';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import type SearchTaskPlanResultDtoInterface from '../../../dto/task_plan/searchTaskPlanResultDto';
import SearchTaskPlanResultDto from '../../../dto/task_plan/searchTaskPlanResultDto';
import type SearchTaskHistoryResultDtoInterface from '../../../dto/task_plan/searchTaskHistoryResultDto';
import SearchTaskHistoryResultDto from '../../../dto/task_plan/searchTaskHistoryResultDto';
import DownloadStackTrace from '../download_stack_trace/DownloadStackTrace.vue';
import type TaskPlanBaseEntityInterface from '../../../entity/taskPlanBaseEntity';

// props,emmits
const props = defineProps<{ isSearchCondition: boolean, userDto: UserPersonLeastInterface }>();

// よく使う定数
// const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

const capsuleDto: Ref<SearchTaskPlanCapsuleDtoInterface> = ref(new SearchTaskPlanCapsuleDto());
capsuleDto.value.userDto = props.userDto;

// StackTraceが取得できるのは管理者だけ
const isGetTrace: ComputedRef<boolean> = computed(
    () => props.userDto.listRoles.includes(UserRoleConstants.ROLE_ADMIN));


// ページング
const pageOption: Ref<SelectOptionNumberInterface[]> = ref([]);
function onChangePaging() {

}

// 検索結果リスト
const resultDto: Ref<SearchTaskPlanResultDtoInterface> = ref(new SearchTaskPlanResultDto());
// 履歴用リスト
const resultHistoryDto: Ref<SearchTaskHistoryResultDtoInterface> = ref(new SearchTaskHistoryResultDto());

function onSearch() {
    // TODO タスクの種類を決定したらチェックボックスを設定し
    // タスクの種類のnumber配列に変換

    // ユーザ以外の検索条件は検索ボタンが押されたら更新
    const entity1: TaskPlanBaseEntityInterface = new TaskPlanBaseEntity();
    entity1.taskPlanCode = 124;
    resultDto.value.listTaskPlan.push(entity1);

    const entity2: TaskPlanBaseEntityInterface = new TaskPlanBaseEntity();
    entity2.taskPlanCode = 929;
    resultDto.value.listTaskPlan.push(entity2);

}

function onShowHistory(selectedCode: number) {
    const entityHistory: TaskPlanBaseEntityInterface = new TaskPlanBaseEntity();
    entityHistory.taskPlanCode = selectedCode;
    resultHistoryDto.value.listTaskHistory.push(entityHistory);
}

function getDateTimeText(datetime: Date): string {
    // TODO util化

    // yyyy/mm/dd hh24:mm:ss
    return datetime.toLocaleDateString('jp') + " " + datetime.toLocaleTimeString('jp');
}

// 検索条件を入力しないときは無条件で本日から3日前に実行した最新タスク
onBeforeMount(() => {
    if (!props.isSearchCondition) {
        onSearch();
    }
}
);
</script>
<template>

    <div v-if="isSearchCondition">
        <h3>検索条件入力</h3>

        <div class="left-area">
            検索期間
        </div>
        <div class="right-area">
            <span><input type="date" v-model="capsuleDto.startDateText"></input></span> <span
                class="left-space">から</span>
            <span class="left-space"><input v-model="capsuleDto.endDateText" type="date"></span><span
                class="left-space">まで</span><span></span>
        </div>
        <div class="clear-both"></div>

        <div class="left-area">
            タスク
        </div>
        <div class="right-area">
            タスクの種類複数選択
            <br>
            <input type="texr" v-model="capsuleDto.searchTaskWord" placeholder="タスク名称自由記述"></input>
        </div>
        <div class="clear-both"></div>

        <div class="left-area">
            検索
        </div>
        <div class="right-area">
            <button @click="onSearch">検索</button>
        </div>
        <div class="clear-both"></div>
    </div>

    <h3>検索結果表示</h3>
    <div class="one-line">
        <!-- ページング -->
        <select @change="onChangePaging">
            <option v-for="option in pageOption" :key="option.value" :value="option.value"> {{ option.text
            }}
            </option>
        </select><br>
        <table>
            <tbody>
                <tr>
                    <th>実行年</th>
                    <th>タスクコード</th>
                    <th>タスク名称</th>
                    <th>開始</th>
                    <th>終了</th>
                    <th>途中停止</th>
                    <th v-if="isGetTrace">&nbsp;</th>
                </tr>
                <tr v-for="entity of resultDto.listTaskPlan">
                    <td>{{ entity.tableYear }}</td>
                    <td><button class="left-space" @click="onShowHistory(entity.taskPlanCode)">履歴({{ entity.taskPlanCode
                            }})</button> </td>
                    <td>{{ entity.taskPlanName }}</td>
                    <td>{{ entity.isStart }}<br>{{ getDateTimeText(entity.startDatetime) }}</td>
                    <td>{{ entity.isFinished }}<br><span v-if="!entity.isSuspended"> {{
                        getDateTimeText(entity.endDatetime) }}</span>
                    </td>
                    <td>{{ entity.isSuspended }}<br><span v-if="entity.isSuspended">{{
                        getDateTimeText(entity.endDatetime) }}</span>
                    </td>
                    <td v-if="isGetTrace">
                        <DownloadStackTrace :task-plan-code="entity.taskPlanCode" :task-year="entity.tableYear"
                            :user-dto="props.userDto"></DownloadStackTrace>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="clear-both"><br></div>
    <h3>履歴</h3>
    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>実行年</th>
                    <th>タスクコード</th>
                    <th>タスク名称</th>
                    <th>開始</th>
                    <th>終了</th>
                    <th>途中停止</th>
                </tr>
                <tr v-for="entity of resultHistoryDto.listTaskHistory">
                    <td>{{ entity.tableYear }}</td>
                    <td>{{ entity.taskPlanCode }}</td>
                    <td>{{ entity.taskPlanName }}</td>
                    <td>{{ entity.isStart }}<br>{{ getDateTimeText(entity.startDatetime) }}</td>
                    <td>{{ entity.isFinished }}<br><span v-if="!entity.isSuspended"> {{
                        getDateTimeText(entity.endDatetime) }}</span>
                    </td>
                    <td>{{ entity.isSuspended }}<br><span v-if="entity.isSuspended">{{
                        getDateTimeText(entity.endDatetime) }}</span>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="clear-both"><br></div>

</template>
<style scoped>
table {
    border-style: solid;
    border-width: 1px;
}

td {
    border-style: solid;
    border-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
}
</style>
