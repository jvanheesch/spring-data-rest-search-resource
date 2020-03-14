package com.github.jvanheesch;

import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RepositoryRestController
public class MyRepositoryRestController {
    private final AuthorRepository authorRepository;

    public MyRepositoryRestController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping(path = "/authors/{id}", produces = "application/hal+json")
    public ResponseEntity<?> getAuthor(
            @PathVariable("id") Long id,
            PersistentEntityResourceAssembler assembler
    ) {
        // imagine we need some custom handler logic here
        return this.authorRepository.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
