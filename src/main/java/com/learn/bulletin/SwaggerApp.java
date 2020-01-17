package com.learn.bulletin;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerApp {
    private final String authUrl = "http://localhost:8080/auth/realms/test/protocol/openid-connect/auth";
    private final String tokenUrl = "http://localhost:8080/auth/realms/test/protocol/openid-connect/token";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo((apiInfo()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.learn.bulletin.controller"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(oauth()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring boot demo")
                .contact(new Contact("Swagger","http://localhost:8333", ""))
                .version("1.0")
                .description("description")
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("OAuth2", new AuthorizationScope[]{})))
                .forPaths(PathSelectors.ant("/api/user/basic"))
                .build();
    }

//
//    private List<SecurityReference> defaultAuth() {
//
//        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[2];
//        authorizationScopes[0] = new AuthorizationScope("read-all", "read all");
////        authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
//        authorizationScopes[1] = new AuthorizationScope("write-all", "write all");
//
//        return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
//    }

    @Bean
    SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId("demo-app")
                .clientSecret("1b6362fe-ec5c-45c1-a948-9680bd3d9152")
                .realm("test")
                .appName("demo-app")
                .scopeSeparator(" ")
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }

    @Bean
    List<GrantType> grantTypes() {
        List<GrantType> grantTypes = new ArrayList<>();
        TokenRequestEndpoint tokenRequestEndpoint = new TokenRequestEndpoint(
                authUrl,
                "demo-app",
                "1b6362fe-ec5c-45c1-a948-9680bd3d9152");
        TokenEndpoint tokenEndpoint = new TokenEndpoint(tokenUrl, "access_token");
        grantTypes.add(new AuthorizationCodeGrant(tokenRequestEndpoint, tokenEndpoint));
        GrantType passwordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(tokenUrl);
        grantTypes.add(passwordCredentialsGrant);
        return grantTypes;
    }

    @Bean
    SecurityScheme oauth() {
        return new OAuthBuilder()
                .name("OAuth2")
                .scopes(Arrays.asList(scopes()))
                .grantTypes(grantTypes())
                .build();
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("read", "Grants openid access"),
                new AuthorizationScope("write", "Grants openid access")
        };
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Allow anyone and anything access. Probably ok for Swagger spec
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/*",config);
        return new CorsFilter(source);
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .validatorUrl(null)
                .build();
    }

    @Bean
    KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver(){
        return new KeycloakSpringBootConfigResolver();
    }
}
