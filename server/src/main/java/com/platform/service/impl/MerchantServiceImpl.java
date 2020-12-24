package com.platform.service.impl;

import com.platform.domain.Goods;
import com.platform.domain.Merchant;
import com.platform.mapper.MerchantMapper;
import com.platform.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl implements IMerchantService{

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public Merchant Login(String zh,String pwd) {
        return merchantMapper.Login(zh,pwd);
    }

    @Override
    public Boolean addMerchant(Merchant merchant) {
        return merchantMapper.addMerchant(merchant);
    }

    @Override
    public Boolean updateMerchant(Merchant merchant) {
        return merchantMapper.updateMerchant(merchant);
    }

    @Override
    public Merchant getMerchantInfo(Merchant merchant) {
        return merchantMapper.getMerchantInfo(merchant);
    }

    @Override
    public List<Goods> getAllGoods() {
        return null;
    }
}
