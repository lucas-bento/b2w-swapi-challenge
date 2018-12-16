package com.swapichallenge.planet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlanetControllerTest {

    private static final String ALDERAAN_REPRESENTATION = "{\"name\":\"Alderaan\",\"climate\":\"temperate\",\"terrain\":\"grasslands\"}";
    private static final String ARRAKIS_REPRESENTATION = "{\"name\":\"Arrakis\",\"climate\":\"dry as fuck\",\"terrain\":\"desert as fuck\"}";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void DADO_um_planeta_que_ainda_nao_existe_QUANDO_for_requisitada_a_sua_criacao_ENTAO_retorna_uma_representacao_JSON_do_planeta_E_status_201() throws Exception {

        RequestEntity<String> requestEntity = RequestEntity.post(URI.create("http://localhost:" + port + "/planets/"))
                                                           .contentType(MediaType.APPLICATION_JSON)
                                                           .body(ARRAKIS_REPRESENTATION);

        ResponseEntity<PlanetResponse> responseEntity = restTemplate.exchange(requestEntity, PlanetResponse.class);

        assertEquals(HttpStatus.CREATED,  responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, responseEntity.getHeaders().getContentType());

        PlanetResponse body = responseEntity.getBody();
        assertEquals("Arrakis", body.getName());
        assertEquals("dry as fuck",  body.getClimate());
        assertEquals("desert as fuck", body.getTerrain());
    }

    @Test
    public void DADO_um_planeta_que_ainda_nao_existe_QUANDO_for_requisitada_a_sua_criacao_com_nome_faltando_ENTAO_retorna_status_400() throws Exception{
        RequestEntity<String> requestEntity = RequestEntity.post(URI.create("http://localhost:" + port + "/planets/"))
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"climate\":\"temperate\",\"terrain\":\"grasslands\"}");

        ResponseEntity<Object> responseEntity = restTemplate.exchange(requestEntity, Object.class);

        assertEquals(HttpStatus.BAD_REQUEST,  responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, responseEntity.getHeaders().getContentType());
    }

    @Test
    public void DADO_um_planeta_que_ainda_nao_existe_QUANDO_for_requisitada_a_sua_criacao_com_terreno_faltando_ENTAO_retorna_status_400() throws Exception{
        RequestEntity<String> requestEntity = RequestEntity.post(URI.create("http://localhost:" + port + "/planets/"))
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"Alderaan\",\"climate\":\"temperate\"}");

        ResponseEntity<Object> responseEntity = restTemplate.exchange(requestEntity, Object.class);

        assertEquals(HttpStatus.BAD_REQUEST,  responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, responseEntity.getHeaders().getContentType());
    }

    @Test
    public void DADO_um_planeta_que_ainda_nao_existe_QUANDO_for_requisitada_a_sua_criacao_com_clima_faltando_ENTAO_retorna_status_400() throws Exception{
        RequestEntity<String> requestEntity = RequestEntity.post(URI.create("http://localhost:" + port + "/planets/"))
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"Alderaan\"terrain\":\"grasslands\"}");

        ResponseEntity<Object> responseEntity = restTemplate.exchange(requestEntity, Object.class);

        assertEquals(HttpStatus.BAD_REQUEST,  responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, responseEntity.getHeaders().getContentType());
    }

    @Test
    public void DADO_um_planeta_que_ja_existe_QUANDO_for_requisitada_a_sua_criacao_ENTAO_retorna_status_409() throws Exception{
        RequestEntity<String> requestEntity = RequestEntity.post(URI.create("http://localhost:" + port + "/planets/"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(ALDERAAN_REPRESENTATION);

        ResponseEntity<PlanetResponse> responseEntity = restTemplate.exchange(requestEntity, PlanetResponse.class);

        assertEquals(HttpStatus.CONFLICT,  responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, responseEntity.getHeaders().getContentType());

    }
}