package com.example.automotomaintenance.enum

import com.example.automotomaintenance.model.InformationDB

enum class ListCarEnum(val value: InformationDB) {

    ONE(
        InformationDB(
            "Моторное масло и масляный фильтр", "10–15т.км/1р.в год", "001", 1
        )
    ),
    TWO(
        InformationDB(
            "Воздушный фильтр", "10-15т.км/1р. в год", "002", 2
        )
    ),
    THREE(
        InformationDB(
            "Салонный фильтр", "10-15т.км/1р. в год", "003", 3
        )
    ),
    FOUR(
        InformationDB(
            "Топливный фильтр", "20-40т.км/1р. в 2-3 года", "004", 4
        )
    ),
    FIVE(
        InformationDB(
            "Свечи зажигания", "20-40т.км/1р. в 2-3 года", "005", 5
        )
    ),
    SIX(
        InformationDB(
            "Тормозная жидкость", "20-40т.км/1р. в 2-3 года", "006", 6
        )
    ),
    SEVEN(
        InformationDB(
            "Тормозные колодки (диски)", "20-40т.км/1р. в 2-3 года", "007", 7
        )
    ),
    EIGHT(
        InformationDB(
            "Масло в АКПП, включая вариатор и робот DSG", "30-50т.км/1р. в 2 года", "008", 8
        )
    ),
    NIGHT(
        InformationDB(
            "Аккумулятор", "40-80т.км/1р. в 3-5 лет", "009", 9
        )
    ),
    TEN(
        InformationDB(
            "Масло в МКПП, раздаточной коробке, дифференциале", "50-75т.км/1р. в 4-5 лет", "010", 10
        )
    ),
    ELEVEN(
        InformationDB(
            "Ремень ГРМ с роликами", "50-75т.км/1р. в 4-5 лет", "011", 11
        )
    ),
    TWELVE(
        InformationDB(
            "Полная замена антифриза", "50-75т.км/1р. в 4-5 лет", "012", 12
        )
    )
}
