package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.db.OfficeVacationYearPreviewVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VacationPreviewDao {

    // 휴가_휴직_현황(연별)_미리보기 list
    List<OfficeVacationYearPreviewVO> selectVacationYearPreviewList(OfficeVacationYearPreviewVO vo);

    // 휴가_휴직_현황(연별)_미리보기 list totalCount
    int selectVacationYearPreviewListTotalCount(OfficeVacationYearPreviewVO vo);

    // 휴가_휴직_현황(연별)_미리보기 detail
    OfficeVacationYearPreviewVO selectVacationYearPreviewInfo(OfficeVacationYearPreviewVO vo);

    // 휴가_휴직_현황(연별)_미리보기 insert
    int insertVacationYearPreview(OfficeVacationYearPreviewVO vo);

    // 휴가_휴직_현황(연별)_미리보기 update
    int updateVacationYearPreview(OfficeVacationYearPreviewVO vo);

}
