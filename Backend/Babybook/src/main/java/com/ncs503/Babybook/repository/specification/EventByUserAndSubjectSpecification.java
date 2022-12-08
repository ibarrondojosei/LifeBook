package com.ncs503.Babybook.repository.specification;

import com.ncs503.Babybook.models.entity.EventEntity;
import com.ncs503.Babybook.models.entity.SubjectEntity;
import com.ncs503.Babybook.models.entity.UserEntity;
import com.ncs503.Babybook.models.request.specification.EventByUserAndSubjectRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class EventByUserAndSubjectSpecification {


    public Specification<EventEntity> getByUserAndSubject(EventByUserAndSubjectRequest filterRequest) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            /*
            QUIERO QUE ME MUESTRE LOS EVENTOS DE X SUJETOS
             */
            String enume = filterRequest.getEventEnum().name();
            System.out.println("\nenume : " + enume);

            if (StringUtils.hasLength(filterRequest.getEventEnum().name())) { // pregunta si tiene algo el filter
                predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(root.get("eventEnum")), //nombre del atributo
                        filterRequest.getEventEnum()
                ));

            }

            if (filterRequest.getSubjectId() != null) {
                Join<EventEntity, SubjectEntity> join = root.join("subjectEntity", JoinType.INNER);  //nombre del atributo
                Expression<String> idUser = join.get("id"); //nombre de la columna
                predicates.add(idUser.in(filterRequest.getSubjectId()));
            }

            //remueve duplicados
            query.distinct(true);

            // Resuelve el orden
            String orderByField = "timestamp"; //"date = nombre del atributo

            query.orderBy(
                    filterRequest.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and((javax.persistence.criteria.Predicate[])
                    predicates.toArray(new Predicate[0]));
        };

    }



}
