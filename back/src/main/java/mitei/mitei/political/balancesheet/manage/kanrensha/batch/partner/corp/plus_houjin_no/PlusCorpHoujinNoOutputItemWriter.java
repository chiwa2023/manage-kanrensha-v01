package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.plus_houjin_no;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;


/**
 * 企業・団体ファイルに法人番号追加
 */
@Component
public class PlusCorpHoujinNoOutputItemWriter extends FlatFileItemWriter<ParterCorpPlusHoujinNoDto> {

    /**
     * コンストラクタ
     *
     */
    public PlusCorpHoujinNoOutputItemWriter() {
        super();
        DelimitedLineAggregator<ParterCorpPlusHoujinNoDto> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(","); // 区切り文字をカンマに設定
        lineAggregator.setQuoteCharacter("\"");
        BeanWrapperFieldExtractor<ParterCorpPlusHoujinNoDto> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] {"partnerName", "allAddress", "corpDelegate", "houjinNo", "isForeignCorpText"}); // 書き出すフィールド名を設定
        lineAggregator.setFieldExtractor(fieldExtractor);
        String[] headers = {"\"名称\"","\"全住所\"","\"代表者名\"","\"法人番号\"","\"外国籍企業該否\""};
        super.setHeaderCallback(writer1 -> writer1.write(String.join(",", headers)) );
        super.setLineAggregator(lineAggregator);
    }

    /**
     * BeforeStep(読み取りファイル指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        String filePath = stepExecution.getJobParameters().getString("writeFilePath");

        Path path = Paths.get(filePath);

        super.setResource(new FileSystemResource(path.toFile()));
    }

}
