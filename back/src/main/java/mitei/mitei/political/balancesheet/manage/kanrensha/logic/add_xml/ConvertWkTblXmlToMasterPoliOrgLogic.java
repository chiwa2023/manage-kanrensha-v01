package mitei.mitei.political.balancesheet.manage.kanrensha.logic.add_xml;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min.PartnerPoliOrgAddMiniCsvProcessor;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgAddMinRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * XMLから登録ワークテーブルから政治団体最小登録ワークテーブルに変換する
 */
@Component
public class ConvertWkTblXmlToMasterPoliOrgLogic {

    /** 仕様チェックProcessor(政治団体最小) */
    @Autowired
    private PartnerPoliOrgAddMiniCsvProcessor partnerPoliOrgAddMiniCsvProcessor;

    /** 政治団体マスタ最小登録ワークテーブルRepository */
    @Autowired
    private WkTblPartnerPoliOrgAddMinRepository wkTblPartnerPoliOrgAddMinRepository;

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

        WkTblPartnerPoliOrgAddMinEntity minEntity = new WkTblPartnerPoliOrgAddMinEntity();

        minEntity.setPartnerName(allByXmlEntity.getPartnerName());
        minEntity.setAllAddress(allByXmlEntity.getAllAddress());
        minEntity.setPoliOrgDelegate(allByXmlEntity.getOrgDelegate());
        minEntity.setDantaiKbn(allByXmlEntity.getDantaiKbn());

        minEntity = partnerPoliOrgAddMiniCsvProcessor.check(minEntity);
        setTableDataHistoryUtil.practiceInsert(userdto, minEntity);

        // コードを取得
        Integer code = 1;
        Optional<WkTblPartnerPoliOrgAddMinEntity> optional = wkTblPartnerPoliOrgAddMinRepository
                .findFirstByOrderByWkTblPartnerPoliOrgAddMinCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getWkTblPartnerPoliOrgAddMinCode();
        }
        minEntity.setWkTblPartnerPoliOrgAddMinCode(code);

        return wkTblPartnerPoliOrgAddMinRepository.save(minEntity).getWkTblPartnerPoliOrgAddMinId();
    }

}
