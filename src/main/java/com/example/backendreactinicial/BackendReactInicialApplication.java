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

        if (categoriaRepository.findById(1L).isEmpty()) {
            Categoria c1 = new Categoria();
            c1.setDenominacion("Viento");
            Categoria c2 = new Categoria();
            c2.setDenominacion("Percusión");
            Categoria c3 = new Categoria();
            c3.setDenominacion("Cuerda");
            Categoria c4 = new Categoria();
            c4.setDenominacion("Teclado");
            Categoria c5 = new Categoria();
            c5.setDenominacion("Electronico");

            categoriaRepository.save(c1);
            categoriaRepository.save(c2);
            categoriaRepository.save(c3);
            categoriaRepository.save(c4);
            categoriaRepository.save(c5);
        }
    }
}