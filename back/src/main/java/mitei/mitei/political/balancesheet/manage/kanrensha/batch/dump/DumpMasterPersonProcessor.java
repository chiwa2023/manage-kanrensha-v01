package mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;

/**
 * 関連者個人CsvDto変換Processor
 * TODO 管理テーブルが増えたら、ここで必要な値を補う
 * 現時点ではentityをそのままDtoに変換しているだけなので無駄に見えるが拡張性を考慮した措置
 */
@Component
public class DumpMasterPersonProcessor implements ItemProcessor<MasterPersonEntity, MasterPersonDto> {

    /**
     * 変換処理を実行する
     */
    @Override
    public MasterPersonDto process(final MasterPersonEntity item) throws Exception {

        MasterPersonDto dto = new MasterPersonDto();
        BeanUtils.copyProperties(item, dto);

        return dto;
    }

}
