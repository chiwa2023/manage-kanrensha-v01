package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.EditMasterPersonAccessLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.EditMasterPersonAddressLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.EditMasterPersonBaseLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.EditMasterPersonLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.EditMasterPersonPropertyLogic;

/**
 * 関連者個人を編集する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class EditKanrenshaPersonService {

    /** 関連者個人マスタ編集Logic */
    @Autowired
    private EditMasterPersonLogic editMasterPersonLogic;

    /** 関連者個人連絡先編集Logic */
    @Autowired
    private EditMasterPersonAccessLogic editMasterPersonAccessLogic;

    /** 関連者個人住所マスタ編集Logic */
    @Autowired
    private EditMasterPersonAddressLogic editMasterPersonAddressLogic;

    /** 関連者個人基本マスタ編集Logic */
    @Autowired
    private EditMasterPersonBaseLogic editMasterPersonBaseLogic;

    /** 関連者個人属性マスタ編集Logic */
    @Autowired
    private EditMasterPersonPropertyLogic editMasterPersonPropertyLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件
     * @return 処理結果
     */
    @Transactional
    public Integer practice(final SaveKanrenshaPersonCapsuleDto capsuleDto)
            throws EmptyResultDataAccessException, ConcurrencyFailureException { // NOPMD UncheckedException

        Integer updateId = 0;

        // マスタを更新
        updateId += editMasterPersonLogic.practice(capsuleDto);

        // 連絡先を更新
        updateId += editMasterPersonAccessLogic.practice(capsuleDto);

        // 住所を更新
        updateId += editMasterPersonAddressLogic.practice(capsuleDto);

        // 基本を更新
        updateId += editMasterPersonBaseLogic.practice(capsuleDto);

        // 属性を更新
        updateId += editMasterPersonPropertyLogic.practice(capsuleDto);

        return updateId;
    }

}
