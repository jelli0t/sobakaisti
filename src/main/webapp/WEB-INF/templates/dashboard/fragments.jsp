<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="sr">
<head>
	<meta charset="utf-8" />
</head>


<th:block th:fragment="authorRegistrationFragment">
	<!-- 
*** New Author box ***
-->
<div class="inputs-container author-registration border-3-rad y-1em-padding x-1em-padding shadow-def">
	<form th:action="@{/sbk-admin/sobakaisti/add}" th:object="${author}" method="post" onkeypress="return event.keyCode != 13;">
		<div class="input-holder half-input-holder" style="margin-right:1%;">
			<label for="firstName" th:text="#{form.label.firstname}">First Name</label>
			<input type="text" th:field="*{firstName}" class="def-input" th:placeholder="#{form.label.firstname}"
				th:classappend="${#fields.hasErrors('firstName')} ? 'non-valid-field'">
			<div class="validation-error" th:errors="*{firstName}"></div> 
		</div>  
		<div class="input-holder half-input-holder"> 
			<label for="lastName" th:text="#{form.label.lastname}">Last Name</label>
			<input type="text" class="def-input" th:field="*{lastName}" placeholder="Prezime autora"
				th:classappend="${#fields.hasErrors('lastName')} ? 'non-valid-field'">
			<div class="validation-error" th:errors="*{lastName}"></div>
		</div>
		<div class="input-holder full-input-holder">
			<label for="profession" th:text="#{form.label.proffesion}">Profession</label>
			<input type="text" th:field="*{profession}" class="def-input" th:placeholder="#{form.label.proffesion}"
				th:classappend="${#fields.hasErrors('profession')} ? 'non-valid-field'">
			<div class="validation-error" th:errors="*{profession}"></div>   
		</div>
		<div class="input-holder full-input-holder">
			<label for="birthplace" th:text="#{form.label.birthplace}">Birthblace</label>
			<input type="text" th:field="*{birthplace}" class="def-input" th:placeholder="#{form.label.birthplace}">
		</div>
		<div class="input-holder full-input-holder">
			<label for="dob" th:text="#{form.label.dob}">Date of birh</label>
			<input type="text" th:field="*{dob}" class="def-input" placeholder="01.01.20xx"
				th:classappend="${#fields.hasErrors('dob')} ? 'non-valid-field'">
			<div class="validation-error" th:errors="*{dob}"></div>   
		</div>
		<div class="input-holder full-input-holder">
			<label for="email" th:text="#{form.label.email}">Date of birh</label>
			<input type="email" th:field="*{email}" class="def-input" th:placeholder="#{form.label.email}"
				th:classappend="${#fields.hasErrors('email')} ? 'non-valid-field'">
			<div class="validation-error" th:errors="*{email}"></div>   
		</div>
		<div class="input-holder full-input-holder">
			<label for="website" th:text="#{form.label.website}">website</label>
			<input type="text" th:field="*{website}" class="def-input" th:placeholder="#{form.label.website}">
		</div>
		<div class="input-holder full-input-holder">
			<label for="shortBio" th:text="#{form.label.shortbio}">biografija</label>
			<textarea th:field="*{shortBio}" rows="4" class="def-input" placeholder="Kratka biografija autora"></textarea>
		</div>
		<div class="input-holder full-input-holder aright">    
			<button type="submit" class="bttn bttn-primary" th:text="#{button.label.save}">Dodaj</button>  
		</div>
	</form>
</div>

</th:block>