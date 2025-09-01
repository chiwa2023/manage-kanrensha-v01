package mitei.mitei.political.balancesheet.manage.kanrensha.logic.add_xml;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min.PartnerCorpAddMiniCsvProcessor;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCorpAddMinRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * XMLから登録ワークテーブルから企業最小登録ワークテーブルに変換する
 */
@Component
public class ConvertWkTblXmlToMasterCorpLogic {

    /** 仕様チェックProcessor(企業最小) */
    @Autowired
    private PartnerCorpAddMiniCsvProcessor partnerCorpAddMiniCsvProcessor;

    /** 企業マスタ最小登録ワークテーブルRepository */
    @Autowired
    private WkTblPartnerCorpAddMinRepository wkTblPartnerCorpAddMinRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param allByXmlEntity XMLから登録ワークテーブルEntity
     * @param userdto        ユーザ最小限Dto
     * @return 処理後Id
     */
    public int practice(final WkTblMasterAllByXmlEntity allByXmlEntity, final UserPersonLeastDto userdto) {

        WkTblPartnerCorpAddMinEntity minEntity = new WkTblPartnerCorpAddMinEntity();

        minEntity.setPartnerName(allByXmlEntity.getPartnerName());
        minEntity.setAllAddress(allByXmlEntity.getAllAddress());
        minEntity.setCorpDelegate(allByXmlEntity.getOrgDelegate());
        minEntity.setHoujinNo(allByXmlEntity.getHoujinNo());

        // ユーザさんが変更しないと決断したらデータ整合チェックはしないで意図をそのまま通す
        final String notUseText = "使用しないに変更;";
        if (!notUseText.equals(minEntity.getJudgeReason())) {
            minEntity = partnerCorpAddMiniCsvProcessor.check(minEntity);
        }

        setTableDataHistoryUtil.practiceInsert(userdto, minEntity);

        // コードを取得
        Integer code = 1;
        Optional<WkTblPartnerCorpAddMinEntity> optional = wkTblPartnerCorpAddMinRepository
                .findFirstByOrderByWkTblPartnerCorpAddMinCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getWkTblPartnerCorpAddMinCode();
        }
        minEntity.setWkTblPartnerCorpAddMinCode(code);

        return wkTblPartnerCorpAddMinRepository.save(minEntity).getWkTblPartnerCorpAddMinId();
    }
}
