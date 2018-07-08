package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Ability;
import entities.MoveCategory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Friday on 06/05/2018.
 */
public class MoveCategoryDAO extends DAO {

    private static final Logger log= LogManager.getLogger(MoveCategoryDAO.class);

    public Object createObject(Object moveCat) {

        int result= -1;

        Session session= connect();

        try{

            session.beginTransaction();
            session.save(moveCat);
            session.getTransaction().commit();
            result= ((MoveCategory) moveCat).getMoveCategoryID();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return result;

    }

    public List readAll() {
        List movCats=null;
        Session session= connect();

        try{

            session.beginTransaction();
            movCats= session.createQuery("FROM MoveCategory").list();
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally{
            disconnect(session);
        }

        return movCats;
    }

    public List readByNameDesc(String input) {
        List movCats=null;
        Session session= connect();

        try{

            session.beginTransaction();
            Query query= session.createQuery("FROM MoveCategory mc WHERE mc.name LIKE :input");
            query.setParameter("input", "%" + input + "%");
            movCats= query.getResultList();
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally{
            disconnect(session);
        }

        return movCats;
    }

    public MoveCategory readByID(int id){

        Object movCat=null;
        Session session= connect();

        try{

            session.beginTransaction();
            Query query= session.createQuery("FROM MoveCategory WHERE moveCategoryID= :mcid");
            query.setParameter("mcid", id);
            movCat= query.getSingleResult();
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally{
            disconnect(session);
        }

        return (MoveCategory) movCat;

    }

    public Object updateObject(Object moveCat) {

        Session session= connect();
        Object result= null;

        try{

            session.beginTransaction();
            session.update(moveCat);
            session.getTransaction().commit();
            result= moveCat;

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return result;

    }

    public boolean deleteObject(Object moveCat) {

        Session session= connect();
        boolean result= false;

        try{

            session.beginTransaction();
            session.delete(moveCat);
            session.getTransaction().commit();
            result= true;

        }catch (Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return result;

    }

    public static void main(String[]args){
        MoveCategoryDAO dao = new MoveCategoryDAO();
        ObjectMapper mapper = new ObjectMapper();

        try {
            log.debug("Create object test");
            MoveCategory nObject= new MoveCategory("MoveCategoryTest");
            int objectID= (int)dao.createObject(nObject);
            log.debug("New object id: " +objectID);

            log.debug("Read all test");
            for (Object obj : dao.readAll()) {
                log.debug(mapper.writeValueAsString(obj));
            }

            log.debug("Read by name/desc test");
            for (Object obj : dao.readByNameDesc("al")) {
                log.debug(mapper.writeValueAsString(obj));
            }

            log.debug("Read by ID test");
            MoveCategory testObject= dao.readByID(objectID);
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
