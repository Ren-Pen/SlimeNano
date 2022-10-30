package com.slimenano.api.interactive.group;

import com.slimenano.api.exception.bot.NoImplementException;
import com.slimenano.api.exception.bot.NoPermissionException;
import com.slimenano.api.interactive.Interactive;
import com.slimenano.api.message.MessageChain;
import com.slimenano.api.message.MessageSource;

import java.util.List;

public interface Group extends Interactive {

    public String getName();

    public void setName(String name) throws NoPermissionException;

    public List<Member> getMembers() throws IllegalStateException;

    public List<Notice> getNotices() throws IllegalStateException, NoImplementException;

    public void publishNotices(Notice notice) throws NoPermissionException, IllegalStateException, NoImplementException;

    public void removeNotices(Notice notice) throws NoPermissionException, IllegalStateException, NoImplementException;

    public void leave() throws IllegalStateException;

    /**
     * 发送消息
     * @throws IllegalStateException 当前无法发送消息时抛出（被禁言，或其他情况）
     * @throws NoImplementException 当前可交互对象未实现该方法时抛出
     */
    public MessageSource send(MessageChain chain) throws IllegalStateException, NoImplementException;

}
