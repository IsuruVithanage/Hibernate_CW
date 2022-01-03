package bo;

import dao.DAOFactory;
import dao.ProgramDAO;
import dao.ProgramDataDAO;
import dao.StudentDAO;
import dto.ProgramDataDTO;
import entity.Program;
import entity.ProgramData;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramDataBOImpl implements ProgramDataBO{

    private final ProgramDataDAO programDataDAO=(ProgramDataDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAMDATA);
    private final ProgramDAO programDAO=(ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);
    private final StudentDAO studentDAO=(StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean add(ProgramDataDTO programDataDTO) throws Exception {
        Student student = studentDAO.find(programDataDTO.getsID());
        Program program = programDAO.find(programDataDTO.getpID());

        return programDataDAO.add(new ProgramData(
                programDataDTO.getId(),
                student,
                program,
                programDataDTO.getDate()
        ));
    }

    @Override
    public List<ProgramDataDTO> findAll() throws Exception {
        List<ProgramDataDTO> programDataDTOS=new ArrayList<>();
        List<ProgramData> regProgram = programDataDAO.findAll();
        System.out.println(regProgram.get(0).getpID());
        for (ProgramData programData : regProgram) {
            programDataDTOS.add(new ProgramDataDTO(
                    programData.getId(),
                    programData.getsID().getStudentID(),
                    programData.getpID().getProgramID(),
                    programData.getDate()
            ));
        }
        return programDataDTOS;
    }

    @Override
    public ProgramDataDTO find() throws Exception {
        return null;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return programDataDAO.delete(id);
    }

    @Override
    public boolean update(ProgramDataDTO programDataDTO) throws Exception {
        return false;
    }

    @Override
    public String generateNewProgramDataId() throws SQLException, ClassNotFoundException {
        return programDataDAO.generateId();
    }

    @Override
    public Program getProgram(String id) throws Exception {
        return programDataDAO.getProgram(id);
    }

    @Override
    public Student getStudent(String id) throws Exception {
        return programDataDAO.getStudent(id);
    }

    @Override
    public List<ProgramDataDTO> getregProgram(String id) throws Exception {
        List<ProgramDataDTO> programDataDTOS=new ArrayList<>();
        List<ProgramData> regProgram = programDataDAO.getRegProgram(id);
        for (ProgramData programData : regProgram) {
            programDataDTOS.add(new ProgramDataDTO(
                    programData.getId(),
                    programData.getsID().getStudentID(),
                    programData.getpID().getProgramID(),
                    programData.getDate()
            ));
        }
        return programDataDTOS;

    }
}
