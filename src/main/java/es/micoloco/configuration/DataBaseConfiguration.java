package es.micoloco.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Clase de configuracion de la base de datos, Hibernate, bd...<br/>
 * En esta clase se instancian los objetos de base de datos (datasource,
 * entitymanager, transactionmanager, etc).
 * 
 * @author Javi
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:configuraciones.properties" })
public class DataBaseConfiguration {

	/**
	 * Inyecta la variable utilizada para acceder al fichero de configuracion
	 * definido con la anotacion \@PropertySource.
	 */
	@Autowired
	private Environment environment;

	/**
	 * Definicion del DataSource
	 * 
	 * @return
	 */
	@Bean
	public BasicDataSource dataSourcePool() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("bd.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("bd.url"));
		dataSource.setUsername(environment.getRequiredProperty("bd.username"));
		dataSource.setPassword(environment.getRequiredProperty("bd.password"));
		dataSource.setConnectionProperties(environment.getRequiredProperty("bd.contextionProperties"));
		dataSource.setInitialSize(Integer.valueOf(environment.getRequiredProperty("bd.initialSize")).intValue());
		dataSource.setMaxTotal(Integer.valueOf(environment.getRequiredProperty("bd.maxTotal")).intValue());
		dataSource.setMinIdle(Integer.valueOf(environment.getRequiredProperty("bd.minIdle")).intValue());
		dataSource.setPoolPreparedStatements(Boolean.valueOf(environment.getRequiredProperty("bd.poolPreparedStatements")).booleanValue());
		dataSource.setDefaultAutoCommit(Boolean.valueOf(environment.getRequiredProperty("bd.defaultAutoCommit")).booleanValue());
		dataSource.setTestOnBorrow(Boolean.valueOf(environment.getRequiredProperty("bd.testOnBorrow")).booleanValue());
		dataSource.setTestOnReturn(Boolean.valueOf(environment.getRequiredProperty("bd.testOnReturn")).booleanValue());
		dataSource.setTestWhileIdle(Boolean.valueOf(environment.getRequiredProperty("bd.testWhileIdle")).booleanValue());
		dataSource.setTimeBetweenEvictionRunsMillis(Long.valueOf(environment.getRequiredProperty("bd.timeBetweenEvictionRunsMillis")).longValue());
		dataSource.setValidationQuery(environment.getRequiredProperty("bd.validationQuery"));
		return dataSource;
	}

	/**
	 * Definicion del JPA EntityManagerFactory
	 * 
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSourcePool());

		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(Boolean.valueOf(environment.getRequiredProperty("hibernate.showSql")).booleanValue());
		jpaVendorAdapter.setDatabasePlatform(environment.getRequiredProperty("hibernate.databasePlatform"));
		entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);

		// Inicializacion base de datos de pruebas
		// TODO ELIMINAR en modo Oracle
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create");
		properties.setProperty("hibernate.hbm2ddl.import_files", "basedatos.sql");
		entityManagerFactory.setJpaProperties(properties);
		// ELIMINAR HASTA AQUI

		return entityManagerFactory;
	}

	/**
	 * Definicion del JPA TransactionManager
	 * 
	 * @param emf
	 * @return
	 */
	@Bean
	public PlatformTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}
