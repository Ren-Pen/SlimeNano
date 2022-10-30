package com.slimenano.api.interactive.group;

import com.slimenano.api.enums.GroupPermission;
import com.slimenano.api.exception.bot.NoImplementException;
import com.slimenano.api.exception.bot.NoPermissionException;
import com.slimenano.api.exception.bot.SecondOutOfBoundsException;
import com.slimenano.api.interactive.user.User;

public interface Member extends User {

    public Group getGourp();

    public String getRemark();

    public GroupPermission getGroupPermission();

    public void changeRemark(String remark) throws NoPermissionException, IllegalStateException, NoImplementException;

    public void kick() throws NoPermissionException, IllegalStateException;

    public void mute(long second) throws NoPermissionException, IllegalStateException, SecondOutOfBoundsException;

    public void unmute() throws NoPermissionException, IllegalStateException;

    public void changeAdministrator(boolean state) throws NoPermissionException, IllegalStateException;

}
