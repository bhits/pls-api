package gov.samhsa.c2s.pls.domain.reference;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class EntityTypeCode{
/*
entityTypeCode =1 Individual
entityTypeCode =2 Organizational
*/
    @Id
    @NotNull
    private String code;

    private String displayName;

}

