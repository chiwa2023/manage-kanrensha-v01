package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体属性マスタ編集Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class EditMasterPoliticalOrganizationPropertyLogic {

    /** マスタ政治団体属性レポジトリ */
    @Autowired
    private MasterPoliticalOrganizationPropertyRepository repository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditMasterPoliOrgPropertyEntityLogic callForEditMasterPoliOrgPropertyEntityLogic;

    /** 関連者政治団体Dto属性変換Logic */
    @Autowired
    private ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationPropertyEntityLogic convertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationPropertyEntityLogic;

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
            throws EmptyResultDataAccessException, ConcurrencyFailureException { // NOPMD UncheckedException

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();
        KanrenshaPoliOrgDto poliOrgDto = capsuleDto.getKanrenshaPoliOrgDto();

        if (0 != poliOrgDto.getPropertyId()) {
            MasterPoliticalOrganizationPropertyEntity oldEntity = callForEditMasterPoliOrgPropertyEntityLogic.practice(poliOrgDto);

            if (Objects.isNull(oldEntity)) {
                return 0;
            }
            setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
            repository.save(oldEntity);
        }

        MasterPoliticalOrganizationPropertyEntity newSaveEntity = convertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationPropertyEntityLogic
                .practice(poliOrgDto);
        newSaveEntity.setPoliOrgKanrenshaCode(poliOrgDto.getPoliOrgKanrenshaCode());
        newSaveEntity.setMasterPoliticalOrganizationPropertyId(0);
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        return repository.save(newSaveEntity).getMasterPoliticalOrganizationPropertyId();
    }

}
