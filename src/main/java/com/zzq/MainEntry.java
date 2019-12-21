package com.zzq;

import com.zzq.dto.YSCUser;

import java.util.ArrayList;
import java.util.List;

public class MainEntry {
    public static void main(String[] args) {
        List<YSCUser> users = new ArrayList<>();
        users.add(new YSCUser("15923584508","475546259"));
        users.add(new YSCUser("18108303047","475546259"));
        users.add(new YSCUser("17320413743","475546259"));
        users.add(new YSCUser("13512386223","475546259"));
        users.add(new YSCUser("15823270454","475546259"));
        for (YSCUser user:users) {
            Processs process = new Processs();
            process.flow2(user);
        }
    }
}
