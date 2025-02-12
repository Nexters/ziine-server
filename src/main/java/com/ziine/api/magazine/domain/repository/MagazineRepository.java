package com.ziine.api.magazine.domain.repository;

import com.ziine.api.magazine.domain.entity.MagazineEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazineRepository extends JpaRepository<MagazineEntity, Long> {

    List<MagazineEntity> findAllByOrderByCreatedAtDesc();
}
