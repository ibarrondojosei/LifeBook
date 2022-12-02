package com.ncs503.Babybook.models.request.specification;

import com.ncs503.Babybook.models.utility.TagsEventEnum;
import lombok.Data;

@Data
public class EventByUserAndSubjectRequest {

    private TagsEventEnum eventEnum;
    private Long subjectId;
    private String order;

    public EventByUserAndSubjectRequest(TagsEventEnum eventEnum, Long subjectId, String order) {
        this.eventEnum=eventEnum;
        this.subjectId=subjectId;
        this.order = order;
    }

    public boolean isASC(){ return this.order.compareToIgnoreCase("ASC") == 0;}
    public boolean isDESC(){ return this.order.compareToIgnoreCase("DESC") == 0;}


}
