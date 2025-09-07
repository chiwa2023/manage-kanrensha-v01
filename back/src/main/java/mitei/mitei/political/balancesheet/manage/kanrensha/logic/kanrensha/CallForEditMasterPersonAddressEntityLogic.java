package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAddressRepository;

/**
 * 編集用に個人住所マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class CallForEditMasterPersonAddressEntityLogic {

    /** 個人住所マスタリポジトリ */
    @Autowired
    private MasterPersonAddressRepository masterPersonAddressRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaPersonDto 関連者個人Dto
     * @return MasterPersonAddressEntity or null
     */
    public MasterPersonAddressEntity practice(final KanrenshaPersonDto kanrenshaPersonDto) {

        // IDをキーにマスタを取得
        MasterPersonAddressEntity entity = masterPersonAddressRepository.findById(kanrenshaPersonDto.getAddressId())
                .orElseThrow(() -> new EmptyResultDataAccessException(
                        "Not found master_person_address. id = " + kanrenshaPersonDto.getAddressId(), 1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException(
                    "Target data is not the latest version. id = " + kanrenshaPersonDto.getAddressId());
        }

        // DTOとEntityの値を比較
        final InputAddressDto inputAddressDto = kanrenshaPersonDto.getInputAddressDto();

        // TODO 未処理の値の設定方法が確定次第修正する
        boolean isNotChanged = Objects.equals(entity.getPartnerName(),
                kanrenshaPersonDto.getInputPersonNameDto().getAllName())
                && Objects.equals(entity.getAddressPostal(), inputAddressDto.getAddressPostal())
                && Objects.equals(entity.getAddressBlock(), inputAddressDto.getAddressBlock())
                && Objects.equals(entity.getAddressBuilding(), inputAddressDto.getAddressBuilding())
                && Objects.equals(entity.getPostal1(), inputAddressDto.getPostal1())
                && Objects.equals(entity.getPostal2(), inputAddressDto.getPostal2())
                && Objects.equals(entity.getLgCode(), inputAddressDto.getLgCode())
                && Objects.equals(entity.getMachiazaId(), inputAddressDto.getMachiazaId())
                && Objects.equals(entity.getBlkId(), inputAddressDto.getBlkId())
                && Objects.equals(entity.getRsdtId(), inputAddressDto.getRsdtId())
                // && Objects.equals(entity.getRsdt2Id(), inputAddressDto.getRsdt2Id())
                && Objects.equals(entity.getIsPostalEdit(), inputAddressDto.getIsPostalEdit())
                && Objects.equals(entity.getIsBlockEdit(), inputAddressDto.getIsBlockEdit())
                && Objects.equals(entity.getIsBuildingEdit(), inputAddressDto.getIsBuildingEdit()
                        );
        // && Objects.equals(entity.getIsPostalAccept(),
        // inputAddressDto.getIsPostalAccept())
        // && Objects.equals(entity.getIsBlockAccept(),
        // inputAddressDto.getIsBlockAccept())
        // && Objects.equals(entity.getIsBuildingAccept(),
        // inputAddressDto.getIsBuildingAccept());

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
