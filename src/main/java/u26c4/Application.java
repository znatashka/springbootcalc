package u26c4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.ConfigurableEnvironment;
import u26c4.builders.ResultBuilder;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        log.info("\n-----------------------------------------------------\n\t" +
                        "Application `{}` is running! Access URLs: \n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n-----------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }
}
