package com.slimenano.api.interactive.bot;

import com.slimenano.api.enums.BotStatus;
import com.slimenano.api.interactive.Interactive;
import com.slimenano.api.interactive.group.Group;
import com.slimenano.api.interactive.user.Friend;
import com.slimenano.api.interactive.user.User;

import java.util.List;

public interface Bot extends User {

    public void setNickname(String name);

    public List<Friend> getFriends();

    public List<Group> getGroups();

    public Group getGroup(String id);

    public Friend getFriend(String id);

    public void login(String password);

    /**
     * 关闭当前机器人实例，被关闭的机器人实例将不可再次登录
     */
    public void close();

    public BotStatus getStatus();

}
