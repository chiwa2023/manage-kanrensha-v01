package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体基本マスタ編集Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class EditMasterPoliticalOrganizationBaseLogic {

    /** マスタ政治団体基本レポジトリ */
    @Autowired
    private MasterPoliticalOrganizationBaseRepository repository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditMasterPoliOrgBaseEntityLogic callForEditMasterPoliOrgBaseEntityLogic;

    /** 関連者政治団体Dto基本変換Logic */
    @Autowired
    private ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationBaseEntityLogic convertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationBaseEntityLogic;

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

        KanrenshaPoliOrgDto poliOrgDto = capsuleDto.getKanrenshaPoliOrgDto();
        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        // 最小登録状態であれば更新できるデータがない
        if (0 != poliOrgDto.getBaseId()) {
            MasterPoliticalOrganizationBaseEntity oldEntity = callForEditMasterPoliOrgBaseEntityLogic.practice(poliOrgDto);
            if (Objects.isNull(oldEntity)) {
                return 0;
            }
            setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
            repository.save(oldEntity);
        }

        MasterPoliticalOrganizationBaseEntity newSaveEntity = convertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationBaseEntityLogic
                .practice(poliOrgDto);
        newSaveEntity.setPoliOrgKanrenshaCode(poliOrgDto.getPoliOrgKanrenshaCode());
        newSaveEntity.setMasterPoliticalOrganizationBaseId(0);
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        return repository.save(newSaveEntity).getMasterPoliticalOrganizationBaseId();
    }

}
