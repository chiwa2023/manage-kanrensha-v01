package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpAddMinResultEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCorpAddMinRepository;

/**
 * 関連者企業・団体処理結果ワークテーブル変換Processor
 */
@Component
public class PartnerCorpAddMiniWkTblFixProcessor
        implements ItemProcessor<WkTblPartnerCorpAddMinResultEntity, WkTblPartnerCorpAddMinEntity> {

    /** 関連者企業・団体登録最小限Repository */
    @Autowired
    private WkTblPartnerCorpAddMinRepository wkTblPartnerCorpAddMinRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPartnerCorpAddMinEntity process(final WkTblPartnerCorpAddMinResultEntity item) throws Exception {

        WkTblPartnerCorpAddMinEntity entity = new WkTblPartnerCorpAddMinEntity();

        Optional<WkTblPartnerCorpAddMinEntity> optional = wkTblPartnerCorpAddMinRepository
                .findById(item.getWkTblPartnerCorpAddMinId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsFinish(true);
            entity.setJudgeReason("正常終了");
        }

        return entity;

    }

}
