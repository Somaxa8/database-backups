package com.somacode.backups.service

import com.somacode.backups.config.exception.BadRequestException
import com.somacode.backups.config.exception.NotFoundException
import com.somacode.backups.entity.Company
import com.somacode.backups.repository.CompanyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CompanyService {

    @Autowired lateinit var companyRepository: CompanyRepository

    fun create(name: String, email: String): Company {
        if (name.isBlank()) throw BadRequestException()
        val company = Company(name = name, email = email)

        return companyRepository.save(company)
    }

    fun update(id: Long, name: String?, email: String?): Company {
        name?.let { if (it.isBlank()) throw BadRequestException() }
        val company = findById(id)

        name?.let { company.name = it }
        email?.let { company.email = it }

        return companyRepository.save(company)
    }

    fun findById(id: Long): Company {
        if (!companyRepository.existsById(id)) {
            throw NotFoundException()
        }
        return companyRepository.getOne(id)
    }

    fun findByEmail(email: String): Company {
        if (!companyRepository.existsByEmail(email)) {
            throw NotFoundException()
        }
        return companyRepository.findByEmail(email)
    }

    fun findAll(): List<Company> {
        return companyRepository.findAll()
    }

    fun delete(id: Long) {
        if (!companyRepository.existsById(id)) {
            throw NotFoundException()
        }
        companyRepository.deleteById(id)
    }

}