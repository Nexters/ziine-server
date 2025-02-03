package com.ziine.artist.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "exhibition")
public class Exhibition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibition_id")
    private Long id;

    @Column(nullable = false, length = 80)
    private String title;

    @Column(nullable = false)
    private LocalDate exhibitionDate;

    @Column(nullable = false)
    private Long artistId;

    public Exhibition(
            final String title,
            final LocalDate exhibitionDate,
            final Long artistId
    ) {
        this.title = title;
        this.exhibitionDate = exhibitionDate;
        this.artistId = artistId;
    }
}
