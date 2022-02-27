package com.somacode.backups.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = [Company_.EMAIL])])
class Company(
        @Id @GeneratedValue
        var id: Long? = null,
        var name: String? = null,
        var email: String? = null,
        @JsonIgnore
        @OneToMany(mappedBy = "company")
        var backups: MutableList<DatabaseBackup> = mutableListOf()
)