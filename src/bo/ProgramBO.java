package bo;

import dto.ProgramDTO;

import java.sql.SQLException;
import java.util.List;

public interface ProgramBO extends SuperBO {
    boolean add(ProgramDTO programDTO) throws Exception;

    List<ProgramDTO> findAll() throws Exception;

    ProgramDTO find() throws Exception;

    boolean delete(String id) throws Exception;

    boolean update(ProgramDTO programDTO) throws Exception;

    String generateNewProgramId() throws SQLException, ClassNotFoundException;


}
