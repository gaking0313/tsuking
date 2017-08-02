package com.example.demo.service.oauth;

import org.springframework.social.connect.ConnectionRepository;

/**
 * Twitterアプリの認可サービスクラス
 *
 * @author kohei_ot
 *
 */
public interface OAuthService {

    /**
     * Twitterアプリの認可判定
     *
     * @param connectionRepository
     * @return OAuth認可済みならtrue未認可ならfalse
     */
    public Boolean hasConnection(ConnectionRepository connectionRepository);

}
