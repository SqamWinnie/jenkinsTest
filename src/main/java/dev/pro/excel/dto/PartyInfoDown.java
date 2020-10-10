package dev.pro.excel.dto;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Table;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;

/**
 * Copyright @2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 *
 * @author yachen.li@hand-china.com
 * @date 2019/12/13
 */
@Table(name = "party_info")
public class PartyInfoDown {
    @ExcelIgnore
    private Long id;

    @ExcelProperty(value = "公司编码")
    private String partyNumber;

    @ExcelProperty(value = "公司名称")
    private String partyName;

    @ExcelProperty(value = "公司类型")
    private Long partyCode;

    @ExcelProperty(value = "注册地址")
    private String address;

    @ExcelProperty(value = "公司电话")
    private String phoneNumber;

    @ExcelProperty(value = "项目开始时间")
    @DateTimeFormat("yyyy-MM")
    private Date startDate;

    @ExcelProperty(value = "项目结束时间")
    @DateTimeFormat("yyyy-MM")
    private Date endDate;

    /**
     * 保留四位小数，默认四舍五入
     */
    @ExcelProperty(value = "金额")
    @NumberFormat(value = "#.0000")
    private BigDecimal partyAmount;

    public String getPartyNumber() {
        return partyNumber;
    }

    public void setPartyNumber(String partyNumber) {
        this.partyNumber = partyNumber;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public Long getPartyCode() {
        return partyCode;
    }

    public void setPartyCode(Long partyCode) {
        this.partyCode = partyCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPartyAmount() {
        return partyAmount;
    }

    public void setPartyAmount(BigDecimal partyAmount) {
        this.partyAmount = partyAmount;
    }
}
