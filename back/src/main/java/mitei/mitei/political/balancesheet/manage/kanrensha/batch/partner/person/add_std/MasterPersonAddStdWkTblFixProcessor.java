package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_std;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPersonJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPersonRepository;

/**
 * 関連者個人マスタ標準処理結果登録Processor
 */
@Component
public class MasterPersonAddStdWkTblFixProcessor
        implements ItemProcessor<WkTblMasterPersonJudgeEntity, WkTblMasterPersonEntity> {

    /** 関連者個人マスタ標準Repository */
    @Autowired
    private WkTblMasterPersonRepository wkTblMasterPersonRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblMasterPersonEntity process(final WkTblMasterPersonJudgeEntity item) throws Exception {

        WkTblMasterPersonEntity entity = new WkTblMasterPersonEntity();

        Optional<WkTblMasterPersonEntity> optional = wkTblMasterPersonRepository
                .findById(item.getWkTblMasterPersonId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsFinish(true);
            entity.setJudgeReason("正常終了");
        }

        return entity;
    }

}
