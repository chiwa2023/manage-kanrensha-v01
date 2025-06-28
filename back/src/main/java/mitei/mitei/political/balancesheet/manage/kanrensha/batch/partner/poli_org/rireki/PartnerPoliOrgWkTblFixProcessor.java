package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.rireki;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgHistoryRepository;

/**
 * 関連者政治団体判定から編集用のワークテーブルを設定する
 */
@Component
public class PartnerPoliOrgWkTblFixProcessor
        implements ItemProcessor<WkTblPartnerPoliOrgJudgeEntity, WkTblPartnerPoliOrgHistoryEntity> {

    /** 関連者政治団体ワークテーブルRepository */
    @Autowired
    private WkTblPartnerPoliOrgHistoryRepository wkTblPartnerPoliOrgHistoryRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPartnerPoliOrgHistoryEntity process(final WkTblPartnerPoliOrgJudgeEntity item) throws Exception {

        WkTblPartnerPoliOrgHistoryEntity entity = new WkTblPartnerPoliOrgHistoryEntity();

        Optional<WkTblPartnerPoliOrgHistoryEntity> optional = wkTblPartnerPoliOrgHistoryRepository
                .findById(item.getWkPartnerPoliOrgHistoryId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsAffected(item.getIsAffected());
            entity.setJudgeReason(item.getJudgeReason());
        }

        return entity;
    }

}
