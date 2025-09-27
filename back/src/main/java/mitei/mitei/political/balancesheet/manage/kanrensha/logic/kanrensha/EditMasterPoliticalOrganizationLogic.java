package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体マスタ編集Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class EditMasterPoliticalOrganizationLogic {

    /** マスタ政治団体レポジトリ */
    @Autowired
    private MasterPoliticalOrganizationRepository repository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditMasterPoliOrgMasterEntityLogic callForEditMasterPoliOrgMasterEntityLogic;

    /** 関連者政治団体Dto変換Logic */
    @Autowired
    private ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationEntityLogic convertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationEntityLogic;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto 関連者政治団体格納Dto
     * @return 保存後のId
     * @throws EmptyResultDataAccessException テーブルIdが呼び出せなかったときの例外
     * @throws ConcurrencyFailureException    排他例外
     */
    public Integer practice(final SaveKanrenshaPoliOrgCapsuleDto capsuleDto)
            throws EmptyResultDataAccessException, ConcurrencyFailureException {  // NOPMD UncheckedException

        KanrenshaPoliOrgDto poliOrgDto = capsuleDto.getKanrenshaPoliOrgDto();
        MasterPoliticalOrganizationEntity oldEntity = callForEditMasterPoliOrgMasterEntityLogic.practice(poliOrgDto);

        if (Objects.isNull(oldEntity)) {
            return 0;
        }

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();
        setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);

        MasterPoliticalOrganizationEntity newSaveEntity = convertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationEntityLogic.practice(poliOrgDto);
        newSaveEntity.setPoliOrgKanrenshaCode(poliOrgDto.getPoliOrgKanrenshaCode());
        newSaveEntity.setMasterPoliticalOrganizationId(0);
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        repository.save(oldEntity);
        return repository.save(newSaveEntity).getMasterPoliticalOrganizationId();
    }

}
