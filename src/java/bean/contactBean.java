/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Contacts;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Disha
 */
@ManagedBean
@RequestScoped
public class contactBean {

    /**
     * Creates a new instance of contactBean
     */
    static Session session = null;
    private String firstName;
    private String lastName, email, mobNo, comName, comLoc, designation, url, notes, addedBy;
    private String gender;
    
    public contactBean() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComLoc() {
        return comLoc;
    }

    public void setComLoc(String comLoc) {
        this.comLoc = comLoc;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public void add()
    {
        System.out.println("It works!");
    }
    
    public void openSession() {
        SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("opening session");
    }

    public void closeSession() {
        session.getTransaction().commit();
        session.flush();
        session.close();
    }

    public String addContact() {
        System.out.println("addContact()");
        openSession();
        System.out.println("Inside add");
        Contacts contact = new Contacts();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setEmail(email);
        contact.setPhoneNo(mobNo);
        contact.setCompanyName(comName);
        contact.setCompanyLoc(comLoc);
        contact.setDesignation(designation);
        contact.setLinkedInUrl(url);
        contact.setAddedBy(addedBy);
        contact.setNotes(notes);

        System.out.println("notes..."+notes);
        boolean check = false;
        System.out.println("check1.." + firstName);
        if (firstName != null && lastName != null && email != null && comName != null && comLoc != null && designation != null && addedBy != null) {
            session.save(contact);
            System.out.println("save in the database..");
            check = true;
        }
        if (check) {
            closeSession();
            return "success";
        } else {
            closeSession();
            return "error";
        }
    }

    public List<Contacts> getContactsDetails() {
        List<Contacts> contactList = new ArrayList<Contacts>();
        try {
            openSession();
            String hql_query = "from Contacts";
            Query query = (Query) session.createQuery(hql_query);
            //prepare statement
            contactList = (List<Contacts>) query.list();
            System.out.println(contactList);
            System.out.println("reading value");
            for (Contacts cl : contactList) {
                System.out.println("check1..");
                System.out.println(cl.getFirstName());
            }
//            for (Iterator it = query.iterate();it.hasNext();) {
//                Contacts c = (Contacts) it.next();
//                System.out.println(c.getFirstName());
//            }
            System.out.println("Done..");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeSession();
        }
        return contactList;
    }
}
