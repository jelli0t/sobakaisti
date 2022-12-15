package org.sobakaisti.mvt.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@Entity
@Table(name = "profile")
public class AuthorProfile extends Profile {

	@OneToOne(targetEntity = Author.class)
	@JoinColumn(name = "author_id")
    @MapsId
	private Author author;
	
	public AuthorProfile(Author author) {
		super();
		this.author = author;
	}

	@Override
	public String getEmail() {
		return Optional.ofNullable(author)
				.map(Author::getEmail)
				.orElse(null);
	}
	
	public String getFullName() {
		return Stream.of(getFirstName(), getLastName())
				.filter(Predicate.not(String::isEmpty))
				.collect(Collectors.joining(" "));
	} 
}