import type WkTblPartnerPersonHistoryInterface from "../../../../entity/wkTblPartnerPersonHistoryEntity";
import WkTblPartnerPersonHistoryEntity from "../../../../entity/wkTblPartnerPersonHistoryEntity";

export default function getMockWkTblPersonList(): WkTblPartnerPersonHistoryInterface[] {

        const list: WkTblPartnerPersonHistoryInterface[] = [];
        list.push(createEntity(1));
        list.push(createEntity(2));
        list.push(createEntity(3));
        list.push(createEntity(4));

        return list;
}


function createEntity(index: number): WkTblPartnerPersonHistoryInterface {

        const entity: WkTblPartnerPersonHistoryInterface = new WkTblPartnerPersonHistoryEntity();
        entity.wkPartnerPersonHistoryId = index;
        entity.isLatest = true;

        entity.partnerName = "迂回献金　太郎" + index;
        entity.allAddress = "和歌山県実在市山麓町" + index;
        entity.personShokugyou = "弁護士" + index;
        entity.personKanrenshaCode = "12-3456" + index;
        entity.isAffected = true;
        entity.judgeReason = "未入力";

        return entity;
}