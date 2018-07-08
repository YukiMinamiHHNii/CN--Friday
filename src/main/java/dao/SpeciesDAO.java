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
 * CN--Friday on 22/06/2018.
 */
public class SpeciesDAO extends DAO {

    private static final Logger log= LogManager.getLogger(SpeciesDAO.class);

    public Object createObject(Object species) {

        String result= "Error";
        Session session= connect();

        try{

            session.beginTransaction();
            session.save(species);
            session.getTransaction().commit();
            result= ((Species)species).getSpeciesID();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally{
            disconnect(session);
        }

        return result;

    }

    public List readAll() {

        List species= null;
        Session session= connect();

        try{

            session.beginTransaction();
            species = session.createQuery("SELECT DISTINCT s FROM Species s LEFT JOIN FETCH s.abilities " +
                    "LEFT JOIN FETCH s.typing " +
                    "LEFT JOIN FETCH s.learnset moves " +
                    "LEFT JOIN FETCH moves.typing " +
                    "LEFT JOIN FETCH moves.category " +
                    "ORDER BY s.speciesID").list();
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return species;

    }

    public List readAllExcerpt(){
        List speciesExcerpt= null;
        Session session= connect();

        try{

            session.beginTransaction();
            speciesExcerpt = session.createQuery("SELECT DISTINCT se FROM SpeciesExcerpt se " +
                    "LEFT JOIN FETCH se.ability " +
                    "LEFT JOIN FETCH se.typing " +
                    "ORDER BY se.speciesID").list();
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return speciesExcerpt;
    }

    public List readAllEntry(){

        List speciesEntry= null;
        Session session= connect();

        try{

            session.beginTransaction();
            speciesEntry = session.createQuery("FROM SpeciesEntry se ORDER BY se.speciesID").list();
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return speciesEntry;

    }

    public List readByNameDesc(String input) {
        List speciesExcerpt= null;
        Session session= connect();

        try{

            session.beginTransaction();
            Query query= session.createQuery("SELECT DISTINCT se FROM SpeciesExcerpt se " +
                    "LEFT JOIN FETCH se.ability " +
                    "LEFT JOIN FETCH se.typing " +
                    "WHERE se.name LIKE :input ");
            query.setParameter("input", "%" + input + "%");
            speciesExcerpt= query.getResultList();
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return speciesExcerpt;
    }

    public Species readByID(String speciesID){

        Species species= null;
        Session session= connect();

        try{

            session.beginTransaction();
            Query query= session.createQuery("SELECT DISTINCT s FROM Species s LEFT JOIN FETCH s.abilities " +
                    "LEFT JOIN FETCH s.typing " +
                    "LEFT JOIN FETCH s.learnset moves " +
                    "LEFT JOIN FETCH moves.typing " +
                    "LEFT JOIN FETCH moves.category " +
                    "WHERE s.speciesID= :speciesID");
            query.setParameter("speciesID", speciesID);
            species= (Species)query.getSingleResult();
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return species;

    }

    public SpeciesExcerpt readExcerptByID(String speciesID){

        SpeciesExcerpt speciesExcerpt= null;
        Session session= connect();

        try{

            session.beginTransaction();
            Query query= session.createQuery("SELECT DISTINCT se FROM SpeciesExcerpt se " +
                    "LEFT JOIN FETCH se.ability " +
                    "LEFT JOIN FETCH se.typing " +
                    "WHERE se.speciesID= :speciesID");
            query.setParameter("speciesID", speciesID);
            speciesExcerpt= (SpeciesExcerpt)query.getSingleResult();
            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return speciesExcerpt;

    }

    public SpeciesEntry readEntryByID(String speciesID){

        SpeciesEntry speciesEntry= null;
        Session session= connect();

        try{

            session.beginTransaction();
            Query query= session.createQuery("FROM SpeciesEntry se WHERE se.speciesID= :speciesID");
            query.setParameter("speciesID", speciesID);
            speciesEntry= (SpeciesEntry)query.getSingleResult();

            session.getTransaction().commit();

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return speciesEntry;

    }

    public Object updateObject(Object species) {

        Session session= connect();
        Object result= null;

        try{

            session.beginTransaction();
            session.update(species);
            session.getTransaction().commit();
            result= species;

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return result;

    }

    public boolean deleteObject(Object species) {

        Session session= connect();
        boolean result= false;

        try{

            session.beginTransaction();
            session.delete(species);
            session.getTransaction().commit();
            result= true;

        }catch(Exception e){
            log.debug("Error with transaction: " + e.getMessage());
        }finally {
            disconnect(session);
        }

        return result;

    }

    public static void main(String[] args) {
        SpeciesDAO dao= new SpeciesDAO();
        AbilityDAO abilityDAO= new AbilityDAO();
        TypingDAO typingDAO= new TypingDAO();
        MoveDAO moveDAO= new MoveDAO();

        Set<Ability> speciesAbility= new HashSet<>();
        speciesAbility.add(abilityDAO.readByID(91));
        speciesAbility.add(abilityDAO.readByID(184));
        speciesAbility.add(abilityDAO.readByID(106));

        Set<Typing> speciesTyping= new HashSet<>();
        speciesTyping.add(typingDAO.readByID(10));
        speciesTyping.add(typingDAO.readByID(14));

        Set<MoveExcerpt> speciesMove= new HashSet<>();
        speciesMove.add(moveDAO.readExcerptByID(71));
        speciesMove.add(moveDAO.readExcerptByID(709));
        speciesMove.add(moveDAO.readExcerptByID(51));
        speciesMove.add(moveDAO.readExcerptByID(151));
        speciesMove.add(moveDAO.readExcerptByID(491));
        speciesMove.add(moveDAO.readExcerptByID(512));
        speciesMove.add(moveDAO.readExcerptByID(367));
        speciesMove.add(moveDAO.readExcerptByID(332));
        speciesMove.add(moveDAO.readExcerptByID(177));
        speciesMove.add(moveDAO.readExcerptByID(495));

        ObjectMapper mapper= new ObjectMapper();

        try {
            log.debug("Create object test");
            Species nObject= new Species("999-9", "TestSpecies", 999, 999, 999, 999, 999, 999, 999, true, speciesAbility,
                    speciesTyping, speciesMove);

            String objectID= (String)dao.createObject(nObject);
            log.debug("New object id: " + objectID);

            log.debug("Read all test");
            for (Object obj : dao.readAll()) {
                log.debug("Reading all objects: " + mapper.writeValueAsString(obj));
            }

            log.debug("Read by name/desc test");
            for (Object obj : dao.readByNameDesc("chu")) {
                log.debug(mapper.writeValueAsString(obj));
            }

            log.debug("Read by ID test");
            Species testObject= dao.readByID(objectID);
            log.debug("Reading by ID: " + mapper.writeValueAsString(testObject));

            log.debug("Update test");
            testObject.setName("UpdatedSpecies");
            log.debug("Update result: " + mapper.writeValueAsString(dao.updateObject(testObject)));

            log.debug("Delete test");
            log.debug("Delete operation result: " + dao.deleteObject(testObject));

            log.debug("Reading all excerpts");
            for (Object obj: dao.readAllExcerpt()){
                log.debug("testing: " + mapper.writeValueAsString(obj));
            }

            log.debug("Reading all entry");
            for (Object obj : dao.readAllEntry()) {
                log.debug(mapper.writeValueAsString(obj));
            }

            log.debug("Read Excerpt by ID");
            log.debug(mapper.writeValueAsString(dao.readExcerptByID("6-0")));

            log.debug("Read Entry by ID");
            log.debug(mapper.writeValueAsString(dao.readEntryByID("6-0")));

            log.debug("Read Species by ID");
            log.debug(mapper.writeValueAsString(dao.readByID("1-0")));

        } catch (Exception e) {
            log.debug("Error while converting object to JSON: " + e.getMessage());
        }
    }

}
