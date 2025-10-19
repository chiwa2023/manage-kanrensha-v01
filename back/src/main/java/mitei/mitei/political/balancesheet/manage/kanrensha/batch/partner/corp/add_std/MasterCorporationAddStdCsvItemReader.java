package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_std;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;


/**
 * 関連者企業・団体マスタ標準Csv登録ItemReadrer
 */
@Component
public class MasterCorporationAddStdCsvItemReader extends FlatFileItemReader<PartnerCorpAddStdDto> {

    /**
     * コンストラクタ
     *
     * @param lineMapper 関連者企業・団体csv読み取りLineMapper
     */
    public MasterCorporationAddStdCsvItemReader(final @Autowired PartnerCorpAddStdLineMapper lineMapper) {
        super();
        super.setLineMapper(lineMapper);
        super.setLinesToSkip(1); // ヘッダがあるので1行読み飛ばし
        // DefaultRecordSeparatorPolicy separatorPolicy = new DefaultRecordSeparatorPolicy();
        // separatorPolicy.setQuoteCharacter("\"");
        // super.setRecordSeparatorPolicy(separatorPolicy);
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
