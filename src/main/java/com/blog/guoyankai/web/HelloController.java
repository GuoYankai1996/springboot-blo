package com.blog.guoyankai.web;
import com.blog.guoyankai.mapper.PostMapper;
import com.blog.guoyankai.pojo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class HelloController {
    //注入mybitis mapper实体
    @Autowired private PostMapper postMapper;
    //读取properties参数
    @Value("${edit.password}")
    private String editPassword;
    //首页
    @RequestMapping(value={"", "/hello"})
    public String hello(Model m, HttpServletRequest req){
        List<Post> posts=postMapper.findPosts();
        transferTime(posts);
        m.addAttribute("posts", posts);
        return "hello";
    }
    //文章页
    @RequestMapping("/article/{id}")
    public String article(Model m,@PathVariable int id) {
        Post post=postMapper.findPost(id);
        transferTime(post);
        m.addAttribute(post);
        return "article";
    }
    //后台编辑页
    @RequestMapping("/edit")
    public String article(Model m) {
        return "edit";
    }
    //新增文章接口
    @RequestMapping("/insert")
    public String insertPost(Model m, @ModelAttribute("form") Post post, @RequestParam("password") String password) throws Exception {
        //校验密码与properties中是否一致
        if(!editPassword.equals(password)){
            //跳转异常处理界面（springboot全局异常处理器 ErrotHandler）
            throw new Exception("密码错误");
        }
        //转换时间格式，插入数据库
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        post.setCreateTime(sdf.format(new Date()));
        postMapper.insertPost(post);
        return "redirect:/hello";
    }

    /*把从数据库获得的datetime类型字符串转化为前台展示的美国时间字符串*/
    public void transferTime(List<Post> posts){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df1 = new SimpleDateFormat("MMMMMMMMMMM yyyy", Locale.ENGLISH);
        for(Post p:posts){
            try {
                Date d=sdf.parse(p.getCreateTime());
                p.setCreateTime(df1.format(d));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    /*把从数据库获得的datetime类型字符串转化为前台展示的美国时间字符串*/
    public void transferTime(Post p){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df1 = new SimpleDateFormat("MMMMMMMMMMM dd,yyyy", Locale.ENGLISH);
            try {
                Date d=sdf.parse(p.getCreateTime());
                p.setCreateTime(df1.format(d));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

}