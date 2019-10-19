package edu.northeastern.cs5200;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Cs5200Fall2019HaoQuanweiJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(Cs5200Fall2019HaoQuanweiJdbcApplication.class, args);
		
		hw_jdbc_hao_quanwei impl = new hw_jdbc_hao_quanwei();
		
		impl.create();
		
		impl.update();
		
		impl.delete();
	}

}
