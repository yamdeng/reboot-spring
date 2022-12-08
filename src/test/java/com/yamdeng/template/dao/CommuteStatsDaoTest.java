package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.constant.Constant;
import com.yamdeng.template.data.dao.CommuteStatsDao;
import com.yamdeng.template.vo.stats.OfficeCommuteMonthDStatsVO;
import com.yamdeng.template.vo.stats.OfficeCommuteMonthHStatsVO;
import com.yamdeng.template.vo.stats.OfficeCommuteWeekStatsVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = BootStandardApplication.class)
@Slf4j
public class CommuteStatsDaoTest {

    @Autowired
    private CommuteStatsDao commuteStatsDao;

    // 통계_출퇴근_주간 list
    @Test
    void selectCommuteStatsWeekList() {
        OfficeCommuteWeekStatsVO vo =
                OfficeCommuteWeekStatsVO.builder()
                        .mondayStartDateStr("20221203")
                        .workWeekTimeKind(Constant.CODE_WEEK_NORMAL_WORK_TIME_BASE_LESS)
//                        .workWeekTimeKind(Constant.CODE_WEEK_NORMAL_WORK_TIME_BASE_GREATER)
                        .build();
        List<OfficeCommuteWeekStatsVO> result = commuteStatsDao.selectCommuteStatsWeekList(vo);
        int totalCount = commuteStatsDao.selectCommuteStatsWeekListTotalCount(vo);
        log.info("selectCommuteStatsWeekList result : {}", result);
        log.info("selectCommuteStatsWeekList totalCount : {}", totalCount);
    }

    // 통계_출퇴근_월간_주별 list
    @Test
    void selectCommuteStatsMonthDList() {
        OfficeCommuteMonthDStatsVO vo =
                OfficeCommuteMonthDStatsVO.builder()
                        .baseMonthStr("202212")
                        .workWeekTimeKind(Constant.CODE_WEEK_NORMAL_WORK_TIME_BASE_LESS)
//                        .workWeekTimeKind(Constant.CODE_WEEK_NORMAL_WORK_TIME_BASE_GREATER)
                        .build();
        List<OfficeCommuteMonthDStatsVO> result = commuteStatsDao.selectCommuteStatsMonthDList(vo);
        int totalCount = commuteStatsDao.selectCommuteStatsMonthDListTotalCount(vo);
        log.info("selectCommuteStatsMonthDList result : {}", result);
        log.info("selectCommuteStatsMonthDList totalCount : {}", totalCount);
    }

    // 통계_출퇴근_월간_주별 list
    @Test
    void selectCommuteStatsMonthHList() {
        OfficeCommuteMonthHStatsVO vo =
                OfficeCommuteMonthHStatsVO.builder()
                        .baseMonthStr("202212")
                        .build();
        List<OfficeCommuteMonthHStatsVO> result = commuteStatsDao.selectCommuteStatsMonthHList(vo);
        int totalCount = commuteStatsDao.selectCommuteStatsMonthHListTotalCount(vo);
        log.info("selectCommuteStatsMonthHList result : {}", result);
        log.info("selectCommuteStatsMonthHList totalCount : {}", totalCount);
    }

}
