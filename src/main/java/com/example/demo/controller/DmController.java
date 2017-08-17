package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.common.util.CsvUtil;
import com.example.demo.service.dm.DMService;
import com.example.demo.service.oauth.OAuthService;
import com.fasterxml.jackson.core.JsonProcessingException;

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

    /** CSV関連の共通機能提供クラス */
    @Autowired
    private CsvUtil csvUtil;

    @GetMapping(value = "*.csv", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
        + "; charset=UTF-8; Content-Disposition: attachment")
    @ResponseBody
    public String getDMUsers(Model model) throws JsonProcessingException {

        // 認可がまだならOAuth
        if (!this.oAuthService.hasConnection(this.connectionRepository)) {

            return "redirect:/connect/twitter";
        }

        // TODO どっかに移す
        //       model.addAttribute(this.twitter.userOperations().getUserProfile());

        // ダイレクトメッセージユーザー一覧取得処理
        List<TwitterProfile> dmUsers = this.dmService.getDMUsers(this.twitter);

        //        model.addAttribute("dmUsers", dmUsers);

        //        return "dm";

        return this.csvUtil.convertTwitterProfToCsv(dmUsers);

    }

}
