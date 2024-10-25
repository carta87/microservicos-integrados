package com.microservice.auth.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permissions")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String name;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof PermissionEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
