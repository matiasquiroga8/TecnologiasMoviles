package com.example.proyectotecnomovil.data

import com.example.proyectotecnomovil.R
import com.example.proyectotecnomovil.model.Producto
import com.example.proyectotecnomovil.model.Productor

object FakeData {
    // Datos de prueba
    val jabones = listOf(
        Producto(
            "Jabón de Lavanda",
            "https://images.pexels.com/photos/7500295/pexels-photo-7500295.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            850.0,
            "Relajante, hecho a mano."
        ),
        Producto(
            "Jabón de Romero",
            "https://images.pexels.com/photos/29160138/pexels-photo-29160138/free-photo-of-jabon-limpio.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            800.0,
            "Revitalizante con aceite esencial."
        ),
        Producto(
            "Jabón de Menta",
            "https://images.pexels.com/photos/14017553/pexels-photo-14017553.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            820.0,
            "Refrescante con extracto natural de menta."
        ),
        Producto(
            "Jabón de Avena",
            "https://images.pexels.com/photos/15225396/pexels-photo-15225396/free-photo-of-madera-cuchara-mesa-semillas.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            870.0,
            "Exfoliante suave ideal para piel sensible."
        ),
        Producto(
            "Bálsamo de Calendula",
            "https://images.pexels.com/photos/932587/pexels-photo-932587.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            950.0,
            "Calma irritaciones y enrojecimiento."
        ),
        Producto(
            "Jabón de Coco",
            "https://images.pexels.com/photos/6187542/pexels-photo-6187542.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            890.0,
            "Hidratante con aceite de coco virgen."
        ),
        Producto(
            "Jabón de Carbón Activado",
            "https://images.pexels.com/photos/7604360/pexels-photo-7604360.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            910.0,
            "Purificante para pieles grasas y con acné."
        )
    )

    val textiles = listOf(
        Producto(
            "Bufanda de lana",
            "https://images.pexels.com/photos/3605077/pexels-photo-3605077.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            2500.0,
            "Tejido artesanal en telar."
        ),
        Producto(
            "Gorro andino",
            "https://images.pexels.com/photos/27054108/pexels-photo-27054108/free-photo-of-llamalandia.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            1800.0,
            "Hecho a mano con motivos del norte."
        ),
        Producto(
            "Poncho Andino",
            "https://images.pexels.com/photos/27167270/pexels-photo-27167270/free-photo-of-mujeres-peruanas-con-vestimenta-tradicional-en-ollantaytambo-cusco.jpeg?auto=compress&cs=tinysrgb&w=600",
            4800.0,
            "Poncho artesanal tejido en telar rústico."
        ),
        Producto(
            "Camino de Mesa",
            "https://images.pexels.com/photos/23784975/pexels-photo-23784975/free-photo-of-madera-flores-petalos-mesa.jpeg?auto=compress&cs=tinysrgb&w=600",
            2200.0,
            "Decoración de mesa con motivos andinos."
        ),
        Producto(
            "Bolso de Lana",
            "https://images.pexels.com/photos/5603259/pexels-photo-5603259.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            3300.0,
            "Tejido resistente y colorido."
        ),
        Producto(
            "Chal de Alpaca",
            "https://images.pexels.com/photos/19032080/pexels-photo-19032080/free-photo-of-granja-peludo-mullido-fotografia-de-animales.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            3900.0,
            "Suave y cálido para noches frescas."
        ),
        Producto(
            "Almohadón Decorativo",
            "https://media.istockphoto.com/id/1161177015/es/foto/moderna-sala-de-estar-en-estilo-natural-y-bot%C3%A1nico.jpg?b=1&s=612x612&w=0&k=20&c=kVuj3WdwodRRIjFhnz_9ymAhauGoh2VLVIlNsCZvH90=",
            2000.0,
            "Diseño étnico, ideal para el living."
        )
    )

    val alimentosOrganicos = listOf(
        Producto(
            "Almohaditas de Avellana",
            "https://images.pexels.com/photos/8161428/pexels-photo-8161428.jpeg",
            500.0,
            "Cereal Integral."
        ),
        Producto(
            "Frutos Secos",
            "https://images.pexels.com/photos/1295572/pexels-photo-1295572.jpeg?auto=compress&cs=tinysrgb&w=600",
            1500.0,
            "Frutos encontrados en nuestro monte nativo."
        ),
        Producto(
            "Miel Orgánica",
            "https://images.pexels.com/photos/8105621/pexels-photo-8105621.jpeg?auto=compress&cs=tinysrgb&w=600",
            1200.0,
            "Miel pura sin aditivos, cosechada de manera artesanal."
        ),
        Producto(
            "Mix de Semillas",
            "https://images.pexels.com/photos/5966153/pexels-photo-5966153.jpeg?auto=compress&cs=tinysrgb&w=600",
            950.0,
            "Chía, lino y girasol tostado. Ideal para ensaladas y yogur."
        ),
        Producto(
            "Granola Casera",
            "https://images.pexels.com/photos/1495534/pexels-photo-1495534.jpeg?auto=compress&cs=tinysrgb&w=600",
            850.0,
            "Mezcla saludable con avena, nueces y miel."
        ),
        Producto(
            "Pan Integral de Centeno",
            "https://images.pexels.com/photos/7656568/pexels-photo-7656568.jpeg?auto=compress&cs=tinysrgb&w=600",
            650.0,
            "Pan artesanal con masa madre y semillas."
        ),
        Producto(
            "Galletas de Algarroba",
            "https://images.pexels.com/photos/32084506/pexels-photo-32084506/free-photo-of-galletas-de-palomitas-de-maiz-con-caramelo-gourmet-y-llovizna-de-toffee.jpeg?auto=compress&cs=tinysrgb&w=600",
            700.0,
            "Dulces naturales sin azúcar refinada, ricas en fibra."
        )
    )

    val productores = listOf(
        Productor("LauraTejidos", "Textiles", textiles, "", R.drawable.textiles),
        Productor("EcoJabones", "Cosmética natural", jabones, "", R.drawable.jabones),
        Productor("CampoVivo", "Alimentos orgánicos", alimentosOrganicos, "", R.drawable.alimento_organico)
        /*
    Productor("Artesanía del Norte", "Cerámica artesanal", listOf(), "", R.drawable.ceramica),
    Productor("Dulzuras del Valle", "Conservas y dulces caseros", listOf(), "", R.drawable.dulces),
    Productor("Monte Nativo", "Hierbas y especias", listOf(), "", R.drawable.hierbas),
    Productor("Sabores Andinos", "Quesos y lácteos artesanales", listOf(), "", R.drawable.quesos),
    Productor("Tierra Verde", "Verduras agroecológicas", listOf(), "", R.drawable.verduras)*/
    )
}