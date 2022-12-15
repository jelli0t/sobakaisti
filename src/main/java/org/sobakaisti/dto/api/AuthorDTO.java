package org.sobakaisti.dto.api;

import lombok.Builder;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDate;

@Data
@Builder
public class AuthorDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = new SecureRandom().nextLong();

    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private LocalDate dob;
    private String birthplace;
    private String email;
    private String website;
    private String shortBio;
    private String profession;
    private String avatarPath;
    private String slug;
    private ProfileDTO profile;
}
