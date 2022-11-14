package com.ncs503.Babybook.models.request;

import com.ncs503.Babybook.models.utility.TagsEventEnum;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Class representing an Events Organization Request.")
public class EventRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ApiModelProperty(notes = "Name of the Events", example = "Primer Dia de Jardin", required = true)
    private String title;

    private String body;

    private LocalDate date;

    private List<String> media;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private Boolean sofdelete = Boolean.FALSE;

//    @ManyToOne
//    @JoinColumn(name = "subject_id")
//    private Subject subjectId;

    private List<TagsEventEnum> eventEnumList;

}
