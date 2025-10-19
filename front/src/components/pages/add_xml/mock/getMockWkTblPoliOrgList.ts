import type WkTblPartnerPoliOrgAddMinInterface from "../../../../entity/wkTblPartnerPoliOrgAddMin";
import WkTblPartnerPoliOrgAddMinDto from "../../../../entity/wkTblPartnerPoliOrgAddMin";

export default function getMockWkTblPoliOrgList(): WkTblPartnerPoliOrgAddMinInterface[] {
    const list: WkTblPartnerPoliOrgAddMinInterface[] = [];
    list.push(createEntity(1));
    list.push(createEntity(2));
    list.push(createEntity(3));
    list.push(createEntity(4));

    return list;
}

function createEntity(index: number): WkTblPartnerPoliOrgAddMinInterface {

    const dto: WkTblPartnerPoliOrgAddMinInterface = new WkTblPartnerPoliOrgAddMinDto();

    dto.partnerName = "迂回献金　太郎" + index;
    dto.allAddress = "和歌山県実在市山麓町" + index;
    dto.poliOrgDelegate = "代表者　太郎" + index;
    dto.dantaiKbn = "01";
    dto.isAffected = true;
    dto.judgeReason = "未入力";

    return dto;
}