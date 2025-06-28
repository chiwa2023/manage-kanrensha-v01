package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.rireki;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;


/**
 * 関連者政治団体Csv読み込みItemReader
 */
@Component
public class PartnerPoliOrgHistoryItemReader extends FlatFileItemReader<PartnerPoliOrgHistoryDto> {

    /**
     * コンストラクタ
     *
     * @param lineMapper 関連者政治団体csv読み取りLineMapper
     */
    public PartnerPoliOrgHistoryItemReader(final @Autowired PartnerPoliOrgHistoryLineMapper lineMapper) {
        super();
        super.setLineMapper(lineMapper);
        super.setLinesToSkip(1); // ヘッダがあるので1行読み飛ばし
    }
    
    /**
     * BeforeStep(読み取りファイル指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        String filePath = stepExecution.getJobParameters().getString("readFilePath");

        Path path = Paths.get(filePath);

        super.setResource(new FileSystemResource(path.toFile()));
    }
    
}
