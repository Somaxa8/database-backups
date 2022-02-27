package com.somacode.backups.repository

import com.somacode.backups.entity.DatabaseBackup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DatabaseBackupRepository: JpaRepository<DatabaseBackup, Long> {

    fun findTopByCompany_Id(companyId: Long): DatabaseBackup?

}