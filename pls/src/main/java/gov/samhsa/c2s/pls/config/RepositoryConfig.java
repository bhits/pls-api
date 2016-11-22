package gov.samhsa.c2s.pls.config;

import gov.samhsa.c2s.pls.domain.provider.Provider;
import gov.samhsa.c2s.pls.domain.reference.EntityType;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Provider.class);
        config.exposeIdsFor(EntityType.class);
    }
}
