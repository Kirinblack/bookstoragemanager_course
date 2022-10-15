package com.lguilherme.bookstoragemanager.Rentals.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RentalReturnStates {

    INITIAL_VALUE("Não devolvido"),
    EARLY("(No prazo)"),
    LATE("(Com atraso)");

    private final String description;
}
