package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationBaseRepository;

/**
 * 編集用に政治団体基本マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class CallForEditMasterPoliOrgBaseEntityLogic {

    /** 政治団体基本マスタリポジトリ */
    @Autowired
    private MasterPoliticalOrganizationBaseRepository masterPoliticalOrganizationBaseRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaPoliOrgDto 関連者政治団体Dto
     * @return MasterPoliticalOrganizationBaseEntity or null
     */
    public MasterPoliticalOrganizationBaseEntity practice(final KanrenshaPoliOrgDto kanrenshaPoliOrgDto) {

        // IDをキーにマスタを取得 (DTOのIDはbaseIdと想定)
        MasterPoliticalOrganizationBaseEntity entity = masterPoliticalOrganizationBaseRepository.findById(kanrenshaPoliOrgDto.getBaseId())
                .orElseThrow(() -> new EmptyResultDataAccessException(
                        "Not found master_political_organization_base. id = " + kanrenshaPoliOrgDto.getBaseId(), 1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException(
                    "Target data is not the latest version. id = " + kanrenshaPoliOrgDto.getBaseId());
        }

        // DTOとEntityの値を比較
        final InputOrgNameDto orgNameDto = kanrenshaPoliOrgDto.getInputOrgNameDto();

        boolean isNotChanged = Objects.equals(entity.getPartnerName(), orgNameDto.getOrgName())
                && Objects.equals(entity.getPartnerName(), orgNameDto.getOrgName())
                && Objects.equals(entity.getOrgNameKana(), orgNameDto.getOrgNameKana())
                && Objects.equals(entity.getOrgDelegateCode(), kanrenshaPoliOrgDto.getOrgDelegateLeastDto().getPersonKanrenshaCode());

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
