package ba.edu.ibu.feeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@SpringBootApplication
public class FeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeeServiceApplication.class, args);
	}

	@Bean
	public Function<String, Double> calculateFee() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (dueDate) -> {
			try {
				return TimeUnit.DAYS.convert((new Date()).getTime() - sdf.parse(dueDate).getTime(), TimeUnit.MILLISECONDS) * 0.5;
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		};
	}

}
