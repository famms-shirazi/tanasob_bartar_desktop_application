package com.tanasobbartar.individual.client.ledger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientLedgerRepository extends JpaRepository<ClientLedger, Long> {

    @Query("""
            SELECT cl FROM ClientLedger cl
            JOIN cl.enrollment e
            WHERE e.client.id = :clientId
            ORDER BY cl.createdAt DESC
            LIMIT 1""")
    Optional<ClientLedger> findEntryByClient(long clientId);

}
