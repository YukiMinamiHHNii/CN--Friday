package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Typing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * CN--Friday on 07/05/2018.
 */
public class TypingDAO extends DAO {

    private static final Logger log= LogManager.getLogger(TypingDAO.class);

    public Object createObject(Object typing){

        int result= -1;
        Session session= connect();

        try{

            session.beginTransaction();
            session.save(typing);
            session.getTransaction().commit();
            result= ((Typing)typing).getTypingID();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return result;

    }

    public List readAll(){
        List typing= null;
        Session session= connect();

        try{

            session.beginTransaction();
            typing=session.createQuery("FROM Typing").list();
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return typing;
    }

    public List readByNameDesc(String input) {
        List typing= null;
        Session session= connect();

        try{

            session.beginTransaction();
            Query query= session.createQuery("FROM Typing t WHERE t.name LIKE :input");
            query.setParameter("input", "%" + input + "%");
            typing= query.getResultList();
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return typing;
    }

    public Typing readByID(int typingID){

        Object typing= null;
        Session session= connect();

        try{

            session.beginTransaction();
            Query query= session.createQuery("FROM Typing WHERE typingID= :typingID");
            query.setParameter("typingID", typingID);
            typing= query.getSingleResult();
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return (Typing) typing;
    }

    public Object updateObject(Object typing){

        Session session= connect();
        Object result= null;

        try{

            session.beginTransaction();
            session.update(typing);
            session.getTransaction().commit();
            result= typing;

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return result;

    }

    public boolean deleteObject(Object typing){

        Session session= connect();
        boolean result= false;

        try{

            session.beginTransaction();
            session.delete(typing);
            session.getTransaction().commit();
            result= true;

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally{
            disconnect(session);
        }

        return result;

    }

    public static void main(String[]args){
        TypingDAO dao= new TypingDAO();
        ObjectMapper mapper= new ObjectMapper();

        try {
            log.debug("Create object test");
            Typing nObject= new Typing("TypingTest");
            int objectID= (int)dao.createObject(nObject);
            log.debug("New object id: " +objectID);

            log.debug("Read all test");
            for (Object obj : dao.readAll()) {
                log.debug(mapper.writeValueAsString(obj));
            }

            log.debug("Read by name/desc test");
            for (Object obj : dao.readByNameDesc("ing")) {
                log.debug(mapper.writeValueAsString(obj));
            }

            log.debug("Read by ID test");
            Typing testObject= dao.readByID(objectID);
            log.debug("Reading by ID: " + mapper.writeValueAsString(testObject));

            log.debug("Update test");
            testObject.setName("This is a test for object: " + objectID);
            log.debug("Update result: " + mapper.writeValueAsString(dao.updateObject(testObject)));

            log.debug("Delete test");
            log.debug("Delete operation result: " + dao.deleteObject(testObject));
            for (Object obj : dao.readAll()) {
                log.debug(mapper.writeValueAsString(obj));
            }
        } catch (Exception e) {
            log.debug("Error while converting object to JSON: " + e.getMessage());
        }

    }

}
