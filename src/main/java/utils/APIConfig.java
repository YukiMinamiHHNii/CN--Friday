package utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

/**
 * CN--Friday on 30/04/2018.
 */
public class APIConfig {

    private static SessionFactory configureDB() {

        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.setProperties(configureConnection());
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);

    }

    private static Properties configureConnection() {

        Properties props = new Properties();

        props.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        props.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:8080/DB");
        props.setProperty("hibernate.connection.username", "user");
        props.setProperty("hibernate.connection.password", "password");
        props.setProperty("hibernate.current_session_context_class", "thread");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

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
