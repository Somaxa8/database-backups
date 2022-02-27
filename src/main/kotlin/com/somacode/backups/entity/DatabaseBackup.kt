package com.somacode.backups.entity

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDate
import javax.persistence.*

@Entity
class DatabaseBackup (
        @Id @GeneratedValue
        var id: Long? = null,
        @OneToOne
        var backup: Document? = null,
        @CreatedDate
        @Column(updatable = false)
        var createdAt: LocalDate? = null,
        var status: Status? = null,
        @ManyToOne
        var company: Company? = null
) {
    enum class Status {
        REMOVED, GOOD, WARNING
    }
}