package com.nick.daily_news.member.repository;

import com.nick.daily_news.member.repository.entity.MemberEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberRepository
    extends JpaRepository<MemberEntity, Long>, JpaSpecificationExecutor<MemberEntity> {

  Optional<MemberEntity> findByEmail(String email);
}
