package mitei.mitei.political.balancesheet.manage.kanrensha.service.works_approval;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.KanrenshaKbnConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterKanrenshaAddressBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.works_apploval.UpdateApprovalKanrenshaCorpAddressLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.works_apploval.UpdateApprovalKanrenshaPersonAddressLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.works_apploval.UpdateApprovalKanrenshaPoliOrgAddressLogic;

/**
 * 住所承認作業保存Service
 */
@Service
public class SaveApprovalAddressService {

    /** 作業承認更新関連者個人Logic */
    @Autowired
    private UpdateApprovalKanrenshaPersonAddressLogic updateApprovalKanrenshaPersonAddressLogic;

    /** 作業承認更新関連者個人Logic */
    @Autowired
    private UpdateApprovalKanrenshaCorpAddressLogic updateApprovalKanrenshaCorpAddressLogic;

    /** 作業承認更新関連者個人Logic */
    @Autowired
    private UpdateApprovalKanrenshaPoliOrgAddressLogic updateApprovalKanrenshaPoliOrgAddressLogic;

    /**
     * 処理を行う
     *
     * @param listAddress 承認作業住所リスト
     * @param userDto     ユーザ最小限Dto
     * @return 更新行数
     */
    public Integer practice(final List<MasterKanrenshaAddressBaseEntity> listAddress,
            final UserPersonLeastDto userDto) {

        int updateCount = 0;

        for (MasterKanrenshaAddressBaseEntity entity : listAddress) {

            switch (entity.getKanrenshaKbn()) {

                case KanrenshaKbnConstants.PERSON:
                    updateCount += updateApprovalKanrenshaPersonAddressLogic.practice(entity, userDto);
                    break;

                case KanrenshaKbnConstants.CORP:
                    updateCount += updateApprovalKanrenshaCorpAddressLogic.practice(entity, userDto);
                    break;

                case KanrenshaKbnConstants.POLI_ORG:
                    updateCount += updateApprovalKanrenshaPoliOrgAddressLogic.practice(entity, userDto);
                    break;

                default:
                    throw new IllegalArgumentException("関連者区分が指定の値ではありません");
            }
        }

        return updateCount;
    }
}
