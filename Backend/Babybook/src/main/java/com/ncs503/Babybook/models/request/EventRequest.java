package com.ncs503.Babybook.models.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncs503.Babybook.models.entity.SubjectEntity;
import com.ncs503.Babybook.models.entity.UserEntity;
import com.ncs503.Babybook.models.utility.TagsEventEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(notes = "Title of the Events", example = "Primer Dia de Jardin", required = true)
    private String title;

    @ApiModelProperty(notes = "Body of the Events", example = "Escolar", required = true)
    private String body;

    @ApiModelProperty(notes = "Date of Events", example = "2022-11-15", required = true)
    private LocalDate date;

    @ApiModelProperty(notes = "images of Events", example = "", required = true)
    private List<String> media;

    @ApiModelProperty(notes = "HighlightMoment of the Events", example = "True / False", required = true)
    private Boolean highlightMoment = Boolean.FALSE;

    @CreationTimestamp
    private Timestamp timestamp;

//    @Column(name = "soft_delete")
    private Boolean sofdelete = Boolean.FALSE;

//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;

    private TagsEventEnum eventEnum;

//    @OneToOne
//    @JoinColumn(name = "user_id")
    private UserEntity userId;

}
