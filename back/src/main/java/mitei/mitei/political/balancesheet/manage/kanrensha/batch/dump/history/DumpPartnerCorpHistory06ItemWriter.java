package mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory06Entity;

/**
 * 関連者企業・団体履歴(06)ItemWriter
 */
@Component
public class DumpPartnerCorpHistory06ItemWriter extends FlatFileItemWriter<PartnerCorpHistory06Entity> {

    /**
     * コンストラクタ
     */
    public DumpPartnerCorpHistory06ItemWriter() {
        super();
        DelimitedLineAggregator<PartnerCorpHistory06Entity> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(","); // 区切り文字をカンマに設定
        lineAggregator.setQuoteCharacter("\"");
        BeanWrapperFieldExtractor<PartnerCorpHistory06Entity> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(
                new String[] { "corpKanrenshaCode", "partnerName", "allAddress", "corpDelegate", "orgDelegateCode", "insertTimestamp" }); // 書き出すフィールド名を設定
        lineAggregator.setFieldExtractor(fieldExtractor);
        String[] headers = { "\"関連者番号\"", "\"名称\"", "\"全住所\"", "\"代表者名\"", "\"代表者コード\"", "\"登録日時\"" };
        super.setHeaderCallback(writer1 -> writer1.write(String.join(",", headers)));
        super.setLineAggregator(lineAggregator);
    }

    /**
     * BeforeStep(読み取りファイル指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        String filePath = stepExecution.getJobParameters().getString("writeFilePath06");
        Path path = Paths.get(filePath);
        super.setResource(new FileSystemResource(path.toFile()));
    }

}
