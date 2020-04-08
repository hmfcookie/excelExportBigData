package com.mfcookie.excel;

import com.mfcookie.excel.mapper.SysSystemReadMapper;
import com.mfcookie.excel.service.ExportExcelService;
import com.mfcookie.excel.mapper.SysSystemVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExcelApplication.class)
public class ExcelApplicationTests {

	@Autowired
	private ExportExcelService exportExcelService;
	@Autowired
	private SysSystemReadMapper sysSystemReadMapper;

	@Test
	public void excel() throws Exception {
		String s = exportExcelService.exportSysSystemExcel(new SysSystemVO());
		System.out.println(s);
	}

	@Test
	public void get() {
		List<SysSystemVO> s = sysSystemReadMapper.selectSysSystemVOList(new SysSystemVO());
		System.out.println(s);
	}

	@Test
	public void count() {
		Integer integer = sysSystemReadMapper.count();
		System.out.println("integer = " + integer);
	}

}
