package com.example.alianzaprueba;

import com.example.Exception.ClientException;
import com.example.dto.ClientDTO;
import com.example.entities.ClientEntity;
import com.example.repository.IClientRepository;
import com.example.service.IClientService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
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

		Optional<ClientEntity> c = Optional.of(new ClientEntity());
		c.get().setId(1);
		c.get().setSharedKey("abc");
		c.get().setBusinessId("hola");
		c.get().setPhone("3103103101");
		c.get().setEmail("c@prueba.co");

		when(iClientRepository.findBySharedKey("abc")).thenReturn(c);
		try {
			log.info("Resultado: " + su.findBySharedKey("abc"));
			ClientDTO client = su.findBySharedKey("abc");
			assertEquals(c, client.getSharedKey());

		} catch (ClientException e) {
			throw new RuntimeException(e);
		}
	}




}
