package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_std;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPoliOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPoliOrgJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPoliOrgRepository;

/**
 * 関連者個人マスタ標準処理結果登録Processor
 */
@Component
public class MasterPoliticalOrganizationAddStdWkTblFixProcessor
        implements ItemProcessor<WkTblMasterPoliOrgJudgeEntity, WkTblMasterPoliOrgEntity> {

    /** 関連者個人マスタ標準Repository */
    @Autowired
    private WkTblMasterPoliOrgRepository wkTblMasterPoliOrgRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblMasterPoliOrgEntity process(final WkTblMasterPoliOrgJudgeEntity item) throws Exception {

        WkTblMasterPoliOrgEntity entity = new WkTblMasterPoliOrgEntity();

        Optional<WkTblMasterPoliOrgEntity> optional = wkTblMasterPoliOrgRepository
                .findById(item.getWkTblMasterPoliOrgId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsFinish(true);
            entity.setJudgeReason("正常終了");
        }

        return entity;
    }

}
