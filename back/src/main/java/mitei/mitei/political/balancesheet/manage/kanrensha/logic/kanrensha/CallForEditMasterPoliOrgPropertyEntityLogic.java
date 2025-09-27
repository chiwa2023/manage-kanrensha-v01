package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationPropertyRepository;

/**
 * 編集用に政治団体属性マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class CallForEditMasterPoliOrgPropertyEntityLogic {

    /** 政治団体属性マスタリポジトリ */
    @Autowired
    private MasterPoliticalOrganizationPropertyRepository masterPoliticalOrganizationPropertyRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaPoliOrgDto 関連者政治団体Dto
     * @return MasterPoliticalOrganizationPropertyEntity or null
     */
    public MasterPoliticalOrganizationPropertyEntity practice(final KanrenshaPoliOrgDto kanrenshaPoliOrgDto) {

        // IDをキーにマスタを取得
        MasterPoliticalOrganizationPropertyEntity entity = masterPoliticalOrganizationPropertyRepository
                .findById(kanrenshaPoliOrgDto.getPropertyId())
                .orElseThrow(() -> new EmptyResultDataAccessException(
                        "Not found master_political_organization_property. id = " + kanrenshaPoliOrgDto.getPropertyId(),
                        1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException(
                    "Target data is not the latest version. id = " + kanrenshaPoliOrgDto.getPropertyId());
        }

        // DTOとEntityの値を比較
        boolean isNotChanged = Objects.equals(entity.getPartnerName(),
                kanrenshaPoliOrgDto.getInputOrgNameDto().getOrgName())
                && Objects.equals(entity.getAccountMgrCode(),
                        kanrenshaPoliOrgDto.getAccounrMgrLeastDto().getPersonKanrenshaCode())
                && Objects.equals(entity.getAccountMgrName(),
                        kanrenshaPoliOrgDto.getAccounrMgrLeastDto().getPersonName());

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
