package hufs.sweepyswipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SweepyswipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SweepyswipeApplication.class, args);
	}

}
