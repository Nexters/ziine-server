package com.ziine.admin.domain.repository;

import com.ziine.admin.domain.entity.ArtworkStatusHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtworkStatusHistoryRepository extends JpaRepository<ArtworkStatusHistoryEntity, Long> {

}
