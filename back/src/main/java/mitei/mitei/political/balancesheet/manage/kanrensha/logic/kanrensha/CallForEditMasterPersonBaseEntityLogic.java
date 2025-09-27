package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputShokugyouDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonBaseRepository;

/**
 * 編集用に個人基本マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class CallForEditMasterPersonBaseEntityLogic {

    /** 個人基本マスタリポジトリ */
    @Autowired
    private MasterPersonBaseRepository masterPersonBaseRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaPersonDto 関連者個人Dto
     * @return MasterPersonBaseEntity or null
     */
    public MasterPersonBaseEntity practice(final KanrenshaPersonDto kanrenshaPersonDto) {

        // IDをキーにマスタを取得 (DTOのIDはbaseIdと想定)
        MasterPersonBaseEntity entity = masterPersonBaseRepository.findById(kanrenshaPersonDto.getBaseId())
                .orElseThrow(() -> new EmptyResultDataAccessException(
                        "Not found master_person_base. id = " + kanrenshaPersonDto.getBaseId(), 1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException(
                    "Target data is not the latest version. id = " + kanrenshaPersonDto.getBaseId());
        }

        // DTOとEntityの値を比較
        final InputPersonNameDto nameDto = kanrenshaPersonDto.getInputPersonNameDto();
        final InputShokugyouDto shokugyouDto = kanrenshaPersonDto.getInputShokugyouDto();

        boolean isNotChanged = Objects.equals(entity.getPartnerName(), nameDto.getAllName())
                // 姓名
                && Objects.equals(entity.getLastName(), nameDto.getLastName())
                && Objects.equals(entity.getFirstName(), nameDto.getFirstName())
                && Objects.equals(entity.getMiddleName(), nameDto.getMiddleName())
                && Objects.equals(entity.getLastNameKana(), nameDto.getLastNameKana())
                && Objects.equals(entity.getFirstNameKana(), nameDto.getFirstNameKana())
                && Objects.equals(entity.getMiddleNameKana(), nameDto.getMiddleNameKana())
                // 職業
                && Objects.equals(entity.getGyoushu(), shokugyouDto.getGyoushu())
                && Objects.equals(entity.getYakushoku(), shokugyouDto.getYakushoku())
                && Objects.equals(entity.getShokugyouUserWrite(), shokugyouDto.getShokugyouUserWrite())
                && Objects.equals(entity.getCorpNo(), shokugyouDto.getCorpNo())
                && Objects.equals(entity.getCorpAddress(), shokugyouDto.getCorpAddress())
                && Objects.equals(entity.getCorpName(), shokugyouDto.getCorpName());
        // && Objects.equals(entity.getIsShokyouEdit(), shokugyouDto.getIsShokyouEdit())
        // && Objects.equals(entity.getIsShokyouAccept(),shokugyouDto.getIsShokyouAccept());

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
