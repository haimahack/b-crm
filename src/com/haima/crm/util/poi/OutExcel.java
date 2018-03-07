package com.haima.crm.util.poi;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;

public final class OutExcel {

    public static void main(String[] args) throws Exception  {

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("table");  //创建table工作薄
        Object[][] datas = {{"区域产品销售额","",""},{"区域", "总销售额(万元)", "总利润(万元)简单的表格"}, {"江苏省" , 9045,  2256}, {"广东省", 3000, 690}};
        HSSFRow row;
        HSSFCell cell;

        short colorIndex = 10;
        HSSFPalette palette = wb.getCustomPalette();
        Color rgb = Color.getHSBColor(208.5246f,0.57276994f,0.8352941f);

        short bgIndex = colorIndex ++;
        palette.setColorAtIndex(bgIndex, (byte) rgb.getRed(), (byte) rgb.getGreen(), (byte) rgb.getBlue());
        short bdIndex = colorIndex ++;
        rgb = Color.getHSBColor(208.5246f,0.57276994f,0.8352941f);
        palette.setColorAtIndex(bdIndex, (byte) rgb.getRed(), (byte) rgb.getGreen(), (byte) rgb.getBlue());

        for(int i = 0; i < datas.length; i++) {
            row = sheet.createRow(i);//创建表格行
            for(int j = 0; j < datas[i].length; j++) {
                cell = row.createCell(j);//根据表格行创建单元格
                cell.setCellValue(String.valueOf(datas[i][j]));

                HSSFCellStyle cellStyle = wb.createCellStyle();
                if(i == 0 || i == 1) {
                    cellStyle.setFillForegroundColor(bgIndex); //bgIndex 背景颜色下标值
                    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                }

                cellStyle.setBorderBottom(BorderStyle.THIN);
                cellStyle.setBorderLeft(BorderStyle.THIN);
                cellStyle.setBorderTop(BorderStyle.THIN);
                cellStyle.setBorderRight(BorderStyle.THIN);
                //bdIndex 边框颜色下标值
                cellStyle.setBottomBorderColor(bdIndex);
                cellStyle.setLeftBorderColor(bdIndex);
                cellStyle.setRightBorderColor(bdIndex);
                cellStyle.setTopBorderColor(bdIndex);

                cellStyle.setAlignment(HorizontalAlignment.CENTER);
                cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

                if(i == datas.length - 1 && j == datas[0].length - 1) {
                    HSSFFont font = wb.createFont();
                    font.setItalic(true);
                    font.setUnderline(HSSFFont.U_SINGLE);
                    font.setBold(true);
                    font.setFontHeightInPoints((short)14);
                    cellStyle.setFont(font);
                }
                cell.setCellStyle(cellStyle);
            }
        }

        //加入图片
        byte[] bt = FileUtils.readFileToByteArray(new File("/test.jpg"));
        int pictureIdx = wb.addPicture(bt, Workbook.PICTURE_TYPE_PNG);
        CreationHelper helper = wb.getCreationHelper();
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setDx1(MSExcelUtil.pixel2WidthUnits(60));
        anchor.setDy1(MSExcelUtil.pixel2WidthUnits(60));
        anchor.setCol1(0);
        anchor.setRow1(4);
        anchor.setCol2(3);
        anchor.setRow2(25);
        drawing.createPicture(anchor, pictureIdx);

        //合并单元格
        CellRangeAddress region = new CellRangeAddress(0, // first row
                0, // last row
                0, // first column
                2 // last column
        );
        sheet.addMergedRegion(region);

        //创建表格之后设置行高与列宽
        for(int i = 0; i < datas.length; i++) {
            row = sheet.getRow(i);
            row.setHeightInPoints(30);
        }
        for(int j = 0; j < datas[0].length; j++) {
            sheet.setColumnWidth(j, MSExcelUtil.pixel2WidthUnits(160));
        }
        wb.write(new FileOutputStream("/test1.xlsx"));
    }



}