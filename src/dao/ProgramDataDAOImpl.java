package dao;

import entity.Program;
import entity.ProgramData;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.List;

public class ProgramDataDAOImpl implements ProgramDataDAO {
    private final ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

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
        boolean bool = false;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("DELETE FROM ProgramData WHERE id=:id");
        query.setParameter("id", s);

        if (query.executeUpdate() > 0) {
            bool = true;
        }


        transaction.commit();
        session.close();
        return bool;
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
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM ProgramData");
        List<ProgramData> list = query.list();


        transaction.commit();

        session.close();
        System.out.println(list.get(0).getId());
        return list;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT id FROM ProgramData ORDER BY id DESC LIMIT 1";
        Query sqlQuery = session.createSQLQuery(hql);
        List<String> idlist = sqlQuery.list();

        transaction.commit();
        session.close();

        for (String id : idlist) {
            if (id != null) {
                int newStudentId = Integer.parseInt(id.replace("D", "")) + 1;
                return String.format("D%03d", newStudentId);
            }
        }
        return "D001";
    }

    @Override
    public Program getProgram(String id) throws Exception {
        Program program = programDAO.find(id);
        System.out.println(program);
        return program;
    }

    @Override
    public List<ProgramData> getRegProgram(String sid) throws Exception {
        System.out.println(sid);
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("gg");

        Query query = session.createQuery("FROM ProgramData p WHERE p.sID=:id");
        query.setParameter("id", sid);
        List<ProgramData> list = query.list();
        /*List<ProgramData> list=new ArrayList<>();
        ProgramData programData = session.get(ProgramData.class, sid);
        list.add(programData);*/

        transaction.commit();

        session.close();
        return list;
    }

    @Override
    public Student getStudent(String id) throws Exception {
        return studentDAO.find(id);
    }
}
