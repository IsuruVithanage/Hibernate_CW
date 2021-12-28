package entity;

public class ProgramData implements SuperEntity{
    private String sID;
    private String pID;
    private String date;

    public ProgramData() {
    }

    public ProgramData(String sID, String pID, String date) {
        this.sID = sID;
        this.pID = pID;
        this.date = date;
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ProgramData{" +
                "sID='" + sID + '\'' +
                ", pID='" + pID + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
