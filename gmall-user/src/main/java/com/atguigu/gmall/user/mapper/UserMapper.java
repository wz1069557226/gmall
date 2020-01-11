package com.atguigu.gmall.user.mapper;


import com.atguigu.gmall.bean.UmsMember;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface UserMapper extends Mapper<UmsMember> {

}
