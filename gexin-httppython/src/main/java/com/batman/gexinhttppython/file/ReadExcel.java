package com.batman.gexinhttppython.file;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liusongwei
 * @Title: ReadExcel
 * @ProjectName batman
 * @Description: TODO
 * @date 2019/5/2214:25
 */
@Service
public class ReadExcel {
    private Logger logger = LoggerFactory.getLogger(ReadExcel.class);

    @Autowired
    private ExcelUtil excelUtil;

    public Map readExcelData(String path) {
        logger.info("开始检验文件");
        Workbook workbook = excelUtil.getWorkBook(path);
        Sheet sheet = workbook.getSheet("明细层");
        //获取第一行单元格
        //Row row0 = sheet.getRow(0);
       // row0.getCell(0);
     //   int colNum = row0.getLastCellNum();
        Map<Integer, String> serialMap = new HashMap<>();
        int i =0;
        for (Row row : sheet) {
           // for(int i =0;i < colNum;i ++){
                String name = excelUtil.getValue(row,2);
                serialMap.put(i,name);
                i++;
           // }
        }
        return serialMap;
    }

}
