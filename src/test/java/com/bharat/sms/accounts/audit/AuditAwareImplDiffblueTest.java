package com.bharat.sms.accounts.audit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuditAwareImpl.class})
@ExtendWith(SpringExtension.class)
class AuditAwareImplDiffblueTest {
    @Autowired
    private AuditAwareImpl auditAwareImpl;

    /**
     * Method under test: {@link AuditAwareImpl#getCurrentAuditor()}
     */
    @Test
    void testGetCurrentAuditor() {
        // Arrange and Act
        Optional<String> actualCurrentAuditor = auditAwareImpl.getCurrentAuditor();

        // Assert
        assertEquals("ACCOUNTS_MS", actualCurrentAuditor.get());
        assertTrue(actualCurrentAuditor.isPresent());
    }
}
