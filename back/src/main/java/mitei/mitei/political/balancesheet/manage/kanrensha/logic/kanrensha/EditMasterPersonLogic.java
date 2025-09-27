package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人マスタ編集Logic
 */
@Component
public class EditMasterPersonLogic {

    /** マスタ個人レポジトリ */
    @Autowired
    private MasterPersonRepository repository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditMasterPersonMasterEntityLogic callForEditMasterPersonMasterEntityLogic;

    /** 関連者個人Dto変換Logic */
    @Autowired
    private ConvertKanrenshaPersonDtoToMasterPersonEntityLogic convertKanrenshaPersonDtoToMasterPersonEntityLogic;

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

        KanrenshaPersonDto personDto = capsuleDto.getKanrenshaPersonDto();
        MasterPersonEntity oldEntity = callForEditMasterPersonMasterEntityLogic.practice(personDto);

        if (Objects.isNull(oldEntity)) {
            return 0;
        }

        
        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();
        setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
        repository.save(oldEntity);

        MasterPersonEntity newSaveEntity = convertKanrenshaPersonDtoToMasterPersonEntityLogic.practice(personDto);
        newSaveEntity.setPersonKanrenshaCode(personDto.getPersonKanrenshaCode());
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);
        newSaveEntity.setMasterPersonId(0);

        return repository.save(newSaveEntity).getMasterPersonId();
    }

}
