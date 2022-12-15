package org.sobakaisti.repository;

import org.sobakaisti.mvt.models.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Optional<Author> findBySlug(String slug);
}
