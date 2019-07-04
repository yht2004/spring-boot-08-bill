package com.yanghongtao.springboot.controller;

import com.yanghongtao.springboot.dao.BillDao;
import com.yanghongtao.springboot.dao.ProviderDao;
import com.yanghongtao.springboot.entities.Bill;
import com.yanghongtao.springboot.entities.BillProvider;
import com.yanghongtao.springboot.entities.Provider;
import com.yanghongtao.springboot.mapper.BillMapper;
import com.yanghongtao.springboot.mapper.ProviderMapper;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class BillController {

    @Autowired
    BillMapper billMapper;
    @Autowired
    ProviderMapper providerMapper;


    Logger logger = LoggerFactory.getLogger(getClass());


    //账单查询
    @GetMapping("/bills")
    public String list(Map<String,Object> map, BillProvider bp){
        logger.info("账单查询："+bp);

        List<BillProvider> billProviders = billMapper.getBills(bp);
        List<Provider> providers = providerMapper.getProviders(null);

        map.put("providers",providers);
        map.put("billProviders",billProviders);

        map.put("billName",bp.getBillName());
        map.put("pid",bp.getPid());
        map.put("pay",bp.getPay());

        return "bill/list";
    }

    //帐单详情
    @GetMapping("/bill/{bid}")
    public String billInfo(Map<String,Object> map,@PathVariable("bid") Integer bid,@RequestParam(value = "type",defaultValue = "view") String type){
        logger.info("查询"+bid+"帐单详情");
        BillProvider bill = billMapper.getBillByBid(bid);

        if("update".equals(type)){
            List<Provider> providers = providerMapper.getProviders(null);
            map.put("providers",providers);
        }

        map.put("bill",bill);

        return "bill/" + type;
    }

    //帐单修改
    @PutMapping("/bill")
    public String billUpdate(Bill bill){
        billMapper.updateBill(bill);
        return "redirect:bills";
    }

    //进入添加账单页面
    @GetMapping("/addView")
    public String addView(Map<String,Object> map){
        List<Provider> providers = providerMapper.getProviders(null);
        map.put("providers",providers);

        return "bill/add";
    }

    //添加账单
    @PostMapping("/addBill")
    public String addBill(Bill bill){
        billMapper.addBill(bill);

        return "redirect:bills";
    }

    //删除账单
    @DeleteMapping("/bill/{bid}")
    public String deleteBill(@PathVariable("bid") Integer bid){
        billMapper.deleteBillByBid(bid);

        return "redirect:/bills";
    }



}
