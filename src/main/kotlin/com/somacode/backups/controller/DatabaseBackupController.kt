package com.somacode.backups.controller

import com.somacode.backups.entity.DatabaseBackup
import com.somacode.backups.service.DatabaseBackupService
import com.somacode.backups.service.tool.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.Email

@RestController
class DatabaseBackupController {

    @Autowired lateinit var databaseBackupService: DatabaseBackupService

    @PostMapping("/api/database-backups")
    fun create(
            @RequestParam backupFile: MultipartFile,
            @RequestParam @Email companyEmail: String
    ): ResponseEntity<DatabaseBackup> {
        return ResponseEntity.status(HttpStatus.CREATED).body(databaseBackupService.create(backupFile, companyEmail))
    }

    @GetMapping("/api/database-backups")
    fun getDatabaseBackups(
            @RequestParam page: Int,
            @RequestParam size: Int,
            @RequestParam(required = false) companyId: Long?,
            @RequestParam(required = false) status: DatabaseBackup.Status?,
    ): ResponseEntity<List<DatabaseBackup>> {
        val result = databaseBackupService.findFilterPageable(page, size, companyId, status)
        return ResponseEntity.status(HttpStatus.OK)
                .header(Constants.X_TOTAL_COUNT_HEADER, result.totalElements.toString())
                .body(result.content)
    }

    @GetMapping("/api/database-backups/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<DatabaseBackup> {
        return ResponseEntity.status(HttpStatus.OK).body(databaseBackupService.findById(id))
    }

    @DeleteMapping("/api/database-backups/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        databaseBackupService.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
    }

}