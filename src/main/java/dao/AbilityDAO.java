package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    public Object createObject(Object ability){

        int result=-1;
        Session session= connect();

        try{

            session.beginTransaction();
            session.save(ability);
            session.getTransaction().commit();
            result= ((Ability)ability).getAbilityID();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return result;

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

    public Ability readByID(int abilityID){

        Object ability= null;
        Session session= connect();

        try {

            session.beginTransaction();
            Query query= session.createQuery("FROM Ability WHERE abilityID= :abilityID");
            query.setParameter("abilityID", abilityID);
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

    public static void main(String[]args){
        AbilityDAO dao= new AbilityDAO();
        ObjectMapper mapper= new ObjectMapper();

        try {
            log.debug("Create object test");
            Ability nObject= new Ability("AbilityTest", "TestAbilityDescription");
            int objectID= (int)dao.createObject(nObject);
            log.debug("New object id: " +objectID);

            log.debug("Read all test");
            for (Object obj : dao.readAll()) {
                log.debug(mapper.writeValueAsString(obj));
            }

            log.debug("Read by ID test");
            Ability testObject= dao.readByID(objectID);
            log.debug("Reading by ID: " + mapper.writeValueAsString(testObject));

            log.debug("Update test");
            testObject.setDescription("This is a test for object: " + objectID);
            dao.updateObject(testObject);
            log.debug("Update result: " + mapper.writeValueAsString(dao.readByID(objectID)));

            log.debug("Delete test");
            dao.deleteObject(testObject);
            for (Object obj : dao.readAll()) {
                log.debug(mapper.writeValueAsString(obj));
            }
        } catch (Exception e) {
            log.debug("Error while converting object to JSON: " + e.getMessage());
        }

    }

}