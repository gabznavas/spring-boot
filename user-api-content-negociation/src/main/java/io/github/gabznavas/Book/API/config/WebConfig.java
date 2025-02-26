package io.github.gabznavas.Book.API.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuração para a negociação de conteúdo, permitindo a escolha do formato da resposta (JSON ou XML)
 * a partir de parâmetros de consulta (query parameters) na URL.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configura a negociação de conteúdo, definindo o uso de parâmetros de consulta
     * para especificar o formato de mídia desejado (JSON ou XML).
     *
     * @param configurer Objeto usado para configurar a negociação de conteúdo.
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                // Exemplo: não será utilizado o parâmetro ?mediaType=xml na URL.
                .favorParameter(false)
                // Ativa o uso do cabeçalho "Accept" da requisição para determinar o tipo de mídia.
                // Por exemplo, se o cabeçalho 'Accept: application/xml' for enviado, a resposta será no formato XML.
                .ignoreAcceptHeader(false)

                // Permite que a negociação de conteúdo não dependa somente das extensões de arquivo registradas.
                .useRegisteredExtensionsOnly(false)

                // Define JSON como o formato de resposta padrão, caso o cabeçalho "Accept" não seja enviado ou seja inválido.
                .defaultContentType(MediaType.APPLICATION_JSON)

                // Registra os tipos de mídia suportados pela API, mapeando a extensão 'json' para JSON
                // e 'xml' para XML, além do cabeçalho "Accept".
                .mediaType("json", MediaType.APPLICATION_JSON)  // Suporta JSON.
                .mediaType("xml", MediaType.APPLICATION_XML)  // Suporta XML.
                .mediaType("x-yaml", MediaType.APPLICATION_YAML);  // Suporta YAML.


//        configurer
//                Exemplo: http://localhost:8080/api/person/v1/2?mediaType=xml
//                  .favorParameter(true)
//                // Define o nome do parâmetro que será usado na URL para especificar o tipo de mídia.
//                .parameterName("mediaType")
//
//                // Ignora o cabeçalho "Accept" da requisição, ou seja, a negociação de conteúdo não será
//                // baseada no valor do cabeçalho Accept (ex: Accept: application/json).
//                .ignoreAcceptHeader(true)
//
//                // Permite que a negociação de conteúdo não dependa apenas das extensões de arquivo
//                // (por exemplo, .json ou .xml) registradas.
//                .useRegisteredExtensionsOnly(false)
//
//                // Define o formato de resposta padrão como JSON, caso nenhum parâmetro seja especificado.
//                .defaultContentType(MediaType.APPLICATION_JSON)
//
//                // Registra os tipos de mídia suportados pela API.
//                // Quando o parâmetro mediaType=xml é passado, a resposta será no formato XML.
//                .mediaType("json", MediaType.APPLICATION_JSON)  // Suporta JSON.
//                .mediaType("xml", MediaType.APPLICATION_XML);  // Suporta XML.

    }
}
