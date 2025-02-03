package com.ziine.artist.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "education_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String tag;

    @Column(nullable = false)
    private Long artistId;

    public Education(
        final String tag,
        final Long artistId
    ) {
        this.tag = tag;
        this.artistId = artistId;
    }
}
