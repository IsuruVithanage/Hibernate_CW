package dao;

import entity.Program;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean add(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Student find(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, s);

        transaction.commit();

        session.close();
        return student;
    }

    @Override
    public List<Student> findAll() throws Exception {
        return null;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT studentID FROM Student ORDER BY studentID DESC LIMIT 1";
        Query sqlQuery = session.createSQLQuery(hql);
        List<String> studentList = sqlQuery.list();

        transaction.commit();
        session.close();

        for (String id : studentList) {
            if (id != null) {
                int newStudentId = Integer.parseInt(id.replace("S", "")) + 1;
                return String.format("S%03d", newStudentId);
            }
        }
        return "S001";
    }
}
