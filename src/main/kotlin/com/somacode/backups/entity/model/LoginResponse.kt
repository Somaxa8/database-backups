package com.somacode.backups.entity.model

import com.somacode.backups.entity.Authority
import com.somacode.backups.entity.User
import org.springframework.security.oauth2.common.OAuth2AccessToken

data class LoginResponse(
        var oAuth2AccessToken: OAuth2AccessToken,
        var user: User? = null,
        var authorities: Set<Authority>
)