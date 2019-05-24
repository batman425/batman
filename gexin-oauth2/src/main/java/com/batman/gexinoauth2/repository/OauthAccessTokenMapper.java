package com.batman.gexinoauth2.repository;

import com.insuresmart.claimoauth.model.OauthAccessToken;
import com.insuresmart.claimoauth.system.dto.RefreshTokenDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OauthAccessTokenMapper {
    int insert(OauthAccessToken record);

    int insertSelective(OauthAccessToken record);

    OauthAccessToken selectOne(RefreshTokenDto refreshTokenDto);

    void deleteToken(RefreshTokenDto refreshTokenDto);
}