package com.somacode.backups.repository

import com.somacode.backups.entity.Document
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentRepository: JpaRepository<Document, Long> {

}