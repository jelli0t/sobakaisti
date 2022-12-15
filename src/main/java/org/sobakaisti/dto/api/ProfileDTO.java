package org.sobakaisti.dto.api;

import org.sobakaisti.mvt.models.SocialNetwork;
import java.io.Serial;
import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;

public class ProfileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = new SecureRandom().nextLong();

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String profession;
    private String location;
    private String website;
    private String shortBio;
    private String avatarName;
    private LocalDate registered;
    private List<SocialNetwork> socialNetwork;
}
