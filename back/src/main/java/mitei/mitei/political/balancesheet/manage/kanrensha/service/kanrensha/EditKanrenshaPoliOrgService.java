package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;

/**
 * 関連者政治団体を編集する
 */
@Service
public class EditKanrenshaPoliOrgService {

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件
     * @return 処理結果
     */
    public FrameworkMessageAndResultDto practice(final FrameworkCapsuleDto capsuleDto) {

        // 更新処理に対して処理結果を返す
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        resultDto.setMessage("政治団体仮設定");

        return resultDto;
    }

}
