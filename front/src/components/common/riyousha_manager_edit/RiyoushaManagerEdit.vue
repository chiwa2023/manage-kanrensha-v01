<script setup lang="ts">
import { computed, type ComputedRef } from 'vue';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import type RiyoushaManagerInterface from '../../../dto/riyousha/riyoushaManagerDto';

// props,emmits
const props = defineProps<{ editDto: RiyoushaManagerInterface, isEditNew: boolean, userDto: UserPersonLeastInterface }>();
const inputPersonNoDto: ComputedRef<RiyoushaManagerInterface> = computed(() => props.editDto);

</script>
<template>
    <h1>ブランク</h1>
</template>
<style scoped></style>
