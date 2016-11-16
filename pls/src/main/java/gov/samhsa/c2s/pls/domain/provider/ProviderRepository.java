package gov.samhsa.c2s.pls.domain.provider;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, String> {

    Page<Provider> findByNpiLike(@Param("npi") String npi,Pageable pageable);

    Provider findByNpi(@Param("npi") String npi);

    @Query("SELECT DISTINCT p FROM Provider p WHERE "
            + " ((?1 = null) OR (p.businessPracticeLocationAddressStateName LIKE ?1))"
            + " AND ((?2 = null) OR (p.businessPracticeLocationAddressCityName LIKE ?2))"
            + " AND ((?3 = null) OR (p.businessPracticeLocationAddressPostalCode LIKE ?3))"
            + " AND ((?4 = null) OR (p.firstName LIKE ?4 ))"
            + " AND ((?5 = null) OR (p.lastName LIKE ?5))"
            + " AND ((?6 = null) OR (p.genderCode LIKE ?6))"
            + " AND ((?7 = null) OR (p.organizationName LIKE ?7))"
            + " AND ((?8 = null) OR (p.businessPracticeLocationAddressTelephoneNumber LIKE ?8))")
    Page<Provider> query(
            @Param("state")String businessPracticeAddressStateName,
            @Param("city")String businessPracticeAddressCityName,
            @Param("zipcode")String businessPracticeAddressPostalCode,
            @Param("firstname")String firstName,
            @Param("lastname")String lastName,
            @Param("gendercode")String genderCode,
            @Param("orgname")String organizationName,
            @Param("phone")String businessPracticeAddressTelephoneNumber,
            Pageable pageable);


}