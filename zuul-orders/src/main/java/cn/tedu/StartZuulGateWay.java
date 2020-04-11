package cn.tedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class StartZuulGateWay {
	public static void main(String[] args) {
		SpringApplication.run(StartZuulGateWay.class, args);
	}
}
