package com.test.boot.web.config;

import javax.sql.DataSource;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = "com.test.boot", annotationClass = Mapper.class)
public class MyBatisConfig {

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
		factoryBean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/**/*.xml"));
		return factoryBean.getObject();
	}

}
