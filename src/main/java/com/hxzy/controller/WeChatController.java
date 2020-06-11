package com.hxzy.controller;

import com.hxzy.bean.WeChatConnectMessage;
import com.hxzy.util.MessageUtil;
import com.hxzy.util.WeChatCheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Map;

/**
 * @author: huYang
 * @description: 微信相关
 * @date: 2020-05-25 19:28
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {


    @RequestMapping("/get")
    public void getaccess_token(HttpServletRequest request, HttpServletResponse response) {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxdc290faea23cf862&secret=97432523c0998d45661998608fb277fa";

        BufferedReader in = null;

        String result = "";
        try {
            URL getwalletAmountUrl = new URL(url);
            URLConnection context = getwalletAmountUrl.openConnection();
            in = new BufferedReader(new InputStreamReader(
                    context.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println(result);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /*public String createMenu(){

    }*/


    @GetMapping("/init")
    public void init(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //微信加密签名
        String signature = request.getParameter("signature");

        //时间戳
        String timestamp = request.getParameter("timestamp");

        //随机数
        String nonce = request.getParameter("nonce");

        //随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter writer = response.getWriter();
        if (WeChatCheckUtil.checkSignature(signature, timestamp, nonce)) {
            writer.print(echostr);
            log.info("链接返回的字符：" + echostr);
            log.info("链接成功您可以使用！");
        } else {
            //out.print("error");
            log.info("不是微信服务器发送的请求！！");
        }
    }

    //这个方法用于自动应答消息
    @PostMapping("/init")
    public void post_init(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String str = null;

            //将request请求，传到Message工具类的转换方法中，返回接收到的Map对象
            Map<String, String> map = MessageUtil.xmlToMap(request);
            //从集合中，获取XML各个节点的内容
            String ToUserName = map.get("ToUserName");
            String FromUserName = map.get("FromUserName");
            String CreateTime = map.get("CreateTime");
            String MsgType = map.get("MsgType");
            String Content = map.get("Content");
            String MsgId = map.get("MsgId");

            if (MsgType.equals("text")) {//判断消息类型是否是文本消息(text)

                WeChatConnectMessage message = new WeChatConnectMessage();
                message.setFromUserName(ToUserName);//原来【接收消息用户】变为回复时【发送消息用户】

                message.setToUserName(FromUserName);

                message.setMsgType("text");

                message.setCreateTime(new Date().getTime());//创建当前时间为消息时间

                message.setContent("您好，" + FromUserName + "\n我是：" + ToUserName + "\n您发送的消息类型为：" + MsgType + "\n您发送的时间为" + CreateTime + "\n我回复的时间为：" + message.getCreateTime() + "您发送的内容是" + Content);

                str = MessageUtil.objectToXml(message); //调用Message工具类，将对象转为XML字符串

                System.out.println(str);
            }

            out.print(str); //返回转换后的XML字符串
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @GetMapping("/gettoken")
    @ResponseBody
    public String token() {
        return "huyang";
    }
}
