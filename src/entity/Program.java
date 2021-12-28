package entity;

public class Program implements SuperEntity{
    private String programID;
    private String proName;
    private String duration;
    private double fee;

    public Program() {
    }

    public Program(String programID, String proName, String duration, double fee) {
        this.programID = programID;
        this.proName = proName;
        this.duration = duration;
        this.fee = fee;
    }

    public String getProgramID() {
        return programID;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programID='" + programID + '\'' +
                ", proName='" + proName + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                '}';
    }
}
