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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/dm")
public class DmController {

    @Autowired
    private Twitter twitter;

    @Autowired
    private ConnectionRepository connectionRepository;

    /** ダイレクトメッセージユーザー一覧取得サービス */
    @Autowired
    private DMService dmService;

    /** Twitterアプリの認可サービス */
    @Autowired
    private OAuthService oAuthService;

    @RequestMapping(method = RequestMethod.GET)
    public String getDMUsers(Model model) {

        // クラス名#メソッド名
        String target = this.getClass().toString() + "#" + "getDmUsers";

        log.info(target + " START");

        // 認可がまだならOAuth
        if (this.oAuthService.hasConnection(this.connectionRepository)) {

            log.info(target + " OUTPUT \"redirect:/connect/twitter\"");

            return "redirect:/connect/twitter";
        }

        // TODO どっかに移す
        model.addAttribute(this.twitter.userOperations().getUserProfile());

        // ダイレクトメッセージユーザー一覧取得処理
        List<TwitterProfile> dmUsers = this.dmService.getDMUsers(this.twitter);

        model.addAttribute("dmUsers", dmUsers);

        log.info(target + " OUTPUT \"dm\"");
        log.info(target + " END");

        return "dm";
    }

}
