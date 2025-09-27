import type RiyoushaComradeInterface from '../../entity/riyoushaComradeEntity';
import type RiyoushaManagerInterface from '../../entity/riyoushaManagerEntity';
import type RiyoushaAdminInterface from '../../entity/riyoushaAdminEntity';


export default interface SearchRiyoushaResultInterface {

}



export default class SearchRiyoushaResultDto implements SearchRiyoushaResultInterface {

    /** APIユーザリスト */
    listComrade: RiyoushaComradeInterface[];
    /** 運営者リスト */
    listManager: RiyoushaManagerInterface[];
    /** 管理者リスト */
    listAdmin: RiyoushaAdminInterface[];

    constructor() {
        this.listAdmin = [];
        this.listComrade = [];
        this.listManager = [];
    }
}