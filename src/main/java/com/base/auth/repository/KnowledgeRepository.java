package com.base.auth.repository;

import com.base.auth.model.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface KnowledgeRepository extends JpaRepository<Knowledge,Long> , JpaSpecificationExecutor<Knowledge> {

}
