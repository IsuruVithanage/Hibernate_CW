package bo;

import dao.DAOFactory;
import dao.ProgramDataDAO;
import dto.ProgramDataDTO;
import entity.Program;
import entity.ProgramData;
import entity.Student;

import java.sql.SQLException;
import java.util.List;

public class ProgramDataBOImpl implements ProgramDataBO{

    private final ProgramDataDAO programDataDAO=(ProgramDataDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAMDATA);

    @Override
    public boolean add(ProgramDataDTO programDataDTO) throws Exception {
        System.out.println(programDataDAO.getProgram(programDataDTO.getpID()));
        return programDataDAO.add(new ProgramData(
                programDataDTO.getId(),
                programDataDAO.getStudent(programDataDTO.getsID()),
                programDataDAO.getProgram(programDataDTO.getpID()),
                programDataDTO.getDate()
        ));
    }

    @Override
    public List<ProgramDataDTO> findAll() throws Exception {
        return null;
    }

    @Override
    public ProgramDataDTO find() throws Exception {
        return null;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public boolean update(ProgramDataDTO programDataDTO) throws Exception {
        return false;
    }

    @Override
    public String generateNewStudentId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Program getProgram(String id) throws Exception {
        return programDataDAO.getProgram(id);
    }

    @Override
    public Student getStudent(String id) throws Exception {
        return programDataDAO.getStudent(id);
    }
}
