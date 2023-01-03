package com.optum.portal.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.annotation.PostConstruct;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableJpaRepositories(bootstrapMode = BootstrapMode.DEFAULT)
public class OptumPotalApplication {

	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.driver}")
	private String driver;
	@Value("${spring.datasource.username}")
	private String user;
	@Value("${spring.datasource.password}")
	private String password;

	public static void main(String[] args) {
		SpringApplication.run(OptumPotalApplication.class, args);
	}

	@PostConstruct
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);

		// schema init
		Resource schema = new ClassPathResource("schema.sql");
		Resource initData = new ClassPathResource("data.sql");
		DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initData);
		DatabasePopulatorUtils.execute(databasePopulator, dataSource);

		return dataSource;
	}
}
