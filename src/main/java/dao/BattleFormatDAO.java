package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.BattleFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import java.util.List;

/**
 * CN--Friday on 08/05/2018.
 */
public class BattleFormatDAO extends DAO {

    private static final Logger log= LogManager.getLogger(BattleFormatDAO.class);

    public void createObject(Object format) {

    }

    public List readAll() {

        List formats= null;
        Session session= connect();

        try{

            session.beginTransaction();
            formats= session.createQuery("FROM BattleFormat").list();
            session.getTransaction().commit();

            for(Object format: formats){
                Hibernate.initialize(((BattleFormat)format).getRestrictedSpecies());
            }

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

    public void updateObject(Object format) {

    }

    public void deleteObject(Object format) {

    }

    public static void main(String[]args){

        BattleFormatDAO dao= new BattleFormatDAO();

        ObjectMapper mapper= new ObjectMapper();

        try {

//            for (Object test : dao.readAll()) {
//                log.debug(mapper.writeValueAsString(test));
//            }

            for (Object test : dao.readAllExcerpt()) {
                log.debug(mapper.writeValueAsString(test));
            }

        }catch(Exception e){
            log.debug("Error while writing object to JSON");
        }

    }

}
