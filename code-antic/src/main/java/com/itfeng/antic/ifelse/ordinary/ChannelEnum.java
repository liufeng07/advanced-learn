package com.itfeng.antic.ifelse.ordinary;

public enum ChannelEnum {
    ALIPAY(1),
    TENCENT(2);
    private int channel;



    ChannelEnum(int channel) {
        this.channel = channel;
    }

    public int getChannel() {
        return channel;
    }

}
