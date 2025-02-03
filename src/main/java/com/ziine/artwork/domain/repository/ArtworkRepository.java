package com.ziine.artwork.domain.repository;

import com.ziine.artwork.domain.entity.ArtworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtworkRepository extends JpaRepository<ArtworkEntity, Long> {

}
