package dao;

import entity.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.util.List;

public class ProgramDAOImpl implements ProgramDAO{

    @Override
    public boolean add(Program entity) throws Exception {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Program entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Program find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Program> findAll() throws Exception {
        return null;
    }
}
