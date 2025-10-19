package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterKanrenshaAddressBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAddressEntity;

/**
 * master_political_organization_address接続用Repository
 */
public interface MasterPoliticalOrganizationAddressRepository
        extends JpaRepository<MasterPoliticalOrganizationAddressEntity, Integer> {

    /**
     * 関連者コードからテーブルId降順で取得する
     *
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<MasterPoliticalOrganizationAddressEntity> findByPoliOrgKanrenshaCodeOrderByMasterPoliticalOrganizationAddressIdDesc(
            String kanrenshaCode);

    /**
     * 作業承認行を抽出する
     *
     * @param startDatetime 検索開始日時
     * @param endDatetime   検索終了日時
     * @param listAccept    作業終了リスト
     * @param pageable      ページング
     * @return 検索結果
     */
    @Query(value = "SELECT master_person_address_id AS kanrensha_address_id, 0 AS kanrensha_master_id,"
            + "                person_kanrensha_code AS kanrensha_code, 1 AS kanrensha_kbn, partner_name AS partner_name,"
            + "                is_latest AS is_latest, address_postal AS address_postal, address_block AS address_block,"
            + "                address_building AS address_building, postal1 AS postal1, postal2 AS postal2,"
            + "                lg_code AS lg_code, machiaza_id AS machiaza_id, blk_id AS blk_id,"
            + "                rsdt_id AS rsdt_id, rsdt2_id AS rsdt2_id,"
            + "                is_postal_edit AS is_postal_edit, is_block_edit AS is_block_edit, is_building_edit AS is_building_edit,"
            + "                is_postal_accept AS is_postal_accept, is_block_accept AS is_block_accept, is_building_accept AS is_building_accept"
            + "              FROM master_person_address " + "                WHERE insert_timestamp between ?1 and ?2"
            + "                  AND (is_postal_edit = 1 OR is_block_edit = 1 OR is_building_edit = 1)"
            + "                  AND (is_postal_accept IN ?3 OR is_block_accept IN ?3 OR is_building_accept IN ?3)"
            + "                  AND is_latest = 1" + "  union  "
            + "SELECT master_corporation_address_id AS kanrensha_address_id, 0 AS kanrensha_master_id,"
            + "                corp_kanrensha_code AS kanrensha_code, 2 AS kanrensha_kbn, partner_name AS partner_name,"
            + "                is_latest AS is_latest, address_postal AS address_postal, address_block AS address_block,"
            + "                address_building AS address_building, postal1 AS postal1, postal2 AS postal2,"
            + "                lg_code AS lg_code, machiaza_id AS machiaza_id, blk_id AS blk_id,"
            + "                rsdt_id AS rsdt_id, rsdt2_id AS rsdt2_id,"
            + "                is_postal_edit AS is_postal_edit, is_block_edit AS is_block_edit, is_building_edit AS is_building_edit,"
            + "                is_postal_accept AS is_postal_accept, is_block_accept AS is_block_accept, is_building_accept AS is_building_accept"
            + "              FROM master_corporation_address "
            + "                WHERE insert_timestamp between ?1 and ?2 "
            + "                  AND (is_postal_edit = 1 OR is_block_edit = 1 OR is_building_edit = 1)"
            + "                  AND (is_postal_accept IN ?3 OR is_block_accept IN ?3 OR is_building_accept IN ?3)"
            + "                  AND is_latest = 1 " + "  union  "
            + "SELECT master_political_organization_address_id AS kanrensha_address_id, 0 AS kanrensha_master_id,"
            + "                poli_org_kanrensha_code AS kanrensha_code, 3 AS kanrensha_kbn, partner_name AS partner_name,"
            + "                is_latest AS is_latest, address_postal AS address_postal, address_block AS address_block,"
            + "                address_building AS address_building, postal1 AS postal1, postal2 AS postal2,"
            + "                lg_code AS lg_code, machiaza_id AS machiaza_id, blk_id AS blk_id,"
            + "                rsdt_id AS rsdt_id, rsdt2_id AS rsdt2_id,"
            + "                is_postal_edit AS is_postal_edit, is_block_edit AS is_block_edit, is_building_edit AS is_building_edit,"
            + "                is_postal_accept AS is_postal_accept, is_block_accept AS is_block_accept, is_building_accept AS is_building_accept"
            + "              FROM master_political_organization_address "
            + "                WHERE insert_timestamp between ?1 and ?2"
            + "                  AND (is_postal_edit = 1 OR is_block_edit = 1 OR is_building_edit = 1)"
            + "                  AND (is_postal_accept IN ?3 OR is_block_accept IN ?3 OR is_building_accept IN ?3)"
            + "                  AND is_latest = 1" + "", nativeQuery = true)
    List<MasterKanrenshaAddressBaseEntity> findIsEditData(LocalDateTime startDatetime, LocalDateTime endDatetime,
            List<Boolean> listAccept, Pageable pageable);

    /**
     * 作業承認行をカウントする
     *
     * @param startDatetime 検索開始日時
     * @param endDatetime   検索狩猟日時
     * @param listAccept    作業終了フラグリスト
     * @return 該当件数
     */
    @Query(value = "WITH person_sum AS ( SELECT count(*) AS person_count FROM master_person_address "
            + "                           WHERE insert_timestamp between ?1 and ?2"
            + "                              AND (is_postal_edit = 1 OR is_block_edit = 1 OR is_building_edit = 1)"
            + "                              AND (is_postal_accept IN ?3 OR is_block_accept IN ?3 OR is_building_accept IN ?3)"
            + "                              AND is_latest = 1 ),corp_sum AS ("
            + "    SELECT count(*) AS corp_count FROM master_corporation_address "
            + "                            WHERE insert_timestamp between ?1 and ?2 "
            + "                              AND (is_postal_edit = 1 OR is_block_edit = 1 OR is_building_edit = 1)"
            + "                              AND (is_postal_accept IN ?3 OR is_block_accept IN ?3 OR is_building_accept IN ?3)"
            + "                              AND is_latest = 1 ),poli_org_sum AS ("
            + "     SELECT count(*) AS poli_count FROM master_political_organization_address "
            + "                            WHERE insert_timestamp between ?1 and ?2"
            + "                              AND (is_postal_edit = 1 OR is_block_edit = 1 OR is_building_edit = 1)"
            + "                              AND (is_postal_accept IN ?3 OR is_block_accept IN ?3 OR is_building_accept IN ?3) "
            + "                              AND is_latest = 1 )"
            + "     select person_count+corp_count+poli_count from person_sum,corp_sum,poli_org_sum", nativeQuery = true)
    Integer countIsEditData(LocalDateTime startDatetime, LocalDateTime endDatetime, List<Boolean> listAccept);
}
