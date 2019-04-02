package com.blog.guoyankai.mapper;
import com.blog.guoyankai.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface PostMapper {
    //查询全部文章
    List<Post> findPosts();
    //根据id查询单个文章
    Post findPost(int id);
    //新增文章
    int insertPost(Post post);
}
