package com.yan.www.mapper;

import com.yan.www.model.Blog;

public interface BlogMapper {

    Blog selectBlog(Integer id);

    Blog queryById(Integer id);
}
