package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人住所マスタ編集Logic
 */
@Component
public class EditMasterPersonAddressLogic {

    /** マスタ個人住所レポジトリ */
    @Autowired
    private MasterPersonAddressRepository repository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditMasterPersonAddressEntityLogic callForEditMasterPersonAddressEntityLogic;

    /** 関連者個人Dto住所変換Logic */
    @Autowired
    private ConvertKanrenshaPersonDtoToMasterPersonAddressEntityLogic convertKanrenshaPersonDtoToMasterPersonAddressEntityLogic;

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
        MasterPersonAddressEntity oldEntity = callForEditMasterPersonAddressEntityLogic.practice(personDto);

        if (Objects.isNull(oldEntity)) {
            return 0;
        }

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();
        setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);

        MasterPersonAddressEntity newSaveEntity = convertKanrenshaPersonDtoToMasterPersonAddressEntityLogic
                .practice(personDto);
        newSaveEntity.setPersonKanrenshaCode(oldEntity.getPersonKanrenshaCode());
        newSaveEntity.setMasterPersonAddressId(0);
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        repository.save(oldEntity);
        return repository.save(newSaveEntity).getMasterPersonAddressId();
    }

}
