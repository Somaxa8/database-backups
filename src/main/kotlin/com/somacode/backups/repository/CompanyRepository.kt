package com.somacode.backups.repository

import com.somacode.backups.entity.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository: JpaRepository<Company, Long> {

    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): Company

}