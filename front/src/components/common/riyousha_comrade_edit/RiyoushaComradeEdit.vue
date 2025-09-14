<script setup lang="ts">
import { computed, type ComputedRef } from 'vue';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import type RiyoushaComradeInterface from '../../../dto/riyousha/riyoushaComradeDto';

// props,emmits
const props = defineProps<{ editDto: RiyoushaComradeInterface, isEditNew: boolean, userDto: UserPersonLeastInterface }>();
const inputPersonNoDto: ComputedRef<RiyoushaComradeInterface> = computed(() => props.editDto);

</script>
<template>
    <h1>ブランク</h1>
</template>
<style scoped></style>
