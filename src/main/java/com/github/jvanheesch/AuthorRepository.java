package com.github.jvanheesch;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "authors")
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Iterable<Author> findAllByName(String name);
}
