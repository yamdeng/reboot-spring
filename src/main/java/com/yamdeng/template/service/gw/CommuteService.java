package com.yamdeng.template.service.gw;

import com.yamdeng.template.constant.Constant;
import com.yamdeng.template.data.dao.CommuteDao;
import com.yamdeng.template.exception.ApplicationException;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommuteService {

    private final CommuteDao commuteDao;

    private String getClientIp() {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String clientIp = httpServletRequest.getHeader("X-FORWARDED-FOR");
        if (!StringUtils.hasText(clientIp)|| "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = httpServletRequest.getHeader("Proxy-Client-IP");
        }
        if (!StringUtils.hasText(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = httpServletRequest.getHeader("WL-Proxy-Client-IP");
        }
        if (!StringUtils.hasText(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = httpServletRequest.getHeader("HTTP_CLIENT_IP");
        }
        if (!StringUtils.hasText(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (!StringUtils.hasText(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = httpServletRequest.getRemoteAddr();
        }
        return clientIp;
    }

    public OfficeCommuteDayVO detail(OfficeCommuteDayVO requestVO) {
        requestVO.setLoginUserId("yamdeng");
        return commuteDao.selectCommuteInfo(requestVO);
    }

    public OfficeCommuteDayVO startWork(OfficeCommuteDayVO requestVO) {
        requestVO.setLoginUserId("yamdeng");
        OfficeCommuteDayVO beforeCommuteDayVo = commuteDao.selectCommuteInfo(requestVO);
        if(beforeCommuteDayVo == null) {
            throw new ApplicationException("출/퇴근 정보가 존재하지 않습니다. 신규 입사지는 입사 다음날부터 출퇴근 입력이 가능합니다.");
        } else if(beforeCommuteDayVo.getStartWorkDate() != null) {
            return beforeCommuteDayVo;
        } else {
            String clientIp = getClientIp();
            requestVO.setStartWorkIp(clientIp);
            // TODO : 상태, 결과 처리
            String inWorkYn = requestVO.getInWorkYn();
            if("Y".equals(inWorkYn)) {
                requestVO.setWorkStatusCode(Constant.CODE_WORK_STATUS_ING);
            } else {
                requestVO.setWorkStatusCode(Constant.CODE_WORK_STATUS_HOME_ING);
            }
            requestVO.setWorkResultCode(Constant.CODE_WORK_RESULT_SUCCESS_NORMAL);
            commuteDao.startWork(requestVO);
            return commuteDao.selectCommuteInfo(requestVO);
        }
    }

    public OfficeCommuteDayVO outWork(OfficeCommuteDayVO requestVO) {
        requestVO.setLoginUserId("yamdeng");
        OfficeCommuteDayVO beforeCommuteDayVo = commuteDao.selectCommuteInfo(requestVO);
        if(beforeCommuteDayVo == null) {
            throw new ApplicationException("출/퇴근 정보가 존재하지 않습니다. 신규 입사지는 입사 다음날부터 출퇴근 입력이 가능합니다.");
        } else if(beforeCommuteDayVo.getOutWorkDate() != null) {
            return beforeCommuteDayVo;
        } else {
            // TODO : 상태, 결과 처리
            String clientIp = getClientIp();
            requestVO.setStartWorkIp(clientIp);
            requestVO.setWorkStatusCode(Constant.CODE_WORK_STATUS_END);
            requestVO.setWorkResultCode(Constant.CODE_WORK_RESULT_SUCCESS_NORMAL);
            LocalDateTime startWorkDate = beforeCommuteDayVo.getStartWorkDate();
            LocalDateTime now = LocalDateTime.now();
            long minutes = startWorkDate.until( now, ChronoUnit.MINUTES );
            double minuteValue = (double)minutes / (double)60;
            double workedTimeValue = Math.ceil(minuteValue * 10) / 10.0;
            requestVO.setWorkedTimeValue(workedTimeValue);
            commuteDao.outWork(requestVO);
            return commuteDao.selectCommuteInfo(requestVO);
        }
    }

    public List<OfficeCommuteDayVO> selectCommuteListByDeptKey(OfficeCommuteDayVO requestVO) {
        return commuteDao.selectCommuteListByDeptKey(requestVO);
    }

}
