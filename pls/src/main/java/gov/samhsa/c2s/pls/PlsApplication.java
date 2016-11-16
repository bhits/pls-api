package gov.samhsa.c2s.pls;

import gov.samhsa.c2s.pls.domain.PlsDomainBasePackageMarkerInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan(basePackageClasses = PlsDomainBasePackageMarkerInterface.class)
@EnableDiscoveryClient
public class PlsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlsApplication.class, args);
    }
}