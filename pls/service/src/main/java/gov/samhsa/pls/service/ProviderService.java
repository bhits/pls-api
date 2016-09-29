package gov.samhsa.pls.service;

import gov.samhsa.pls.service.dto.ProviderDto;

import java.util.Map;

/**
 * Contract for service to retrieve HIPPA providers
 */
public interface ProviderService {
    String SEARCH_STRING = "%";

    /**
     * Get a single {@link gov.samhsa.pls.service.dto.ProviderDto based on National Provider Id
     *
     * @param npi unique National Provider Id
     * @return {@link gov.samhsa.pls.service.dto.ProviderDto
     */
    ProviderDto getProvider(String npi);

    /**
     * Retrieve {@link gov.samhsa.pls.service.dto.ProviderDto using the
     * parameters described below. Since the postal code may be wildcarded, use
     * this method to do searches not pertaining to locaion by setting
     *
     * @param genderCode               implementation should understand <b>m/f</b> and
     *                                 <b>male/female</b> and may be wildcard to '%'. May or may not
     *                                 be case sensitive.
     * @param postalCode               wildcard searchable if contains less than 9 characters
     *                                 implementation should add '%' at the end for search i.e.
     *                                 '12345%' May be wildcard to '%'
     * @param taxonomy                 wildcard searchable speciality so implementaion should add '%'
     *                                 at the end for search i.e. 'general%' May be wildcard to '%'
     * @param phone                    phone number no partial wildcard but may be fully wildcard to
     *                                 '%'
     * @param lastName                 no partial wildcard but may be fully wildcard to '%'
     * @param firstName                no partial wildcard but may be fully wildcard to '%'
     * @param entityType               wildcard searchable speciality so implementation should add
     *                                 '%' at the end for search i.e. 'general%' May be wildcard to
     *                                 '%'
     * @param providerOrganizationName wildcard searchable speciality so implementation should add
     *                                 '%' at the end for search i.e. 'general%' May be wildcard to
     *                                 '%'
     * @param pageNumber               page number to display
     * @return {@code java.util.List} of
     * {@link gov.samhsa.pls.service.dto.ProviderDto
     * @code postalCode} to "%".
     */
    Map<String, Object> getByGenderCodeAndPostalCodeAndSpecialityAndTelephoneNumberAndLastNameAndFirstNameAndEntityTypeAndProviderOrganizationName(
            String genderCode, String postalCode, String taxonomy,
            String phone, String lastName, String firstName, String entityType,
            String providerOrganizationName, String pageNumber);

    Map<String, Object> getByGenderCodeAndPostalCodeAndSpecialityAndTelephoneNumberAndLastNameOrProviderOrganizationNameAndFirstNameAndEntityType(
            String genderCode, String postalCode, String taxonomy,
            String phone, String lastNameOrFacilityName, String firstName,
            String entityType, String pageNumber);

    /**
     * Retrieve {@link gov.samhsa.pls.service.dto.ProviderDto the
     * parameters described below. In this case, {@code usStateAbbreviation} and
     * {@code city} together represent a compound key when seaarching so "%" for
     * {@code city} and {@code usStateAbbreviation} are not guarenteed to return
     * accurate results.
     *
     * @param genderCode               implementation should understand <b>m/f</b> and
     *                                 <b>male/female</b> and may be wildcard to '%'. May or may not
     *                                 be case sensitive.
     * @param usStateAbbreviation      no wildcard
     * @param city                     city name no wildcard
     * @param taxonomy                 wildcard searchable speciality so implementaion should add '%'
     *                                 at the end for search i.e. 'general%' May be wildcard to '%'
     * @param phone                    phone number no partial wildcard but may be fully wildcard to
     *                                 '%'
     * @param lastName                 no partial wildcard but may be fully wildcard to '%'
     * @param firstName                no partial wildcard but may be fully wildcard to '%'
     * @param entityType               wildcard searchable speciality so implementation should add
     *                                 '%' at the end for search i.e. 'general%' May be wildcard to
     *                                 '%'
     * @param providerOrganizationName wildcard searchable speciality so implementation should add
     *                                 '%' at the end for search i.e. 'general%' May be wildcard to
     *                                 '%
     * @param pageNumber               page number to display
     * @return {@code java.util.List} of
     * {@link gov.samhsa.pls.service.dto.ProviderDto
     */
    Map<String, Object> getByGenderCodeAndUSStateAbbreviationAndCityAndSpecialityAndTelephoneNumberAndLastNameAndFirstNameAndEntityTypeAndProviderOrganizationName(
            String genderCode, String usStateAbbreviation, String city,
            String taxonomy, String phone, String lastName, String firstName,
            String entityType, String providerOrganizationName,
            String pageNumber);

    Map<String, Object> getByGenderCodeAndUSStateAbbreviationAndCityAndSpecialityAndTelephoneNumberAndLastNameOrProviderOrganizationNameAndFirstNameAndEntityType(
            String genderCode, String usStateAbbreviation, String city,
            String taxonomy, String phone, String lastNameOrFacilityName,
            String firstName, String entityType, String pageNumber);
}
