package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.constant.Constant;
import com.yamdeng.template.data.dao.CommuteStatsDao;
import com.yamdeng.template.data.dao.VacationStatsDao;
import com.yamdeng.template.vo.stats.OfficeCommuteMonthDStatsVO;
import com.yamdeng.template.vo.stats.OfficeCommuteMonthHStatsVO;
import com.yamdeng.template.vo.stats.OfficeCommuteWeekStatsVO;
import com.yamdeng.template.vo.stats.OfficeVacationMonthStatsVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = BootStandardApplication.class)
@Slf4j
public class VacationStatsDaoTest {

    @Autowired
    private VacationStatsDao vacationStatsDao;

    // 통계_휴가_월별_사용현황 list
    @Test
    void selectVacationMonthStatsList() {
        OfficeVacationMonthStatsVO vo =
                OfficeVacationMonthStatsVO.builder()
                        .baseYear("2022")
                        .build();
        List<OfficeVacationMonthStatsVO> result = vacationStatsDao.selectVacationMonthStatsList(vo);
        int totalCount = vacationStatsDao.selectVacationMonthStatsListTotalCount(vo);
        log.info("selectVacationMonthStatsList result : {}", result);
        log.info("selectVacationMonthStatsList totalCount : {}", totalCount);
    }

}
