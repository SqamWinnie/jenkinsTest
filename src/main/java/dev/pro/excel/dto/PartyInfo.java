package dev.pro.excel.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Auto Generated By Generator
 *
 * @author 11861
 */

public class PartyInfo {

    private Long id;

    private String partyNumber;

    private String partyName;

    private Long partyCode;

    private String address;

    private String phoneNumber;

    private Date startDate;

    private Date endDate;

    private BigDecimal partyAmount;

    private Long objectVersionNumber;

    private Long requestId;

    private Long programId;

    private Long createdBy;

    private Date creationDate;

    private Long lastUpdatedBy;

    private Date lastUpdateDate;

    private Long lastUpdateLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartyNumber() {
        return partyNumber;
    }

    public void setPartyNumber(String partyNumber) {
        this.partyNumber = partyNumber == null ? null : partyNumber.trim();
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName == null ? null : partyName.trim();
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
        this.address = address == null ? null : address.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
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

    public Long getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Long objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Long lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    /** 导入 Excel 名称对照 */
    public static final String PARTY_NUMBER = "公司编码";
    public static final String PARTY_NAME = "公司名称";
    public static final String PARTY_CODE = "公司类型";
    public static final String ADDRESS = "注册地址";
    public static final String PHONE_NUMBER = "公司电话";
    public static final String START_DATE = "项目开始时间";
    public static final String END_DATE = "项目结束时间";
    public static final String PARTY_AMOUNT = "金额";
}