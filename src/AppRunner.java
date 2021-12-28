import entity.Student;
import javafx.application.Application;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

public class AppRunner{

    public static void main(String[] args) {
        Student s1=new Student("S002","Isuru",22,"Galle","200/03/11","077343434","099834834","Susantha","P001","93243");

        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        session.save(s1);

        transaction.commit();
        session.close();
    }

}
