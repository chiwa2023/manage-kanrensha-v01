import type PagingFrameworkInterface from "../../../dto/pagingFrameworkDto";
import type SelectOptionNumberInterface from "../../../dto/selectOptionNumberDto";
import SelectOptionNumberDto from "../../../dto/selectOptionNumberDto";

/**
 * ページング機能の選択項目を取得する
 * @param pagingDto ページング機能Dto
 * @returns 選択項目
 */
export default function getPagingOption(pagingDto:PagingFrameworkInterface): SelectOptionNumberInterface[] {

    const list: SelectOptionNumberInterface[] = [];
    const count:number = pagingDto.allCount;
    const limit:number = pagingDto.limit;

    const loop: number = count / limit;
    for (let page = 0; page < loop; page++) {
        list.push(createDto(page, limit, count));
    }

    return list;
}

function createDto(page: number, limit: number, count: number) {

    const dto: SelectOptionNumberInterface = new SelectOptionNumberDto();
    dto.value = page;

    const textEnd: number = (page + 1) * limit;
    let lastNumber = "";
    if (textEnd < count) {
        lastNumber = String(textEnd);
    } else {
        lastNumber = String(count);
    }

    dto.text = page * limit + 1 + "から" + lastNumber;

    return dto;
}