package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.service.oauth.OAuthService;

@Controller
@RequestMapping("/")
public class OAuthController {

    @Autowired
    private ConnectionRepository connectionRepository;

    /** Twitterアプリの認可サービス */
    @Autowired
    private OAuthService oAuthService;

    @RequestMapping(method = RequestMethod.GET)
    public String oAuth() {

        // 認可がまだならOAuth
        if (!this.oAuthService.hasConnection(this.connectionRepository)) {

            return "redirect:/connect/twitter";
        }

        return "connect/twitterConnected";

    }
}
