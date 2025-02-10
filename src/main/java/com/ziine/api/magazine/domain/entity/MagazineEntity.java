package com.ziine.api.magazine.domain.entity;

import com.ziine.global.jpa.domain.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "magazine")
public class MagazineEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "magazine_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 200)
    private String summary;

    @Column(nullable = false, length = 255)
    private String thumbnailImageUrl;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "magazineEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<KeywordEntity> keywordEntities = new ArrayList<>();

    public MagazineEntity(
        final String title,
        final String summary,
        final String thumbnailImageUrl,
        final String content
    ) {
        this.title = title;
        this.summary = summary;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.content = content;
    }

    public void addKeywords(final List<KeywordEntity> keywordEntities) {
        keywordEntities.forEach(this::addKeyword);
    }

    public void addKeyword(final KeywordEntity keywordEntity) {
        this.keywordEntities.add(keywordEntity);
    }
}
