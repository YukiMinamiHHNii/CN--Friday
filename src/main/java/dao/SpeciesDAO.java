package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.SpeciesEntry;
import entities.SpeciesExcerpt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * CN--Friday on 22/06/2018.
 */
public class SpeciesDAO extends DAO {

    private static final Logger log= LogManager.getLogger(SpeciesDAO.class);

    public int createObject(Object input) {
        return 0;
    }

    public List readAll() {
        return null;
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

    public void updateObject(Object input) {

    }

    public void deleteObject(Object input) {

    }

    public static void main(String[] args) {
        SpeciesDAO speciesDAO = new SpeciesDAO();
        ObjectMapper mapper = new ObjectMapper();

        try {
            log.debug("Read SpeciesEntry test");
            log.debug(mapper.writeValueAsString(speciesDAO.readEntryByID("1-0")));
        } catch (Exception e) {
            log.debug("Error while converting object to JSON: " + e.getMessage());
        }
    }

}
