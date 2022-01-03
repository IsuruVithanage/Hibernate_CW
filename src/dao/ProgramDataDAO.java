package dao;

import entity.Program;
import entity.ProgramData;
import entity.Student;

import java.util.List;

public interface ProgramDataDAO extends SuperDAO<ProgramData, String> {
    Program getProgram(String id) throws Exception;

    List<ProgramData> getRegProgram(String sid) throws Exception;

    Student getStudent(String id) throws Exception;
}
