package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業団体住所マスタ編集Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class EditMasterCorporationAddressLogic {

    /** マスタ企業団体住所レポジトリ */
    @Autowired
    private MasterCorporationAddressRepository repository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditMasterCorporationAddressEntityLogic callForEditMasterCorporationAddressEntityLogic;

    /** 関連者企業団体Dto住所変換Logic */
    @Autowired
    private ConvertKanrenshaCorpDtoToMasterCorporationAddressEntityLogic convertKanrenshaCorpDtoToMasterCorporationAddressEntityLogic;

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

        //　マスタ最小であれば呼び出す住所がない
        if (0 != corpDto.getAddressId()) {
            MasterCorporationAddressEntity oldEntity = callForEditMasterCorporationAddressEntityLogic.practice(corpDto);

            if (Objects.isNull(oldEntity)) {
                return 0;
            }
            setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
            repository.save(oldEntity);
        }

        MasterCorporationAddressEntity newSaveEntity = convertKanrenshaCorpDtoToMasterCorporationAddressEntityLogic
                .practice(corpDto);
        newSaveEntity.setCorpKanrenshaCode(corpDto.getCorpKanrenshaCode());
        newSaveEntity.setMasterCorporationAddressId(0);
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        return repository.save(newSaveEntity).getMasterCorporationAddressId();
    }

}
