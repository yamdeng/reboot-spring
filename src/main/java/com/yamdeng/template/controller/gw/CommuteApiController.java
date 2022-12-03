package com.yamdeng.template.controller.gw;

import com.yamdeng.template.service.gw.CommuteService;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
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
    public List<OfficeCommuteDayVO> list(OfficeCommuteDayVO vo) {
        return null;
    }
    @GetMapping("/detail")
    @ResponseBody
    public OfficeCommuteDayVO detail(OfficeCommuteDayVO vo) {
        return commuteService.detail(vo);
    }

    @PutMapping("/start-work")
    @ResponseBody
    public OfficeCommuteDayVO startWork(@RequestBody OfficeCommuteDayVO vo) {
        return commuteService.startWork(vo);
    }

    @PutMapping("/out-work")
    @ResponseBody
    public OfficeCommuteDayVO endWork(@RequestBody OfficeCommuteDayVO vo) {
        return commuteService.outWork(vo);
    }

}
