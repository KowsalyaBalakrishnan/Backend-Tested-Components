package com.async.practice.asyncimplservice.controller;

import com.async.practice.asyncimplservice.dto.AMRInboundReportModel;
import com.async.practice.asyncimplservice.service.FlatFileReaderService;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FileProcessorController {

    @Autowired
    private FlatFileReaderService flatFileReaderService;

    @GetMapping("/readFile")
    public ResponseEntity<?> readFile() {
        String filePath = "/Users/c2220081/Documents/FlatFilesData/Amr/FileProcessors/Active/V45_UT2_ZRPV491A_AMR_V106_OM_20230223_172541.txt"; // replace with your file path
        Exchange exchange = new org.apache.camel.support.DefaultExchange(new org.apache.camel.impl.DefaultCamelContext());
        exchange.getIn().setBody(filePath);

        try {
            flatFileReaderService.readFlatFile(exchange);
            /*System.out.println(records.get(33));
            System.out.println(">>>>>>>>>>>>>>>>> SIZE=" + records.size());*/
            return ResponseEntity.ok(exchange.getIn().getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
