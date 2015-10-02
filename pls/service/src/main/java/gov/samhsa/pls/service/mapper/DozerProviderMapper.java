package gov.samhsa.pls.service.mapper;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.samhsa.pls.domain.Provider;
import gov.samhsa.pls.service.dto.ProviderDto;

/**
 * Dozer implementation of {@code ProviderMapper}.

 * @see ProviderMapper
 */
@SuppressWarnings("unchecked")
@Component
public class DozerProviderMapper implements ProviderMapper {

	@Autowired private Mapper mapper;

	public DozerProviderMapper() {}

	@Override
	public ProviderDto map(Provider provider) {
		return mapper.map(provider, ProviderDto.class);
	}

	@Override
	public Provider map(ProviderDto provider) {
		return mapper.map(provider, Provider.class);
	}

	@Override
	public List<ProviderDto> mapToProviderDtoList(List<Provider> providers) {
		return mapper.map(providers, List.class);
	}

	@Override
	public List<Provider> mapToProviderList(List<ProviderDto> providers) {
		return mapper.map(providers, List.class);
	}

}
