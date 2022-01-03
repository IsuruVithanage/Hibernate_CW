package bo;

import dao.DAOFactory;
import dao.StudentDAO;
import dto.StudentDTO;
import entity.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);


    @Override
    public boolean add(StudentDTO studentDTO) throws Exception {
        return studentDAO.add(new Student(
                studentDTO.getStudentID(),
                studentDTO.getName(),
                studentDTO.getAge(),
                studentDTO.getAddress(),
                studentDTO.getDateOfBirth(),
                studentDTO.getPhoneNumber(),
                studentDTO.getParentPhoneNumber(),
                studentDTO.getParentName()
        ));
    }

    @Override
    public List<StudentDTO> findAll() throws Exception {
        return null;
    }

    @Override
    public StudentDTO find(String id) throws Exception {
        Student student = studentDAO.find(id);
        return new StudentDTO(
                student.getStudentID(),
                student.getName(),
                student.getAge(),
                student.getAddress(),
                student.getDateOfBirth(),
                student.getAddress(),
                student.getPhoneNumber(),
                student.getParentPhoneNumber(),
                student.getParentName()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return studentDAO.delete(id);
    }

    @Override
    public boolean update(StudentDTO customerDTO) throws Exception {
        return false;
    }

    @Override
    public String generateNewStudentId() throws SQLException, ClassNotFoundException {
        return studentDAO.generateId();
    }
}
