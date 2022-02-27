package com.somacode.backups.repository

import com.somacode.backups.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun findByEmail(email: String): User

    fun existsByEmail(email: String): Boolean

    fun existsByEmailAndActiveTrue(email: String): Boolean

    fun countByIdIn(ids: List<Long>): Int

}