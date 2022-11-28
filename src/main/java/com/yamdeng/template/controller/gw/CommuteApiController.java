package com.yamdeng.template.controller.gw;

import com.yamdeng.template.service.gw.CommuteService;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.request.OfficeCommuteDayRequestVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/commutes")
public class CommuteApiController {

    private final CommuteService commuteService;

    @GetMapping
    @ResponseBody
    public List<OfficeCommuteDayVO> list(OfficeCommuteDayRequestVO requestVO) {
        return null;
    }
    @GetMapping("/detail")
    @ResponseBody
    public OfficeCommuteDayVO detail(OfficeCommuteDayRequestVO requestVO) {
        return commuteService.detail(requestVO);
    }

    @PutMapping("/start-work")
    @ResponseBody
    public OfficeCommuteDayVO startWork(@RequestBody OfficeCommuteDayRequestVO requestVO) {
        return commuteService.startWork(requestVO);
    }

    @PutMapping("/out-work")
    @ResponseBody
    public OfficeCommuteDayVO endWork(@RequestBody OfficeCommuteDayRequestVO requestVO) {
        return commuteService.outWork(requestVO);
    }

}
