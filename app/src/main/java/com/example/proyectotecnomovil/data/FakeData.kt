package com.example.proyectotecnomovil.data

object FakeData {
    val jabones = listOf(
        Producto(
            "Jabón de Lavanda",
            "https://example.com/lavanda.jpg",
            850.0,
            "Relajante, hecho a mano."
        ),
        Producto(
            "Jabón de Romero",
            "https://example.com/romero.jpg",
            800.0,
            "Revitalizante con aceite esencial."
        )
    )

    val textiles = listOf(
        Producto(
            "Bufanda de lana",
            "https://example.com/bufanda.jpg",
            2500.0,
            "Tejido artesanal en telar."
        ),
        Producto(
            "Gorro andino",
            "https://example.com/gorro.jpg",
            1800.0,
            "Hecho a mano con motivos del norte."
        )
    )

    val listaDeProductores = listOf(
        Productor("Eco Jabones", "Cosmética Natural", jabones, "https://example.com/jabones.jpg"),
        Productor("Laura Tejidos", "Textiles", textiles, "https://example.com/laura.jpg")
    )