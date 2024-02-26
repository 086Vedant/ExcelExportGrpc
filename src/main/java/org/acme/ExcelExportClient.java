package org.acme;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ExcelExportClient {

     @Inject
     UserService userSvc;

     @Inject
     ExcelExportService excelExportSvc;
    
     // Method to generate an Excel workbook containing user data
     public byte[] generateExcelWorkbookUser() throws IOException 
     {  
          List<String> Header = new ArrayList<>();
          Header.add("name");
          Header.add("phone");
          Header.add("city");
          
          return excelExportSvc.generateExcelWorkbook(userSvc.getAllUsers() , Header,"UserData");
     }
    
}
