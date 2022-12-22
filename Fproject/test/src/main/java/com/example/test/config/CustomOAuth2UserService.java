package com.example.test.config;

import com.example.test.config.dto.OAuthAttributes;
import com.example.test.config.dto.SessionUser;
import com.example.test.entity.Member;
import com.example.test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Member mem = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionMember(mem));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(mem.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private Member saveOrUpdate(OAuthAttributes attributes) {
        Member member = memberRepository.findByEmail(attributes.getEmail());
        if(member != null) {
            Member mem = memberRepository.findByEmails(attributes.getEmail())
                    .map(entity -> entity.update(attributes.getName(), attributes.getNameAttributeKey(),
                            attributes.getEmail(), passwordEncoder))
                    .orElse(attributes.toEntity());

            return memberRepository.save(mem);
        }else {
            System.out.println(memberRepository.findByEmails(attributes.getEmail()));
            Member mem = new Member();
            mem = mem.createMember2(attributes.getName(), attributes.getNameAttributeKey(),
                            attributes.getEmail(), passwordEncoder,attributes.getEmail());
            System.out.println(mem.getEmail());


            return memberRepository.save(mem);
        }
    }



}
