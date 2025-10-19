import type WkTblPartnerCorpHistoryInterface from "../../../../entity/wkTblPartnerCorpHistoryEntity";
import WkTblPartnerCorpHistoryEntity from "../../../../entity/wkTblPartnerCorpHistoryEntity";

export default function getMockWkTblCorpList(): WkTblPartnerCorpHistoryInterface[] {

        const list:WkTblPartnerCorpHistoryInterface[] = [];

        list.push(createEntity(1));
        list.push(createEntity(2));
        list.push(createEntity(3));
        list.push(createEntity(4));

        return list;

}

function createEntity(index: number): WkTblPartnerCorpHistoryInterface {

        const entity: WkTblPartnerCorpHistoryInterface = new WkTblPartnerCorpHistoryEntity();
        entity.wkPartnerCorpHistoryId = index;
        entity.isLatest = true;

        entity.partnerName = "よくばり企業" + index;
        entity.allAddress = "和歌山県実在市山麓町" + index;
        entity.corpDelegate = "代表者　太郎" + index;
        entity.corpKanrenshaCode = "1-2345-4" + index;
        entity.isAffected = true;
        entity.judgeReason = "未入力";

        return entity;
}