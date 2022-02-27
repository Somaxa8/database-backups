package com.somacode.backups.controller

import com.somacode.backups.entity.Company
import com.somacode.backups.service.CompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@RestController
class CompanyController {
    @Autowired lateinit var companyService: CompanyService

    @PostMapping("/api/companies")
    fun create(
            @RequestParam @Size(min = 2) name: String,
            @RequestParam @Email email: String
    ): ResponseEntity<Company> {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.create(name, email))
    }

    @PatchMapping("/api/companies/{id}")
    fun update(
            @PathVariable id: Long,
            @RequestParam(required = false) @Size(min = 2) name: String?,
            @RequestParam(required = false) @Email email: String?
    ): ResponseEntity<Company> {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.update(id, name, email))
    }

    @GetMapping("/api/companies")
    fun findAll(): ResponseEntity<List<Company>> {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.findAll())
    }

    @GetMapping("/api/companies/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Company> {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.findById(id))
    }

    @DeleteMapping("/api/companies/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        companyService.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
    }

}