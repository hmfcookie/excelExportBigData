package com.mfcookie.excel.service;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.github.pagehelper.PageHelper;
import com.mfcookie.excel.consts.ExcelConstant;
import com.mfcookie.excel.mapper.SysSystemReadMapper;
import com.mfcookie.excel.mapper.SysSystemVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExportExcelServiceImpl implements ExportExcelService {
    @Autowired
    private SysSystemReadMapper sysSystemReadMapper;

    @Override
    public String exportSysSystemExcel(SysSystemVO sysSystemVO) throws Exception {

        FileOutputStream out = null;
        try {
            String fileName = new String(("SystemExcel").getBytes(), "UTF-8");
            out = new FileOutputStream("/Users/hmfcookie/Downloads/excel/temp/" + fileName + ".xlsx");
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

            // 设置EXCEL名称

            // 设置SHEET名称
            String sheetName = "系统列表sheet";

            // 设置标题
            Table table = new Table(1);
            List<List<String>> titles = new ArrayList<List<String>>();
            titles.add(Arrays.asList("系统名称"));
            titles.add(Arrays.asList("系统标识"));
            titles.add(Arrays.asList("描述"));
            titles.add(Arrays.asList("状态"));
            titles.add(Arrays.asList("创建人"));
            titles.add(Arrays.asList("创建时间"));
            table.setHead(titles);

            // 查询总数并封装相关变量(这块直接拷贝就行了不要改)
            long l = System.currentTimeMillis();
            Integer totalRowCount = this.sysSystemReadMapper.count();
            long l1 = System.currentTimeMillis();
            System.out.println("查询总数：" + totalRowCount + " 耗时：" + (l1 - l));
            Integer perSheetRowCount = ExcelConstant.PER_SHEET_ROW_COUNT;
            Integer pageSize = ExcelConstant.PER_WRITE_ROW_COUNT;
            Integer sheetCount = totalRowCount % perSheetRowCount == 0 ? (totalRowCount / perSheetRowCount) : (totalRowCount / perSheetRowCount + 1);
            Integer previousSheetWriteCount = perSheetRowCount / pageSize;
            Integer lastSheetWriteCount = totalRowCount % perSheetRowCount == 0 ?
                    previousSheetWriteCount :
                    (totalRowCount % perSheetRowCount % pageSize == 0 ? totalRowCount % perSheetRowCount / pageSize : (totalRowCount % perSheetRowCount / pageSize + 1));


            for (int i = 0; i < sheetCount; i++) {

                // 创建SHEET
                Sheet sheet = new Sheet(i, 0);
                sheet.setSheetName(sheetName + i);

                // 写数据 这个j的最大值判断直接拷贝就行了，不要改动
                for (int j = 0; j < (i != sheetCount - 1 ? previousSheetWriteCount : lastSheetWriteCount); j++) {
                    List<List<String>> dataList = new ArrayList<>();

                    // 此处查询并封装数据即可 currentPage, pageSize这俩个变量封装好的 不要改动
                    PageHelper.startPage(j + 1 + previousSheetWriteCount * i, pageSize);
                    List<SysSystemVO> sysSystemVOList = this.sysSystemReadMapper.selectSysSystemVOList(sysSystemVO);
                    if (!CollectionUtils.isEmpty(sysSystemVOList)) {
                        sysSystemVOList.forEach(eachSysSystemVO -> {
                            dataList.add(Arrays.asList(
                                    eachSysSystemVO.getSystemName(),
                                    eachSysSystemVO.getSystemKey(),
                                    eachSysSystemVO.getDescription(),
                                    eachSysSystemVO.getState(),
                                    eachSysSystemVO.getCreateUid(),
                                    eachSysSystemVO.getCreateTime()
                            ));
                        });
                    }
                    writer.write0(dataList, sheet, table);
                }
            }

            // 下载EXCEL
//            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName).getBytes("gb2312"), "ISO-8859-1") + ".xls");
//            response.setContentType("multipart/form-data");
//            response.setCharacterEncoding("utf-8");
            writer.finish();
            out.flush();
            long l2 = System.currentTimeMillis();
            System.out.println("分批写入：" + " 耗时：" + (l2 - l1));
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return "导出系统列表EXCEL成功";
    }
}
