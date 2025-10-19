package mitei.mitei.political.balancesheet.manage.kanrensha.logic.year.y2025;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCombineOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.year.y2025.PartnerCombineOrg2025Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.year.y2025.PartnerCombineOrg2025Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 紺団体紐づけテーブル挿入Logic(2025)
 */
@Component
public class InsertCombineOrgY2025Logic {

    /** 個人団体紐づけRepository(2025) */
    @Autowired
    private PartnerCombineOrg2025Repository partnerCombineOrg2025Repository;
    
    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param entityWkTbl 複写元ワークテーブルEntity
     * @return 登録完了Id
     */
    public Integer practice(final WkTblPartnerCombineOrgEntity entityWkTbl,final UserPersonLeastDto userDto) {

        Optional<PartnerCombineOrg2025Entity> optional = partnerCombineOrg2025Repository
                .findFirstByOrderByPartnerCombineOrgCodeDesc();
        Integer code = 1;
        if (!optional.isEmpty()) {
            code = optional.get().getPartnerCombineOrgCode();
        }

        PartnerCombineOrg2025Entity entity = new PartnerCombineOrg2025Entity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCombineOrgCode(code);
        entity.setPartnerCombineOrgId(0); // auto_increment明示

        return partnerCombineOrg2025Repository.save(entity).getPartnerCombineOrgId();

    }

}
