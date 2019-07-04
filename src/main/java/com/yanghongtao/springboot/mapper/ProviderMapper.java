package com.yanghongtao.springboot.mapper;

import com.github.pagehelper.PageHelper;
import com.yanghongtao.springboot.entities.Provider;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper  在启动类增加@MapperScan扫描
public interface ProviderMapper {


    List<Provider> getProviders(Provider provider);

    Provider getProviderByPid(Integer pid);

    int addProvider(Provider provider);

    int deleteProviderByPid(Integer pid);

    int updateProvider(Provider provider);

}
