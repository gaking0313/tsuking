package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.csv.Csv;
import com.example.demo.service.oauth.OAuthService;
import com.example.demo.service.retweeters.RetweetersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@RestController
@RequestMapping("/retweeters")
public class RetweeterController {

    @Autowired
    private Twitter twitter;

    @Autowired
    private ConnectionRepository connectionRepository;

    /** リツイートユーザー一覧取得サービス */
    @Autowired
    private RetweetersService retweetersService;

    /** Twitterアプリの認可サービス */
    @Autowired
    private OAuthService oAuthService;

    @GetMapping(value = "*.csv", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
        + "; charset=UTF-8; Content-Disposition: attachment")
    @ResponseBody
    public String getRetweeters(Model model) throws JsonProcessingException {

        // 認可がまだならOAuth
        if (this.oAuthService.hasConnection(this.connectionRepository)) {

            return "redirect:/connect/twitter";
        }

        // TODO どっかに移す
        //       model.addAttribute(this.twitter.userOperations().getUserProfile());

        // リツイートユーザー一覧取得処理
        List<TwitterProfile> dmUsers =
            this.retweetersService.getRetweeters(this.twitter);

        //        model.addAttribute("dmUsers", dmUsers);

        //        return "dm";
        long no = 1;
        List<Csv> demos = new ArrayList<Csv>();
        for (TwitterProfile user : dmUsers) {
            demos.add(new Csv(no, user.getScreenName()));
            no++;
        }

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(Csv.class).withHeader();
        return mapper.writer(schema).writeValueAsString(demos);

    }

}
