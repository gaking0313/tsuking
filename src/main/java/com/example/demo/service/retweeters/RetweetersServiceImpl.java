package com.example.demo.service.retweeters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;


@Service
public class RetweetersServiceImpl implements RetweetersService{

	@Override
	public List<TwitterProfile> getRetweeters(Twitter twitter) {
		
        // 全アカウントのリスト
        List<TwitterProfile> retweeters = new ArrayList<TwitterProfile>();
		
		retweeters = getRetweeters(twitter);
				
		return retweeters;
	}
	
	

}
