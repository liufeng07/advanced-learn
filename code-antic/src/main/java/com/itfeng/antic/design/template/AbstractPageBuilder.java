package com.itfeng.antic.design.template;

/**
 * 抽象父类
 */
public abstract class AbstractPageBuilder implements PageBuilder {
    //通用方法
    //对于一些构建步骤和顺序。通常是不希望甚至不允许子类去覆盖的。所以在某些场景中，可以直接将父类中提供的骨架方法声明为final类型。
    private static final String DEFAULT_DOCTYPE = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">";
    //通用方法
    private static final String DEFAULT_XMLNS = "http://www.w3.org/1999/xhtml";

    private StringBuffer stringBuffer = new StringBuffer();

    //定义一个模版方法，定义好骨架。具体子类去实现抽象方法
    @Override
    public String bulidHtml() {
        stringBuffer.append(DEFAULT_DOCTYPE);
        stringBuffer.append("<html xmlns=\"" + DEFAULT_XMLNS + "\">");
        stringBuffer.append("<head>");
        appendTitle(stringBuffer);
        appendMeta(stringBuffer);
        appendLink(stringBuffer);
        appendScript(stringBuffer);
        stringBuffer.append("</head>");
        appendBody(stringBuffer);
        stringBuffer.append("</html>");
        return stringBuffer.toString();
    }

    //普通方法子类可以覆写也可以不覆写
    protected void appendMeta(StringBuffer stringBuffer) {
    }

    protected void appendLink(StringBuffer stringBuffer) {
    }

    protected void appendScript(StringBuffer stringBuffer) {
    }

    //抽象方法子类必须实现的
    protected abstract void appendTitle(StringBuffer stringBuffer);

    protected abstract void appendBody(StringBuffer stringBuffer);
}
