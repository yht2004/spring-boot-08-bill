package com.yanghongtao.springboot;

import com.yanghongtao.springboot.entities.Bill;
import com.yanghongtao.springboot.entities.BillProvider;
import com.yanghongtao.springboot.entities.Provider;
import com.yanghongtao.springboot.entities.User;
import com.yanghongtao.springboot.mapper.BillMapper;
import com.yanghongtao.springboot.mapper.ProviderMapper;

import com.yanghongtao.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootBillApplicationTests {

    @Autowired
    ProviderMapper providerMapper;
    @Autowired
    BillMapper billMapper;
    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads() throws SQLException {
//        List<Provider> providers = providerMapper.getProviders(null);
//        for(Provider p : providers){
//            System.out.println(p);
//        }


//        providerMapper.addProvider(new Provider(null,"P001","华为供应商",
//                "任正非","15010002341","华为创业园","010-43215099","A级"));
        Provider provider = providerMapper.getProviderByPid(1);
        System.out.println(provider);

        provider.setProviderCode("P90111");
        int size = providerMapper.updateProvider(provider);
        System.out.println(size);
    }

    @Test
    public void testBill(){
//        Bill bill = new Bill();
//
//        List<BillProvider> bills = billMapper.getBills(bill);
//
//        BillProvider billByBid = billMapper.getBillByBid(2);
//        System.out.println(billByBid);
//
//        billByBid.setBillName("无相劫空");
//        int size = billMapper.updateBill(billByBid);

        billMapper.addBill(new Bill(3001, "Bi-AA11", "粮油aaa", "斤", 80,480.8,
                new Provider(2, "PR-BB", "梦学谷供应商222", "小李", "18888666982", "深圳软件园",
                        "0911-0123453", "品质B"), 1));
    }

    @Test
    public void testUser(){
        List<User> users = userMapper.getUsers(null);
        System.out.println(users.get(0));

        User user = userMapper.getUserById(2);
        System.out.println(user);
    }




}
