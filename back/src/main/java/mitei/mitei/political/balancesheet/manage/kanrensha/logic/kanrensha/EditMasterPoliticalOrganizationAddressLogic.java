package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体住所マスタ編集Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class EditMasterPoliticalOrganizationAddressLogic {

    /** マスタ政治団体住所レポジトリ */
    @Autowired
    private MasterPoliticalOrganizationAddressRepository repository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditMasterPoliOrgAddressEntityLogic callForEditMasterPoliOrgAddressEntityLogic;

    /** 関連者政治団体Dto住所変換Logic */
    @Autowired
    private ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAddressEntityLogic convertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAddressEntityLogic;

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

        //　マスタ最小であれば呼び出す住所がない
        if (0 != poliOrgDto.getAddressId()) {
            MasterPoliticalOrganizationAddressEntity oldEntity = callForEditMasterPoliOrgAddressEntityLogic.practice(poliOrgDto);

            if (Objects.isNull(oldEntity)) {
                return 0;
            }
            setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
            repository.save(oldEntity);
        }

        MasterPoliticalOrganizationAddressEntity newSaveEntity = convertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAddressEntityLogic
                .practice(poliOrgDto);
        newSaveEntity.setPoliOrgKanrenshaCode(poliOrgDto.getPoliOrgKanrenshaCode());
        newSaveEntity.setMasterPoliticalOrganizationAddressId(0);
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        return repository.save(newSaveEntity).getMasterPoliticalOrganizationAddressId();
    }

}
