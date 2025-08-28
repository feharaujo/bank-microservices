package com.fetrova.accounts.audit

import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.Optional

@Component("auditorAwareImpl")
class AuditAwareImpl : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of("ACCOUNTS_CS")
    }
}