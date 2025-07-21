package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_std;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterCorpEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterCorpJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterCorpRepository;

/**
 * 関連者企業・団体マスタ標準処理結果登録Processor
 */
@Component
public class MasterCorporationAddStdWkTblFixProcessor
        implements ItemProcessor<WkTblMasterCorpJudgeEntity, WkTblMasterCorpEntity> {

    /** 関連者企業・団体マスタ標準Repository */
    @Autowired
    private WkTblMasterCorpRepository wkTblMasterCorpRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblMasterCorpEntity process(final WkTblMasterCorpJudgeEntity item) throws Exception {

        WkTblMasterCorpEntity entity = new WkTblMasterCorpEntity();

        Optional<WkTblMasterCorpEntity> optional = wkTblMasterCorpRepository
                .findById(item.getWkTblMasterCorpId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsFinish(true);
            entity.setJudgeReason("正常終了");
        }

        return entity;
    }

}
