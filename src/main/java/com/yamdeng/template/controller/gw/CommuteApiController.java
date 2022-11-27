package com.yamdeng.template.controller.gw;

import com.yamdeng.template.dto.MemberDto;
import com.yamdeng.template.service.gw.CommuteService;
import com.yamdeng.template.util.TypeConvertUtil;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.request.OfficeCommuteDayRequestVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/commutes")
public class CommuteApiController {

    private final CommuteService commuteService;

    @GetMapping
    @ResponseBody
    public List<OfficeCommuteDayVO> list(OfficeCommuteDayRequestVO requestVO) {
        return commuteService.selectTest(requestVO);
    }

}
