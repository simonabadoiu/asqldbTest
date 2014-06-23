package test.java.org.n52.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class RastestExample
{
    private static SessionFactory sessionFactory = null;

    public static void insert(String name) {
        Session session = null;
        try 
        {
            try
            {
                sessionFactory = AppFactory.getSessionFactory();
                session = sessionFactory.openSession();

                System.out.println("Inserting Record");
                Transaction tx = session.beginTransaction();

                Person person = new Person();
                person.setName(name);                            

                session.save(person);
                tx.commit();

                System.out.println("Insert Done");
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        finally
        {
            session.close();
        }
    }

    public static void select(int id) {
        Session session = null;
        try 
        {
            try
            {
                sessionFactory = AppFactory.getSessionFactory();
                session = sessionFactory.openSession();

                System.out.println("Selecting Records");
                Transaction tx = session.beginTransaction();

                List<Rastest> rastest = session.createCriteria(Rastest.class)
                        .add(Restrictions.eq("id",id))
                        .list();


                System.out.println("The result is :" + rastest.get(0).getId() + 
                        "  " + rastest.get(0).getColl());

                tx.commit();
                System.out.println("Select Done");
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        finally
        {
            session.close();
        }
    }
    
    public static void main(String[] args)
    {               
        Session session = null;
//        insert("Andreea");
        select(0);
    }
}