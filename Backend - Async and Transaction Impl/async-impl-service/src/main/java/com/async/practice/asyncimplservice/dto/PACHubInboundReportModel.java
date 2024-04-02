package com.async.practice.asyncimplservice.dto;

import lombok.Data;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;

@Data
@FixedLengthRecord(ignoreTrailingChars = true)
public class PACHubInboundReportModel {

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
    private String ovcs;

    @DataField(pos = 108, length = 10)
    private String salesOrder;

    @DataField(pos = 119, length = 6)
    private String sdst;

    @DataField(pos = 126, length = 40)
    private String materialDescription;

    @DataField(pos = 167, length = 4)
    private String shpt;

    @DataField(pos = 172, length = 5)
    private String shipp;

    @DataField(pos = 178, length = 5)
    private String dlvty;

    @DataField(pos = 184, length = 10)
    private String acGlDate;

    @DataField(pos = 195, length = 10)
    private String delvyDate;

    @DataField(pos = 206, length = 15)
    private String custCmitDate;

    @DataField(pos = 222, length = 10)
    private String orderDate;

    @DataField(pos = 233, length = 20)
    private String purchaseOrder;

    @DataField(pos = 254, length = 35)
    private String customerName;

    @DataField(pos = 290, length = 40)
    private String name2;

    @DataField(pos = 331, length = 35)
    private String city;

    @DataField(pos = 367, length = 12)
    private String createdBy;

    @DataField(pos = 380, length = 19)
    private String prodHierLevelOne;

    @DataField(pos = 400, length = 19)
    private String prodHierLevelTwo;

    @DataField(pos = 420, length = 4)
    private String hubShbl;

    @DataField(pos = 425, length = 10)
    private String hubDfStatus;

}
