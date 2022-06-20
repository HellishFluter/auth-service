package ru.weber.auth.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.weber.auth.service.model.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findClientByClientId(String clientId);
}
