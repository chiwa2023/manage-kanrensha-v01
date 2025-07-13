package mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;

/**
 * 関連者政治団体CsvDto変換Processor
 * TODO 管理テーブルが増えたら、ここで必要な値を補う
 * 現時点ではentityをそのままDtoに変換しているだけなので無駄に見えるが拡張性を考慮した措置
 */
@Component
public class DumpMasterPoliticalOrganizationProcessor
        implements ItemProcessor<MasterPoliticalOrganizationEntity, MasterPoliticalOrganizationDto> {

    /**
     * 変換処理を実行する
     */
    @Override
    public MasterPoliticalOrganizationDto process(final MasterPoliticalOrganizationEntity item) throws Exception {

        MasterPoliticalOrganizationDto dto = new MasterPoliticalOrganizationDto();
        BeanUtils.copyProperties(item, dto);

        return dto;
    }

}
