package com.library.entidades.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "attendant")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attendant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @OneToOne(mappedBy = "attendant", cascade = CascadeType.ALL)
    private Student student;
}
