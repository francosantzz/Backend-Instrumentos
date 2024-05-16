package com.example.backendreactinicial;

import com.example.backendreactinicial.entities.Categoria;
import com.example.backendreactinicial.repositories.CategoriaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendReactInicialApplication {

    @Autowired
    private CategoriaRepository categoriaRepository; // Asegúrate de tener un repositorio para las categorías

    public static void main(String[] args) {
        SpringApplication.run(BackendReactInicialApplication.class, args);
    }

    @PostConstruct
    public void init() {
        saveCategoriaIfNotExists("Viento");
        saveCategoriaIfNotExists("Percusión");
        saveCategoriaIfNotExists("Cuerda");
        saveCategoriaIfNotExists("Teclado");
        saveCategoriaIfNotExists("Electronico");
    }

    private void saveCategoriaIfNotExists(String denominacion) {
        if (categoriaRepository.findByDenominacion(denominacion).isEmpty()) {
            Categoria categoria = new Categoria();
            categoria.setDenominacion(denominacion);
            categoriaRepository.save(categoria);
        }
    }
}