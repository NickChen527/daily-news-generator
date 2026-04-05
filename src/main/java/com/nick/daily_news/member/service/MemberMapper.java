package com.nick.daily_news.member.service;

import com.nick.daily_news.member.repository.entity.MemberEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "SPRING")
public interface MemberMapper {

  MemberVo fromEntity(MemberEntity entity);
}
