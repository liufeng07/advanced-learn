package com.itfeng.antic.design.template;

/**
 * 具体模版类，继承抽象模版类，实现具体方法。
 */
public class MyPageBuilder extends AbstractPageBuilder {

    //子类覆盖，子类也可以选择不覆盖。
    protected void appendMeta(StringBuffer stringBuffer) {
        stringBuffer.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
    }

    @Override
    protected void appendTitle(StringBuffer stringBuffer) {
        stringBuffer.append("<title>你好</title>");

    }

    @Override
    protected void appendBody(StringBuffer stringBuffer) {
        stringBuffer.append("<body>你好，世界！</body>");
    }
}
