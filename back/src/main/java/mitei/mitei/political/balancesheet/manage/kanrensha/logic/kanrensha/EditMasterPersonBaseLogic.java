package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人基本マスタ編集Logic
 */
@Component
public class EditMasterPersonBaseLogic {

    /** マスタ個人基本レポジトリ */
    @Autowired
    private MasterPersonBaseRepository repository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditMasterPersonBaseEntityLogic callForEditMasterPersonBaseEntityLogic;

    /** 関連者個人Dto基本変換Logic */
    @Autowired
    private ConvertKanrenshaPersonDtoToMasterPersonBaseEntityLogic convertKanrenshaPersonDtoToMasterPersonBaseEntityLogic;

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
            throws EmptyResultDataAccessException, ConcurrencyFailureException { // NOPMD UncheckedException

        KanrenshaPersonDto personDto = capsuleDto.getKanrenshaPersonDto();
        MasterPersonBaseEntity oldEntity = callForEditMasterPersonBaseEntityLogic.practice(personDto);

        if (Objects.isNull(oldEntity)) {
            return 0;
        }

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();
        setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);

        MasterPersonBaseEntity newSaveEntity = convertKanrenshaPersonDtoToMasterPersonBaseEntityLogic
                .practice(personDto);
        newSaveEntity.setPersonKanrenshaCode(oldEntity.getPersonKanrenshaCode());
        newSaveEntity.setMasterPersonBaseId(0);
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        repository.save(oldEntity);
        return repository.save(newSaveEntity).getMasterPersonBaseId();
    }

}
