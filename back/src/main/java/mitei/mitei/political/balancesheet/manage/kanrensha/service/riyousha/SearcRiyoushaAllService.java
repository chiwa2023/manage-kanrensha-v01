package mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SearchRiyoushaCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SearchRiyoushaResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaAdminRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaComradeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaManagerRepository;

/**
 * 利用者(APIユーザ・運営者・管理者)全検索Service
 */
@Service
public class SearcRiyoushaAllService {

    /** 利用者APIユーザRepository */
    @Autowired
    private RiyoushaComradeRepository riyoushaComradeRepository;

    /** 利用者運営者Repository */
    @Autowired
    private RiyoushaManagerRepository riyoushaManagerRepository;

    /** 利用者管理者Repository */
    @Autowired
    private RiyoushaAdminRepository riyoushaAdminRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 利用者検索結果Dto
     */
    public SearchRiyoushaResultDto practice(final SearchRiyoushaCapsuleDto capsuleDto) {

        SearchRiyoushaResultDto resultDto = new SearchRiyoushaResultDto();

        // TODO 他ユーザを検索する機会は多くない想定なので仮実装としてざっくり全件策とする
        // 検索編集する機会が多い場合は、検索条件追加やページングなどを追加しroleごとに分離する
        if (capsuleDto.getIsComradeSearch()) {
            resultDto.setListComrade(riyoushaComradeRepository.findByIsLatest(true));
        }

        if (capsuleDto.getIsManagerSearch()) {
            resultDto.setListManager(riyoushaManagerRepository.findByIsLatest(true));
        }

        if (capsuleDto.getIsAdminSearch()) {
            resultDto.setListAdmin(riyoushaAdminRepository.findByIsLatest(true));
        }

        return resultDto;
    }

}
