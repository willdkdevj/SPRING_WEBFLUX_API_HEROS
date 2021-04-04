package br.com.supernova.apimembers.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UniverseMemberEnum {
    MARVEL("Marvel"),
    DC_COMICS("DC Comics");

    private final String universe;
}
