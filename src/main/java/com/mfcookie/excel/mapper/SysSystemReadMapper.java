package com.mfcookie.excel.mapper;

import java.util.List;

public interface SysSystemReadMapper {
    /**
     * 分页查询
     * @param sysSystemVO
     * @return
     */
    List<SysSystemVO> selectSysSystemVOList(SysSystemVO sysSystemVO);

    /**
     * 查询总数
     * @return
     */
    Integer count();
}
