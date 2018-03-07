package com.haima.crm.util.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * 导出Excel公共方法
 */
public class ExportExcelUtil {

    /*
     * 创建
     * */
    public static HSSFWorkbook createWorkbook(String title, String[] rowName, List<Object[]> dataList) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();                        // 创建工作簿对象
        HSSFSheet sheet = workbook.createSheet(title);                     // 创建工作表

        // 产生表格标题行
        HSSFRow rowm = sheet.createRow(0);
        HSSFCell cellTiltle = rowm.createCell(0);

        //sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面  - 可扩展】
        HSSFCellStyle titlestyle = getTitleStyle(workbook);
        HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook);//获取列头样式对象
        HSSFCellStyle style = getStyle(workbook);                    //单元格样式对象

        //合并单元格
        CellRangeAddress region = new CellRangeAddress(0, // first row
                2, // last row
                0, // first column
                (rowName.length - 1) // last column
        );
        sheet.addMergedRegion(region);
        cellTiltle.setCellStyle(titlestyle);
        cellTiltle.setCellValue(title);

        // 定义所需列数
        int columnNum = rowName.length;
        HSSFRow rowRowName = sheet.createRow(3);                // 在索引2的位置创建行(最顶端的行开始的第二行)

        // 将列头设置到sheet的单元格中
        for (int n = 0; n < columnNum; n++) {
            HSSFCell cellRowName = rowRowName.createCell(n);                //创建列头对应个数的单元格
            cellRowName.setCellType(CellType.STRING);                //设置列头单元格的数据类型
            HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
            cellRowName.setCellValue(text);                                    //设置列头单元格的值
            cellRowName.setCellStyle(columnTopStyle);                        //设置列头单元格样式
        }

        //将查询出的数据设置到sheet对应的单元格中
        for (int i = 0; i < dataList.size(); i++) {

            Object[] obj = dataList.get(i);//遍历每个对象
            HSSFRow row = sheet.createRow(i + 4);//创建所需的行数

            for (int j = 0; j < obj.length; j++) {
                HSSFCell cell;   //设置单元格的数据类型
                if (j == 0) {
                    cell = row.createCell(j, CellType.NUMERIC);
                    cell.setCellValue(i + 1);
                } else {
                    cell = row.createCell(j, CellType.STRING);
                    if (!"".equals(obj[j]) && obj[j] != null) {
                        cell.setCellValue(obj[j].toString());                        //设置单元格的值
                    }
                }
                cell.setCellStyle(style);                                    //设置单元格样式
            }
        }

        //让列宽随着导出的列长自动适应
        for (int colNum = 0; colNum < columnNum; colNum++) {
            int columnWidth = sheet.getColumnWidth(colNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                HSSFRow currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }
                if (currentRow.getCell(colNum) != null) {
                    HSSFCell currentCell = currentRow.getCell(colNum);
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            if (colNum == 0) {
                sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
            } else {
                sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
            }
        }
        return workbook;
    }

    /**
     * 导出

    public static void export() throws  Exception{
        HSSFWorkbook workbook = createWorkbook();

        if (workbook != null) {
            String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xlsx";
            fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");

            System.out.println(fileName + " 《《《《《");

            response.setContentType("APPLICATION/OCTET-STREAM");
            String headStr = "attachment; filename=" + fileName;
            response.setHeader("Content-Disposition", headStr);

            OutputStream out = response.getOutputStream();
            workbook.write(out);

            out.close();
            workbook.close();
        }
    }
     */

    /*
     * 标题单元格样式
     */
    public static HSSFCellStyle getTitleStyle(HSSFWorkbook workbook) {

        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 20);
        //字体加粗
        font.setBold(true);
        //设置字体名字
        font.setFontName("Georgia");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(BorderStyle.THIN);
        //设置底边框颜色;
        //style.setBottomBorderColor((short)120);
        //设置左边框;
        style.setBorderLeft(BorderStyle.THIN);
        //设置左边框颜色;
        //style.setLeftBorderColor((short)120);
        //设置右边框;
        style.setBorderRight(BorderStyle.THIN);
        //设置右边框颜色;
        //style.setRightBorderColor((short)120);
        //设置顶边框;
        style.setBorderTop(BorderStyle.THIN);
        //设置顶边框颜色;
        //style.setTopBorderColor((short)120);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;

    }

    /* 
     * 列头单元格样式
     */
    public static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 12);
        //字体加粗
        font.setBold(true);
        //设置字体名字 
        font.setFontName("Arial");
        //设置样式; 
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框; 
        style.setBorderBottom(BorderStyle.THIN);
        //设置底边框颜色;  
        //style.setBottomBorderColor((short)9);
        //设置左边框;   
        style.setBorderLeft(BorderStyle.THIN);
        //设置左边框颜色; 
        //style.setLeftBorderColor((short)9);
        //设置右边框; 
        style.setBorderRight(BorderStyle.THIN);
        //设置右边框颜色; 
        //style.setRightBorderColor((short)9);
        //设置顶边框; 
        style.setBorderTop(BorderStyle.THIN);
        //设置顶边框颜色;  
        //style.setTopBorderColor((short)9);
        //在样式用应用设置的字体;  
        style.setFont(font);
        //设置自动换行; 
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;  
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;

    }

    /*  
   * 列数据信息单元格样式
   */
    public static HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 12);
        //字体加粗
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字 
        font.setFontName("Century");
        //设置样式; 
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框; 
        style.setBorderBottom(BorderStyle.THIN);
        //设置底边框颜色;  
        //style.setBottomBorderColor((short)88);
        //设置左边框;   
        style.setBorderLeft(BorderStyle.THIN);
        //设置左边框颜色; 
       // style.setLeftBorderColor((short)88);
        //设置右边框; 
        style.setBorderRight(BorderStyle.THIN);
        //设置右边框颜色; 
        //style.setRightBorderColor((short)88);
        //设置顶边框; 
        style.setBorderTop(BorderStyle.THIN);
        //设置顶边框颜色;  
        //style.setTopBorderColor((short)88);
        //在样式用应用设置的字体;  
        style.setFont(font);
        //设置自动换行; 
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;  
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;

    }
}