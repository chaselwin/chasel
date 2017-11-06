package com.chasel.family.dataSource;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
  public class DataSourceConfiguration {
 
     @Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "dbcp.datasource")
     public DataSource dataSource()
     {
         return DataSourceBuilder.create().type(com.mchange.v2.c3p0.ComboPooledDataSource.class).build();
     }
 }