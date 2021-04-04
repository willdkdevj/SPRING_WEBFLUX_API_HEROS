package br.com.supernova.apimembers.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlignmentMemberEnum {
    HERO("Hero"),
    VILLAIN("Villain");

    private final String alignment;
}
