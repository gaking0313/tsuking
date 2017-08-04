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
import com.example.demo.service.retweeters.RetweetersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private Twitter twitter;

    @Autowired
    private ConnectionRepository connectionRepository;

    /** フォロワー一覧取得サービス */
    @Autowired
    private FollowersService followersService;

    @Autowired
    private RetweetersService RetweetersService;
    
    /** Twitterアプリの認可サービス */
    @Autowired
    private OAuthService oAuthService;

    // TODO もうちょっとコントローラ分けたい
    @RequestMapping(method = RequestMethod.GET)
    public String helloTwitter(Model model) {

        // クラス名#メソッド名
        String target = this.getClass().toString() + "#" + "helloTwitter";

        log.info(target + " START");

        // 認可がまだならOAuth
        if (this.oAuthService.hasConnection(this.connectionRepository)) {

            log.info(target + " OUTPUT \"redirect:/connect/twitter\"");

            return "redirect:/connect/twitter";
        }

        // TODO どっかに移す
        model.addAttribute(this.twitter.userOperations().getUserProfile());

//        // フォロワー一覧取得処理
//        List<TwitterProfile> followers =
//            this.followersService.getFollowers(this.twitter);

      // フォロワー一覧取得処理
      List<TwitterProfile> retweeters =
          this.RetweetersService.getRetweeters(this.twitter);
        
        model.addAttribute("followers", retweeters);

        log.info(target + " OUTPUT \"hello\"");
        log.info(target + " END");

        return "hello";

    }

}