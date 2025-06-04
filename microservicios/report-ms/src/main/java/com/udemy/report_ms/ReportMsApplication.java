package com.udemy.report_ms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;



@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//public class ReportMsApplication implements CommandLineRunner{
public class ReportMsApplication {
	
	//En ves de inyectar con final puso Autowired pero esto solo es recomendable en test
//	@Autowired
//	private EurekaClient eurekaClient;
	public static void main(String[] args) {
		SpringApplication.run(ReportMsApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		this.eurekaClient.getAllKnownRegions().forEach(System.out::println);
//		System.out.print("hola");
//		System.out.print(this.eurekaClient.getApplication("companies-crud"));
//		
//	}

}
