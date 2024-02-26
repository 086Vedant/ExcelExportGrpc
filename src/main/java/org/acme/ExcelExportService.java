package org.acme;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

//This class provides methods for transfer data into object to excel format 
@ApplicationScoped
public class ExcelExportService {
    

    public <T> byte[] generateExcelWorkbook(List<T> Data, List<String>headers, String FileName) throws IOException 
    {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet =  workbook.createSheet(FileName);
        XSSFRow headRow = sheet.createRow(0);

        int headIndex =0;
        for(String headStr : headers )
        {
            headRow.createCell(headIndex++).setCellValue(headStr); 
        }

        int dataRowIndex =1;
        for(T DataObject : Data )
        {
            XSSFRow DataRow = sheet.createRow(dataRowIndex++);
            int CellIndex =0;

            for(Field field : DataObject.getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                
                try {
                    Object value = field.get(DataObject);
                    XSSFCell DataCell = DataRow.createCell(CellIndex++);

                   if (value != null) {
						if (value instanceof String) {
							DataCell.setCellValue((String) value);
						} else if (value instanceof Long) {
							DataCell.setCellValue((Long) value);
						} else if (value instanceof Integer) {
							DataCell.setCellValue((Integer) value);
						} else if (value instanceof Double) {
                            DataCell.setCellValue((Double) value);
						} else {
							DataCell.setCellValue((String) value);
                        }
					}

                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        
         //Retrieving Excel as a Byte array
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         workbook.write(bos);
         bos.close();
         workbook.close();

        return bos.toByteArray();
    }
        

}
