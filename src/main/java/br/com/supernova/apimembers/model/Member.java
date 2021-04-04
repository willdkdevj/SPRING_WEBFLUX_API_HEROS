package br.com.supernova.apimembers.model;

import br.com.supernova.apimembers.enums.AlignmentMemberEnum;
import br.com.supernova.apimembers.enums.TypeMemberEnum;
import br.com.supernova.apimembers.enums.UniverseMemberEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private static final Logger log = LoggerFactory.getLogger(Member.class);

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UniverseMemberEnum universe;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AlignmentMemberEnum alignment;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeMemberEnum type;

    @Column(nullable = false)
    private Integer films;
}
