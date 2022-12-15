package org.sobakaisti.api;

import lombok.RequiredArgsConstructor;
import org.sobakaisti.api.definition.AuthorsApi;
import org.sobakaisti.dto.api.AuthorDTO;
import org.sobakaisti.dto.api.ProfileDTO;
import org.sobakaisti.mappers.AuthorMapper;
import org.sobakaisti.mvt.service.AuthorService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthorsApiController implements AuthorsApi {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @Override
    public AuthorDTO findById(Long id) {

        var author = authorService.findFull(id.intValue());
        return authorMapper.map(author);
    }

    @Override
    public List<AuthorDTO> findAllAuthors() {
        var authors = authorService.findAll();

        return authors.stream()
                .map(authorMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public ProfileDTO findProfileByAuthorId(Long id) {
        return null;
    }
}
