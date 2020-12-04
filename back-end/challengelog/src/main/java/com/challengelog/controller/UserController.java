package com.challengelog.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.challengelog.mapper.ChallengesMapper;
import com.challengelog.mapper.UserMapper;
import com.challengelog.mapper.UserstoryMapper;
import com.challengelog.pojo.Challenges;
import com.challengelog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



//import com.alibaba.druid.util.StringUtils;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.teeqee.threeKingdomsGame.wx.miniapp.config.WxMaProperties;
//import com.teeqee.threeKingdomsGame.wx.miniapp.pojo.SanGuoUser;
//import com.teeqee.threeKingdomsGame.wx.miniapp.service.SanGuoUserService;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.ResponseHandler;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.BasicResponseHandler;
//import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;


@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public final static String getPageOpenidUrl = "https://api.weixin.qq.com/sns/jscode2session";
    public final static String GetPageAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //微信公众号获取用户信息
    public final static String GetPageUserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    //微信小程序获取用户信息
    public final static String GetPageUserInfoUrl_XCX = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";


    @Autowired
    UserMapper userMapper;

    @Autowired
    ChallengesMapper challengesMapper;

    @Autowired
    UserstoryMapper userstoryMapper;


    /**
     * 输入自己的id跟密码，获取微信的安全密令字符串
     * @param APP_ID
     * @param APPSECRET
     * @return
     */
    public static String getAccess_token( String APP_ID,String APPSECRET) {
        //设置变量 url与返回值其中url使用拼接带入参数APP_ID， APPSECRET
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + APP_ID+ "&secret=" + APPSECRET;
        String accessToken = null;
        try {
            //设置链接
            URL urlGet = new URL(url);
            //设置外网代理链接
            InetSocketAddress addr = new InetSocketAddress("192.168.99.100",80);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
            //启动链接
            HttpURLConnection http = (HttpURLConnection) urlGet .openConnection(proxy);
            //设置链接参数与要求
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30
//            链接
            http.connect();
            //获取返回值json字节流
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            //转化成字符串
            String message = new String(jsonBytes, "UTF-8");
//            转化成json对象然后返回accessToken属性的值
            JSONObject demoJson =JSONObject.parseObject(message);
            accessToken = demoJson.getString("access_token");
            System.out.println(accessToken);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }


    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
//    wxab9b037d01003967
//    bd6e444d1bb2f64d3e471be7421d000e
    @RequestMapping("/login")
    @ResponseBody
    public String toLogin(@RequestBody JSONObject jsonObject){
        String code = jsonObject.getString("code");
        String result = sendGet("https://api.weixin.qq.com/sns/jscode2session",
                "appid=" + "wxab9b037d01003967" + //小程序APPID
                        "&secret="+ "bd6e444d1bb2f64d3e471be7421d000e" + //小程序秘钥
                        "&js_code="+ code + //前端传来的code
                        "&grant_type=authorization_code");
        JSONObject jsonObject1 = JSONObject.parseObject(result);
        if (jsonObject1.containsKey("errcode")) {
//            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION, "code无效");
            System.out.println("errcode");
        }
        String openId = jsonObject1.get("openid").toString();
//        if (StringUtils.isNullOrEmpty(openId)) {
//            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION, "openid为空");
//        }
        int flag = 0;
        List<User> userList = userMapper.queryUserList();
        User tempuser = null;
        for (User user:userList
             ) {
            if (user.getOpen_id().equals(openId)) {
                tempuser = user;
                flag = 1;
            }
        }
        if (flag == 0) {
            tempuser = new User(openId, "temp", "tempurl", "女", 1);
            tempuser.setCurrent_plot_id(1);
            userMapper.insertUser(tempuser);
            userstoryMapper.insertUserStory(tempuser.getId(), 1);
            userstoryMapper.insertUserStory(tempuser.getId(), 3);
        }

        int user_id = tempuser.getId();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("open_id", openId);
        jsonObject2.put("user_id", user_id);
        return jsonObject2.toJSONString();
    }


    @RequestMapping("/userList")
    public List<User> queryUserList() {
//        challengesMapper.insertChallenges(new Challenges(1, "第一个挑战", "打亿行代码", new Timestamp(10), new Timestamp(11), false, false));

//        System.out.println(plotMapper.queryPlotById(1));
//        System.out.println(storyMapper.queryStoryById(1));

//        System.out.println(userMapper.queryUserById(1).toString());
//        userMapper.insertUser(new User("abc", "刘姥姥", "skj", "女", 1));
//        System.out.println(userMapper.updateUser(new User("abc", "刘姥姥", "skj", "女", 1)));
//        userMapper.deleteUserById();
        return userMapper.queryUserList();
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
