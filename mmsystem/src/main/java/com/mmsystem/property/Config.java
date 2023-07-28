package com.mmsystem.property;

import java.util.Properties;  
import javax.sql.DataSource;  
import org.springframework.beans.factory.annotation.Value;  
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;  
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.ComponentScan;  
import org.springframework.context.annotation.ComponentScans;  
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;  
import org.springframework.orm.hibernate5.HibernateTransactionManager;  
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;  
import org.springframework.transaction.annotation.EnableTransactionManagement;  
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.zaxxer.hikari.HikariDataSource;  
  


@Configuration  
@EnableTransactionManagement  
@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class})  
@ComponentScans(value = { 
			
          @ComponentScan("com.mmsystem.property.model"),  
          @ComponentScan("com.mmsystem.property.controller"),  
          @ComponentScan("com.mmsystem.property.repo"),  
          @ComponentScan("com.mmsystem.property.service")})  
public class Config {  
		
     	  
  
        @Bean//(name="firstEntityManagerFactory")  
        public LocalSessionFactoryBean secondSessionFactory() {  
            LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();  
            sessionFactory.setDataSource(dataSource());  
            sessionFactory.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);  
            Properties hibernateProperties = new Properties();  
            hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);  
            hibernateProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);  
            hibernateProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);  
            sessionFactory.setHibernateProperties(hibernateProperties);  
            return sessionFactory;  
        }  
        
        @Bean(name="entityManagerFactory") 
        @Primary
        public LocalSessionFactoryBean sessionFactory() {  
            LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();  
            sessionFactory.setDataSource(dataSource());  
            sessionFactory.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);  
            Properties hibernateProperties = new Properties();  
            hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);  
            hibernateProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);  
            hibernateProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);  
            sessionFactory.setHibernateProperties(hibernateProperties);  
            return sessionFactory;  
        }
 
        @Bean
        @ConfigurationProperties("db")
        public HikariDataSource dataSource() {
            return DataSourceBuilder.create().type(HikariDataSource.class).build();
        }
  
        @Bean  
        public HibernateTransactionManager transactionManager() {  
            HibernateTransactionManager txManager = new HibernateTransactionManager();  
            txManager.setSessionFactory(sessionFactory().getObject());  
            return txManager;  
        }  
 
         
        
	        @Value("${db.driver}")  
	        private String DB_DRIVER;  
	  
	        @Value("${db.password}")  
	        private String DB_PASSWORD;  
	  
	        @Value("${db.jdbc-url}")  
	        private String DB_URL;  
	  
	        @Value("${db.username}")  
	        private String DB_USERNAME;  
	  
	        @Value("${hibernate.dialect}")  
	        private String HIBERNATE_DIALECT;  
	  
	        @Value("${hibernate.show_sql}")  
	        private String HIBERNATE_SHOW_SQL;  
	  
	        @Value("${hibernate.hbm2ddl.auto}")  
	        private String HIBERNATE_HBM2DDL_AUTO;  
	  
	        @Value("${entitymanager.packagesToScan}")  
	        private String ENTITYMANAGER_PACKAGES_TO_SCAN;
         
         
    }  