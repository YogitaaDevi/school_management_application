import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.i2i.sma")
@EntityScan(basePackages = "com.i2i.sma.models")
@EnableJpaRepositories(basePackages = "com.i2i.sma.repository")
public class MainController {
	public static void main(String[] args) {
		SpringApplication.run(MainController.class, args);
	}
}