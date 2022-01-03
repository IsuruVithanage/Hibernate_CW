package dao;

import entity.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {

    @Override
    public boolean add(Program entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Program entity) throws Exception {
        boolean bool = false;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("UPDATE Program SET duration=:duration,fee=:fee WHERE programID=:id");
        query.setParameter("duration", entity.getDuration());
        query.setParameter("fee", entity.getFee());
        query.setParameter("id", entity.getProgramID());

        if (query.executeUpdate() > 0) {
            bool = true;
        }

        transaction.commit();
        session.close();
        return bool;
    }

    @Override
    public boolean delete(String s) throws Exception {

        return false;
    }

    @Override
    public Program find(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Program program = session.get(Program.class, s);

        transaction.commit();

        session.close();
        return program;
    }

    @Override
    public List<Program> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query program = session.createQuery("FROM Program");

        List<Program> list = program.list();

        transaction.commit();

        session.close();
        return list;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT programID FROM Program ORDER BY programID DESC";
        Query sqlQuery = session.createQuery(hql);
        List<String> programids = sqlQuery.list();

        transaction.commit();
        session.close();

        for (String id : programids) {
            if (id != null) {
                int newProgramId = Integer.parseInt(id.replace("CT", "")) + 1;
                return String.format("CT%03d", newProgramId);
            }
        }
        return "CT001";
    }

}
