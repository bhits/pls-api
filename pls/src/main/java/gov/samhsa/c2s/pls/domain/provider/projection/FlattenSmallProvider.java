package gov.samhsa.c2s.pls.domain.provider.projection;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.samhsa.c2s.pls.domain.provider.Provider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="FlattenSmallProvider", types={Provider.class})
public interface FlattenSmallProvider {
    String getNpi();

    @Value("#{target.entityType.code}")
    String getEntityTypeCode();

    @Value("#{target.entityType.displayName}")
    String getEntityTypeDisplayName();

    String getLastName();
    String getFirstName();
    String getMiddleName();
    String getOrganizationName();

    @JsonProperty("firstLinePracticeLocationAddress")
    String getFirstLineBusinessPracticeLocationAddress();

    @JsonProperty("secondLinePracticeLocationAddress")
    String getSecondLineBusinessPracticeLocationAddress();

    @JsonProperty("practiceLocationAddressCityName")
    String getBusinessPracticeLocationAddressCityName();

    @JsonProperty("practiceLocationAddressStateName")
    String getBusinessPracticeLocationAddressStateName();

    @JsonProperty("practiceLocationAddressPostalCode")
    String getBusinessPracticeLocationAddressPostalCode();

    @JsonProperty("practiceLocationAddressCountryCode")
    String getBusinessPracticeLocationAddressCountryCode();

    @JsonProperty("practiceLocationAddressTelephoneNumber")
    String getBusinessPracticeLocationAddressTelephoneNumber();

    String getBusinessPracticeLocationAddressFaxNumber();
    String getEnumerationDate();
    String getLastUpdateDate();

}

