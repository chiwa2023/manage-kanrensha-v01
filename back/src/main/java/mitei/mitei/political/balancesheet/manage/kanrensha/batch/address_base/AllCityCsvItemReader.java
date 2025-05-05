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
 * アドレス・ベース・レジストリ住居表示－住居Csv(mt_rsdtdsp_rsdt_prefxx.csv)読み取りItemReader
 */
@Component
class AllCityCsvItemReader extends FlatFileItemReader<AllCityCsvDto> {

    /**
     * コンストラクタ
     *
     * @param lineMapper lineMapper
     */
    public AllCityCsvItemReader(final @Autowired AllCityCsvLineMapper lineMapper) {
        super();
        super.setLineMapper(lineMapper);
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
        this.setLinesToSkip(1); // ヘッダがあるので1行読み飛ばし
    }

}
