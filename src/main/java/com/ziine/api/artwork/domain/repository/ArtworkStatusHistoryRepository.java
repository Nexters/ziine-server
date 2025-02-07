package com.ziine.api.artwork.domain.repository;

import com.ziine.api.artwork.domain.entity.ArtworkStatusHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtworkStatusHistoryRepository extends JpaRepository<ArtworkStatusHistoryEntity, Long> {

}
