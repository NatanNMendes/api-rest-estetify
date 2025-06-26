package com.estetify.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Ativa o perfil de teste
public class BackendApplicationTests {

	@Test
	void contextLoads() {
		// Teste vazio para verificar o contexto
	}
}