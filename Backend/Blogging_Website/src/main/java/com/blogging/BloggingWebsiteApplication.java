package com.blogging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
	info = @Info(
		title = "Blogging WebSite REST APIs",
		description = "REST APIs for performing CRUD operation for a blogging website. The website is divide into Post, Commment and Category to use the API http://{baseUrl}/{partOfWebsite}",
		contact = @Contact(
			name = "Mirza Tabish Hasan",
			email = "mirzatabish8065@gmail.com",
			url = "https://linkedin.com/in/hmirza8065"
		),
		license = @License(
			name = "Mirza Tabish Hasan",
			url = "https://linkedin.com/in/hmirza8065"
		)
	)
)
@SpringBootApplication
public class BloggingWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingWebsiteApplication.class, args);
	}

}
