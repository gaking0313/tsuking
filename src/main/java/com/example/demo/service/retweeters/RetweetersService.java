package com.example.demo.service.retweeters;

import java.util.List;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;

public interface RetweetersService {
	
    /**
     * リツイートされたユーザ一覧取得処理
     * 
     * @param twitter 
     * @return ユーザ一覧のリスト
     */
    public List<TwitterProfile> getRetweeters(Twitter twitter);

}
