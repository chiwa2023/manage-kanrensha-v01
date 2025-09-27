package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonPropertyRepository;

/**
 * 編集用に個人属性マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class CallForEditMasterPersonPropertyEntityLogic {

    /** 個人属性マスタリポジトリ */
    @Autowired
    private MasterPersonPropertyRepository masterPersonPropertyRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaPersonDto 関連者個人Dto
     * @return MasterPersonPropertyEntity or null
     */
    public MasterPersonPropertyEntity practice(final KanrenshaPersonDto kanrenshaPersonDto) {

        // IDをキーにマスタを取得
        MasterPersonPropertyEntity entity = masterPersonPropertyRepository.findById(kanrenshaPersonDto.getPropertyId())
                .orElseThrow(() -> new EmptyResultDataAccessException("Not found master_person_property. id = " + kanrenshaPersonDto.getPropertyId(), 1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException("Target data is not the latest version. id = " + kanrenshaPersonDto.getPropertyId());
        }

        // DTOとEntityの値を比較
        boolean isNotChanged = Objects.equals(entity.getIsForeign(), kanrenshaPersonDto.getIsForeign())
                && Objects.equals(entity.getPartnerName(), kanrenshaPersonDto.getInputPersonNameDto().getAllName());

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
