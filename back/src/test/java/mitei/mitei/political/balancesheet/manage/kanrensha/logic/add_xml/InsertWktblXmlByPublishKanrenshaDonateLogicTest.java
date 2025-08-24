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
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0707DonateDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0708MediationDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn070701Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn070702Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn070703Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn070801Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn070802Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn070803Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070711DonateDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070812MediationDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070701DonatePersonDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070702DonateGroupDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070703DonatePoliticOrgDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070801MediationPersonDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070802MediationGroupDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070803MediationPoliticOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertWktblXmlByPublishKanrenshaDonateLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertWktblXmlByPublishKanrenshaDonateLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertWktblXmlByPublishKanrenshaDonateLogic insertWktblXmlByPublishKanrenshaDonateLogic;

    /** XMLから最小マスタ登録Repositry */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** 判定理由 */
    private static final String JUDGE_REASON = "別テ)";

    @Test
    @Tag("TableTruncate") // NOPMD
    @Transactional
    @Sql("delete_wk_tbl_master_all_by_xml.sql") // NOPMD
    void test0701() throws Exception {

        AllBookDto allBookDto = new AllBookDto();
        allBookDto.setAllSheet0707DonateDto(new AllSheet0707DonateDto());
        allBookDto.setAllSheet0708MediationDto(new AllSheet0708MediationDto());

        allBookDto.getAllSheet0707DonateDto().setAllSheetKbn070701Dto(new AllSheetKbn070701Dto());
        allBookDto.getAllSheet0707DonateDto().setAllSheetKbn070702Dto(new AllSheetKbn070702Dto());
        allBookDto.getAllSheet0707DonateDto().setAllSheetKbn070703Dto(new AllSheetKbn070703Dto());
        allBookDto.getAllSheet0708MediationDto().setAllSheetKbn070801Dto(new AllSheetKbn070801Dto());
        allBookDto.getAllSheet0708MediationDto().setAllSheetKbn070802Dto(new AllSheetKbn070802Dto());
        allBookDto.getAllSheet0708MediationDto().setAllSheetKbn070803Dto(new AllSheetKbn070803Dto());

        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070701Dto()
                .setSheet070701DonatePersonDto(new Sheet070701DonatePersonDto());
        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070702Dto()
                .setSheet070702DonateGroupDto(new Sheet070702DonateGroupDto());
        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070703Dto()
                .setSheet070703DonatePoliticOrgDto(new Sheet070703DonatePoliticOrgDto());

        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070801Dto()
                .setSheet070801MediationPersonDto(new Sheet070801MediationPersonDto());
        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070802Dto()
                .setSheet070802MediationGroupDto(new Sheet070802MediationGroupDto());
        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070803Dto()
                .setSheet070803MediationPoliticOrgDto(new Sheet070803MediationPoliticOrgDto());

        Row070711DonateDto rowDto = new Row070711DonateDto();
        rowDto.setKifusha("名称A"); // NOPMD
        rowDto.setJusho("事務所住所B"); // NOPMD
        rowDto.setShokugyou("職業または団体代表者C"); // NOPMD

        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070701Dto().getSheet070701DonatePersonDto().getList()
                .add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishKanrenshaDonateLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getKifusha(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getKifusha(), entity.getPartnerName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals(rowDto.getShokugyou(), entity.getInputSrcKey());
        assertEquals(rowDto.getShokugyou(), entity.getPersonShokugyou());
        assertEquals((short) 7, entity.getYoushikiKbn());
        assertEquals((short) 1, entity.getYoushikiEdaKbn());
        assertEquals((short) 1, entity.getKanrenshaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_wk_tbl_master_all_by_xml.sql")
    void test0702() throws Exception {

        AllBookDto allBookDto = new AllBookDto();
        allBookDto.setAllSheet0707DonateDto(new AllSheet0707DonateDto());
        allBookDto.setAllSheet0708MediationDto(new AllSheet0708MediationDto());

        allBookDto.getAllSheet0707DonateDto().setAllSheetKbn070701Dto(new AllSheetKbn070701Dto());
        allBookDto.getAllSheet0707DonateDto().setAllSheetKbn070702Dto(new AllSheetKbn070702Dto());
        allBookDto.getAllSheet0707DonateDto().setAllSheetKbn070703Dto(new AllSheetKbn070703Dto());
        allBookDto.getAllSheet0708MediationDto().setAllSheetKbn070801Dto(new AllSheetKbn070801Dto());
        allBookDto.getAllSheet0708MediationDto().setAllSheetKbn070802Dto(new AllSheetKbn070802Dto());
        allBookDto.getAllSheet0708MediationDto().setAllSheetKbn070803Dto(new AllSheetKbn070803Dto());

        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070701Dto()
                .setSheet070701DonatePersonDto(new Sheet070701DonatePersonDto());
        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070702Dto()
                .setSheet070702DonateGroupDto(new Sheet070702DonateGroupDto());
        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070703Dto()
                .setSheet070703DonatePoliticOrgDto(new Sheet070703DonatePoliticOrgDto());

        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070801Dto()
                .setSheet070801MediationPersonDto(new Sheet070801MediationPersonDto());
        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070802Dto()
                .setSheet070802MediationGroupDto(new Sheet070802MediationGroupDto());
        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070803Dto()
                .setSheet070803MediationPoliticOrgDto(new Sheet070803MediationPoliticOrgDto());

        Row070711DonateDto rowDto = new Row070711DonateDto();
        rowDto.setKifusha("名称A");
        rowDto.setJusho("事務所住所B");
        rowDto.setShokugyou("職業または団体代表者C");

        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070702Dto().getSheet070702DonateGroupDto().getList()
                .add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishKanrenshaDonateLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getKifusha(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getKifusha(), entity.getPartnerName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals(rowDto.getShokugyou(), entity.getInputSrcKey());
        assertEquals(rowDto.getShokugyou(), entity.getPersonShokugyou());
        assertEquals((short) 7, entity.getYoushikiKbn());
        assertEquals((short) 2, entity.getYoushikiEdaKbn());
        assertEquals((short) 2, entity.getKanrenshaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_wk_tbl_master_all_by_xml.sql")
    void test0703() throws Exception {

        AllBookDto allBookDto = new AllBookDto();
        allBookDto.setAllSheet0707DonateDto(new AllSheet0707DonateDto());
        allBookDto.setAllSheet0708MediationDto(new AllSheet0708MediationDto());

        allBookDto.getAllSheet0707DonateDto().setAllSheetKbn070701Dto(new AllSheetKbn070701Dto());
        allBookDto.getAllSheet0707DonateDto().setAllSheetKbn070702Dto(new AllSheetKbn070702Dto());
        allBookDto.getAllSheet0707DonateDto().setAllSheetKbn070703Dto(new AllSheetKbn070703Dto());
        allBookDto.getAllSheet0708MediationDto().setAllSheetKbn070801Dto(new AllSheetKbn070801Dto());
        allBookDto.getAllSheet0708MediationDto().setAllSheetKbn070802Dto(new AllSheetKbn070802Dto());
        allBookDto.getAllSheet0708MediationDto().setAllSheetKbn070803Dto(new AllSheetKbn070803Dto());

        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070701Dto()
                .setSheet070701DonatePersonDto(new Sheet070701DonatePersonDto());
        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070702Dto()
                .setSheet070702DonateGroupDto(new Sheet070702DonateGroupDto());
        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070703Dto()
                .setSheet070703DonatePoliticOrgDto(new Sheet070703DonatePoliticOrgDto());

        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070801Dto()
                .setSheet070801MediationPersonDto(new Sheet070801MediationPersonDto());
        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070802Dto()
                .setSheet070802MediationGroupDto(new Sheet070802MediationGroupDto());
        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070803Dto()
                .setSheet070803MediationPoliticOrgDto(new Sheet070803MediationPoliticOrgDto());

        Row070711DonateDto rowDto = new Row070711DonateDto();
        rowDto.setKifusha("名称A");
        rowDto.setJusho("事務所住所B");
        rowDto.setShokugyou("職業または団体代表者C");

        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070703Dto().getSheet070703DonatePoliticOrgDto().getList()
                .add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishKanrenshaDonateLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getKifusha(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getKifusha(), entity.getPartnerName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals(rowDto.getShokugyou(), entity.getInputSrcKey());
        assertEquals(rowDto.getShokugyou(), entity.getPersonShokugyou());
        assertEquals((short) 7, entity.getYoushikiKbn());
        assertEquals((short) 3, entity.getYoushikiEdaKbn());
        assertEquals((short) 3, entity.getKanrenshaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_wk_tbl_master_all_by_xml.sql")
    void test0801() throws Exception {

        AllBookDto allBookDto = new AllBookDto();
        allBookDto.setAllSheet0707DonateDto(new AllSheet0707DonateDto());
        allBookDto.setAllSheet0708MediationDto(new AllSheet0708MediationDto());

        allBookDto.getAllSheet0707DonateDto().setAllSheetKbn070701Dto(new AllSheetKbn070701Dto());
        allBookDto.getAllSheet0707DonateDto().setAllSheetKbn070702Dto(new AllSheetKbn070702Dto());
        allBookDto.getAllSheet0707DonateDto().setAllSheetKbn070703Dto(new AllSheetKbn070703Dto());
        allBookDto.getAllSheet0708MediationDto().setAllSheetKbn070801Dto(new AllSheetKbn070801Dto());
        allBookDto.getAllSheet0708MediationDto().setAllSheetKbn070802Dto(new AllSheetKbn070802Dto());
        allBookDto.getAllSheet0708MediationDto().setAllSheetKbn070803Dto(new AllSheetKbn070803Dto());

        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070701Dto()
                .setSheet070701DonatePersonDto(new Sheet070701DonatePersonDto());
        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070702Dto()
                .setSheet070702DonateGroupDto(new Sheet070702DonateGroupDto());
        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070703Dto()
                .setSheet070703DonatePoliticOrgDto(new Sheet070703DonatePoliticOrgDto());

        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070801Dto()
                .setSheet070801MediationPersonDto(new Sheet070801MediationPersonDto());
        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070802Dto()
                .setSheet070802MediationGroupDto(new Sheet070802MediationGroupDto());
        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070803Dto()
                .setSheet070803MediationPoliticOrgDto(new Sheet070803MediationPoliticOrgDto());

        Row070812MediationDto rowDto = new Row070812MediationDto();
        rowDto.setName("名称A");
        rowDto.setJuusho("事務所住所B");
        rowDto.setShokugyou("職業または団体代表者C");

        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070801Dto().getSheet070801MediationPersonDto().getList()
                .add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishKanrenshaDonateLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJuusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getPartnerName());
        assertEquals(rowDto.getJuusho(), entity.getAllAddress());
        assertEquals(rowDto.getShokugyou(), entity.getInputSrcKey());
        assertEquals(rowDto.getShokugyou(), entity.getPersonShokugyou());
        assertEquals((short) 8, entity.getYoushikiKbn());
        assertEquals((short) 1, entity.getYoushikiEdaKbn());
        assertEquals((short) 1, entity.getKanrenshaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_wk_tbl_master_all_by_xml.sql")
    void test0802() throws Exception {

        AllBookDto allBookDto = new AllBookDto();
        allBookDto.setAllSheet0707DonateDto(new AllSheet0707DonateDto());
        allBookDto.setAllSheet0708MediationDto(new AllSheet0708MediationDto());

        allBookDto.getAllSheet0707DonateDto().setAllSheetKbn070701Dto(new AllSheetKbn070701Dto());
        allBookDto.getAllSheet0707DonateDto().setAllSheetKbn070702Dto(new AllSheetKbn070702Dto());
        allBookDto.getAllSheet0707DonateDto().setAllSheetKbn070703Dto(new AllSheetKbn070703Dto());
        allBookDto.getAllSheet0708MediationDto().setAllSheetKbn070801Dto(new AllSheetKbn070801Dto());
        allBookDto.getAllSheet0708MediationDto().setAllSheetKbn070802Dto(new AllSheetKbn070802Dto());
        allBookDto.getAllSheet0708MediationDto().setAllSheetKbn070803Dto(new AllSheetKbn070803Dto());

        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070701Dto()
                .setSheet070701DonatePersonDto(new Sheet070701DonatePersonDto());
        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070702Dto()
                .setSheet070702DonateGroupDto(new Sheet070702DonateGroupDto());
        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070703Dto()
                .setSheet070703DonatePoliticOrgDto(new Sheet070703DonatePoliticOrgDto());

        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070801Dto()
                .setSheet070801MediationPersonDto(new Sheet070801MediationPersonDto());
        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070802Dto()
                .setSheet070802MediationGroupDto(new Sheet070802MediationGroupDto());
        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070803Dto()
                .setSheet070803MediationPoliticOrgDto(new Sheet070803MediationPoliticOrgDto());

        Row070812MediationDto rowDto = new Row070812MediationDto();
        rowDto.setName("名称A");
        rowDto.setJuusho("事務所住所B");
        rowDto.setShokugyou("職業または団体代表者C");

        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070802Dto().getSheet070802MediationGroupDto().getList()
                .add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishKanrenshaDonateLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJuusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getPartnerName());
        assertEquals(rowDto.getJuusho(), entity.getAllAddress());
        assertEquals(rowDto.getShokugyou(), entity.getInputSrcKey());
        assertEquals(rowDto.getShokugyou(), entity.getPersonShokugyou());
        assertEquals((short) 8, entity.getYoushikiKbn());
        assertEquals((short) 2, entity.getYoushikiEdaKbn());
        assertEquals((short) 2, entity.getKanrenshaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_wk_tbl_master_all_by_xml.sql")
    void test0803() throws Exception {

        AllBookDto allBookDto = new AllBookDto();
        allBookDto.setAllSheet0707DonateDto(new AllSheet0707DonateDto());
        allBookDto.setAllSheet0708MediationDto(new AllSheet0708MediationDto());

        allBookDto.getAllSheet0707DonateDto().setAllSheetKbn070701Dto(new AllSheetKbn070701Dto());
        allBookDto.getAllSheet0707DonateDto().setAllSheetKbn070702Dto(new AllSheetKbn070702Dto());
        allBookDto.getAllSheet0707DonateDto().setAllSheetKbn070703Dto(new AllSheetKbn070703Dto());
        allBookDto.getAllSheet0708MediationDto().setAllSheetKbn070801Dto(new AllSheetKbn070801Dto());
        allBookDto.getAllSheet0708MediationDto().setAllSheetKbn070802Dto(new AllSheetKbn070802Dto());
        allBookDto.getAllSheet0708MediationDto().setAllSheetKbn070803Dto(new AllSheetKbn070803Dto());

        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070701Dto()
                .setSheet070701DonatePersonDto(new Sheet070701DonatePersonDto());
        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070702Dto()
                .setSheet070702DonateGroupDto(new Sheet070702DonateGroupDto());
        allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070703Dto()
                .setSheet070703DonatePoliticOrgDto(new Sheet070703DonatePoliticOrgDto());

        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070801Dto()
                .setSheet070801MediationPersonDto(new Sheet070801MediationPersonDto());
        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070802Dto()
                .setSheet070802MediationGroupDto(new Sheet070802MediationGroupDto());
        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070803Dto()
                .setSheet070803MediationPoliticOrgDto(new Sheet070803MediationPoliticOrgDto());

        Row070812MediationDto rowDto = new Row070812MediationDto();
        rowDto.setName("名称A");
        rowDto.setJuusho("事務所住所B");
        rowDto.setShokugyou("職業または団体代表者C");

        allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070803Dto().getSheet070803MediationPoliticOrgDto()
                .getList().add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishKanrenshaDonateLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJuusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getPartnerName());
        assertEquals(rowDto.getJuusho(), entity.getAllAddress());
        assertEquals(rowDto.getShokugyou(), entity.getInputSrcKey());
        assertEquals(rowDto.getShokugyou(), entity.getPersonShokugyou());
        assertEquals((short) 8, entity.getYoushikiKbn());
        assertEquals((short) 3, entity.getYoushikiEdaKbn());
        assertEquals((short) 3, entity.getKanrenshaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

}
