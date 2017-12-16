package Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author ( name= "Hardok" )
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@EnableWebMvc

// spring boot application
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
