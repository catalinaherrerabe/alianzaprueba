package com.example.alianzaprueba;

import com.example.Exception.ClientException;
import com.example.entities.ClientEntity;
import com.example.repository.IClientRepository;
import com.example.service.IClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class AlianzapruebaApplicationTests {

	@MockBean
	public IClientRepository iClientRepository;

	@MockBean
	public IClientService su;

	@Test
	void contextLoads() throws ClientException {
		when(iClientRepository.findBySharedKey("abc")).thenReturn(newSharedKey());
		try {
			System.out.println("Resultado: " + su.findBySharedKey("abc"));
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
