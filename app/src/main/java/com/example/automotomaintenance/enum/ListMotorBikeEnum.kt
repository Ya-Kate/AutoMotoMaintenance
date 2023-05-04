package com.example.automotomaintenance.enum

import com.example.automotomaintenance.model.InformationDB

enum class ListMotorBikeEnum(val value: InformationDB) {
    ONE(
        InformationDB(
            "Моторное масло и масляный фильтр", "4-5т.км/перед сезоном", "001", 1
        )
    ),
    TWO(
        InformationDB(
            "Топливный фильтр", "20-30т.км/1р в 2-3 года", "002", 2
        )
    ),
    THREE(
        InformationDB(
            "Свечи зажигания", "6-8т.км", "003", 3
        )
    ),
    FOUR(
        InformationDB(
            "Тормозная жидкость", "1р в 2 года", "004", 4
        )
    ),
    FIVE(
        InformationDB(
            "Аккумулятор", "5-8 лет", "005", 5
        )
    ),
    SIX(
        InformationDB(
            "Полная замена антифриза", "1р в 2 года", "006", 6
        )
    ),
    SEVEN(
        InformationDB(
            "Износ шин", "Не менее 1,6-2 мм.", "007", 7
        )
    )
}