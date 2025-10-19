import type WkTblMasterCorpInterface from "../../../../entity/wkTblMasterCorpEntity";
import WkTblMasterCorpRntity from "../../../../entity/wkTblMasterCorpEntity";

export default function getMockWkTblCorpList(): WkTblMasterCorpInterface[] {
        const list: WkTblMasterCorpInterface[] = [];

        list.push(createEntity(1));
        list.push(createEntity(2));
        list.push(createEntity(3));
        list.push(createEntity(4));

        return list;
}

function createEntity(index: number): WkTblMasterCorpInterface {

        const entity: WkTblMasterCorpInterface = new WkTblMasterCorpRntity();
        entity.wkTblMasterCorpId = index;
        entity.isLatest = true;

        entity.partnerName = "よくばり企業" + index;
        entity.allAddress = "和歌山県実在市山麓町" + index;
        entity.corpDelegate = "代表者　太郎" + index;
        entity.houjinNo = "1-2345" + index;

        entity.addressPostal = "和歌山県実在市山麓町" + index;
        entity.addressBlock = "100番地" + index;
        entity.addressBuilding = "三角ビル" + index;
        entity.postal1 = "012" + index;
        entity.postal2 = "3456" + index;
        entity.phon1 = "023" + index;
        entity.phon2 = "4567" + index;
        entity.phon3 = "8901" + index;
        entity.email = "info@fundakuri.net" + index;
        entity.myPortalUrl = "https://fundakuri.net/welcom.html" + index;
        entity.isForeign = true;
        entity.houjinSbts = "401";
        entity.orgNameKana = "ふんだくりきぎょう" + index;
        entity.isShiten = false;
        entity.orgDelegateCode = "12-ABCD-3456" + index;
        entity.snsServiceName = "弱小SNS" + index;
        entity.snsAccount = "@funda-kuri" + index;
        entity.lgCode = "011998" + index;
        entity.machiazaId = "12" + index;
        entity.blkId = "123" + index;
        entity.rsdtId = "001" + index;
        entity.rsdt2Id = "002" + index;
        entity.isAffected = true;
        entity.judgeReason = "未入力";

        return entity;
}