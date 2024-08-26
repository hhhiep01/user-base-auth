package com.base.auth.model.criteria;

import com.base.auth.model.Knowledge;
import com.base.auth.model.User;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class KnowledgeCriteria {
    private Long id;

    private String name;

    private String content;

    private String knowledgeStatus;

    public Specification<Knowledge> getSpecification()
    {
        return new Specification<Knowledge>(){
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Knowledge> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(getId()!=null){
                    predicates.add(cb.equal(root.get("id"),getId()));
                }
                if(!StringUtils.isBlank(getName())){
                    predicates.add(cb.like(cb.lower(root.get("name")), "%"+getName().toLowerCase()+"%"));
                }
                if(!StringUtils.isBlank(getContent())){
                    predicates.add(cb.like(cb.lower(root.get("content")), "%"+getContent().toLowerCase()+"%"));
                }
                if (!StringUtils.isBlank(getKnowledgeStatus())) {
                    predicates.add(cb.like(cb.lower(root.get("knowledgeStatus")), "%"+getKnowledgeStatus().toLowerCase()+"%"));;
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }

        };

    }


}
