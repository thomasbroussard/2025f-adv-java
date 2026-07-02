package fr.epita.quiz.rest;

import fr.epita.quiz.services.EmbeddedStudentDAO;
import fr.epita.quiz.services.IStudentDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class Config {


    @Bean
    public DataSource dataSource(){
        return new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
    }

    @Bean
    public IStudentDAO getDao(@Autowired DataSource ds){
        return new EmbeddedStudentDAO(ds);
    }



//    @Bean
//    public SessionFactory sessionFactory(DataSource ds, @Qualifier("hibernateProperties") Properties hibernateProperties){
//        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
//        localSessionFactoryBean.setDataSource(ds);
//        localSessionFactoryBean.setHibernateProperties(hibernateProperties);
//        return localSessionFactoryBean.getObject();
//    }

    @Bean
    public Properties hibernateProperties(){
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        props.put("hibernate.hbm2ddl.auto", "validate");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
        return props;

    }

}
