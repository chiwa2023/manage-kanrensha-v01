package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業団体基本マスタ編集Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class EditMasterCorporationBaseLogic {

    /** マスタ企業団体基本レポジトリ */
    @Autowired
    private MasterCorporationBaseRepository repository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditMasterCorporationBaseEntityLogic callForEditMasterCorporationBaseEntityLogic;

    /** 関連者企業団体Dto基本変換Logic */
    @Autowired
    private ConvertKanrenshaCorpDtoToMasterCorporationBaseEntityLogic convertKanrenshaCorpDtoToMasterCorporationBaseEntityLogic;

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

        KanrenshaCorpDto corpDto = capsuleDto.getKanrenshaCorpDto();
        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        // 最小登録状態であれば更新できるデータがない
        if (0 != corpDto.getBaseId()) {
            MasterCorporationBaseEntity oldEntity = callForEditMasterCorporationBaseEntityLogic.practice(corpDto);
            if (Objects.isNull(oldEntity)) {
                return 0;
            }
            setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
            repository.save(oldEntity);
        }

        MasterCorporationBaseEntity newSaveEntity = convertKanrenshaCorpDtoToMasterCorporationBaseEntityLogic
                .practice(corpDto);
        newSaveEntity.setCorpKanrenshaCode(corpDto.getCorpKanrenshaCode());
        newSaveEntity.setMasterCorporationBaseId(0);
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        return repository.save(newSaveEntity).getMasterCorporationBaseId();
    }

}
