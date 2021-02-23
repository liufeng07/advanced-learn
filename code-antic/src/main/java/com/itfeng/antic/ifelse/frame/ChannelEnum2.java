package com.itfeng.antic.ifelse.frame;

/**
 * 枚举类维护渠道与类对应关系
 */
public enum ChannelEnum2 {
    ALIPAY(1,"aliPayServiceImpl2"),
    TENCENT(2,"tencentPayServiceImpl2");
    private int channel;
    private String ServerType;

    ChannelEnum2(int channel, String serverType) {
        this.channel = channel;
        this.ServerType = serverType;
    }

    public int getChannel() {
        return channel;
    }

    public String getServerType() {
        return ServerType;
    }

    public static String getServiceTypeEnum(Integer mode) {
        for (ChannelEnum2 resCodeEnum : ChannelEnum2.values()) {
            if (mode.equals(resCodeEnum.getChannel())) {
                return resCodeEnum.getServerType();
            }
        }
        return null;
    }
}
