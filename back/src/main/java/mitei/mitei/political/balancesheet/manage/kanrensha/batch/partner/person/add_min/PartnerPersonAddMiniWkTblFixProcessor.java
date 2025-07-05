package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_min;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonAddMinResultEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonAddMinRepository;

/**
 * 関連者個人処理結果ワークテーブル変換Processor
 */
@Component
public class PartnerPersonAddMiniWkTblFixProcessor
        implements ItemProcessor<WkTblPartnerPersonAddMinResultEntity, WkTblPartnerPersonAddMinEntity> {

    /** 関連者個人登録最小限Repository */
    @Autowired
    private WkTblPartnerPersonAddMinRepository wkTblPartnerPersonAddMinRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPartnerPersonAddMinEntity process(final WkTblPartnerPersonAddMinResultEntity item) throws Exception {

        WkTblPartnerPersonAddMinEntity entity = new WkTblPartnerPersonAddMinEntity();

        Optional<WkTblPartnerPersonAddMinEntity> optional = wkTblPartnerPersonAddMinRepository
                .findById(item.getWkTblPartnerPersonAddMinId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsFinish(true);
            entity.setJudgeReason("正常終了");
        }

        return entity;

    }

}
