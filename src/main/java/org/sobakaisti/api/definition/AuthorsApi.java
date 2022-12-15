package org.sobakaisti.api.definition;

import org.sobakaisti.dto.api.AuthorDTO;
import org.sobakaisti.dto.api.ProfileDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/api")
public interface AuthorsApi {

    @GetMapping(
            value = "/v1/authors/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    AuthorDTO findById(@PathVariable Long id);

    @GetMapping(
            value = "/v1/authors",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    List<AuthorDTO> findAllAuthors();

    @GetMapping(
            value = "/v1/authors/{id}/profile",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    ProfileDTO findProfileByAuthorId(@PathVariable Long id);
}
