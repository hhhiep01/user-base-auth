package com.base.auth.config;

import com.base.auth.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class CustomTokenGranter extends AbstractTokenGranter {

    private UserServiceImpl userService;
    private AuthenticationManager authenticationManager;

    protected CustomTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
    }

    public CustomTokenGranter(AuthenticationManager authenticationManager,AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType, UserServiceImpl userService) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        return super.getOAuth2Authentication(client, tokenRequest);
    }

    protected OAuth2AccessToken getAccessToken(ClientDetails client, TokenRequest tokenRequest) {
        String username = tokenRequest.getRequestParameters().get("username");
        String password = tokenRequest.getRequestParameters().get("password");
        String grantType = tokenRequest.getGrantType();
        log.info("Grant type received: " + grantType);
        try {
            if(SecurityConstant.GRANT_TYPE_CITIZEN_ID_CARD.equalsIgnoreCase(tokenRequest.getGrantType())){
                String citizenIdCard = tokenRequest.getRequestParameters().get("citizen_id_card");
                String issuanceDateStr = tokenRequest.getRequestParameters().get("issuance_date");

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date issuanceDate = dateFormat.parse(issuanceDateStr);
                return userService.getAccessTokenForCmnd(client, tokenRequest, citizenIdCard, issuanceDate, password, this.getTokenServices());
            }
            else if(SecurityConstant.GRANT_TYPE_USER.equalsIgnoreCase(tokenRequest.getGrantType())){
                String phone = tokenRequest.getRequestParameters().get("phone");
                return userService.getAccessTokenForUser(client, tokenRequest, password, phone, this.getTokenServices());
            }else{
                return userService.getAccessTokenForCustomType(client, tokenRequest, username, password, this.getTokenServices());
            }
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            throw new InvalidTokenException("account or tenant invalid");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
