import type WkTblPartnerPoliOrgAddMinInterface from "../../../../entity/wkTblPartnerPoliOrgAddMin";
import WkTblPartnerPoliOrgAddMinEntity from "../../../../entity/wkTblPartnerPoliOrgAddMin";

export default function getMockWkTblPoliOrgList(): WkTblPartnerPoliOrgAddMinInterface[] {
    const list: WkTblPartnerPoliOrgAddMinInterface[] = [];
    list.push(createEntity(1));
    list.push(createEntity(2));
    list.push(createEntity(3));
    list.push(createEntity(4));

    return list;
}

function createEntity(index: number): WkTblPartnerPoliOrgAddMinInterface {

    const entity: WkTblPartnerPoliOrgAddMinInterface = new WkTblPartnerPoliOrgAddMinEntity();
    entity.wkTblPartnerPoliOrgAddMinId = index;
    entity.isLatest = true;
    entity.partnerName = "いいかげん政治団体" + index;
    entity.allAddress = "和歌山県実在市山麓町" + index;
    entity.poliOrgDelegate = "代表者　太郎" + index;
    entity.dantaiKbn = "01";
    entity.isAffected = true;
    entity.judgeReason = "未入力";

    return entity;
}