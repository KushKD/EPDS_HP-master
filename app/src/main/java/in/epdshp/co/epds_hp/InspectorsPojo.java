package in.epdshp.co.epds_hp;

import java.io.Serializable;

/**
 * Created by KD on 7/31/2015.
 */
public class InspectorsPojo implements Serializable {

    private String SrNo;
    private String District;
    private String NameofBlock;
    private String NameofInspector;
    private String PersonalMobileNumber;
    private String OfficialMobileNumber;
    private String OfficeAddress;

    public String getSrNo() {
        return SrNo;
    }

    public void setSrNo(String srNo) {
        SrNo = srNo;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getNameofBlock() {
        return NameofBlock;
    }

    public void setNameofBlock(String nameofBlock) {
        NameofBlock = nameofBlock;
    }

    public String getNameofInspector() {
        return NameofInspector;
    }

    public void setNameofInspector(String nameofInspector) {
        NameofInspector = nameofInspector;
    }

    public String getPersonalMobileNumber() {
        return PersonalMobileNumber;
    }

    public void setPersonalMobileNumber(String personalMobileNumber) {
        PersonalMobileNumber = personalMobileNumber;
    }

    public String getOfficialMobileNumber() {
        return OfficialMobileNumber;
    }

    public void setOfficialMobileNumber(String officialMobileNumber) {
        OfficialMobileNumber = officialMobileNumber;
    }

    public String getOfficeAddress() {
        return OfficeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        OfficeAddress = officeAddress;
    }
}