package dao;

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

    public void createObject(Object moveCat) {

        Session session= connect();

        try{

            session.beginTransaction();
            session.save(moveCat);
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

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

    public void updateObject(Object moveCat) {

        Session session= connect();

        try{

            session.beginTransaction();
            session.update(moveCat);
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

    }

    public void deleteObject(Object moveCat) {

        Session session= connect();

        try{

            session.beginTransaction();
            session.delete(moveCat);
            session.getTransaction().commit();

        }catch (Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

    }

}
