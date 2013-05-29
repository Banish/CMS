/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import entities.Contacts;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Disha
 */
public class contactHelper {
    
    static Session session = null;
    public static void main(String[] args) {
        
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();

            String hql_query = "from Contacts";
            Query query = session.createQuery(hql_query);
            //prepare statement

            
            System.out.println("reading value");
            for (Iterator it = query.iterate();
                    it.hasNext();) {
                Contacts c = (Contacts) it.next();
                System.out.println(c.getFirstName());
            }

            session.getTransaction()
                    .commit();
            System.out.println("Done..");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }


    }
    public List getContactsDetails() {
        List<Contacts> contactList = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select firstname from Contacts");
//            contactList = (List<Contacts>) 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactList;
    }
    
}
