package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.plus_houjin_no;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min.PartnerCorpAddMiniDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.HoujinNoLatestEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpPlusHojinNoEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.HoujinNoLatestRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.util.GetPrefectureLgCodeService;

/**
 * csvから邦字番号追加ワークテーブルEnttuty変換Processor
 */
@Component
public class PlusCorpHoujinNoInputProcessor
        implements ItemProcessor<PartnerCorpAddMiniDto, WkTblPartnerCorpPlusHojinNoEntity> {

    /** 法人番号マスタ */
    @Autowired
    private HoujinNoLatestRepository houjinNoLatestRepository;

    /** 法人番号マスタ */
    @Autowired
    private GetPrefectureLgCodeService getPrefectureLgCodeService;

    /** 法人番号最大記載数 */
    private static final int LIMIT_LIST = 19;

    /** TODO 定数化 法人種類外国籍 */
    private static final String KIND_FOREIGN = "401";

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPartnerCorpPlusHojinNoEntity process(final PartnerCorpAddMiniDto item) throws Exception {

        WkTblPartnerCorpPlusHojinNoEntity entity = new WkTblPartnerCorpPlusHojinNoEntity();
        BeanUtils.copyProperties(item, entity);

        // 団体名と所在地の件が一致するデータを取得
        List<HoujinNoLatestEntity> listEntity = houjinNoLatestRepository.findByNameAndLatestAndPrefectureCode(
                item.getPartnerName(), true, getPrefectureLgCodeService.practiceString(item.getAllAddress()));

        int size = listEntity.size();
        if (size > LIMIT_LIST) {
            size = LIMIT_LIST;
        }

        // 同一名団体が取得できたら法人番号を列挙
        String noList = "";
        StringBuilder builder = new StringBuilder();
        if (!listEntity.isEmpty()) {
            for (int index = 0; index < size; index++) {
                builder.append(listEntity.get(index).getCorporateNumber()).append('+');
            }
            noList = builder.substring(0, builder.length() - 1);

        }
        // 1件だけの時は外国籍かどうかもチェック
        if (size == 1 && KIND_FOREIGN.equals(listEntity.get(0).getKind())) {
            entity.setIsForeignCorp(true);
        }
        entity.setHoujinNo(noList);

        return entity;
    }

}
