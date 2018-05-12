package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Move;
import entities.MoveExcerpt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Friday on 06/05/2018.
 */
public class MoveDAO extends DAO{

    private static final Logger log= LogManager.getLogger(MoveDAO.class);

    public void createObject(Object move) {

        Session session= connect();

        try{

            session.beginTransaction();
            session.save(move);
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally{
            disconnect(session);
        }

    }

    public List readAll() {

        List moves= null;
        Session session= connect();

        try{

            session.beginTransaction();
            moves= session.createQuery("FROM Move").list();

            for(Object move: moves){
                Hibernate.initialize(((Move)move).getCategory());
            }

            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return moves;
    }

    public List readAllExcerpt(){

        List moves= null;
        Session session= connect();

        try{

            session.beginTransaction();
            moves= session.createQuery("FROM MoveExcerpt me join fetch me.typing join fetch me.category").list();
            //Join fetch is used to initialize foreign entity values when querying
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return moves;

    }

    public Move readByID(int moveID) {

        Move move= null;
        Session session= connect();

        try{

            session.beginTransaction();
            Query query= session.createQuery("FROM Move WHERE moveID= :moveID");
            query.setParameter("moveID", moveID);
            move= (Move)query.getSingleResult();

            Hibernate.initialize(move.getCategory());

            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return move;
    }

    public void updateObject(Object move) {

        Session session= connect();

        try{

            session.beginTransaction();
            session.update(move);
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

    }

    public void deleteObject(Object move) {

        Session session= connect();

        try{

            session.beginTransaction();
            session.delete(move);
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

    }

    public static void main(String[]args){

        MoveDAO dao= new MoveDAO();
        ObjectMapper mapper= new ObjectMapper();

        try {

            for (Object test : dao.readAllExcerpt()) {
                log.debug(mapper.writeValueAsString(test));
            }

        }catch(Exception e){
            log.debug("Error while converting object to JSON: " + e.getMessage());
        }

    }

}
