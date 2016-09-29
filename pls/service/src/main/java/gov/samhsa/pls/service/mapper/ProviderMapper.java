package gov.samhsa.pls.service.mapper;

import gov.samhsa.pls.domain.Provider;
import gov.samhsa.pls.service.dto.ProviderDto;

import java.util.List;

/**
 * Provides mapping methods to map between {@code gov.samhsa.pls.ProviderDto}
 * and {@code gov.samhsa.pls.domain.Provider} and
 * {@code java.util.List}s thereof.
 */
public interface ProviderMapper {
    ProviderDto map(Provider provider);

    Provider map(ProviderDto dto);

    List<ProviderDto> mapToProviderDtoList(List<Provider> providers);

    List<Provider> mapToProviderList(List<ProviderDto> providers);
}
