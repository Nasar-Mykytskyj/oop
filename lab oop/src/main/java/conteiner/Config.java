package conteiner;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration

@ComponentScan(basePackages = {"entity","controller","repository", "game"})

public class Config {

}
