package com.ncs503.Babybook.models.entity;

import com.ncs503.Babybook.models.utility.TagsEventEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SQLDelete(sql = "UPDATE events SET soft_delete = true Where id=?")
@Where(clause = "soft_delete = false")
@Table( name= "events")
public class EventEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The name can't be null")
    @NotEmpty(message = "The name can't be null")
    @NotBlank(message = "The name can't be null")
    private String title;

    private String body;

    private LocalDate date;

    private List<String> media;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private Boolean sofdelete = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subjectenenene subjectId;

    private List<TagsEventEnum> eventEnumList;

}
