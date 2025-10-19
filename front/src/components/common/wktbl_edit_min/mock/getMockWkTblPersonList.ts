import type WkTblPartnerPersonAddMinInterface from "../../../../entity/wkTblPartnerPersonAddMin";
import WkTblPartnerPersonAddMinEntity from "../../../../entity/wkTblPartnerPersonAddMin";

export default function getMockWkTblPersonList(): WkTblPartnerPersonAddMinInterface[] {

        const list: WkTblPartnerPersonAddMinInterface[] = [];
        list.push(createEntity(1));
        list.push(createEntity(2));
        list.push(createEntity(3));
        list.push(createEntity(4));

        return list;
}


function createEntity(index: number): WkTblPartnerPersonAddMinInterface {

        const entity: WkTblPartnerPersonAddMinInterface = new WkTblPartnerPersonAddMinEntity();

        entity.wkTblPartnerPersonAddMinId = index;
        entity.isLatest = true;
        entity.partnerName = "迂回献金　太郎" + index;
        entity.allAddress = "和歌山県実在市山麓町" + index;
        entity.personShokugyou = "弁護士" + index;
        entity.isAffected = true;
        entity.judgeReason = "未入力";

        return entity;
}