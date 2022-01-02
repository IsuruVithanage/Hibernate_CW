package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProgramData implements SuperEntity {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "student_ID", referencedColumnName = "studentID")
    private Student sID;

    @ManyToOne
    @JoinColumn(name = "Program_ID", referencedColumnName = "programID")
    private Program pID;

    private String date;

    public ProgramData() {
    }

    public ProgramData(String id, Student sID, Program pID, String date) {
        this.setId(id);
        this.setsID(sID);
        this.setpID(pID);
        this.setDate(date);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getsID() {
        return sID;
    }

    public void setsID(Student sID) {
        this.sID = sID;
    }

    public Program getpID() {
        return pID;
    }

    public void setpID(Program pID) {
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
                "id='" + id + '\'' +
                ", sID=" + sID +
                ", pID=" + pID +
                ", date='" + date + '\'' +
                '}';
    }
}
