package br.com.supernova.apimembers.builder;

import br.com.supernova.apimembers.enums.AlignmentMemberEnum;
import br.com.supernova.apimembers.enums.TypeMemberEnum;
import br.com.supernova.apimembers.enums.UniverseMemberEnum;
import br.com.supernova.apimembers.model.Member;
import lombok.Builder;

@Builder
public class MemberBuilder {
    @Builder.Default
    private final Long id = 1L;
    @Builder.Default
    private final String name = "Superman";
    @Builder.Default
    private final UniverseMemberEnum universe = UniverseMemberEnum.DC_COMICS;
    @Builder.Default
    private final AlignmentMemberEnum alignment = AlignmentMemberEnum.HERO;
    @Builder.Default
    private final TypeMemberEnum type = TypeMemberEnum.TEAM;
    @Builder.Default
    private final Integer films = 5;

    public Member toMember(){
        return new Member(id, name, universe, alignment, type,films);
    }
}
