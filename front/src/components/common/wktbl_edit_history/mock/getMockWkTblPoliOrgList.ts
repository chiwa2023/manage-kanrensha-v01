import type WkTblPartnerPoliOrgHistoryInterface from "../../../../entity/wkTblPartnerPoliOrgHistoryEntity";
import WkTblPartnerPoliOrgHistoryEntity from "../../../../entity/wkTblPartnerPoliOrgHistoryEntity";

export default function getMockWkTblPoliOrgList(): WkTblPartnerPoliOrgHistoryInterface[] {
    const list: WkTblPartnerPoliOrgHistoryInterface[] = [];
    list.push(createEntity(1));
    list.push(createEntity(2));
    list.push(createEntity(3));
    list.push(createEntity(4));

    return list;
}

function createEntity(index: number): WkTblPartnerPoliOrgHistoryInterface {

    const entity: WkTblPartnerPoliOrgHistoryInterface = new WkTblPartnerPoliOrgHistoryEntity();
    entity.wkPartnerPoliOrgHistoryId = index;
    entity.isLatest = true;

    entity.partnerName = "いいかげん政治団体" + index;
    entity.allAddress = "和歌山県実在市山麓町" + index;
    entity.poliOrgDelegate = "代表者　太郎" + index;
    entity.poliOrgKanrenshaCode = "123-4569" + index;
    entity.isAffected = true;
    entity.judgeReason = "未入力";

    return entity;
}