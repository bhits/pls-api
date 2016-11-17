package gov.samhsa.c2s.pls.domain.provider;

import gov.samhsa.c2s.pls.domain.reference.EntityType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@Entity
public class Provider {
    @Id
    @NotNull
    private String npi;

    @ManyToOne(cascade = CascadeType.ALL)
    private EntityType entityType;

    @Size(min = 2, max = 30)
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ]+[-]?[a-zA-ZÀ-ÿ']*[a-zA-ZÀ-ÿ]$")
    private String lastName;

    @Size(min = 2, max = 30)
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ]+[-]?[a-zA-ZÀ-ÿ']*[a-zA-ZÀ-ÿ]$")
    private String firstName;


    @Size(min = 2, max = 30)
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ]+[-]?[a-zA-ZÀ-ÿ']*[a-zA-ZÀ-ÿ]$")
    private String middleName;

    @Size(min = 2, max = 255)
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ]+[-]?[a-zA-ZÀ-ÿ']*[a-zA-ZÀ-ÿ]$")
    private String organizationName;

    private String genderCode;

    private String firstLineBusinessMailingAddress;

    private String secondLineBusinessMailingAddress;

    private String businessMailingAddressCityName;

    private String businessMailingAddressStateName;

    private String businessMailingAddressPostalCode;

    private String businessMailingAddressCountryCode;

    private String businessMailingAddressTelephoneNumber;

    private String businessMailingAddressFaxNumber;

    private String firstLineBusinessPracticeLocationAddress;

    private String secondLineBusinessPracticeLocationAddress;

    private String businessPracticeLocationAddressCityName;

    private String businessPracticeLocationAddressStateName;

    private String businessPracticeLocationAddressPostalCode;

    private String businessPracticeLocationAddressCountryCode;

    private String businessPracticeLocationAddressTelephoneNumber;

    private String businessPracticeLocationAddressFaxNumber;

    @Past
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date enumerationDate;

    @Past
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date lastUpdateDate;


}