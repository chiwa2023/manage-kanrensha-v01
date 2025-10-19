package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業団体属性マスタ編集Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class EditMasterCorporationPropertyLogic {

    /** マスタ企業団体属性レポジトリ */
    @Autowired
    private MasterCorporationPropertyRepository repository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditMasterCorporationPropertyEntityLogic callForEditMasterCorporationPropertyEntityLogic;

    /** 関連者企業団体Dto属性変換Logic */
    @Autowired
    private ConvertKanrenshaCorpDtoToMasterCorporationPropertyEntityLogic convertKanrenshaCorpDtoToMasterCorporationPropertyEntityLogic;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto 関連者企業団体格納Dto
     * @return 保存後のId
     * @throws EmptyResultDataAccessException テーブルIdが呼び出せなかったときの例外
     * @throws ConcurrencyFailureException    排他例外
     */
    public Integer practice(final SaveKanrenshaCorpCapsuleDto capsuleDto)
            throws EmptyResultDataAccessException, ConcurrencyFailureException { // NOPMD UncheckedException

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();
        KanrenshaCorpDto corpDto = capsuleDto.getKanrenshaCorpDto();

        if (0 != corpDto.getPropertyId()) {
            MasterCorporationPropertyEntity oldEntity = callForEditMasterCorporationPropertyEntityLogic.practice(corpDto);

            if (Objects.isNull(oldEntity)) {
                return 0;
            }
            setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
            repository.save(oldEntity);
        }

        MasterCorporationPropertyEntity newSaveEntity = convertKanrenshaCorpDtoToMasterCorporationPropertyEntityLogic
                .practice(corpDto);
        newSaveEntity.setCorpKanrenshaCode(corpDto.getCorpKanrenshaCode());
        newSaveEntity.setMasterCorporationPropertyId(0);
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        return repository.save(newSaveEntity).getMasterCorporationPropertyId();
    }

}
