package com.fetrova.accounts.mapper

import com.fetrova.accounts.dto.AccountDTO
import com.fetrova.accounts.entity.Accounts

fun Accounts.mapToAccountsDto(accountsDto: AccountDTO): AccountDTO {
    accountsDto.accountNumber = this.accountNumber
    accountsDto.accountType = this.accountType
    accountsDto.branchAddress = this.branchAddress
    return accountsDto
}

fun AccountDTO.mapToAccounts(accounts: Accounts): Accounts {
    accounts.accountNumber = this.accountNumber
    accounts.accountType = this.accountType
    accounts.branchAddress = this.branchAddress
    return accounts
}
