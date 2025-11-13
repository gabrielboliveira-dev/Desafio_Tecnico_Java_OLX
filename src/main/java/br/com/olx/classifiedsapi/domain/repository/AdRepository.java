package br.com.olx.classifiedsapi.domain.repository;

import br.com.olx.classifiedsapi.domain.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdRepository extends JpaRepository<Ad, UUID> {

    List<Ad> findAllByUserId(UUID userId);
}