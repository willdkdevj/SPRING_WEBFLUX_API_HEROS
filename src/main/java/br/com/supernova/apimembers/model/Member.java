package br.com.supernova.apimembers.model;

import br.com.supernova.apimembers.enums.AlignmentMemberEnum;
import br.com.supernova.apimembers.enums.TypeMemberEnum;
import br.com.supernova.apimembers.enums.UniverseMemberEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "Unique entity identifier", required = true)
    private Long id;

    @Column(nullable = false, unique = true)
    @ApiModelProperty(notes = "Unique value where it cannot be null", required = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Value cannot be null", required = true)
    private UniverseMemberEnum universe;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Value cannot be null", required = true)
    private AlignmentMemberEnum alignment;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Value cannot be null", required = true)
    private TypeMemberEnum type;

    @Column(nullable = false)
    @ApiModelProperty(notes = "Value cannot be null", required = true)
    private Integer films;

}
