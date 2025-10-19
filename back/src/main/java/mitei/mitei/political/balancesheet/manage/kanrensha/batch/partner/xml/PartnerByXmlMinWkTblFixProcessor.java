package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.xml;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;

/**
 * XMLによる最小マスタ登録処理結果ワークテーブル変換Processor
 */
@Component
public class PartnerByXmlMinWkTblFixProcessor
        implements ItemProcessor<WkTblMasterAllByXmlJudgeEntity, WkTblMasterAllByXmlEntity> {

    /** 関連者政治団体登録最小限Repository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblMasterAllByXmlEntity process(final WkTblMasterAllByXmlJudgeEntity item) throws Exception {

        WkTblMasterAllByXmlEntity entity = new WkTblMasterAllByXmlEntity();

        Optional<WkTblMasterAllByXmlEntity> optional = wkTblMasterAllByXmlRepository
                .findById(item.getWkTblMasterAllByXmlId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsFinish(true);
            entity.setJudgeReason("正常終了");
        }

        return entity;
    }
}
