package com.fetrova.accounts.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "customer")
class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    var customerId: Long? = null,

    var name: String? = null,
    var email: String? = null,

    @Column(name = "mobile_number")
    var mobileNumber: String? = null
) : BaseEntity() {

    override fun toString(): String {
        return "Customer(customerId=$customerId, name=$name, email=$email, mobileNumber=$mobileNumber, " +
                "createdAt=$createdAt, createdBy=$createdBy, updatedAt=$updatedAt, updatedBy=$updatedBy)"
    }
}