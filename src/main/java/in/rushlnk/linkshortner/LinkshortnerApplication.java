package in.rushlnk.linkshortner;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition
@SpringBootApplication
public class LinkshortnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkshortnerApplication.class, args);
	}

}

