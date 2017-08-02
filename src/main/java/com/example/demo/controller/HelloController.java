package com.example.demo.controller;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class HelloController {

    private Twitter twitter;

    private ConnectionRepository connectionRepository;

    @Inject
    public HelloController(Twitter twitter,
            ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String helloTwitter(Model model) {
        // クラス名#メソッド名
        String target = this.getClass().toString() + "#" + "helloTwitter";
        log.info(target + " START");

        if (this.connectionRepository
            .findPrimaryConnection(Twitter.class) == null) {

            log.info(target + "OUTPUT \"redirect:/connect/twitter\"");

            return "redirect:/connect/twitter";
        }

        model.addAttribute(this.twitter.userOperations().getUserProfile());
        CursoredList<TwitterProfile> followers =
            this.twitter.friendOperations().getFollowers();
        model.addAttribute("followers", followers);

        log.info(target + "OUTPUT \"hello\"");
        log.info(target + " END");

        return "hello";

    }

}