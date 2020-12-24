package com.platform.service;

import com.platform.domain.Goods;
import com.platform.domain.Merchant;

import java.util.List;

public interface IMerchantService {
    Merchant Login(String zh, String pwd);

    Boolean addMerchant(Merchant merchant);

    Boolean updateMerchant(Merchant merchant);

    Merchant getMerchantInfo(Merchant merchant);

    List<Goods> getAllGoods();
}
