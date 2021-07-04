package com.stefanini.selecao.tests;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.stefanini.selecao.peoplews.Application;
import com.stefanini.selecao.peoplews.controller.dto.PessoaDto;
import com.stefanini.selecao.peoplews.model.Pessoa;


@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PessoaControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;
	
	@Value("${app.basic.user}")
	private String user;

	@Value("${app.basic.secretKey}")
	private String secretKey;

	private String getRootUrl() {
		return "http://localhost:" + port+"/api/v1";
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testListarPessoas() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/pessoas",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testDetalharPessoa() {
		Pessoa pessoa = restTemplate.getForObject(getRootUrl() + "/pessoas/1", Pessoa.class);
		System.out.println(pessoa.getNome());
		assertNotNull(pessoa);
	}

	@Test
	public void testCriarPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Tomás José Samuel Costa");
		pessoa.setCpf("430.771.017-77");
		pessoa.setDataNascimento(LocalDate.of(1980, 2, 1));	
		pessoa.setEmail("testnovapessoa@gmail.com");
		pessoa.setNacionalidade("brasileira");
		pessoa.setNaturalidade("Curitiba/PR");		

		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(user, secretKey);
		HttpEntity<Pessoa> entity = new HttpEntity<>(pessoa, headers);
		
		ResponseEntity<PessoaDto> postResponse = restTemplate.postForEntity(getRootUrl() + "/pessoas", entity,  PessoaDto.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testAtualizarPessoa() {
		int id = 1;
		Pessoa pessoa = restTemplate.getForObject(getRootUrl() + "/pessoas/" + id, Pessoa.class);
		pessoa.setEmail("testnovapessoaalt@gmail.com");
		pessoa.setDataNascimento(LocalDate.of(1985, 2, 1));

		restTemplate.put(getRootUrl() + "/pessoas/" + id, pessoa);

		PessoaDto updatedPessoa = restTemplate.getForObject(getRootUrl() + "/pessoas/" + id, PessoaDto.class);
		assertNotNull(updatedPessoa);
	}

	@Test
	public void testRemoverPessoa() {
		int id = 2;
		Pessoa pessoa = restTemplate.getForObject(getRootUrl() + "/pessoas/" + id, Pessoa.class);
		assertNotNull(pessoa);

		restTemplate.delete(getRootUrl() + "/pessoas/" + id);

		try {
			pessoa = restTemplate.getForObject(getRootUrl() + "/pessoas/" + id, Pessoa.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
