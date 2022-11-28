package com.yamdeng.template.service.gw;

import com.yamdeng.template.data.dao.CommuteDao;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.request.OfficeCommuteDayRequestVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public OfficeCommuteDayVO detail(OfficeCommuteDayRequestVO requestVO) {
        requestVO.setLoginUserId("yamdeng");
        return commuteDao.selectCommuteInfoByUserId(requestVO);
    }

    public OfficeCommuteDayVO startWork(OfficeCommuteDayRequestVO requestVO) {
        requestVO.setLoginUserId("yamdeng");
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String clientIp = req.getHeader("X-FORWARDED-FOR");
        if (clientIp == null)
            clientIp = req.getRemoteAddr();
//        String clientIp = request.getHeader("X-Forwarded-For");
//        if (StringUtils.isEmpty(clientIp)|| "unknown".equalsIgnoreCase(clientIp)) {
//            //Proxy 서버인 경우
//            clientIp = request.getHeader("Proxy-Client-IP");
//        }
//        if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
//            //Weblogic 서버인 경우
//            clientIp = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
//            clientIp = request.getHeader("HTTP_CLIENT_IP");
//        }
//        if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
//            clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
//        }
//        if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
//            clientIp = request.getRemoteAddr();
//        }
        requestVO.setStartWorkIp(clientIp);
        // TODO : 상태, 결과 처리
        String inWorkYn = requestVO.getInWorkYn();
        if("Y".equals(inWorkYn)) {
            requestVO.setWorkStatusCode("ING");
        } else {
            requestVO.setWorkStatusCode("ING_HOME");
        }
        requestVO.setWorkResultCode("SUCCESS_NORMAL");
        commuteDao.startWork(requestVO);
        return commuteDao.selectCommuteInfoByUserId(requestVO);
    }

    public OfficeCommuteDayVO outWork(OfficeCommuteDayRequestVO requestVO) {
        requestVO.setLoginUserId("yamdeng");
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String clientIp = req.getHeader("X-FORWARDED-FOR");
        if (clientIp == null)
            clientIp = req.getRemoteAddr();
        requestVO.setOutWorkIp(clientIp);
        // TODO : 상태, 결과 처리
        requestVO.setWorkStatusCode("END");
        requestVO.setWorkResultCode("SUCCESS_NORMAL");
        OfficeCommuteDayVO beforeDetailInfo = commuteDao.selectCommuteInfoByUserId(requestVO);
        LocalDateTime startWorkDate = beforeDetailInfo.getStartWorkDate();
        LocalDateTime now = LocalDateTime.now();
        long minutes = startWorkDate.until( now, ChronoUnit.MINUTES );
        double minuteValue = (double)minutes / (double)60;
        double workedTimeValue = Math.ceil(minuteValue * 10) / 10.0;
        requestVO.setWorkedTimeValue(workedTimeValue);
        commuteDao.outWork(requestVO);
        return commuteDao.selectCommuteInfoByUserId(requestVO);
    }

}
