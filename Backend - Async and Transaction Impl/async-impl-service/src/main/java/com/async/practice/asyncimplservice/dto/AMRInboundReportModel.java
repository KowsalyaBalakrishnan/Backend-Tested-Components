package com.async.practice.asyncimplservice.dto;

import lombok.Data;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;

@Data
@FixedLengthRecord(ignoreTrailingChars = true)
public class AMRInboundReportModel {

    @DataField(pos = 2, length = 11)
    private String soldToPt;

    @DataField(pos = 14, length = 10)
    private String shipTo;

    @DataField(pos = 25, length = 35)
    private String customerName;

    @DataField(pos = 61, length = 10)
    private String deliveryNumber;

    @DataField(pos = 72, length = 10)
    private String item;

    @DataField(pos = 83, length = 5)
    private String sorg;

    @DataField(pos = 89, length = 4)
    private String plant;

    @DataField(pos = 94, length = 18)
    private String material;

    @DataField(pos = 113, length = 40)
    private String materialDescription;

    @DataField(pos = 154, length = 4)
    private String shpt;

    @DataField(pos = 159, length = 4)
    private String ovcs;

    @DataField(pos = 164, length = 10)
    private String salesOrder;

    @DataField(pos = 175, length = 10)
    private String salesItem;

    @DataField(pos = 186, length = 10)
    private String createdOn;

    @DataField(pos = 197, length = 8)
    private String createdTime;

    @DataField(pos = 206, length = 10)
    private String dlvyQty;

    @DataField(pos = 217, length = 19)
    private String creditReleaseDate;

    @DataField(pos = 237, length = 4)
    private String customerGroup;

    @DataField(pos = 242, length = 6)
    private String sdst;

    @DataField(pos = 249, length = 10)
    private String carrier;

    @DataField(pos = 260, length = 10)
    private String lisc;

    @DataField(pos = 271, length = 15)
    private String custCmitDate;

    @DataField(pos = 287, length = 9)
    private String giftPack;

    @DataField(pos = 297, length = 10)
    private String webGmInd;

    @DataField(pos = 308, length = 9)
    private String giftCard;

    @DataField(pos = 318, length = 19)
    private String prodHierLevelOne;

    @DataField(pos = 338, length = 19)
    private String prodHierLevelTwo;
}
