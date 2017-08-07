package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.service.followers.FollowersService;
import com.example.demo.service.oauth.OAuthService;

@Controller
@RequestMapping("/")
public class FollowersController {

    @Autowired
    private Twitter twitter;

    @Autowired
    private ConnectionRepository connectionRepository;

    /** フォロワー一覧取得サービス */
    @Autowired
    private FollowersService followersService;

    /** Twitterアプリの認可サービス */
    @Autowired
    private OAuthService oAuthService;

    // TODO もうちょっとコントローラ分けたい
    @RequestMapping(method = RequestMethod.GET)
    public String getFollowers(Model model) {

        // 認可がまだならOAuth
        if (this.oAuthService.hasConnection(this.connectionRepository)) {
            return "redirect:/connect/twitter";
        }

        // TODO どっかに移す
        model.addAttribute(this.twitter.userOperations().getUserProfile());

        // フォロワー一覧取得処理
        List<TwitterProfile> followers =
            this.followersService.getFollowers(this.twitter);

        model.addAttribute("followers", followers);

        return "followers";

    }

}