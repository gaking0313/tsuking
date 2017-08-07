package com.example.demo.service.oauth;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

@Service
public class OAuthServiceImpl implements OAuthService {

    @Override
    public Boolean hasConnection(ConnectionRepository connectionRepository) {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return true;
        } else {
            return false;
        }
    }

}
