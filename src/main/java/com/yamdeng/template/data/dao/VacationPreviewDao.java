package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.db.OfficeVacationPlusPreviewVO;
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

    // 휴가_휴직_현황(포상휴가)_미리보기 list
    List<OfficeVacationPlusPreviewVO> selectVacationPlusPreviewList(OfficeVacationPlusPreviewVO vo);

    // 휴가_휴직_현황(포상휴가)_미리보기 list totalCount
    int selectVacationPlusPreviewListTotalCount(OfficeVacationPlusPreviewVO vo);

    // 휴가_휴직_현황(포상휴가)_미리보기 detail
    OfficeVacationPlusPreviewVO selectVacationPlusPreviewInfo(OfficeVacationPlusPreviewVO vo);

    // 휴가_휴직_현황(포상휴가)_미리보기 insert
    int insertVacationPlusPreview(OfficeVacationPlusPreviewVO vo);

    // 휴가_휴직_현황(포상휴가)_미리보기 update
    int updateVacationPlusPreview(OfficeVacationPlusPreviewVO vo);

    // 휴가_휴직_현황(포상휴가)_미리보기 delete
    int deleteVacationPlusPreview(OfficeVacationPlusPreviewVO vo);

}
