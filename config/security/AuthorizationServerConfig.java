package com.integrys.backend.config.security;

import com.integrys.backend.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    // Enable in memory Oauth2
//    static final String GRANT_TYPE_PASSWORD = "password";
//    static final String AUTHORIZATION_CODE = "authorization_code";
//    static final String CLIENT_CREDENTIALS = "client_credentials";
//    static final String REFRESH_TOKEN = "refresh_token";
//    static final String IMPLICIT = "implicit";
//    static final String SCOPE_READ = "read";
//    static final String SCOPE_WRITE = "write";
//    static final String TRUST = "trust";
//    static final int ACCESS_TOKEN_VALIDITY_SECONDS = 12 * 60 * 60;
//    static final int REFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;

    @Value("${swagger.client.id}")
    private String swaggerClientId;
    @Value("${swagger.client.secret}")
    private String swaggerClientSecret;

    @Value("${mobile.client.id}")
    private String mobileClientId;
    @Value("${mobile.client.secret}")
    private String mobileClientSecret;

    @Value("${web.client.id}")
    private String webClientId;
    @Value("${web.client.secret}")
    private String webClientSecret;

    @Value("${token.signingKey}")
    private String signingKey;


    private final AuthenticationManager authenticationManager;

    private final DataSource dataSource;

    public AuthorizationServerConfig(AuthenticationManager authenticationManager, DataSource dataSource) {
        this.authenticationManager = authenticationManager;
        this.dataSource = dataSource;
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain chain = new TokenEnhancerChain();
        chain.setTokenEnhancers(List.of(tokenEnhancer(), tokenConverter()));
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenEnhancer(chain)
                .reuseRefreshTokens(false)
        // Rename defaults endpoint's names
                .pathMapping("/oauth/token", "/login");
    }


    @Bean
    public JwtAccessTokenConverter tokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setAccessTokenConverter(authExtractor());
        converter.setSigningKey(signingKey);
        return converter;
    }


    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenConverter());
    }

    private TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                Map<String, Object> additionalInfo = new HashMap<>();
                additionalInfo.put("user", authentication.getPrincipal());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            }
            return accessToken;
        };
    }

    @Bean
    public DefaultAccessTokenConverter authExtractor() {
        return new DefaultAccessTokenConverter() {
            @Override
            public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
                OAuth2Authentication authentication = super.extractAuthentication(claims);
                authentication.setDetails(claims);
                return authentication;
            }
        };
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(this.dataSource);
    }

    // Enable in memory Oauth2
//    @Override
//    public void configure(final ClientDetailsServiceConfigurer configurer) throws Exception {
//        configurer.inMemory().withClient(swaggerClientId).secret("{noop}" + swaggerClientSecret)
//                .authorizedGrantTypes(CLIENT_CREDENTIALS).scopes(SCOPE_READ, SCOPE_WRITE, TRUST).and()
//                .withClient(mobileClientId).secret("{noop}" + mobileClientSecret)
//                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN)
//                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST).and().withClient(webClientId)
//                .secret("{noop}" + webClientSecret)
//                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN)
//                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
//                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
//                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
//    }
}
