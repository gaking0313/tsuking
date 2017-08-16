package com.example.demo.service.dm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.social.twitter.api.DirectMessage;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

@Service
public class DMServiceImpl implements DMService {

    @Override
    public List<TwitterProfile> getDMUsers(Twitter twitter) {

        // 受信したダイレクトメッセージを取得
        // 1ページ200件取得し、200件以下になるまで繰り返し取得
        int page = 1;
        List<DirectMessage> recievedDms = new ArrayList<>();
        do {
            recievedDms.addAll(
                twitter.directMessageOperations().getDirectMessagesReceived(
                    page,
                    200));
            page++;
        } while (recievedDms.size() == 200);

        // ユーザー情報を取り出し、重複を排除
        Set<TwitterProfile> dmUsersSet = new HashSet<>();
        for (DirectMessage directMessage : recievedDms) {
            dmUsersSet.add(directMessage.getSender());
        }

        // リストとして返却
        List<TwitterProfile> dmUsers = new ArrayList<>(dmUsersSet);

        return dmUsers;
    }

}
