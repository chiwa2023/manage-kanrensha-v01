import type WkTblMasterPoliOrgInterface from "../../../../entity/wkTblMasterPoliOrgEntity";
import WkTblMasterPoliOrgEntity from "../../../../entity/wkTblMasterPoliOrgEntity";

export default function getMockWkTblPoliOrgList(): WkTblMasterPoliOrgInterface[] {
    const list: WkTblMasterPoliOrgInterface[] = [];
    list.push(createEntity(1));
    list.push(createEntity(2));
    list.push(createEntity(3));
    list.push(createEntity(4));

    return list;
}

function createEntity(index: number): WkTblMasterPoliOrgInterface {

    const entity: WkTblMasterPoliOrgInterface = new WkTblMasterPoliOrgEntity();
    entity.wkTblMasterPoliOrgId = index;
    entity.isLatest = true;

    entity.partnerName = "ちゃらんぽらん政治団体" + index;
    entity.allAddress = "和歌山県実在市山麓町" + index;
    entity.poliOrgDelegate = "代表者　太郎" + index;
    entity.dantaiKbn = "01";
    entity.addressPostal = "和歌山県実在市山麓町";
    entity.addressBlock = "100番地3";
    entity.addressBuilding = "三角ビル2F";
    entity.postal1 = "012";
    entity.postal2 = "3456";
    entity.phon1 = "098";
    entity.phon2 = "7654";
    entity.phon3 = "3210";
    entity.email = "info@charan-poran.com";
    entity.myPortalUrl = "https://charan-poran.com";
    entity.snsServiceName = "弱小SNS";
    entity.snsAccount = "@charan-poran";
    entity.orgNameKana = "ちゃらんぽらんせいじだんたい";
    entity.orgDelegateCode = "12-34AB-56cd";
    entity.accountMgrCode = "98-76EF-54gh";
    entity.accountMgrName = "会計責任者　花子";
    entity.lgCode = "0987654";
    entity.machiazaId = "11";
    entity.blkId = "222";
    entity.rsdtId = "333";
    entity.rsdt2Id = "44";
    entity.isAffected = true;
    entity.judgeReason = "未入力";

    return entity;
}