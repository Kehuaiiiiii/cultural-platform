package com.platform.mapper;

import com.platform.domain.Merchant;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MerchantMapper {
    @Select("select * from cultural_merchant where username=#{zh} and password=#{pwd}")
    Merchant Login(@Param("zh") String zh, @Param("pwd") String pwd);

    @Insert("insert into cultural_merchant values(null,#{username},#{password},#{name},#{email},#{phone},#{address})")
    Boolean addMerchant(Merchant merchant);

    @Update("update cultural_merchant set username=#{username},password=#{password},name=#{name},email=#{email},phone=#{phone},address=#{address} where pid=#{pid}")
    Boolean updateMerchant(Merchant merchant);

    @Select("select * from cultural_merchant where pid=#{pid}")
    Merchant getMerchantInfo(Merchant merchant);
}
