import type WkTblPartnerCorpAddMinInterface from "../../../../entity/wkTblPartnerCorpAddMinEntity";
import WkTblPartnerCorpAddMinDto from "../../../../entity/wkTblPartnerCorpAddMinEntity";

export default function getMockWkTblCorpList(): WkTblPartnerCorpAddMinInterface[] {
        const list: WkTblPartnerCorpAddMinInterface[] = [];

        list.push(createEntity(1));
        list.push(createEntity(2));
        list.push(createEntity(3));
        list.push(createEntity(4));

        return list;

}

function createEntity(index: number): WkTblPartnerCorpAddMinInterface {

        const dto: WkTblPartnerCorpAddMinInterface = new WkTblPartnerCorpAddMinDto();

        dto.partnerName = "よくばり企業" + index;
        dto.allAddress = "和歌山県実在市山麓町" + index;
        dto.corpDelegate = "代表者　太郎" + index;
        dto.houjinNo = "1-2345" + index;
        dto.isAffected = true;
        dto.judgeReason = "未入力";

        return dto;
}