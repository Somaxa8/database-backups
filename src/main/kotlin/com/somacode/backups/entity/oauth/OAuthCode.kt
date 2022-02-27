package com.somacode.backups.entity.oauth

import javax.persistence.*

@Entity
@Table(name = "oauth_code")
class OAuthCode(
        @Id @GeneratedValue
        var id: Long? = null,
        var code: String? = null,
        @Lob
        var authentication: String? = null
)