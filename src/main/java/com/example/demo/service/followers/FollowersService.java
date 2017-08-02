package com.example.demo.service.followers;

import java.util.List;

import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;

/**
 * フォロワー取得処理のサービスクラス
 *
 * @author kohei_ot
 *
 */
public interface FollowersService {

    /**
     * フォロワー一覧取得処理
     *
     * @return フォロワー一覧のリスト
     */
    public List<TwitterProfile> getFollowers(Twitter twitter);

}
