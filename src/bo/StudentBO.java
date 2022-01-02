package bo;

import dto.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO{
    public boolean add(StudentDTO studentDTO) throws Exception;

    public List<StudentDTO> findAll() throws Exception;

    public boolean delete(String id) throws Exception;

    public boolean update(StudentDTO studentDTO) throws Exception;

    public String generateNewStudentId() throws SQLException, ClassNotFoundException;
}
