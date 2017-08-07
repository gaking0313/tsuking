package com.example.demo.service.followers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

@Service
public class FollowersServiceImpl implements FollowersService {

    @Override
    public List<TwitterProfile> getFollowers(Twitter twitter) {

        // 1ページ(20アカウント)分のリスト
        CursoredList<TwitterProfile> followersPage;

        // 全アカウントのリスト
        List<TwitterProfile> followers = new ArrayList<TwitterProfile>();

        long cursor = -1;
        // 次ページがなくなるまでAPIを叩く
        do {
            followersPage =
                twitter.friendOperations().getFollowersInCursor(cursor);
            followers.addAll(followersPage);
            cursor = followersPage.getNextCursor();
        } while (followersPage.hasNext());

        return followers;
    }

}
