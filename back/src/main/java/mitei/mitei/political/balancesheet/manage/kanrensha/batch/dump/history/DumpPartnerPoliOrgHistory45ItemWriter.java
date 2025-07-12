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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory45Entity;

/**
 * 関連者政治団体団体履歴(45)ItemWriter
 */
@Component
public class DumpPartnerPoliOrgHistory45ItemWriter extends FlatFileItemWriter<PartnerPoliOrgHistory45Entity> {

    /**
     * コンストラクタ
     */
    public DumpPartnerPoliOrgHistory45ItemWriter() {
        super();
        DelimitedLineAggregator<PartnerPoliOrgHistory45Entity> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(","); // 区切り文字をカンマに設定
        lineAggregator.setQuoteCharacter("\"");
        BeanWrapperFieldExtractor<PartnerPoliOrgHistory45Entity> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(
                new String[] { "poliOrgKanrenshaCode", "partnerName", "allAddress", "poliOrgDelegate", "insertTimestamp" }); // 書き出すフィールド名を設定
        lineAggregator.setFieldExtractor(fieldExtractor);
        String[] headers = { "\"関連者番号\"", "\"名称\"", "\"全住所\"", "\"代表者名\"", "\"登録日時\"" };
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

        String filePath = stepExecution.getJobParameters().getString("writeFilePath45");
        Path path = Paths.get(filePath);
        super.setResource(new FileSystemResource(path.toFile()));
    }

}
