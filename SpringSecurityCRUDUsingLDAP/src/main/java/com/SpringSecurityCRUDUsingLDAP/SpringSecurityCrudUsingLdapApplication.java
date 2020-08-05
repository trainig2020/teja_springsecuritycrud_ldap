package com.SpringSecurityCRUDUsingLDAP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class SpringSecurityCrudUsingLdapApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityCrudUsingLdapApplication.class, args);
	}

}
