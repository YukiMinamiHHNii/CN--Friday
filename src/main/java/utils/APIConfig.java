package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

/**
 * CN--Friday on 30/04/2018.
 */
public class APIConfig {

    public static SessionFactory configureDB() {

        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.setProperties(configureConnection());

        return configuration.buildSessionFactory();

    }

    private static Properties configureConnection() {

        Properties props = new Properties();

        props.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        props.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/poke_data_tools?useLegacyDatetimeCode=false&serverTimezone=UTC");
        props.setProperty(Environment.USER, "pdt-user");
        props.setProperty(Environment.PASS, "H567kil16A");
        props.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

        configureConnectionPool(props);

        return props;
    }

    private static void configureConnectionPool(Properties props) {
        // Maximum waiting time for a connection from the pool
        props.setProperty("hibernate.hikari.connectionTimeout", "20000");
        // Minimum number of idle connections in the pool
        props.setProperty("hibernate.hikari.minimumIdle", "1");
        // Maximum number of actual connection in the pool
        props.setProperty("hibernate.hikari.maximumPoolSize", "30");
        // Maximum time that a connection is allowed to sit idle in the pool
        props.setProperty("hibernate.hikari.idleTimeout", "3000");
    }

}
