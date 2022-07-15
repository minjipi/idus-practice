package com.hongminji.idus.member.repository;

import com.hongminji.idus.member.entity.DeniedToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeniedTokenRepository extends CrudRepository<DeniedToken, String> {
}
