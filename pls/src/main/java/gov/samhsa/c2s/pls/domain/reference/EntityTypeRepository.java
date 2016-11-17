package gov.samhsa.c2s.pls.domain.reference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface EntityTypeRepository extends JpaRepository<EntityType, String> {

}