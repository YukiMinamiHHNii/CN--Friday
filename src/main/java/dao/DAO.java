package dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.APIConfig;

import java.util.List;

/**
 * Friday on 06/05/2018.
 */
public abstract class DAO {

    private static final Logger log= LogManager.getLogger(DAO.class);

    protected Session connect() {

        Session session = null;

        try {
            SessionFactory sessionFactory = APIConfig.configureDB();
            session = sessionFactory.openSession();
        } catch (Exception e) {
            log.debug("Error while connecting session: " + e.getMessage());
        }

        return session;

    }

    protected void disconnect(Session session){
        session.close();
    }

    abstract public Object createObject(Object input);
    abstract public List readAll();
    abstract public void updateObject(Object input);
    abstract public void deleteObject(Object input);

}
