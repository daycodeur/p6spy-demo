package com.daycodeur.infrastructure.database;

import com.daycodeur.infrastructure.dao.Customers;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackageClasses = {Customers.class},
    entityManagerFactoryRef = "customerEntityManager",
    transactionManagerRef = "customerTransactionManager")
public class DataSourceConfiguration {

  @Value("${hibernate.dialect}")
  private String dialect;

  @Bean
  public DataSource getDataSource() {

    try {
      Context initContext = new InitialContext();
      Context envContext = (Context) initContext.lookup("java:comp/env");
      return (DataSource) envContext.lookup("database");
    } catch (Exception e) {
      return null;
    }
  }

  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean customerEntityManager(final DataSource dataSource) {
    final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setPackagesToScan("com.daycodeur.model");
    em.setDataSource(dataSource);
    em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    em.setJpaProperties(additionalProperties());
    return em;
  }

  @Bean
  @Primary
  public JpaTransactionManager customerTransactionManager(@Qualifier("customerEntityManager") final EntityManagerFactory factory) {
    return new JpaTransactionManager(factory);
  }

  private Properties additionalProperties() {
    final Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty("hibernate.show_sql", "false");
    hibernateProperties.setProperty("hibernate.dialect", dialect);
    hibernateProperties.setProperty("hibernate.cache.use_query_cache", "false");
    hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "false");
    return hibernateProperties;
  }
}