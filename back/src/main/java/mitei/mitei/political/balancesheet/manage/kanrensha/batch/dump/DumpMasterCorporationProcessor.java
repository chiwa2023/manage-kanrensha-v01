package mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;

/**
 * 関連者企業・団体CsvDto変換Processor 
 * TODO 管理テーブルが増えたら、ここで必要な値を補う
 * 現時点ではentityをそのままDtoに変換しているだけなので無駄に見えるが、拡張性を考慮した措置
 */
@Component
public class DumpMasterCorporationProcessor implements ItemProcessor<MasterCorporationEntity, MasterCorporationDto> {

    /**
     * 変換処理を実行する
     */
    @Override
    public MasterCorporationDto process(final MasterCorporationEntity item) throws Exception {

        MasterCorporationDto dto = new MasterCorporationDto();
        BeanUtils.copyProperties(item, dto);

        return dto;
    }

}
