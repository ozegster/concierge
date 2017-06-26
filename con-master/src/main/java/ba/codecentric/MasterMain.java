package ba.codecentric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MasterMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MasterMain.class, args);
    }
}
