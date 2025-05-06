package mitei.mitei.political.balancesheet.manage.kanrensha.batch.houjin_no;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;


/**
 * 法人番号Csｖ読み取りItemReader
 */
@Component
public class HoujinNoCsvItemReader extends FlatFileItemReader<HoujinNoCsvDto> {

    /**
     * コンストラクタ
     *
     * @param lineMapper lineMapper
     */
    public HoujinNoCsvItemReader(final @Autowired HoujinNoCsvLineMapper lineMapper) {
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
        // いきなりデータから始まるので読み飛ばしなし
    }

    
    
}
