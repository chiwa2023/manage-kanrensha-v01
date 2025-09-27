package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.EditMasterPoliticalOrganizationAccessLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.EditMasterPoliticalOrganizationAddressLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.EditMasterPoliticalOrganizationBaseLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.EditMasterPoliticalOrganizationLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.EditMasterPoliticalOrganizationPropertyLogic;

/**
 * 関連者政治団体を編集する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class EditKanrenshaPoliOrgService {

    /** 関連者政治団体マスタ編集Logic */
    @Autowired
    private EditMasterPoliticalOrganizationLogic editMasterPoliticalOrganizationLogic;

    /** 関連者政治団体連絡先編集Logic */
    @Autowired
    private EditMasterPoliticalOrganizationAccessLogic editMasterPoliticalOrganizationAccessLogic;

    /** 関連者政治団体住所マスタ編集Logic */
    @Autowired
    private EditMasterPoliticalOrganizationAddressLogic editMasterPoliticalOrganizationAddressLogic;

    /** 関連者政治団体基本マスタ編集Logic */
    @Autowired
    private EditMasterPoliticalOrganizationBaseLogic editMasterPoliticalOrganizationBaseLogic;

    /** 関連者政治団体属性マスタ編集Logic */
    @Autowired
    private EditMasterPoliticalOrganizationPropertyLogic editMasterPoliticalOrganizationPropertyLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件
     * @return 処理結果
     */
    @Transactional
    public Integer practice(final SaveKanrenshaPoliOrgCapsuleDto capsuleDto)
            throws EmptyResultDataAccessException, ConcurrencyFailureException { // NOPMD UncheckedException

        Integer updateId = 0;

        // マスタを更新
        updateId += editMasterPoliticalOrganizationLogic.practice(capsuleDto);

        // 連絡先を更新
        updateId += editMasterPoliticalOrganizationAccessLogic.practice(capsuleDto);

        // 住所を更新
        updateId += editMasterPoliticalOrganizationAddressLogic.practice(capsuleDto);

        // 基本を更新
        updateId += editMasterPoliticalOrganizationBaseLogic.practice(capsuleDto);

        // 属性を更新
        updateId += editMasterPoliticalOrganizationPropertyLogic.practice(capsuleDto);

        return updateId;
    }

}
