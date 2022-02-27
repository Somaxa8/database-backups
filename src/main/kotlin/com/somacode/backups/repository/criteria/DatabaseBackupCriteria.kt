package com.somacode.backups.repository.criteria

import com.somacode.backups.entity.DatabaseBackup
import com.somacode.backups.service.tool.CriteriaTool
import org.springframework.data.domain.Page
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.Predicate

@Repository
class DatabaseBackupCriteria {
    @PersistenceContext lateinit var entityManager: EntityManager

    fun findFilterPageable(page: Int, size: Int, companyId: Long?, status: DatabaseBackup.Status?): Page<DatabaseBackup> {
        val cb = entityManager.criteriaBuilder
        val q = cb.createQuery(DatabaseBackup::class.java)
        val databaseBackup = q.from(DatabaseBackup::class.java)

        val order = databaseBackup.get(DatabaseBackup_.id)

        val predicates: MutableList<Predicate> = mutableListOf()

        companyId?.let {
            predicates.add(cb.equal(databaseBackup.join(DatabaseBackup_.company).get(Company_.id), it))
        }

        status?.let {
            predicates.add(cb.equal(databaseBackup.get(DatabaseBackup_.status), it))
        }

        q.select(databaseBackup).where(
                *predicates.toTypedArray(),
        ).orderBy(cb.desc(order))

        return CriteriaTool.page(entityManager, q, page, size)
    }
}