package za.co.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
// import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
// import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
// import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import za.co.cput.domain.User;
import za.co.cput.domain.Contact;
import za.co.cput.repository.ContactRepository;



@Service
public class OAuth2UserService {
	private final ContactRepository contactRepository;

	@Autowired
	public OAuth2UserService(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	public User getUserByEmail(String email) {
		return contactRepository.findByEmail(email)
			.map(Contact::getUser)
			.orElse(null);
	}
}