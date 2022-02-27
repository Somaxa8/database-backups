package com.somacode.backups.service

import com.somacode.backups.config.exception.NotFoundException
import com.somacode.backups.entity.DatabaseBackup
import com.somacode.backups.entity.Document
import com.somacode.backups.repository.DatabaseBackupRepository
import com.somacode.backups.repository.criteria.DatabaseBackupCriteria
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class DatabaseBackupService {

    @Autowired lateinit var databaseBackupRepository: DatabaseBackupRepository
    @Autowired lateinit var companyService: CompanyService
    @Autowired lateinit var documentService: DocumentService
    @Autowired lateinit var databaseBackupCriteria: DatabaseBackupCriteria

    fun create(backupFile: MultipartFile, companyEmail: String): DatabaseBackup {
        val company = companyService.findByEmail(companyEmail)

        val previous = databaseBackupRepository.findTopByCompany_Id(company.id!!)
        var status = DatabaseBackup.Status.GOOD
        previous?.let {
            if (backupFile.size < it.backup!!.size!!) status = DatabaseBackup.Status.WARNING
        }

        val databaseBackup = DatabaseBackup(
                company = company,
                backup = documentService.create(backupFile, Document.Type.DATABASE, DatabaseBackup::class.java.simpleName),
                status = status
        )

        return databaseBackupRepository.save(databaseBackup)
    }

    fun findById(id: Long): DatabaseBackup {
        if (!databaseBackupRepository.existsById(id)) {
            throw NotFoundException()
        }
        return databaseBackupRepository.getOne(id)
    }

    fun findFilterPageable(page: Int, size: Int, companyId: Long?, status: DatabaseBackup.Status?): Page<DatabaseBackup> {
        return databaseBackupCriteria.findFilterPageable(page, size, companyId, status)
    }

    fun delete(id: Long) {
        val databaseBackup = findById(id)
        databaseBackup.backup?.let {
            documentService.delete(it.id!!)
        }
        databaseBackup.status = DatabaseBackup.Status.REMOVED
        databaseBackupRepository.save(databaseBackup)
    }
}