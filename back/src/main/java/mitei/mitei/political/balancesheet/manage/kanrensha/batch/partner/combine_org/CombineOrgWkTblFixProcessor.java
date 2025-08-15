package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.combine_org;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCombineOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCombineOrgJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCombineOrgRepository;

/**
 * 個人団体紐づけワークテーブル修正Processor
 */
@Component
public class CombineOrgWkTblFixProcessor
        implements ItemProcessor<WkTblPartnerCombineOrgJudgeEntity, WkTblPartnerCombineOrgEntity> {

    /** 個人団体紐づけワークテーブルRepository */
    @Autowired
    private WkTblPartnerCombineOrgRepository wkTblPartnerCombineOrgRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPartnerCombineOrgEntity process(final WkTblPartnerCombineOrgJudgeEntity item) throws Exception {

        WkTblPartnerCombineOrgEntity entity = new WkTblPartnerCombineOrgEntity();

        Optional<WkTblPartnerCombineOrgEntity> optional = wkTblPartnerCombineOrgRepository
                .findById(item.getWkTblPartnerCombineOrgId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsFinish(true);
            entity.setJudgeReason("正常終了");
        }

        return entity;

    }

}
