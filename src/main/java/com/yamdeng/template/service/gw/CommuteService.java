package com.yamdeng.template.service.gw;

import com.yamdeng.template.data.dao.CommuteDao;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.request.OfficeCommuteDayRequestVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommuteService {

    private final CommuteDao commuteDao;

    public List<OfficeCommuteDayVO> selectTest(OfficeCommuteDayRequestVO requestVO) {
        return commuteDao.selectByBaseDateStrMergeUser(requestVO);
    }

}
