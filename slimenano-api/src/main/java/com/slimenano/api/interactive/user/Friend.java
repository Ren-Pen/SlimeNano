package com.slimenano.api.interactive.user;

import com.slimenano.api.exception.bot.NoImplementException;

public interface Friend extends User{

    public String getRemark();

    public void changeRemark(String remark) throws IllegalStateException, NoImplementException;

    public void remove() throws IllegalStateException, NoImplementException;

}
