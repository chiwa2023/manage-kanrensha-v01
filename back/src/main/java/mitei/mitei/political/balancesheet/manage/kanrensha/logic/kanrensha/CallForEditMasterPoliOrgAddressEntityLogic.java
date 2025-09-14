package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAddressRepository;

/**
 * 編集用に政治団体住所マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class CallForEditMasterPoliOrgAddressEntityLogic {

    /** 政治団体住所マスタリポジトリ */
    @Autowired
    private MasterPoliticalOrganizationAddressRepository masterPoliticalOrganizationAddressRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaPoliOrgDto 関連者政治団体Dto
     * @return MasterPoliticalOrganizationAddressEntity or null
     */
    public MasterPoliticalOrganizationAddressEntity practice(final KanrenshaPoliOrgDto kanrenshaPoliOrgDto) {

        // IDをキーにマスタを取得
        MasterPoliticalOrganizationAddressEntity entity = masterPoliticalOrganizationAddressRepository.findById(kanrenshaPoliOrgDto.getAddressId())
                .orElseThrow(() -> new EmptyResultDataAccessException(
                        "Not found master_political_organization_address. id = " + kanrenshaPoliOrgDto.getAddressId(), 1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException(
                    "Target data is not the latest version. id = " + kanrenshaPoliOrgDto.getAddressId());
        }

        // DTOとEntityの値を比較
        final InputAddressDto inputAddressDto = kanrenshaPoliOrgDto.getInputAddressDto();

        boolean isNotChanged = Objects.equals(entity.getPartnerName(),
                kanrenshaPoliOrgDto.getInputOrgNameDto().getOrgName())
                && Objects.equals(entity.getAddressPostal(), inputAddressDto.getAddressPostal())
                && Objects.equals(entity.getAddressBlock(), inputAddressDto.getAddressBlock())
                && Objects.equals(entity.getAddressBuilding(), inputAddressDto.getAddressBuilding())
                && Objects.equals(entity.getPostal1(), inputAddressDto.getPostalcode1())
                && Objects.equals(entity.getPostal2(), inputAddressDto.getPostalcode2())
                && Objects.equals(entity.getLgCode(), inputAddressDto.getLgCode())
                && Objects.equals(entity.getMachiazaId(), inputAddressDto.getMachiazaId())
                && Objects.equals(entity.getBlkId(), inputAddressDto.getBlkId())
                && Objects.equals(entity.getRsdtId(), inputAddressDto.getRsdtId())
                && Objects.equals(entity.getIsPostalEdit(), inputAddressDto.getIsPostalEdit())
                && Objects.equals(entity.getIsBlockEdit(), inputAddressDto.getIsBlockEdit())
                && Objects.equals(entity.getIsBuildingEdit(), inputAddressDto.getIsBuildingEdit()
                        );

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
