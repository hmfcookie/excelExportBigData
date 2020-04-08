package com.mfcookie.excel.service;

import com.mfcookie.excel.mapper.SysSystemVO;

public interface ExportExcelService {

    String exportSysSystemExcel(SysSystemVO sysSystemVO) throws Exception;
}
