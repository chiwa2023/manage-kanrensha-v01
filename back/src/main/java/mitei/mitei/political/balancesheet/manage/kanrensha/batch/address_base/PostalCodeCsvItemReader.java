package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

/**
 * 郵便番号Csv読み取りItemreader
 */
@Component
public class PostalCodeCsvItemReader extends FlatFileItemReader<PostalCodeCsvDto> {

    /**
     * コンストラクタ
     *
     * @param lineMapper lineMapper
     */
    public PostalCodeCsvItemReader(final @Autowired PostalCodeCsvLineMapper lineMapper) {
        super();
        super.setLineMapper(lineMapper);
        super.setStrict(false);
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
