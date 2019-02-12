package com.example.hsu.customize.usableClass;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//文本過濾器
public class ContentStrainer {
    //script標籤
    public static final String REGEX_SCRIPT = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    //style標籤
    public static final String REGEX_STYLE = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    //其餘的html標籤
    public static final String REGEX_HTML = "<[^>]+>";
    //空白符、回車鍵及換行符
    public static final String REGEX_SPACE = "\\s*|\t|\r|\n|&nbsp";


    @NotNull
    public static String delAllTag(String content){
        content = delHTMLTag(content);
        content = delSpaceTag(content);
        return content.trim();
    }

    @NotNull
    public static String delSpaceTag(String content){
        //過濾空白符、回車鍵及換行符
        content = strain(content, REGEX_SPACE);
        return content.trim();
    }

    public static String delHTMLTag(String content){
        //過濾script
        content = strain(content, REGEX_SCRIPT);
        //過濾style
        content = strain(content, REGEX_STYLE);
        //過濾其餘html標籤
        content = strain(content, REGEX_HTML);

        return content;
    }

    public static String strain(String content, String regex){
        return strain(content, regex, "");
    }

    public static String strain(String content, String regex, String replace){
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(content);
        return m.replaceAll(replace);
    }

}
