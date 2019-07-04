package com.yanghongtao.springboot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.yanghongtao.springboot.entities.Provider;
import com.yanghongtao.springboot.mapper.ProviderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ProviderController {
    Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    ProviderMapper providerMapper;

    /**
     * 商品列表页
     * @param map
     * @param
     * @return
     */
    @GetMapping("/providers")
    public String list(Map<String, Object> map, Provider provider){
        logger.info("查询供应商列表"+provider);
        //PageHelper.startPage(pageNum,3);
        List<Provider> providers = providerMapper.getProviders(provider);
        //PageInfo<Provider> pageInfo = new PageInfo<Provider>(providers);
        //model.addAttribute("pageInfo",pageInfo);

        map.put("providers",providers);
        map.put("providerName",provider.getProviderName());

        return "provider/list";
    }

    /**
     * 商品详情页
     * @param pid
     * @param map
     * @return
     */
    @GetMapping("/provider/{pid}")
    public String view(@RequestParam(value = "type",defaultValue = "view") String type,@PathVariable("pid") Integer pid, Map<String,Object> map){

        Provider provider = providerMapper.getProviderByPid(pid);
        logger.info("查询供应商详情:"+ provider);
        map.put("provider",provider);
        return "provider/"+type;
    }

    /**
     * 修改供应商信息
     * @param provider
     * @return
     */
    @PutMapping("/provider")
    public String update(Provider provider){
        logger.info("修改供应商信息"+provider);
        provider.setCreateDate(new Date());
        providerMapper.updateProvider(provider);

        return "redirect:/providers";
    }

    /**
     * 前往添加供应商页面
     * @return
     */
    @GetMapping("/provider")
    public String toAddPage(){
        return "provider/add";
    }

    @PostMapping("/provider")
    public String addProvider(Provider provider){
        logger.info("添加供应商信息："+provider);

        provider.setCreateDate(new Date());
        providerMapper.addProvider(provider);

        return "redirect:/providers";
    }

    @DeleteMapping("/provider/{pid}")
    public String delete(@PathVariable("pid") Integer pid){
        logger.info("删除供应商:"+pid);
        providerMapper.deleteProviderByPid(pid);
        return "redirect:/providers";
    }



}
