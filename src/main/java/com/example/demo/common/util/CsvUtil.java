package com.example.demo.common.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * CSV関連の共通機能提供クラス
 *
 * @author kohei_ot
 *
 */
@Component
public class CsvUtil {

    /**
     * TwitterProfileのListを
     * CSV形式の連番とユーザーのスクリーンネーム(@以降)に変換
     *
     * @param twitterProfile
     * @return CSV形式の連番とユーザーのスクリーンネーム(@以降)
     * @throws JsonProcessingException
     */
    public String convertTwitterProfToCsv(List<TwitterProfile> twitterProfile)
            throws JsonProcessingException {
        long no = 1;
        List<User> demos = new ArrayList<User>();
        for (TwitterProfile user : twitterProfile) {
            demos.add(new User(no, user.getScreenName()));
            no++;
        }

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(User.class).withHeader();
        return mapper.writer(schema).writeValueAsString(demos);
    }
}
