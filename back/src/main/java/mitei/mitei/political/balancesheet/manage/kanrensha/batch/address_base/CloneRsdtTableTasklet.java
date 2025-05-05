package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressAllCityRepository;

/**
 * アドレス・ベース・レジストリ住居テーブルを展開するTasklet
 */
@Component
public class CloneRsdtTableTasklet implements Tasklet {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /** 地方自治体Repository */
    @Autowired
    private AddressAllCityRepository addressAllCityRepository;

    /** 処理単位件数 */
    private static final int CHUNK_SIZE = 250;

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        long count = addressAllCityRepository.count();
        for (long loop = 0; loop < (count / CHUNK_SIZE) + 1; loop++) {
            Pageable pageable = Pageable.ofSize(CHUNK_SIZE).withPage(Math.toIntExact(loop));
            List<String> list = addressAllCityRepository.findLgCode(pageable);
            for (String lgCode : list) {
                String table = "address_rsdt_" + lgCode;

                Query queryDrop = entityManager.createNativeQuery("DROP TABLE IF EXISTS " + table);
                queryDrop.executeUpdate();
                
                Query queryCreate = entityManager
                        .createNativeQuery("CREATE TABLE " + table + " LIKE address_rsdt_template");
                queryCreate.executeUpdate();
            }
        }

        // 処理終了
        return RepeatStatus.FINISHED;

    }

}
