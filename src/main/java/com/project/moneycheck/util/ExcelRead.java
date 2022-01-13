package com.project.moneycheck.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelRead {
    public static List<Map<String, String>> read(ExcelReadOption excelReadOption) {

        Workbook wb = ExcelFileType.getWorkbook(excelReadOption.getFilePath());

        //Sheet sheet = wb.getSheetAt(0); // 1번째 시트만 가져옴

        /**
         * sheet에서 유효한(데이터가 있는) 행의 개수를 가져온다.
         */
        int sheetNum = wb.getNumberOfSheets();
        int numOfCells = 0;

        Row row = null;
        Cell cell = null;

        String cellName = "";
        /**
         * 각 row마다의 값을 저장할 맵 객체
         * 저장되는 형식은 다음과 같다.
         * put("A", "이름");
         * put("B", "게임명");
         */
        Map<String, String> map = null;
        /**
         * 각 Row를 리스트에 담는다.
         * 하나의 Row를 하나의 Map으로 표현되며
         * List에는 모든 Row가 포함될 것이다.
         */
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        /**
         * 각 Row만큼 반복을 한다.
         */
        for(int i = 0; i < sheetNum; i++) {
//          워크북에서 가져온 시트에서 rowIndex에 해당하는 Row를 가져온다 하나의 Row는 여러개의 Cell을 가진다.
            Sheet sheet = wb.getSheetAt(i);

           int numOfRows = sheet.getPhysicalNumberOfRows();
        for(int rowIndex = excelReadOption.getStartRow() - 1; rowIndex < numOfRows; rowIndex++) {

            row = sheet.getRow(rowIndex);

            if(row != null) {

                numOfCells =  row.getLastCellNum();

                map = new HashMap<String, String>();

                for(int cellIndex = 0; cellIndex < numOfCells; cellIndex++) {

                    cell = row.getCell(cellIndex);
      
                    cellName = ExcelCellRef.getName(cell, cellIndex); // 이름 가져옴 A,B,C,D..

                    if( !excelReadOption.getOutputColumns().contains(cellName) ) {
                        continue;
                    }
                    map.put(cellName, ExcelCellRef.getValue(cell));
                }

                result.add(map);

                }
            }
        }
        return result;
    }
}

