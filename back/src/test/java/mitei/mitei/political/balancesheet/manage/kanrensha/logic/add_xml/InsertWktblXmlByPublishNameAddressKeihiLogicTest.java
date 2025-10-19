package mitei.mitei.political.balancesheet.manage.kanrensha.logic.add_xml; // NOPMD

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0705IncomeRelatedToGrantsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0714ConstsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0716RelatedToGrantsDtoDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071401Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071402Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071403Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070500IncomeRelatedToGrantsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row071415OrdinaryExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row071600ExpendituresRelatedToGrantsProvidedDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070500IncomeRelatedToGrantsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071401UtilityCostsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071402EquipmentCostsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071403OfficeExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071600ExpendituresRelatedToGrantsDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertWktblXmlByPublishNameAddressKeihiLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertWktblXmlByPublishNameAddressKeihiLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertWktblXmlByPublishNameAddressKeihiLogic insertWktblXmlByPublishNameAddressKeihiLogic;

    /** XMLから最小マスタ登録Repositry */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** 判定理由 */
    private static final String JUDGE_REASON = "関連者区分が未決定です;";

    @Test
    @Tag("TableTruncate") // NOPMD
    @Transactional
    @Sql("delete_wk_tbl_master_all_by_xml.sql") // NOPMD
    void test0705() throws Exception {

        AllBookDto allBookDto = new AllBookDto();

        allBookDto.setAllSheet0705IncomeRelatedToGrantsDto(new AllSheet0705IncomeRelatedToGrantsDto());
        allBookDto.setAllSheet0714ConstsDto(new AllSheet0714ConstsDto());
        allBookDto.setAllSheet0716RelatedToGrantsDtoDto(new AllSheet0716RelatedToGrantsDtoDto());

        allBookDto.getAllSheet0705IncomeRelatedToGrantsDto()
                .setSheet070500IncomeRelatedToGrantsDto(new Sheet070500IncomeRelatedToGrantsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071401Dto(new AllSheetKbn071401Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071401Dto()
                .setSheet071401UtilityCostsDto(new Sheet071401UtilityCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071402Dto(new AllSheetKbn071402Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071402Dto()
                .setSheet071402EquipmentCostsDto(new Sheet071402EquipmentCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071403Dto(new AllSheetKbn071403Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071403Dto()
                .setSheet071403OfficeExpensesDto(new Sheet071403OfficeExpensesDto());
        allBookDto.getAllSheet0716RelatedToGrantsDtoDto()
                .setSheet071600ExpendituresRelatedToGrantsDto(new Sheet071600ExpendituresRelatedToGrantsDto());

        Row070500IncomeRelatedToGrantsDto rowDto = new Row070500IncomeRelatedToGrantsDto();
        rowDto.setHonbuShibuName("名称1"); // NOPMD
        rowDto.setJimushoJuusho("事務所住所1"); // NOPMD

        allBookDto.getAllSheet0705IncomeRelatedToGrantsDto().getSheet070500IncomeRelatedToGrantsDto().getList()
                .add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressKeihiLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getHonbuShibuName(), entity.getInputSrcName());
        assertEquals(rowDto.getJimushoJuusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getHonbuShibuName(), entity.getPartnerName());
        assertEquals(rowDto.getJimushoJuusho(), entity.getAllAddress());
        assertEquals((short) 5, entity.getYoushikiKbn());
        assertEquals((short) 0, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_wk_tbl_master_all_by_xml.sql")
    void test0716() throws Exception {

        AllBookDto allBookDto = new AllBookDto();

        allBookDto.setAllSheet0705IncomeRelatedToGrantsDto(new AllSheet0705IncomeRelatedToGrantsDto());
        allBookDto.setAllSheet0714ConstsDto(new AllSheet0714ConstsDto());
        allBookDto.setAllSheet0716RelatedToGrantsDtoDto(new AllSheet0716RelatedToGrantsDtoDto());

        allBookDto.getAllSheet0705IncomeRelatedToGrantsDto()
                .setSheet070500IncomeRelatedToGrantsDto(new Sheet070500IncomeRelatedToGrantsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071401Dto(new AllSheetKbn071401Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071401Dto()
                .setSheet071401UtilityCostsDto(new Sheet071401UtilityCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071402Dto(new AllSheetKbn071402Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071402Dto()
                .setSheet071402EquipmentCostsDto(new Sheet071402EquipmentCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071403Dto(new AllSheetKbn071403Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071403Dto()
                .setSheet071403OfficeExpensesDto(new Sheet071403OfficeExpensesDto());
        allBookDto.getAllSheet0716RelatedToGrantsDtoDto()
                .setSheet071600ExpendituresRelatedToGrantsDto(new Sheet071600ExpendituresRelatedToGrantsDto());

        Row071600ExpendituresRelatedToGrantsProvidedDto rowDto = new Row071600ExpendituresRelatedToGrantsProvidedDto();
        rowDto.setHonShibuName("名称1");
        rowDto.setJusho("事務所住所1");

        allBookDto.getAllSheet0716RelatedToGrantsDtoDto().getSheet071600ExpendituresRelatedToGrantsDto().getList()
                .add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressKeihiLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getHonShibuName(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getHonShibuName(), entity.getPartnerName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals((short) 16, entity.getYoushikiKbn());
        assertEquals((short) 0, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_wk_tbl_master_all_by_xml.sql")
    void test071401() throws Exception {

        AllBookDto allBookDto = new AllBookDto();

        allBookDto.setAllSheet0705IncomeRelatedToGrantsDto(new AllSheet0705IncomeRelatedToGrantsDto());
        allBookDto.setAllSheet0714ConstsDto(new AllSheet0714ConstsDto());
        allBookDto.setAllSheet0716RelatedToGrantsDtoDto(new AllSheet0716RelatedToGrantsDtoDto());

        allBookDto.getAllSheet0705IncomeRelatedToGrantsDto()
                .setSheet070500IncomeRelatedToGrantsDto(new Sheet070500IncomeRelatedToGrantsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071401Dto(new AllSheetKbn071401Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071401Dto()
                .setSheet071401UtilityCostsDto(new Sheet071401UtilityCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071402Dto(new AllSheetKbn071402Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071402Dto()
                .setSheet071402EquipmentCostsDto(new Sheet071402EquipmentCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071403Dto(new AllSheetKbn071403Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071403Dto()
                .setSheet071403OfficeExpensesDto(new Sheet071403OfficeExpensesDto());
        allBookDto.getAllSheet0716RelatedToGrantsDtoDto()
                .setSheet071600ExpendituresRelatedToGrantsDto(new Sheet071600ExpendituresRelatedToGrantsDto());

        Row071415OrdinaryExpensesDto rowDto = new Row071415OrdinaryExpensesDto();
        rowDto.setName("名称1");
        rowDto.setJusho("事務所住所1");

        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071401Dto().getSheet071401UtilityCostsDto().getList()
                .add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressKeihiLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getPartnerName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals((short) 14, entity.getYoushikiKbn());
        assertEquals((short) 2, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("delete_wk_tbl_master_all_by_xml.sql")
    void test071402() throws Exception {

        AllBookDto allBookDto = new AllBookDto();

        allBookDto.setAllSheet0705IncomeRelatedToGrantsDto(new AllSheet0705IncomeRelatedToGrantsDto());
        allBookDto.setAllSheet0714ConstsDto(new AllSheet0714ConstsDto());
        allBookDto.setAllSheet0716RelatedToGrantsDtoDto(new AllSheet0716RelatedToGrantsDtoDto());

        allBookDto.getAllSheet0705IncomeRelatedToGrantsDto()
                .setSheet070500IncomeRelatedToGrantsDto(new Sheet070500IncomeRelatedToGrantsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071401Dto(new AllSheetKbn071401Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071401Dto()
                .setSheet071401UtilityCostsDto(new Sheet071401UtilityCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071402Dto(new AllSheetKbn071402Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071402Dto()
                .setSheet071402EquipmentCostsDto(new Sheet071402EquipmentCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071403Dto(new AllSheetKbn071403Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071403Dto()
                .setSheet071403OfficeExpensesDto(new Sheet071403OfficeExpensesDto());
        allBookDto.getAllSheet0716RelatedToGrantsDtoDto()
                .setSheet071600ExpendituresRelatedToGrantsDto(new Sheet071600ExpendituresRelatedToGrantsDto());

        Row071415OrdinaryExpensesDto rowDto = new Row071415OrdinaryExpensesDto();
        rowDto.setName("名称1");
        rowDto.setJusho("事務所住所1");

        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071402Dto().getSheet071402EquipmentCostsDto().getList()
                .add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressKeihiLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getPartnerName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals((short) 14, entity.getYoushikiKbn());
        assertEquals((short) 3, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("delete_wk_tbl_master_all_by_xml.sql")
    void test071403() throws Exception {

        AllBookDto allBookDto = new AllBookDto();

        allBookDto.setAllSheet0705IncomeRelatedToGrantsDto(new AllSheet0705IncomeRelatedToGrantsDto());
        allBookDto.setAllSheet0714ConstsDto(new AllSheet0714ConstsDto());
        allBookDto.setAllSheet0716RelatedToGrantsDtoDto(new AllSheet0716RelatedToGrantsDtoDto());

        allBookDto.getAllSheet0705IncomeRelatedToGrantsDto()
                .setSheet070500IncomeRelatedToGrantsDto(new Sheet070500IncomeRelatedToGrantsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071401Dto(new AllSheetKbn071401Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071401Dto()
                .setSheet071401UtilityCostsDto(new Sheet071401UtilityCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071402Dto(new AllSheetKbn071402Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071402Dto()
                .setSheet071402EquipmentCostsDto(new Sheet071402EquipmentCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071403Dto(new AllSheetKbn071403Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071403Dto()
                .setSheet071403OfficeExpensesDto(new Sheet071403OfficeExpensesDto());
        allBookDto.getAllSheet0716RelatedToGrantsDtoDto()
                .setSheet071600ExpendituresRelatedToGrantsDto(new Sheet071600ExpendituresRelatedToGrantsDto());

        Row071415OrdinaryExpensesDto rowDto = new Row071415OrdinaryExpensesDto();
        rowDto.setName("名称1");
        rowDto.setJusho("事務所住所1");

        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071403Dto().getSheet071403OfficeExpensesDto().getList()
                .add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressKeihiLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getPartnerName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals((short) 14, entity.getYoushikiKbn());
        assertEquals((short) 4, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

}
