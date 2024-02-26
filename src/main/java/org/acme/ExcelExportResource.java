package org.acme;

import java.io.IOException;

import io.quarkus.grpc.GrpcService;
import io.quarkus.hello.ExcelExportRequest;
import io.quarkus.hello.ExcelExportResponce;
import io.quarkus.hello.ExcelExportServicegRPC;
import io.quarkus.hello.ExcelExportError;

import io.smallrye.mutiny.Uni;

import jakarta.inject.Inject;

@GrpcService
public class ExcelExportResource implements ExcelExportServicegRPC {


      @Inject
      ExcelExportClient excelExportCli;

      //gRPC method for Excel export
      @Override
      public Uni<ExcelExportResponce> excelExportgRPC(ExcelExportRequest request)
      {
         String objectName = request.getObjectName();
         byte[] excelFile;

         try {
            excelFile = excelExportCli.generateExcelWorkbookUser();
            return Uni.createFrom().item(()-> ExcelExportResponce.newBuilder()
                                         .setExcelFile(com.google.protobuf.ByteString.copyFrom(excelFile))
                                         .build()       
            );
                     

         } catch (IOException e) {
            e.printStackTrace();
            return Uni.createFrom().item(()-> ExcelExportResponce.newBuilder()
                                              .setError(ExcelExportError.newBuilder()
                                                        .setExcelExportErrorMessage("Error generating Excel file: " + e.getMessage())
                                                        .build())
                                              .build()
                                        );
         }
      }
}