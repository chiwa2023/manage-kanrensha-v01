package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体連絡先マスタ編集Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class EditMasterPoliticalOrganizationAccessLogic {

    /** マスタ政治団体連絡先レポジトリ */
    @Autowired
    private MasterPoliticalOrganizationAccessRepository repository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditMasterPoliOrgAccessEntityLogic callForEditMasterPoliOrgAccessEntityLogic;

    /** 関連者政治団体Dto連絡先変換Logic */
    @Autowired
    private ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAccessEntityLogic convertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAccessEntityLogic;

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

        // テーブル更新が必要か判定
        KanrenshaPoliOrgDto poliOrgDto = capsuleDto.getKanrenshaPoliOrgDto();
        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();
        
        // マスタ最小登録状態の場合は過去履歴が存在しないので履歴に変更処理をしない
        if(0 != poliOrgDto.getAccessId()) {
            MasterPoliticalOrganizationAccessEntity accessEntity = callForEditMasterPoliOrgAccessEntityLogic.practice(poliOrgDto);
            // 変更箇所がなく更新の必要がなければ中断
            if (Objects.isNull(accessEntity)) {
                return 0;
            }
            // 元データを履歴に変更
            setTableDataHistoryUtil.practiceDelete(userDto, accessEntity);
            repository.save(accessEntity);
        }

        // 新しい履歴を積み上げ
        MasterPoliticalOrganizationAccessEntity newSaveEntity = convertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAccessEntityLogic
                .practice(poliOrgDto);
        newSaveEntity.setPoliOrgKanrenshaCode(poliOrgDto.getPoliOrgKanrenshaCode());
        newSaveEntity.setMasterPoliticalOrganizationAccessId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        // 保存
        return repository.save(newSaveEntity).getMasterPoliticalOrganizationAccessId();
    }

}
