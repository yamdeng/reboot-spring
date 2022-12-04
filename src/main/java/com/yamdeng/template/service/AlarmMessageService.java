package com.yamdeng.template.service;

import com.yamdeng.template.constant.Constant;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class AlarmMessageService {


    private static Map<String, MessageFormat> alarmMessageMap = new HashMap<>();

    static {

        // 개인출퇴근 : {일자} 출근 체크를 진행해주세요.
        alarmMessageMap.put(Constant.CODE_ALARM_COMMUTE_PRIVATE, new MessageFormat("{0} 출근 체크를 진행해주세요."));

        // 개인출퇴근(팀장) : {팀원 이름}(이)가 출근전 상태입니다.
        // 팀원이 지정된 출근 시간(팀별 설정된 업무 시작 시간 + 10분)이 지나도 출근하지 않은 경우
        alarmMessageMap.put(Constant.CODE_ALARM_COMMUTE_MANAGER, new MessageFormat("{0}(이)가 출근전 상태입니다."));

    }

    public static String getMessage(String alarmCode, Object[] messageArgument) {
        MessageFormat messageFormat = alarmMessageMap.get(alarmCode);
        String message = "";
        if(messageFormat != null) {
            message = messageFormat.format(messageArgument);
        }
        return message;
    }

}
