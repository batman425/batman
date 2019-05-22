package com.batman.gexinhttppython.file;

import com.batman.gexinhttppython.common.exception.BusinessException;
import com.batman.gexinhttppython.common.response.ResponseCode;
import com.github.pagehelper.util.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * @author liusongwei
 * @Title: ExcelUtil
 * @ProjectName batman
 * @Description: TODO
 * @date 2019/5/2214:05
 */
public class ExcelUtil {
    private Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 获取workbook
     * @param path 文件路径
     * @return
     */
    public Workbook getWorkBook(String path) {
        Workbook workbook = null;
        try {
            if (path.substring(path.lastIndexOf(".") + 1).equals("xlsx")) {
                workbook = new XSSFWorkbook(path);
            } else {
                workbook = new HSSFWorkbook(new FileInputStream(path));
            }
        } catch (IOException e) {
            logger.error("获取workbook失败：", e);
            throw new BusinessException(ResponseCode.ERROR, "文件验证失败");
        }
        return workbook;
    }

    /**
     * 创建excel文件
     *
     * @param workbook
     * @param path 文件路径
     * @return
     */
    public boolean createExcel(Workbook workbook, String path) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(path));
            workbook.write(outputStream);
            return true;
        } catch (IOException e) {
            logger.error("文件生成失败：", e);
            throw new BusinessException(ResponseCode.ERROR, "文件生成失败");
        } finally {
            try {

                if (workbook != null) {
                    workbook.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e1) {
                logger.error("关闭文件流失败：", e1);
            }
        }
    }

    /**
     * 输入若为科学计数法  转换为字符串
     * @param str
     * @return
     */
    public String  bigNumChangeStr(String str){
        if (StringUtil.isEmpty(str)) {
            return null;
        }

        Pattern pattern = Pattern.compile("(-?\\d+\\.?\\d*)[Ee]{1}[\\+-]?[0-9]*");
        //判断输入字符串是否为科学计数法
        if (!pattern.matcher(str).matches()) {
            return str;
        }

        BigDecimal temp = null;
        try {
            temp = new BigDecimal(str);
        } catch (Exception e) {
            temp = BigDecimal.valueOf(0);
        }
        return temp.toPlainString();
    }

    /**
     * 获取单元格值
     * @param row 行
     * @param col 列
     * @return
     */
    public String getValue(Row row, int col) {
        return row.getCell(col) == null ? "" : row.getCell(col).toString();
    }
}
