package org.sobakaisti.mappers;

import org.mapstruct.Mapper;
import org.sobakaisti.dto.api.AuthorDTO;
import org.sobakaisti.mappers.config.BaseMapperConfig;
import org.sobakaisti.mvt.models.Author;

@Mapper(config = BaseMapperConfig.class)
public interface AuthorMapper {

    AuthorDTO map(Author entity);
}
