package com.yanghongtao.springboot.mapper;


import com.yanghongtao.springboot.entities.Bill;
import com.yanghongtao.springboot.entities.BillProvider;

import java.util.List;

public interface BillMapper {

    List<BillProvider> getBills(Bill bill);

    BillProvider getBillByBid(Integer bid);

    int updateBill(Bill bill);

    int addBill(Bill bill);

    int deleteBillByBid(Integer bid);

}
