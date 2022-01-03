package bo;

import dto.ProgramDataDTO;
import entity.Program;
import entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface ProgramDataBO extends SuperBO {
    boolean add(ProgramDataDTO programDataDTO) throws Exception;

    List<ProgramDataDTO> findAll() throws Exception;

    ProgramDataDTO find() throws Exception;

    boolean delete(String id) throws Exception;

    boolean update(ProgramDataDTO programDataDTO) throws Exception;

    String generateNewProgramDataId() throws SQLException, ClassNotFoundException;

    Program getProgram(String id) throws Exception;

    Student getStudent(String id) throws Exception;

    List<ProgramDataDTO> getregProgram(String id) throws Exception;
}
