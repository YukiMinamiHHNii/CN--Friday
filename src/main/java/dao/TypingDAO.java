package dao;

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

    public void createObject(Object typing){

        Session session= connect();

        try{

            session.beginTransaction();
            session.save(typing);
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

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

    public void updateObject(Object typing){

        Session session= connect();

        try{

            session.beginTransaction();
            session.update(typing);
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

    }

    public void deleteObject(Object typing){

        Session session= connect();

        try{

            session.beginTransaction();
            session.delete(typing);
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally{
            disconnect(session);
        }

    }

}
