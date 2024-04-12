//package com.laudoStratus.demo;
//
//import com.laudoStratus.demo.models.UserBank;
//import com.laudoStratus.demo.repository.UserRepository;
//import com.laudoStratus.demo.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class BankWebSiteApplicationTests {
//
//
//	@Autowired
//	private MockMvc mockMvc;
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private UserRepository userRepository;
//
//	@Test
//	@DisplayName("Teste de criação de usuario")
//	public void testCreateAccount() throws Exception {
//
//		//simula dados inserido
//		UserBank userBank = new UserBank();
//		userBank.setName("John Doe");
//		userBank.setCpfUser("123456789");
//		userBank.setTelephone("139955221");
//
//		//converte dados para json
//		ObjectMapper objectMapper = new ObjectMapper();
//		String userJson = objectMapper.writeValueAsString(userBank);
//
//		//simula envio de solicitação POST para a uri para o endpoint
//		mockMvc.perform(post("/account/create")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(userJson))
//				.andExpect(status().isCreated());
//
//		//verifica se o nome John Doe foi inserido corretamente
//		UserBank savedUser = userRepository.findByName("John Doe");
//
//		//verifica se os dados estão inserido corretamente e se não retorna nulo
//		assertNotNull(savedUser);
//		assertEquals("John Doe", savedUser.getName());
//		assertEquals("123456789", savedUser.getCpfUser());
//		assertEquals("139955221", savedUser.getTelephone());
//	}
//}
//
////	@Test
////	@DisplayName("Teste de consulta de usuario")
////	public void testConsultAccount() {
////		//simula criação de usuario
////		UserRepository userRepository = mock(UserRepository.class);
////		UserBank user = new UserBank();
////		user.setIdUser(1L);
////		user.setName("Denilson");
////		user.setCpf("123212321232");
////		user.setTelephone("13225154857");
////
////		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
////
////		UserService userService = new UserService(userRepository);
////
////		Optional<UserBank> resultUser = userService.ConsultAccountUser(1L);
////        //verifica se o resultado não e nulo
////		assertNotNull(resultUser);
////		assertEquals("Denilson", resultUser.getName());
////		assertEquals("13225154857", resultUser.getTelephone());
////	}
////}