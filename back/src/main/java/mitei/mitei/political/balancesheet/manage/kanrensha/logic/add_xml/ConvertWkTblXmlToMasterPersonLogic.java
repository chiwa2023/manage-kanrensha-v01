package mitei.mitei.political.balancesheet.manage.kanrensha.logic.add_xml;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_min.PartnerPersonAddMiniCsvProcessor;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonAddMinRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * XMLから登録ワークテーブルから個人最小登録ワークテーブルに変換する
 */
@Component
public class ConvertWkTblXmlToMasterPersonLogic {

    /** 仕様チェックProcessor(個人最小) */
    @Autowired
    private PartnerPersonAddMiniCsvProcessor partnerPersonAddMiniCsvProcessor;

    /** 個人マスタ最小登録ワークテーブルRepository */
    @Autowired
    private WkTblPartnerPersonAddMinRepository wkTblPartnerPersonAddMinRepository;

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

        WkTblPartnerPersonAddMinEntity minEntity = new WkTblPartnerPersonAddMinEntity();

        minEntity.setPartnerName(allByXmlEntity.getPartnerName());
        minEntity.setAllAddress(allByXmlEntity.getAllAddress());
        minEntity.setPersonShokugyou(allByXmlEntity.getPersonShokugyou());

        // ユーザさんが変更しないと決断したらデータ整合チェックはしないで意図をそのまま通す
        final String notUseText = "使用しないに変更;";
        if (!notUseText.equals(minEntity.getJudgeReason())) {
            minEntity = partnerPersonAddMiniCsvProcessor.check(minEntity);
        }
        setTableDataHistoryUtil.practiceInsert(userdto, minEntity);

        // コードを取得
        Integer code = 1;
        Optional<WkTblPartnerPersonAddMinEntity> optional = wkTblPartnerPersonAddMinRepository
                .findFirstByOrderByWkTblPartnerPersonAddMinCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getWkTblPartnerPersonAddMinCode();
        }
        minEntity.setWkTblPartnerPersonAddMinCode(code);

        return wkTblPartnerPersonAddMinRepository.save(minEntity).getWkTblPartnerPersonAddMinId();
    }

}
