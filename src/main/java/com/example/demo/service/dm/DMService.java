package com.example.demo.service.dm;

import java.util.List;

import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;

/**
 * ダイレクトメッセージユーザー一覧取得処理のサービスクラス
 *
 * @author kohei_ot
 *
 */
public interface DMService {

    /**
     * ダイレクトメッセージユーザー一覧取得処理
     *
     * @param twitter
     * @return ダイレクトメッセージのやり取りがあるユーザーの一覧
     */
    public List<TwitterProfile> getDMUsers(Twitter twitter);

}
