package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.rireki;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory01Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体最終書き込み処理
 */
@Component
public class PartnerPoliOrgWkTblFixItemWriter extends JpaItemWriter<WkTblPartnerPoliOrgHistoryEntity> {

    /** 関連者政治団体ワークテーブルRepository */
    @Autowired
    private WkTblPartnerPoliOrgHistoryRepository wkTbPartnerPoliOrgHistoryRepository;

    /** 関連者政治団体履歴(01)Repository */
    @Autowired
    private PartnerPoliOrgHistory01Repository partnerPoliOrgHistory01Repository;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** ユーザ最低限Dto */
    private UserPersonLeastDto userDto;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public PartnerPoliOrgWkTblFixItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /**
     * BeforeStep(読み取りファイル指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        userDto = createUserLeastDtoByBatchParamUtil.practice(stepExecution);
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends WkTblPartnerPoliOrgHistoryEntity> items) {

        final Integer zero = 0;
        
        final List<WkTblPartnerPoliOrgHistoryEntity> list = new ArrayList<>();
        
        // 編集処理
        for (WkTblPartnerPoliOrgHistoryEntity entity : items) {
            if(!zero.equals(entity.getWkPartnerPoliOrgHistoryId())) {
                if (entity.getIsAffected()) {
                    // 判定が影響させるの場合は本体に書き込み
                    this.insertHistoryTable(entity);
                    entity.setIsFinish(true);
                    entity.setJudgeReason("正常保存");
                } else {
                    entity.setIsFinish(false);
                }
                entity.setWkPartnerPoliOrgHistoryCode(entity.getWkPartnerPoliOrgHistoryId());
                setTableDataHistoryUtil.practiceInsert(userDto, entity);
                list.add(entity);
            }
            // ワークうテーブルから呼び出しできなかったテーブルIdが0のデータは更新対象外
        }

        wkTbPartnerPoliOrgHistoryRepository.saveAll(list);
    }

    /* 履歴テーブル本体に保存する */
    private void insertHistoryTable(final WkTblPartnerPoliOrgHistoryEntity entityWkTbl) {

        // TODO 47都道府県とそれ以外に分割して登録する
        PartnerPoliOrgHistory01Entity entity = new PartnerPoliOrgHistory01Entity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示

        partnerPoliOrgHistory01Repository.saveAndFlush(entity);
    }

}
