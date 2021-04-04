package br.com.supernova.apimembers.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeMemberEnum {
    INDIVIDUAL("Individual"),
    PLACE("Place"),
    TEAM("Team");

    private final String type;
}
