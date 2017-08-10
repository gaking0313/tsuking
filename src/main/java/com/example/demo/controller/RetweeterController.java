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

import com.example.demo.service.dm.DMService;
import com.example.demo.service.oauth.OAuthService;
import com.example.demo.service.retweeters.RetweetersService;

@Controller
@RequestMapping("/retweet")
public class RetweeterController {
	
	   @Autowired
	    private Twitter twitter;

	    @Autowired
	    private ConnectionRepository connectionRepository;

	    /** リツイートユーザー一覧取得サービス */
	    @Autowired
	    private RetweetersService retweeterService;

	    /** Twitterアプリの認可サービス */
	    @Autowired
	    private OAuthService oAuthService;

	    @RequestMapping(method = RequestMethod.GET)
	    public String getDMUsers(Model model) {

	        // 認可がまだならOAuth
	        if (this.oAuthService.hasConnection(this.connectionRepository)) {

	            return "redirect:/connect/twitter";
	        }

	        // TODO どっかに移す
	        model.addAttribute(this.twitter.userOperations().getUserProfile());

	        // ダイレクトメッセージユーザー一覧取得処理
	        List<TwitterProfile> retweeters = this.retweeterService.getRetweeters(this.twitter);

	        model.addAttribute("Retweeters", retweeters);

	        return "retweet";
	    }


}
