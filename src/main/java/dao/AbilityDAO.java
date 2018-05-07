package dao;

import entities.Ability;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Friday on 06/05/2018.
 */
public class AbilityDAO extends DAO {

    private static final Logger log = LogManager.getLogger(AbilityDAO.class);

    public void createObject(Object ability){

        Session session= connect();

        try{

            session.beginTransaction();
            session.save(ability);
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

    }

    public List readAll(){

        List abilities= null;
        Session session= connect();

        try {

            session.beginTransaction();
            abilities= session.createQuery("FROM Ability").list();
            session.getTransaction().commit();

        } catch (Exception e) {
            log.debug("Error with transaction: " + e.getMessage());
        } finally {
            disconnect(session);
        }

        return abilities;

    }

    public Ability readByID(int id){

        Object ability= null;
        Session session= connect();

        try {

            session.beginTransaction();
            Query query= session.createQuery("FROM Ability WHERE abilityID= :abilityID");
            query.setParameter("abilityID", id);
            ability= query.getSingleResult();
            session.getTransaction().commit();

        } catch (Exception e) {
            log.debug("Error with transaction: " + e.getMessage());
        }

        return (Ability) ability;

    }

    public void updateObject(Object ability){

        Session session= connect();

        try{

            session.beginTransaction();
            session.update(ability);
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

    }

    public void deleteObject(Object ability){

        Session session= connect();

        try{

            session.beginTransaction();
            session.delete(ability);
            session.getTransaction().commit();

        }catch (Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

    }

}