package mitei.mitei.political.balancesheet.manage.kanrensha.service.works_approval;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 職業承認作業保存Service
 */
@Service
public class SaveApprovalShokugyouService {

    /** 関連者個人BaseRespository */
    @Autowired
    private MasterPersonBaseRepository masterPersonBaseRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param listShokugyou 関連者個人基本リスト
     * @param userDto       職業最小限Dto
     * @return 処理行数
     */
    public Integer practice(final List<MasterPersonBaseEntity> listShokugyou, final UserPersonLeastDto userDto) {

        List<MasterPersonBaseEntity> listHistory = new ArrayList<>();
        List<MasterPersonBaseEntity> listInsert = new ArrayList<>();

        for (MasterPersonBaseEntity entity : listShokugyou) {

            MasterPersonBaseEntity entityHistory = this.judgeSaveEntity(entity, userDto);
            if (!Objects.isNull(entityHistory)) {
                listHistory.add(entityHistory);

                listInsert.add(this.cloneNewEntity(entity, userDto));
            }
        }

        // TODO マスタ本体も変更

        masterPersonBaseRepository.saveAll(listHistory);
        return masterPersonBaseRepository.saveAll(listInsert).size();
    }

    private MasterPersonBaseEntity judgeSaveEntity(final MasterPersonBaseEntity entity,
            final UserPersonLeastDto userDto) {

        // Idで取得できない時は全処理中断
        MasterPersonBaseEntity previousEntity = masterPersonBaseRepository.findById(entity.getMasterPersonBaseId())
                .get();

        // 職業に一切変更がなければnullを戻してこのEntityについては処理をしない
        if (Objects.equals(entity.getGyoushu(), previousEntity.getGyoushu())
                && Objects.equals(entity.getYakushoku(), previousEntity.getYakushoku())
                && Objects.equals(entity.getShokugyouUserWrite(), previousEntity.getShokugyouUserWrite())
                && Objects.equals(entity.getCorpName(), previousEntity.getCorpName())
                && Objects.equals(entity.getCorpAddress(), previousEntity.getCorpAddress())
                && Objects.equals(entity.getIsShokyouEdit(), previousEntity.getIsShokyouEdit())
                && Objects.equals(entity.getIsShokyouAccept(), previousEntity.getIsShokyouAccept())) {
            return null;
        } else {
            // 変更があった場合は履歴に変更したEntityを戻す
            MasterPersonBaseEntity entityChange = new MasterPersonBaseEntity();
            BeanUtils.copyProperties(previousEntity, entityChange);
            setTableDataHistoryUtil.practiceDelete(userDto, entityChange);
            return entityChange;
        }
    }

    private MasterPersonBaseEntity cloneNewEntity(final MasterPersonBaseEntity entity,
            final UserPersonLeastDto userDto) {

        MasterPersonBaseEntity entityChange = new MasterPersonBaseEntity();

        BeanUtils.copyProperties(entity, entityChange);
        entityChange.setMasterPersonBaseId(0);
        setTableDataHistoryUtil.practiceInsert(userDto, entityChange);
        return entityChange;
    }

}
