package com.qst.financial.util;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class POIUtil  {
	
    private static Logger logger  = Logger.getLogger(POIUtil.class);  
    private final static String xls = "xls";  
    private final static String xlsx = "xlsx";  
    /** 
     * 读入excel文件，解析后返回 
     * @param file 
     * @throws IOException  
     */  
    public static List<List> readExcel(MultipartFile file) throws IOException{  
        //检查文件  
        checkFile(file);  
        //获得Workbook工作薄对象  
        Workbook workbook = getWorkBook(file);  
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回  
        List<List> listArray = new ArrayList<List>();  
        if(workbook != null){  
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){  
                List<Object[]> list = new ArrayList<Object[]>();  
                //获得当前sheet工作表  
                Sheet sheet = workbook.getSheetAt(sheetNum);  
                if(sheet == null){  
                    continue;  
                }  
                //获得当前sheet的开始行  
                int firstRowNum  = sheet.getFirstRowNum();  
                //获得当前sheet的结束行  
                int lastRowNum = sheet.getLastRowNum();  
                //循环除了第一行的所有行  
                for(int rowNum = firstRowNum;rowNum <= lastRowNum;rowNum++){  
                    //获得当前行  
                	Row  row = sheet.getRow(rowNum);  
                    if(row == null){  
                        continue;  
                    }  
                    //获得当前行的开始列  
                    int firstCellNum = row.getFirstCellNum();  
                    //获得当前行的列数  
                    //int lastCellNum = row.getPhysicalNumberOfCells();  
                    if(firstCellNum>=0) {
                    	int lastCellNum = sheet.getRow(0).getPhysicalNumberOfCells();  
                        Object[] cells = new Object[lastCellNum];  
                        //循环当前行  
                        for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){  
                        	System.out.println("行："+rowNum+"列："+cellNum);
                            Cell cell = row.getCell(cellNum);  
                            System.out.println(cellNum);
                            cells[cellNum] = getCellValue(cell);  
                        }  
                        list.add(cells);  
                    }
                    
                }  
                listArray.add(list);
            }  
            workbook.close();  
        }  
        return listArray;  
    }  
    public static void checkFile(MultipartFile file) throws IOException{  
        //判断文件是否存在  
        if(null == file){  
            logger.error("文件不存在！");  
            throw new FileNotFoundException("文件不存在！");  
        }  
        //获得文件名  
        String fileName = file.getOriginalFilename();  
        //判断文件是否是excel文件  
        if(!fileName.endsWith(xls) && !fileName.endsWith(xlsx)){  
            logger.error(fileName + "不是excel文件");  
            throw new IOException(fileName + "不是excel文件");  
        }  
    } 
    public static void getFileName(MultipartFile file) throws IOException{  
        //获得文件名  
        String fileName = file.getOriginalFilename();  
    }  
    public static Workbook getWorkBook(MultipartFile file) {  
        //获得文件名  
        String fileName = file.getOriginalFilename();  
        //创建Workbook工作薄对象，表示整个excel  
        Workbook workbook = null;  
        try {  
            //获取excel文件的io流  
            InputStream is = file.getInputStream();  
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象  
            if(fileName.endsWith(xls)){  
                //2003  
                workbook = new HSSFWorkbook(is);  
            }else if(fileName.endsWith(xlsx)){  
                //2007  
                workbook = new XSSFWorkbook(is);  
            }  
        } catch (IOException e) {  
            logger.info(e.getMessage());  
        }  
        return workbook;  
    }  
    
    public static boolean isBlankRow(HSSFRow row){
        if(row == null) return true;
        boolean result = true;
        for(int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++){
            HSSFCell cell = row.getCell(i, HSSFRow.RETURN_BLANK_AS_NULL);
            String value = "";
            if(cell != null){
                switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    value = String.valueOf((int) cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    value = String.valueOf(cell.getCellFormula());
                    break;
                default:
                    break;
                }
                if(!value.trim().equals("")){
                    result = false;
                    break;
                }
            }
        }
         
        return result;
    }
    public static Object getCellValue(Cell cell){  
        Object cellValue = "";  
        if(cell == null){  
            return cellValue;  
        }  
        //把数字当成String来读，避免出现1读成1.0的情况  
       /* if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){  
            cell.setCellType(Cell.CELL_TYPE_STRING);  
        }  */
        //判断数据的类型  
        switch (cell.getCellType()){  
            case Cell.CELL_TYPE_NUMERIC: //数字 
            	if (HSSFDateUtil.isCellDateFormatted(cell)) {
            		Date date = cell.getDateCellValue();
            		cellValue = DateFormatUtils.format(date, "yyyy-MM-dd");
            	} else {
            		cellValue =new BigDecimal(String.valueOf(cell.getNumericCellValue())) ;  
            	}
            	break;
               
            case Cell.CELL_TYPE_STRING: //字符串  
                cellValue = String.valueOf(cell.getStringCellValue());  
                break;  
            case Cell.CELL_TYPE_BOOLEAN: //Boolean  
                cellValue = String.valueOf(cell.getBooleanCellValue());  
                break;  
            case Cell.CELL_TYPE_FORMULA: //公式  
                cellValue = String.valueOf(cell.getCellFormula());  
                break;  
            case Cell.CELL_TYPE_BLANK: //空值   
                cellValue = "";  
                break;  
            case Cell.CELL_TYPE_ERROR: //故障  
                cellValue = "非法字符";  
                break;  
            default:  
                cellValue = "未知类型";  
                break;  
        }  
        return cellValue;  
    }  
}
