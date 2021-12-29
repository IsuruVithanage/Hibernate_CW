package bo;

import dao.DAOFactory;
import dao.ProgramDAO;
import dto.ProgramDTO;
import entity.Program;

import java.util.List;

public class ProgramBOImpl implements ProgramBO{

    private final ProgramDAO programDAO=(ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public boolean add(ProgramDTO programDTO) throws Exception {
        return programDAO.add(new Program(
                programDTO.getProgramID(),
                programDTO.getProName(),
                programDTO.getDuration(),
                programDTO.getFee()
        ));
    }

    @Override
    public List<ProgramDTO> findAll() throws Exception {
        return null;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public boolean update(ProgramDTO programDTO) throws Exception {
        return false;
    }
}
