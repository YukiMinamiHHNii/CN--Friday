package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.BattleFormat;
import entities.SpeciesEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * CN--Friday on 08/05/2018.
 */
public class BattleFormatDAO extends DAO {

    private static final Logger log= LogManager.getLogger(BattleFormatDAO.class);

    public Object createObject(Object format) {

        int result= -1;
        Session session= connect();

        try{

            session.beginTransaction();
            session.save(format);
            session.getTransaction().commit();
            result= ((BattleFormat)format).getFormatID();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }

        return result;

    }

    public List readAll() {
        List formats= null;
        Session session= connect();

        try{

            session.beginTransaction();
            formats= session.createQuery("SELECT DISTINCT bf FROM BattleFormat bf " +
                    "LEFT JOIN FETCH bf.restrictedSpecies").list();
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return formats;
    }

    public List readAllExcerpt(){
        List formats= null;
        Session session= connect();

        try{

            session.beginTransaction();
            formats= session.createQuery("FROM BattleFormatExcerpt").list();
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally{
            disconnect(session);
        }

        return formats;
    }

    public List readByNameDesc(String input) {
        List formats= null;
        Session session= connect();

        try{

            session.beginTransaction();
            Query query= session.createQuery("SELECT DISTINCT bf FROM BattleFormat bf " +
                    "LEFT JOIN FETCH bf.restrictedSpecies " +
                    "WHERE bf.name LIKE :input OR bf.description LIKE :input");
            query.setParameter("input", "%" + input + "%");
            formats= query.getResultList();
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally{
            disconnect(session);
        }

        return formats;
    }

    public BattleFormat readByID(int formatID) {

        BattleFormat format= null;
        Session session= connect();

        try{

            session.beginTransaction();
            Query query= session.createQuery("FROM BattleFormat bf LEFT JOIN FETCH bf.restrictedSpecies" +
                    " WHERE bf.formatID= :formatID");
            query.setParameter("formatID", formatID);
            format= (BattleFormat)query.getSingleResult();

            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return format;
    }

    public Object updateObject(Object format) {

        Session session= connect();
        Object result= null;

        try{

            session.beginTransaction();
            session.update(format);
            session.getTransaction().commit();
            result= format;

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return result;

    }

    public boolean deleteObject(Object format) {

        Session session= connect();
        boolean result= false;

        try{

            session.beginTransaction();
            session.delete(format);
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
        BattleFormatDAO dao= new BattleFormatDAO();
        SpeciesDAO speciesDAO= new SpeciesDAO();

        Set<SpeciesEntry> formatSpecies= new HashSet<>();
        formatSpecies.add(speciesDAO.readEntryByID("253-0"));
        formatSpecies.add(speciesDAO.readEntryByID("254-0"));
        formatSpecies.add(speciesDAO.readEntryByID("254-1"));
        formatSpecies.add(speciesDAO.readEntryByID("255-0"));
        formatSpecies.add(speciesDAO.readEntryByID("256-0"));
        formatSpecies.add(speciesDAO.readEntryByID("257-0"));
        formatSpecies.add(speciesDAO.readEntryByID("257-1"));
        formatSpecies.add(speciesDAO.readEntryByID("258-0"));
        formatSpecies.add(speciesDAO.readEntryByID("259-0"));
        formatSpecies.add(speciesDAO.readEntryByID("26-0"));
        formatSpecies.add(speciesDAO.readEntryByID("26-1"));

        ObjectMapper mapper= new ObjectMapper();

        try {
            log.debug("Create object test");
            BattleFormat nObject= new BattleFormat("TestBattleFormat1", "CreateBattleFormat test", formatSpecies);
            int objectID= (int)dao.createObject(nObject);
            log.debug("New object id: " + objectID);

            log.debug("Read all test");
            for (Object obj : dao.readAll()) {
                log.debug("Reading all objects: " + mapper.writeValueAsString(obj));
            }

            log.debug("Read by name/desc test");
            for (Object obj : dao.readByNameDesc("1")) {
                log.debug(mapper.writeValueAsString(obj));
            }

            log.debug("Read by ID test");
            BattleFormat testObject= dao.readByID(objectID);
            log.debug("Reading by ID: " + mapper.writeValueAsString(testObject));

            log.debug("Update test");
            testObject.setDescription("Update test for battleFormat");
            log.debug("Update result: " + mapper.writeValueAsString(dao.updateObject(testObject)));

            log.debug("Delete test");
            log.debug("Delete operation result: " + dao.deleteObject(testObject));
            for (Object obj : dao.readAll()) {
                log.debug(mapper.writeValueAsString(obj));
            }

            log.debug("Reading all excerpts");
            for (Object obj: dao.readAllExcerpt()){
                log.debug(mapper.writeValueAsString(obj));
            }
        } catch (Exception e) {
            log.debug("Error while converting object to JSON: " + e.getMessage());
        }
    }

}
