package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinResultEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgAddMinRepository;

/**
 * 関連者政治団体処理結果ワークテーブル変換Processor
 */
@Component
public class PartnerPoliOrgAddMiniWkTblFixProcessor
        implements ItemProcessor<WkTblPartnerPoliOrgAddMinResultEntity, WkTblPartnerPoliOrgAddMinEntity> {

    /** 関連者政治団体登録最小限Repository */
    @Autowired
    private WkTblPartnerPoliOrgAddMinRepository wkTblPartnerPoliOrgAddMinRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPartnerPoliOrgAddMinEntity process(final WkTblPartnerPoliOrgAddMinResultEntity item) throws Exception {

        WkTblPartnerPoliOrgAddMinEntity entity = new WkTblPartnerPoliOrgAddMinEntity();

        Optional<WkTblPartnerPoliOrgAddMinEntity> optional = wkTblPartnerPoliOrgAddMinRepository
                .findById(item.getWkTblPartnerPoliOrgAddMinId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsFinish(true);
            entity.setJudgeReason("正常終了");
        }

        return entity;

    }

}
