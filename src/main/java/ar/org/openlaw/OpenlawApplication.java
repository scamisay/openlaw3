package ar.org.openlaw;

import ar.org.openlaw.interceptor.LoggedInUserInterceptor;
import ar.org.openlaw.interceptor.MenuSelectedInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableScheduling
public class OpenlawApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenlawApplication.class, args);
	}

	@Bean
	public WebMvcConfigurerAdapter adapter() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(new LoggedInUserInterceptor())
						.excludePathPatterns("/login");

				registry.addInterceptor(new MenuSelectedInterceptor())
						.excludePathPatterns("/login");
			}
		};
	}


}
