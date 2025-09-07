package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人連絡先マスタ編集Logic
 */
@Component
public class EditMasterPersonAccessLogic {

    /** マスタ個人連絡先レポジトリ */
    @Autowired
    private MasterPersonAccessRepository repository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditMasterPersonAccessEntityLogic callForEditMasterPersonAccessEntityLogic;

    /** 関連者個人Dto連絡先変換Logic */
    @Autowired
    private ConvertKanrenshaPersonDtoToMasterPersonAccessEntityLogic convertKanrenshaPersonDtoToMasterPersonAccessEntityLogic;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto 関連者個人格納Dto
     * @return 保存後のId
     * @throws EmptyResultDataAccessException テーブルIdが呼び出せなかったときの例外
     * @throws ConcurrencyFailureException    排他例外
     */
    public Integer practice(final SaveKanrenshaPersonCapsuleDto capsuleDto)
            throws EmptyResultDataAccessException, ConcurrencyFailureException {  // NOPMD UncheckedException

        // テーブル更新が必要か判定
        KanrenshaPersonDto personDto = capsuleDto.getKanrenshaPersonDto();
        MasterPersonAccessEntity accessEntity = callForEditMasterPersonAccessEntityLogic.practice(personDto);

        // 変更箇所がなく更新の必要がなければ中断
        if (Objects.isNull(accessEntity)) {
            return 0;
        }

        // 元データを履歴に変更
        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();
        setTableDataHistoryUtil.practiceDelete(userDto, accessEntity);

        // 新しい履歴を積み上げ
        MasterPersonAccessEntity newSaveEntity = convertKanrenshaPersonDtoToMasterPersonAccessEntityLogic
                .practice(personDto);
        newSaveEntity.setPersonKanrenshaCode(accessEntity.getPersonKanrenshaCode());
        newSaveEntity.setMasterPersonAccessId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        // 保存
        repository.save(accessEntity);
        return repository.save(newSaveEntity).getMasterPersonAccessId();
    }

}
