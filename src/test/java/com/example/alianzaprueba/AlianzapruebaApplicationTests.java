package com.example.alianzaprueba;

import com.example.Exception.ClientException;
import com.example.entities.ClientEntity;
import com.example.repository.IClientRepository;
import com.example.service.IClientService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@Log4j2
class AlianzapruebaApplicationTests {

	@MockBean
	public IClientRepository iClientRepository;

	@Autowired
	public IClientService su;

	@Test
	void contextLoads() throws ClientException {
		when(iClientRepository.findBySharedKey("abc")).thenReturn(newSharedKey());
		try {
			log.info("Resultado: " + su.findBySharedKey("abc"));
			assert equals("El cliente con Shared Key existe en nuestra base de datos");
		} catch (ClientException e) {
			throw new RuntimeException(e);
		}
	}

	private Optional<ClientEntity> newSharedKey() {
		 Optional<ClientEntity> c = Optional.of(new ClientEntity());
		 c.get().setSharedKey("abc");
		 return c;
	}


}
