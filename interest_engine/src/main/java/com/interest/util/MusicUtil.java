package com.interest.util;


import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 431 on 2015/4/30.
 */
public class MusicUtil {
    private static String EXCLUDE_URL_KEY_WORD = "zhangmenshiting";
    private static String URL = "http://box.zhangmen.baidu.com/x?op=12&count=1&title=";
    public static List handleXml(String song, String singer) {
        if(song==null) song = "";
        if(singer==null) singer = "";
        String url = URL + song + "$$" + singer;
        List result = new ArrayList();
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(url);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = doc.getRootElement();
        List nodes=root.elements("url");
        for(Iterator it = nodes.iterator();it.hasNext();){
            Element element = (Element)it.next();
            String str1 = element.element("encode").getStringValue();
            str1 = str1.substring(0,str1.lastIndexOf('/')+1);
            String str2 = element.element("decode").getStringValue();
            if(str1.indexOf(EXCLUDE_URL_KEY_WORD)==-1) result.add(str1+str2);
        }
        return result;
    }
}
