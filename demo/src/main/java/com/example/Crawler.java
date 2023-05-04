package com.example;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;

public class Crawler {
    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
        WebClient webClient = new WebClient(BrowserVersion.EDGE);
        String url = "https://api.bilibili.com/x/web-interface/view/detail?platform=web&bvid=BV14J4114768&aid=80149248&need_operation_card=1&web_rm_repeat=1&need_elec=1&out_referer=https://www.bilibili.com/index.html&page_no=1&p=1";
        Page page = webClient.getPage(url);
        WebResponse webResponse = page.getWebResponse();
        String json = webResponse.getContentAsString();
        JSONObject parseObject = JSONObject.parseObject(json);
        JSONObject data = parseObject.getJSONObject("data");
        JSONObject View = data.getJSONObject("View");
        JSONArray pages = View.getJSONArray("pages");
        for (Object tmp : pages) {
            JSONObject parseObject2 = JSONObject.parseObject(tmp.toString());
            Object out_page = parseObject2.get("page");
            String out_part = (String)parseObject2.get("part");
            String out = out_page+"--"+out_part;
            FileUtils.writeStringToFile(new File("D://pink老师课程.txt"),  out+"\n", "UTF-8", true);
            // System.out.println(out_page+"--"+out_part);
        }
        webClient.close();
    }
}
