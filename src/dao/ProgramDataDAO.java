package dao;

import entity.Program;
import entity.ProgramData;
import entity.Student;

public interface ProgramDataDAO extends SuperDAO<ProgramData, String> {
    Program getProgram(String id) throws Exception;
    Student getStudent(String id) throws Exception;
}
