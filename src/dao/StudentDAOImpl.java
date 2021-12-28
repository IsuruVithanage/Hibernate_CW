package dao;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.util.List;

public class StudentDAOImpl implements StudentDAO{

    @Override
    public boolean add(Student entity) throws Exception {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

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
        return null;
    }

    @Override
    public List<Student> findAll() throws Exception {
        return null;
    }
}
