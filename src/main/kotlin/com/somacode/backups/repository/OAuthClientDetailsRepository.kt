package com.somacode.backups.repository

import com.somacode.backups.entity.oauth.OAuthClientDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OAuthClientDetailsRepository: JpaRepository<OAuthClientDetails, String> {

}