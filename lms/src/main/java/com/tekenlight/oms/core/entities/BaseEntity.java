package com.tekenlight.oms.core.entities;

import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class BaseEntity implements Serializable {

    @Id
    @Column(name = "id", unique = true, length = 60)
    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(nullable = false, name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "del_flg", nullable = false, length = 1)
    private String delFlg;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(nullable = false, name = "updated_by")
    private String updatedBy;

    @Version
    @Column(nullable = false, name = "db_lock")
    private LocalDateTime dbLock;

    @Transient
    @JsonIgnore
    private String timeZone;

    public BaseEntity() {
        this.setTimeZone("UTC");
    }

    @PrePersist
    public void fillModel() {
        if (id == null || id.isBlank()) {
            setId(UUID.randomUUID().toString());
        }

        LocalDateTime now = LocalDateTime.now();

        if (dbLock == null) setDbLock(now);
        if (createdAt == null) setCreatedAt(now);

        setUpdatedAt(now);

        if (createdBy == null || createdBy.isBlank()) setCreatedBy("System");
        if (updatedBy == null || updatedBy.isBlank()) setUpdatedBy("System");

        setDelFlg(delFlg == null || delFlg.isBlank() ? "N" : delFlg);
    }

    @PreUpdate
    public void fillModelBeforeUpdate() {
        setUpdatedAt(LocalDateTime.now());
        setDelFlg(delFlg == null || delFlg.isBlank() ? "N" : delFlg);
        if (updatedBy == null || updatedBy.isBlank()) setUpdatedBy("System");
    }
}