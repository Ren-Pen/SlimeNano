package com.slimenano.api.exception.bot;

import lombok.Getter;

public class SecondOutOfBoundsException extends RuntimeException{

    @Getter
    private final long maxSecond;

    public SecondOutOfBoundsException(long maxSecond) {
        super("超出最大允许时间限制！最大秒数：" + maxSecond);
        this.maxSecond = maxSecond;
    }


}
