package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人属性マスタ編集Logic
 */
@Component
public class EditMasterPersonPropertyLogic {

    /** マスタ個人属性レポジトリ */
    @Autowired
    private MasterPersonPropertyRepository repository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditMasterPersonPropertyEntityLogic callForEditMasterPersonPropertyEntityLogic;

    /** 関連者個人Dto属性変換Logic */
    @Autowired
    private ConvertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic convertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic;

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

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();
        KanrenshaPersonDto personDto = capsuleDto.getKanrenshaPersonDto();

        if (0 != personDto.getPropertyId()) {
            MasterPersonPropertyEntity oldEntity = callForEditMasterPersonPropertyEntityLogic.practice(personDto);

            if (Objects.isNull(oldEntity)) {
                return 0;
            }
            setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
            repository.save(oldEntity);
        }

        MasterPersonPropertyEntity newSaveEntity = convertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic
                .practice(personDto);
        newSaveEntity.setPersonKanrenshaCode(personDto.getPersonKanrenshaCode());
        newSaveEntity.setMasterPersonPropertyId(0);
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        return repository.save(newSaveEntity).getMasterPersonPropertyId();
    }

}
