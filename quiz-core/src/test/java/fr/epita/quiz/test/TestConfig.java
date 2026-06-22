package fr.epita.quiz.test;

import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.services.EmbeddedStudentDAO;
import fr.epita.quiz.services.IStudentDAO;
import jakarta.transaction.TransactionManager;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

@Configuration
public class TestConfig {


    @Bean
    public DataSource dataSource(){
        return new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
    }

    @Bean
    public IStudentDAO getDao(@Autowired DataSource ds){
        return new EmbeddedStudentDAO(ds);
    }

    public IStudentDAO getFakeDao(@Autowired DataSource ds){
        return new IStudentDAO() {
            @Override
            public void create(Student student) {
            }

            @Override
            public void update(Student student) {

            }

            @Override
            public void delete(Student student) {

            }

            @Override
            public List<Student> search(String id) {
                return List.of();
            }

            @Override
            public Student getById(String id) {
                return null;
            }
        };
    }

    @Bean
    public SessionFactory sessionFactory(DataSource ds, @Qualifier("hibernateProperties") Properties hibernateProperties){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(ds);
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);
        return localSessionFactoryBean.getObject();
    }

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
