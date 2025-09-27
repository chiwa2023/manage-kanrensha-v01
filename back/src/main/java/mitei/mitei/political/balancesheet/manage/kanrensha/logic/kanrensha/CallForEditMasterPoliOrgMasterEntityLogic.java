package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationRepository;

/**
 * 編集用に政治団体マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class CallForEditMasterPoliOrgMasterEntityLogic {

    /** 政治団体マスタリポジトリ */
    @Autowired
    private MasterPoliticalOrganizationRepository masterPoliticalOrganizationRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaPoliOrgDto 関連者政治団体Dto
     * @return MasterPoliticalOrganizationEntity or null
     */
    public MasterPoliticalOrganizationEntity practice(final KanrenshaPoliOrgDto kanrenshaPoliOrgDto) {

        // IDをキーにマスタを取得
        MasterPoliticalOrganizationEntity entity = masterPoliticalOrganizationRepository.findById(kanrenshaPoliOrgDto.getMasterId())
                .orElseThrow(() -> new EmptyResultDataAccessException("Not found master_political_organization. id = " + kanrenshaPoliOrgDto.getMasterId(), 1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException("Target data is not the latest version. id = " + kanrenshaPoliOrgDto.getMasterId());
        }

        // DTOとEntityの値を比較
        boolean isNotChanged = Objects.equals(entity.getPartnerName(), kanrenshaPoliOrgDto.getInputOrgNameDto().getOrgName())
                && Objects.equals(entity.getAllAddress(), kanrenshaPoliOrgDto.getInputAddressDto().getAddressAll());

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
