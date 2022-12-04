package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.constant.Constant;
import com.yamdeng.template.data.dao.DeptWorkTimeSettingDao;
import com.yamdeng.template.data.dao.PushAlarmDao;
import com.yamdeng.template.vo.db.OfficeDeptWorkTimeSettingVO;
import com.yamdeng.template.vo.db.OfficePushAlarmVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BootStandardApplication.class)
@Slf4j
class DeptWorkTimeSettingDaoTest {

	@Autowired
	private DeptWorkTimeSettingDao deptWorkTimeSettingDao;

	// 부서별 근무시간 상세 정보 조회
	@Test
	void selectDeptWorkTimeSettingInfoByDeptId() {
		OfficeDeptWorkTimeSettingVO result = deptWorkTimeSettingDao.selectDeptWorkTimeSettingInfoByDeptId("dept1");
		log.info("selectDeptWorkTimeSettingInfoByDeptId result : {}", result);
	}

}
