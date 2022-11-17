package com.ncs503.Babybook.models.request.specification;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectByNameRequest {

    private String firstName;
    private String order;

    public SubjectByNameRequest(String firstName, String order) {
       this.firstName=firstName;
        this.order = order;
    }

    public boolean isASC(){ return this.order.compareToIgnoreCase("ASC") == 0;}
    public boolean isDESC(){ return this.order.compareToIgnoreCase("DESC") == 0;}
}
