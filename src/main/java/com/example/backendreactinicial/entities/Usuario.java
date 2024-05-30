package com.example.backendreactinicial.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario extends Base{
    @Column(unique = true, nullable = false)
    private String nombreUsuario;

    @Column(nullable = false)
    private String clave;

    @Column(nullable = false)
    private String rol;
}
