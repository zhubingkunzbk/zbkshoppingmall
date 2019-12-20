package com.shoppingmall.configure;

import java.io.IOException;
import java.util.Properties;

public class AliPayConf {
    private Properties properties;
    public String return_url ;
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public String notify_url;
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public String app_id;
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public String alipay_public_key;
    // 商户私钥，您的PKCS8格式RSA2私钥
    public String merchant_private_key;
    // 签名方式
    public String sign_type;
    // 字符编码格式
    public String charset;
    // 支付宝网关
    public String gatewayUrl;
    private static volatile AliPayConf instance;

    private AliPayConf(){
        this.properties = new Properties();
        try {
            this.properties.load(AliPayConf.class.getClassLoader().getResourceAsStream("alipay.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.return_url = this.properties.getProperty("return_url");
        this.notify_url = this.properties.getProperty("notify_url");
        this.app_id = this.properties.getProperty("app_id");
        this.alipay_public_key = this.properties.getProperty("alipay_public_key");
        this.merchant_private_key = this.properties.getProperty("merchant_private_key");
        this.sign_type = this.properties.getProperty("sign_type");
        this.charset = this.properties.getProperty("charset");
        this.gatewayUrl = this.properties.getProperty("gatewayUrl");
    }
    public static AliPayConf getInstance(){
        if(instance == null){
            synchronized (AliPayConf.class){
                if(instance == null){
                    instance = new AliPayConf();
                }
            }
        }
        return instance;
    }

    public String getReturn_url() {
        return return_url;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public String getApp_id() {
        return app_id;
    }

    public String getAlipay_public_key() {
        return alipay_public_key;
    }

    public String getMerchant_private_key() {
        return merchant_private_key;
    }

    public String getSign_type() {
        return sign_type;
    }

    public String getCharset() {
        return charset;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }
}
