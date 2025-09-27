package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.EditMasterCorporationAccessLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.EditMasterCorporationAddressLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.EditMasterCorporationBaseLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.EditMasterCorporationLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.EditMasterCorporationPropertyLogic;

/**
 * 関連者企業・団体を編集する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class EditKanrenshaCorpService {

    /** 関連者企業団体マスタ編集Logic */
    @Autowired
    private EditMasterCorporationLogic editMasterCorporationLogic;

    /** 関連者企業団体連絡先編集Logic */
    @Autowired
    private EditMasterCorporationAccessLogic editMasterCorporationAccessLogic;

    /** 関連者企業団体住所マスタ編集Logic */
    @Autowired
    private EditMasterCorporationAddressLogic editMasterCorporationAddressLogic;

    /** 関連者企業団体基本マスタ編集Logic */
    @Autowired
    private EditMasterCorporationBaseLogic editMasterCorporationBaseLogic;

    /** 関連者企業団体属性マスタ編集Logic */
    @Autowired
    private EditMasterCorporationPropertyLogic editMasterCorporationPropertyLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件
     * @return 処理結果
     */
    @Transactional
    public Integer practice(final SaveKanrenshaCorpCapsuleDto capsuleDto)
            throws EmptyResultDataAccessException, ConcurrencyFailureException { // NOPMD UncheckedException

        Integer updateId = 0;

        // マスタを更新
        updateId += editMasterCorporationLogic.practice(capsuleDto);

        // 連絡先を更新
        updateId += editMasterCorporationAccessLogic.practice(capsuleDto);

        // 住所を更新
        updateId += editMasterCorporationAddressLogic.practice(capsuleDto);

        // 基本を更新
        updateId += editMasterCorporationBaseLogic.practice(capsuleDto);

        // 属性を更新
        updateId += editMasterCorporationPropertyLogic.practice(capsuleDto);

        return updateId;
    }

}
