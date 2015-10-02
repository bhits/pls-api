package gov.samhsa.pls.service.mapper;

import java.util.List;

import gov.samhsa.pls.domain.Provider;
import gov.samhsa.pls.service.dto.ProviderDto;

/**
 * Provides mapping methods to map between {@code gov.samhsa.pls.ProviderDto}
 * and {@code gov.samhsa.pls.domain.Provider} and
 * {@code java.util.List}s thereof.
 *
 */
public interface ProviderMapper {
	public ProviderDto map(Provider provider);
	public Provider map(ProviderDto dto);
	public List<ProviderDto> mapToProviderDtoList(List<Provider> providers);
	public List<Provider> mapToProviderList(List<ProviderDto> providers);
}
