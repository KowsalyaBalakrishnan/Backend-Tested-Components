package com.async.practice.asyncimplservice.service;

import com.async.practice.asyncimplservice.dto.AMRInboundReportModel;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class FlatFileReaderService {

    public void readFlatFile(Exchange exchange) throws IOException {
        File file = ResourceUtils.getFile((String) exchange.getIn().getBody());
//        List<AMRInboundReportModel> records = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger(0);
        int chunkSize = 1000;
        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String[] lines = new String[chunkSize];
            int index = 0;
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                lines[index] = line;
                index++;
                if (index == chunkSize) {
                    //processChunk(lines, counter, records);
                    processChunk(lines, counter);
                    index = 0;
                }
            }
            if (index > 0) {
                //processChunk(Arrays.copyOfRange(lines, 0, index), counter, records);
                processChunk(Arrays.copyOfRange(lines, 0, index), counter);
            }
        }
        //return records;
    }

    private void processChunk(String[] lines, AtomicInteger counter) {
        List<AMRInboundReportModel> records = new ArrayList<>();
        List<AMRInboundReportModel> chunkRecords = Arrays.stream(lines)
                .parallel()
                .map(this::extractData)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        records.addAll(chunkRecords);
        int count = counter.addAndGet(chunkRecords.size());
        System.out.println("====>>>> " + count + " records processed. chunkRecords.size()=> " + chunkRecords.size());
    }

    private AMRInboundReportModel extractData(String record) {
        AMRInboundReportModel model = null;
        try (CamelContext camelContext = new DefaultCamelContext();
             ProducerTemplate producer = camelContext.createProducerTemplate();
             BindyFixedLengthDataFormat bindy = new BindyFixedLengthDataFormat(AMRInboundReportModel.class);) {
            bindy.setCamelContext(camelContext);
            BindyFixedLengthDataFormat finalBindy = bindy;
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("direct:start").unmarshal(finalBindy);
                }
            });
            camelContext.start();
            model = producer.requestBody("direct:start", record, AMRInboundReportModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error processing record: " + record);
        }
        return model;
    }
}
