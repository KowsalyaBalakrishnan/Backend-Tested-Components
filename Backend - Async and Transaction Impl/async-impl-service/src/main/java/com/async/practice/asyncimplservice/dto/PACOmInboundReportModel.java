package com.async.practice.asyncimplservice.dto;

import lombok.Data;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;

@Data
@FixedLengthRecord(ignoreTrailingChars = true)
public class PACOmInboundReportModel {

    @DataField(pos = 2, length = 11)
    private String deliveryNumber;

    @DataField(pos = 14, length = 10)
    private String item;

    @DataField(pos = 25, length = 18)
    private String material;

    @DataField(pos = 44, length = 10)
    private String dlvyQty;

    @DataField(pos = 55, length = 10)
    private String createdOn;

    @DataField(pos = 66, length = 8)
    private String createdTime;

    @DataField(pos = 75, length = 5)
    private String sorg;

    @DataField(pos = 81, length = 10)
    private String soldToPt;

    @DataField(pos = 92, length = 10)
    private String shipTo;

    @DataField(pos = 103, length = 4)
    private String plant;

    @DataField(pos = 108, length = 4)
    private String ovcs;

    @DataField(pos = 113, length = 10)
    private String salesOrder;

    @DataField(pos = 124, length = 12)
    private String yourRef;

    @DataField(pos = 137, length = 6)
    private String sdst;

    @DataField(pos = 144, length = 40)
    private String materialDescription;

    @DataField(pos = 185, length = 4)
    private String shpt;

    @DataField(pos = 190, length = 5)
    private String shipp;

    @DataField(pos = 196, length = 5)
    private String dlvty;

    @DataField(pos = 202, length = 3)
    private String cty;

    @DataField(pos = 206, length = 3)
    private String poe;

    @DataField(pos = 210, length = 15)
    private String custCmitDate;

    @DataField(pos = 226, length = 12)
    private String createdBy;

    @DataField(pos = 239, length = 35)
    private String customerName;

    @DataField(pos = 275, length = 10)
    private String acGlDate;

    @DataField(pos = 286, length = 19)
    private String prodHierLevelOne;

    @DataField(pos = 306, length = 10)
    private String orderDate;

    @DataField(pos = 317, length = 30)
    private String prodHierLevelTwo;

    @DataField(pos = 348, length = 30)
    private String carrier;

}
