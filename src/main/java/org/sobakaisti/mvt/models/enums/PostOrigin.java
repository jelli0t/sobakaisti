package org.sobakaisti.mvt.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Media;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Publication;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum PostOrigin {

    ARTICLE(Article.class),
    PUBLICATION(Publication.class),
    MEDIA(Media.class);

    private final Class<? extends Post> type;

    public String getTypeName() {
        return this.type.getName();
    }

    public static PostOrigin getByEntityType(Class<? extends Post> entityType) {

        return Arrays.asList(values()).stream()
                .filter(origin -> origin.getType().isAssignableFrom(entityType))
                .findFirst()
                .orElse(null);
    }

    public static PostOrigin getByTypeName(String entityName) {

        return Arrays.asList(values()).stream()
                .filter(origin -> origin.getType().getName().equals(entityName))
                .findFirst()
                .orElse(null);
    }
}
