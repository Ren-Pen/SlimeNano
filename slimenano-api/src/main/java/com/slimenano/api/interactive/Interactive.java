package com.slimenano.api.interactive;

import com.slimenano.api.interactive.bot.Bot;

public interface Interactive {

    /**
     * 在目标平台标识该对象的唯一id
     */
    public String getId();

    /**
     * 对象所属于的平台
     */
    public String getPlatform();

    /**
     * 可交互对象所属bot
     */
    public Bot getBot();


}
