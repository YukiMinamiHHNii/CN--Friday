package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Friday on 06/05/2018.
 */
public class MoveDAO extends DAO{

    private static final Logger log= LogManager.getLogger(MoveDAO.class);

    public Object createObject(Object move) {

        int result= -1;
        Session session= connect();

        try{

            session.beginTransaction();
            session.save(move);
            session.getTransaction().commit();
            result= ((Move)move).getMoveID();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally{
            disconnect(session);
        }

        return result;

    }

    public List readAll() {

        List moves= null;
        Session session= connect();

        try{

            session.beginTransaction();
            moves = session.createQuery("SELECT DISTINCT m FROM Move m JOIN FETCH m.typing JOIN FETCH m.category " +
                    "LEFT JOIN FETCH m.learntBy ORDER BY m.moveID").list();

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
            moves= session.createQuery("FROM MoveExcerpt me JOIN FETCH me.typing JOIN FETCH me.category").list();
            //(left, right, inner) Join fetch is used to initialize foreign entity values when querying
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return moves;
    }

    public List readByNameDesc(String input) {
        List moves= null;
        Session session= connect();

        try{

            session.beginTransaction();
            Query query= session.createQuery("FROM MoveExcerpt me JOIN FETCH me.typing JOIN FETCH me.category " +
                    "WHERE me.name LIKE :input OR me.description LIKE :input");
            query.setParameter("input", "%" + input + "%");
            moves= query.getResultList();
            //(left, right, inner) Join fetch is used to initialize foreign entity values when querying
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
            Query query= session.createQuery("FROM Move m JOIN FETCH m.typing JOIN FETCH m.category " +
                    "LEFT JOIN FETCH m.learntBy WHERE m.moveID= :moveID");
            query.setParameter("moveID", moveID);
            move= (Move)query.getSingleResult();

            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return move;
    }

    public MoveExcerpt readExcerptByID(int moveID){

        MoveExcerpt moveExcerpt= null;
        Session session= connect();

        try{

            session.beginTransaction();
            Query query= session.createQuery("FROM MoveExcerpt me JOIN FETCH me.typing JOIN FETCH me.category " +
                    "WHERE me.moveID= :moveID");
            query.setParameter("moveID", moveID);
            moveExcerpt= (MoveExcerpt)query.getSingleResult();
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return moveExcerpt;

    }

    public Object updateObject(Object move) {

        Session session= connect();
        Object result= null;

        try{

            session.beginTransaction();
            session.update(move);
            session.getTransaction().commit();
            result= move;

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return result;

    }

    public boolean deleteObject(Object move) {

        Session session= connect();
        boolean result= false;

        try{

            session.beginTransaction();
            session.delete(move);
            session.getTransaction().commit();
            result= true;

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return result;

    }

    public static void main(String[]args){
        MoveDAO dao= new MoveDAO();
        TypingDAO typingDAO= new TypingDAO();
        MoveCategoryDAO moveCategoryDAO= new MoveCategoryDAO();
        SpeciesDAO speciesDAO= new SpeciesDAO();

        Set<SpeciesEntry> moveSpecies= new HashSet<>();
        moveSpecies.add(speciesDAO.readEntryByID("253-0"));
        moveSpecies.add(speciesDAO.readEntryByID("254-0"));
        moveSpecies.add(speciesDAO.readEntryByID("254-1"));
        moveSpecies.add(speciesDAO.readEntryByID("255-0"));
        moveSpecies.add(speciesDAO.readEntryByID("256-0"));
        moveSpecies.add(speciesDAO.readEntryByID("257-0"));
        moveSpecies.add(speciesDAO.readEntryByID("257-1"));
        moveSpecies.add(speciesDAO.readEntryByID("258-0"));
        moveSpecies.add(speciesDAO.readEntryByID("259-0"));
        moveSpecies.add(speciesDAO.readEntryByID("26-0"));
        moveSpecies.add(speciesDAO.readEntryByID("26-1"));

        ObjectMapper mapper= new ObjectMapper();

        try {
            log.debug("Create object test");
            Move nObject= new Move("TestMove", typingDAO.readByID(1), 999, 999, "This is a createMove test",
                    moveCategoryDAO.readByID(2), moveSpecies);
            int objectID= (int)dao.createObject(nObject);
            log.debug("New object id: " + objectID);

            log.debug("Read all test");
            for (Object obj : dao.readAll()) {
                log.debug("Reading all objects: " + mapper.writeValueAsString(obj));
            }

            log.debug("Read by name/desc test");
            for (Object obj : dao.readByNameDesc("Placeholder")) {
                log.debug(mapper.writeValueAsString(obj));
            }

            log.debug("Read by ID test");
            Move testObject= dao.readByID(objectID);
            log.debug("Reading by ID: " + mapper.writeValueAsString(testObject));

            log.debug("Update test");
            log.debug("Update result: " + mapper.writeValueAsString(dao.updateObject(testObject)));

            log.debug("Delete test");
            log.debug("Delete operation result: " + dao.deleteObject(testObject));
            log.debug("Reading all excerpts");
            for (Object obj: dao.readAllExcerpt()){
                log.debug(mapper.writeValueAsString(obj));
            }

        } catch (Exception e) {
            log.debug("Error while converting object to JSON: " + e.getMessage());
        }
    }

}
