import type WkTblPartnerCorpAddMinInterface from "../../../../entity/wkTblPartnerCorpAddMinEntity";
import WkTblPartnerCorpAddMinEntity from "../../../../entity/wkTblPartnerCorpAddMinEntity";

export default function getMockWkTblCorpList(): WkTblPartnerCorpAddMinInterface[] {
        const list: WkTblPartnerCorpAddMinInterface[] = [];

        list.push(createEntity(1));
        list.push(createEntity(2));
        list.push(createEntity(3));
        list.push(createEntity(4));

        return list;

}

function createEntity(index: number): WkTblPartnerCorpAddMinInterface {

        const entity: WkTblPartnerCorpAddMinInterface = new WkTblPartnerCorpAddMinEntity();

        entity.wkTblPartnerCorpAddMinId = index;
        entity.isLatest = true;
        entity.partnerName = "よくばり企業" + index;
        entity.allAddress = "和歌山県実在市山麓町" + index;
        entity.corpDelegate = "代表者　太郎" + index;
        entity.houjinNo = "1-2345" + index;
        entity.isAffected = true;
        entity.judgeReason = "未入力";

        return entity;
}