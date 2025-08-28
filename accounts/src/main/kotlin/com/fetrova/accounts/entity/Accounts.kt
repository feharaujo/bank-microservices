package com.fetrova.accounts.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "accounts")
class Accounts(
    @Id
    @Column(name = "account_number")
    var accountNumber: Long? = null,

    @Column(name = "customer_id")
    var customerId: Long? = null,

    @Column(name = "account_type")
    var accountType: String? = null,

    @Column(name = "branch_address")
    var branchAddress: String? = null
) : BaseEntity() {
    override fun toString(): String {
        return "Accounts(customerId=$customerId, accountNumber=$accountNumber, accountType=$accountType, branchAddress=$branchAddress, " +
                "createdAt=$createdAt, createdBy=$createdBy, updatedAt=$updatedAt, updatedBy=$updatedBy)"
    }
}