package com.taller2.datavalidator;



import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class XLSXValidator {

    public static String validateXSLX(String route){
        int validCounter = 0;
        int invalidCounter = 0;
        Set<String> reportType = new HashSet<>();
        reportType.add("Near Miss");
        reportType.add("Lost Time");
        reportType.add("First Aid");

        try{
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(route));

            XSSFSheet sheet = workbook.getSheet("SafetyData");
            XSSFRow row = null;
            int i=0;

            while((row = sheet.getRow(i)) != null){

                if(!row.getCell(1).getStringCellValue().equals("N/A") && reportType
                        .contains(row.getCell(7).getStringCellValue())){
                    validCounter++;
                }else{
                    invalidCounter++;
                }
                i++;
            }

        }catch(IOException e){
            e.printStackTrace();
        }

        return "Datos válidos " + validCounter + " Datos inválidos " + invalidCounter;
    }
}
