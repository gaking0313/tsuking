package com.example.demo.service.oauth;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

/**
 * Twitterアプリの認可サービスクラス
 *
 * @author kohei_ot
 *
 */
@Service
public class OAuthServiceImpl implements OAuthService {

    /**
     * Twitterアプリの認可判定
     *
     * @param connectionRepository
     * @return OAuth認可済みならtrue未認可ならfalse
     */
    @Override
    public Boolean hasConnection(ConnectionRepository connectionRepository) {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return true;
        } else {
            return false;
        }
    }

}
