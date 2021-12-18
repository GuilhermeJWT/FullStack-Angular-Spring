package br.com.systemsgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.systemsgs.config.SystemsGsPropertyProfile;

@SpringBootApplication
@EnableConfigurationProperties(SystemsGsPropertyProfile.class)
public class FullstackAngularSpringAlgaworksApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullstackAngularSpringAlgaworksApplication.class, args);
	}


}
