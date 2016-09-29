package gov.samhsa.pls.web.config.di;

import gov.samhsa.pls.service.ServiceBasePackageMarker;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
@ComponentScan(basePackageClasses = {ServiceBasePackageMarker.class})
public class ServiceLayerConfig {

    @Bean
    public Mapper mapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        ArrayList<String> fileLists = new ArrayList<>();
        fileLists.add("dozer-bean-mappings.xml");
        dozerBeanMapper.setMappingFiles(fileLists);
        return dozerBeanMapper;
    }
}
