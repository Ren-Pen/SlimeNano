package com.slimenano.api.interactive.user;

import com.slimenano.api.exception.bot.NoImplementException;
import com.slimenano.api.interactive.Interactive;
import com.slimenano.api.message.MessageChain;
import com.slimenano.api.message.MessageSource;

public interface User extends Interactive {
    /**
     * 用户的昵称
     */
    public String getNickname();

    /**
     * 获取用户的资料，返回json字符串
     */
    public String getProfile();

    /**
     * 发送消息
     * @throws IllegalStateException 当前无法发送消息时抛出（被禁言，或其他情况）
     * @throws NoImplementException 当前可交互对象未实现该方法时抛出
     */
    public MessageSource send(MessageChain chain) throws IllegalStateException, NoImplementException;


}
