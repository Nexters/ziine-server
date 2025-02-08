package com.ziine.api.artwork.domain.repository;

import com.ziine.api.artwork.domain.entity.ArtworkEntity;
import com.ziine.api.artwork.domain.entity.ArtworkStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtworkRepository extends JpaRepository<ArtworkEntity, Long> {

    List<ArtworkEntity> findByStatusOrderByCreatedAtAsc(ArtworkStatus status);

    List<ArtworkEntity> findByStatusOrderByCreatedAtDesc(ArtworkStatus status);

    List<ArtworkEntity> findAllByOrderByCreatedAtAsc();
}
