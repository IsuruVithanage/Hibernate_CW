package dao;

import bo.BOFactory;
import entity.Program;
import entity.ProgramData;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.List;

public class ProgramDataDAOImpl implements ProgramDataDAO{
    private final ProgramDAO programDAO=(ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);
    private final StudentDAO studentDAO=(StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean add(ProgramData entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(ProgramData entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public ProgramData find(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ProgramData programData = session.get(ProgramData.class, s);

        transaction.commit();

        session.close();
        return programData;
    }

    @Override
    public List<ProgramData> findAll() throws Exception {
        return null;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Program getProgram(String id) throws Exception {
        return programDAO.find(id);
    }

    @Override
    public Student getStudent(String id) throws Exception {
        return studentDAO.find(id);
    }
}
