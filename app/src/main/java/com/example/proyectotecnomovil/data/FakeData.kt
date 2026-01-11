package com.example.proyectotecnomovil.data

import com.example.proyectotecnomovil.model.Producto
import com.example.proyectotecnomovil.model.Productor

object FakeData {
    // Datos de prueba
    val jabones = listOf(
        Producto(
            "prod_j1", // <--- NUEVO: ID
            "Jabón de Lavanda",
            "https://images.pexels.com/photos/7500295/pexels-photo-7500295.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            850.0,
            "Relajante, hecho a mano."
        ),
        Producto(
            "prod_j2",
            "Jabón de Romero",
            "https://images.pexels.com/photos/29160138/pexels-photo-29160138/free-photo-of-jabon-limpio.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            800.0,
            "Revitalizante con aceite esencial."
        ),
        Producto(
            "prod_j3",
            "Jabón de Menta",
            "https://images.pexels.com/photos/14017553/pexels-photo-14017553.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            820.0,
            "Refrescante con extracto natural de menta."
        ),
        Producto(
            "prod_j4",
            "Jabón de Avena",
            "https://images.pexels.com/photos/15225396/pexels-photo-15225396/free-photo-of-madera-cuchara-mesa-semillas.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            870.0,
            "Exfoliante suave ideal para piel sensible."
        ),
        Producto(
            "prod_j5",
            "Bálsamo de Calendula",
            "https://images.pexels.com/photos/932587/pexels-photo-932587.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            950.0,
            "Calma irritaciones y enrojecimiento."
        ),
        Producto(
            "prod_j6",
            "Jabón de Coco",
            "https://images.pexels.com/photos/6187542/pexels-photo-6187542.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            890.0,
            "Hidratante con aceite de coco virgen."
        ),
        Producto(
            "prod_j7",
            "Jabón de Carbón Activado",
            "https://images.pexels.com/photos/7604360/pexels-photo-7604360.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            910.0,
            "Purificante para pieles grasas y con acné."
        )
    )

    val textiles = listOf(
        Producto(
            "prod_t1",
            "Bufanda de lana",
            "https://images.pexels.com/photos/3605077/pexels-photo-3605077.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            2500.0,
            "Tejido artesanal en telar."
        ),
        Producto(
            "prod_t2",
            "Gorro andino",
            "https://images.pexels.com/photos/27054108/pexels-photo-27054108/free-photo-of-llamalandia.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            1800.0,
            "Hecho a mano con motivos del norte."
        ),
        Producto(
            "prod_t3",
            "Poncho Andino",
            "https://images.pexels.com/photos/27167270/pexels-photo-27167270/free-photo-of-mujeres-peruanas-con-vestimenta-tradicional-en-ollantaytambo-cusco.jpeg?auto=compress&cs=tinysrgb&w=600",
            4800.0,
            "Poncho artesanal tejido en telar rústico."
        ),
        Producto(
            "prod_t4",
            "Camino de Mesa",
            "https://images.pexels.com/photos/23784975/pexels-photo-23784975/free-photo-of-madera-flores-petalos-mesa.jpeg?auto=compress&cs=tinysrgb&w=600",
            2200.0,
            "Decoración de mesa con motivos andinos."
        ),
        Producto(
            "prod_t5",
            "Bolso de Lana",
            "https://images.pexels.com/photos/5603259/pexels-photo-5603259.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            3300.0,
            "Tejido resistente y colorido."
        ),
        Producto(
            "prod_t6",
            "Chal de Alpaca",
            "https://images.pexels.com/photos/19032080/pexels-photo-19032080/free-photo-of-granja-peludo-mullido-fotografia-de-animales.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            3900.0,
            "Suave y cálido para noches frescas."
        ),
        Producto(
            "prod_t7",
            "Almohadón Decorativo",
            "https://media.istockphoto.com/id/1161177015/es/foto/moderna-sala-de-estar-en-estilo-natural-y-bot%C3%A1nico.jpg?b=1&s=612x612&w=0&k=20&c=kVuj3WdwodRRIjFhnz_9ymAhauGoh2VLVIlNsCZvH90=",
            2000.0,
            "Diseño étnico, ideal para el living."
        )
    )

    val alimentosOrganicos = listOf(
        Producto(
            "prod_a1",
            "Almohaditas de Avellana",
            "https://images.pexels.com/photos/8161428/pexels-photo-8161428.jpeg",
            500.0,
            "Cereal Integral."
        ),
        Producto(
            "prod_a2",
            "Frutos Secos",
            "https://images.pexels.com/photos/1295572/pexels-photo-1295572.jpeg?auto=compress&cs=tinysrgb&w=600",
            1500.0,
            "Frutos encontrados en nuestro monte nativo."
        ),
        Producto(
            "prod_a3",
            "Miel Orgánica",
            "https://images.pexels.com/photos/8105621/pexels-photo-8105621.jpeg?auto=compress&cs=tinysrgb&w=600",
            1200.0,
            "Miel pura sin aditivos, cosechada de manera artesanal."
        ),
        Producto(
            "prod_a4",
            "Mix de Semillas",
            "https://images.pexels.com/photos/5966153/pexels-photo-5966153.jpeg?auto=compress&cs=tinysrgb&w=600",
            950.0,
            "Chía, lino y girasol tostado. Ideal para ensaladas y yogur."
        ),
        Producto(
            "prod_a5",
            "Granola Casera",
            "https://images.pexels.com/photos/1495534/pexels-photo-1495534.jpeg?auto=compress&cs=tinysrgb&w=600",
            850.0,
            "Mezcla saludable con avena, nueces y miel."
        ),
        Producto(
            "prod_a6",
            "Pan Integral de Centeno",
            "https://images.pexels.com/photos/7656568/pexels-photo-7656568.jpeg?auto=compress&cs=tinysrgb&w=600",
            650.0,
            "Pan artesanal con masa madre y semillas."
        ),
        Producto(
            "prod_a7",
            "Galletas de Algarroba",
            "https://images.pexels.com/photos/32084506/pexels-photo-32084506/free-photo-of-galletas-de-palomitas-de-maiz-con-caramelo-gourmet-y-llovizna-de-toffee.jpeg?auto=compress&cs=tinysrgb&w=600",
            700.0,
            "Dulces naturales sin azúcar refinada, ricas en fibra."
        )
    )

    val mermeladas = listOf(
        Producto(
            "prod_m1",
            "Mermelada de Frutilla",
            "https://images.pexels.com/photos/8108024/pexels-photo-8108024.jpeg?auto=compress&cs=tinysrgb&w=600",
            500.0,
            "Dulce artesanal con fruta natural y sin conservantes."
        ),
        Producto(
            "prod_m2",
            "Mermelada de Naranja",
            "https://images.pexels.com/photos/6588616/pexels-photo-6588616.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            480.0,
            "Hecha con naranjas frescas, ideal para desayunos."
        ),
        Producto(
            "prod_m3",
            "Mermelada de Durazno",
            "https://images.pexels.com/photos/18515152/pexels-photo-18515152/free-photo-of-fruta-organico-maduro-frescura.jpeg?auto=compress&cs=tinysrgb&w=600",
            520.0,
            "Sabor suave y textura cremosa, sin aditivos."
        ),
        Producto(
            "prod_m4",
            "Mermelada de Frambuesa",
            "https://images.pexels.com/photos/5590958/pexels-photo-5590958.jpeg?auto=compress&cs=tinysrgb&w=600",
            550.0,
            "Intenso sabor a frambuesas de cosecha orgánica."
        ),
        Producto(
            "prod_m5",
            "Mermelada de Ciruela",
            "https://images.pexels.com/photos/162851/plums-ripe-healthy-food-162851.jpeg?auto=compress&cs=tinysrgb&w=600",
            490.0,
            "Con trozos de fruta y sin azúcar agregada."
        ),
        Producto(
            "prod_m6",
            "Mermelada Light",
            "https://images.pexels.com/photos/2942336/pexels-photo-2942336.jpeg?auto=compress&cs=tinysrgb&w=600",
            600.0,
            "Opción reducida en calorías, apta para diabéticos."
        ),
        Producto(
            "prod_m7",
            "Mermelada Mixta",
            "https://images.pexels.com/photos/4162851/pexels-photo-4162851.jpeg?auto=compress&cs=tinysrgb&w=600",
            530.0,
            "Combinación de frutos rojos para un sabor único."
        )
    )

    val lacteos = listOf(
        Producto(
            "prod_l1",
            "Leche Orgánica",
            "https://images.pexels.com/photos/5946724/pexels-photo-5946724.jpeg?auto=compress&cs=tinysrgb&w=600",
            180.0,
            "Leche fresca de vacas alimentadas naturalmente."
        ),
        Producto(
            "prod_l2",
            "Yogur Natural",
            "https://images.pexels.com/photos/13466256/pexels-photo-13466256.jpeg?auto=compress&cs=tinysrgb&w=600",
            200.0,
            "Con probióticos, sin azúcar agregada."
        ),
        Producto(
            "prod_l3",
            "Queso Cremoso",
            "https://images.pexels.com/photos/306801/pexels-photo-306801.jpeg?auto=compress&cs=tinysrgb&w=600",
            450.0,
            "Ideal para sandwiches y pizzas."
        ),
        Producto(
            "prod_l4",
            "Dulce de Leche",
            "https://images.pexels.com/photos/28302503/pexels-photo-28302503.jpeg?auto=compress&cs=tinysrgb&w=600",
            370.0,
            "Clásico argentino, elaborado artesanalmente."
        ),
        Producto(
            "prod_l5",
            "Manteca Casera",
            "https://images.pexels.com/photos/7965943/pexels-photo-7965943.jpeg?auto=compress&cs=tinysrgb&w=600",
            320.0,
            "De sabor suave, perfecta para untar."
        ),
        Producto(
            "prod_l6",
            "Queso de Cabra",
            "https://images.pexels.com/photos/9946103/pexels-photo-9946103.jpeg?auto=compress&cs=tinysrgb&w=600",
            520.0,
            "Textura firme, sabor intenso y natural."
        ),
        Producto(
            "prod_l7",
            "Ricota Artesanal",
            "https://media.istockphoto.com/id/683358816/es/foto/reques%C3%B3n-fresco-en-placa.jpg?b=1&s=612x612&w=0&k=20&c=CS0ANTb_I5zrna2-DbDklG6ePRCY8q6Lm_-FDN3XFgM=",
            340.0,
            "Ligera y fresca, ideal para preparaciones dulces o saladas."
        )
    )

    val verduras = listOf(
        Producto(
            "prod_v1",
            "Zanahoria Fresca",
            "https://images.pexels.com/photos/32123553/pexels-photo-32123553.jpeg?auto=compress&cs=tinysrgb&w=600",
            120.0,
            "Cultivo agroecológico, crocante y dulce."
        ),
        Producto(
            "prod_v2",
            "Lechuga Criolla",
            "https://images.pexels.com/photos/116728/pexels-photo-116728.jpeg?auto=compress&cs=tinysrgb&w=600",
            100.0,
            "Hojas verdes frescas, lavadas y listas para consumir."
        ),
        Producto(
            "prod_v3",
            "Tomate Redondo",
            "https://images.pexels.com/photos/400958/pexels-photo-400958.jpeg?auto=compress&cs=tinysrgb&w=600",
            160.0,
            "Tomates maduros y jugosos, sin agroquímicos."
        ),
        Producto(
            "prod_v4",
            "Cebolla Morada",
            "https://images.pexels.com/photos/8859780/pexels-photo-8859780.jpeg?auto=compress&cs=tinysrgb&w=600",
            130.0,
            "Sabor intenso, ideal para ensaladas."
        ),
        Producto(
            "prod_v5",
            "Acelga",
            "https://images.pexels.com/photos/5738769/pexels-photo-5738769.jpeg?auto=compress&cs=tinysrgb&w=600",
            150.0,
            "Hojas grandes y frescas, para tortillas y guisos."
        ),
        Producto(
            "prod_v6",
            "Zpallo Anquito",
            "https://images.pexels.com/photos/4110301/pexels-photo-4110301.jpeg?auto=compress&cs=tinysrgb&w=600",
            140.0,
            "Cultivado sin pesticidas, ideal para hervir o saltear."
        ),
        Producto(
            "prod_v7",
            "Espinaca",
            "https://images.pexels.com/photos/8852027/pexels-photo-8852027.jpeg?auto=compress&cs=tinysrgb&w=600",
            135.0,
            "Fuente de hierro y fibra, perfecta para pastas."
        )
    )

    val plantas = listOf(
        Producto(
            "prod_p1",
            "Romero Seco",
            "https://i.pinimg.com/736x/d1/8a/40/d18a409c31f1221c374b1a3478150236.jpg",
            400.0,
            "Hierba aromática ideal para carnes, papas y panificados. Cultivado orgánicamente."
        ),
        Producto(
            "prod_p2",
            "Orégano Andino",
            "https://i.pinimg.com/736x/98/68/c9/9868c9ed3d2f89e73b48d86f777c667b.jpg",
            350.0,
            "Intenso sabor tradicional, cosechado a mano en las sierras."
        ),
        Producto(
            "prod_p3",
            "Tomillo Natural",
            "https://i.pinimg.com/736x/22/60/07/226007cce79d7f8eeff2fbf13bf3570c.jpg",
            420.0,
            "Tomillo seco artesanal, excelente para guisos y asados."
        ),
        Producto(
            "prod_p4",
            "Albahaca Deshidratada",
            "https://i.pinimg.com/736x/a4/aa/e6/a4aae690cae23ecae6e1422310beee04.jpg",
            370.0,
            "Ideal para pizzas, salsas y pestos. Aroma intenso y fresco."
        ),
        Producto(
            "prod_p5",
            "Ají Molido",
            "https://i.pinimg.com/736x/19/c2/43/19c2437a63624df16a99c563d5c70b54.jpg",
            390.0,
            "Picante artesanal sin aditivos, perfecto para empanadas y guisos."
        ),
        Producto(
            "prod_p6",
            "Laurel de Montaña",
            "https://i.pinimg.com/736x/3f/2d/c8/3f2dc8c7d0df048d709ce8346beabd07.jpg",
            280.0,
            "Hojas secas de laurel recolectadas de manera sustentable."
        ),
        Producto(
            "prod_p7",
            "Cilantro en Semilla",
            "https://i.pinimg.com/736x/31/1d/ed/311dedb6bcc4eafe433dee21c4782706.jpg",
            430.0,
            "Semillas enteras de cilantro para moler o usar en infusiones y platos orientales."
        )
    )

    // NOTA: Aquí también actualicé los Productores con ID (primer campo)
    // y URL de imagen (último campo). He puesto URLs vacías "" por ahora.
    val productores = listOf(
        Productor("1", "Laura Tejidos", "Textiles", textiles, ""),
        Productor("2", "Eco Jabones", "Cosmética natural", jabones, ""),
        Productor("3", "Campo Vivo", "Alimentos orgánicos", alimentosOrganicos, ""),
        Productor("4", "Dulzuras del Valle", "Conservas y dulces caseros", mermeladas, ""),
        Productor("5", "Sabores Andinos", "Quesos y lácteos artesanales", lacteos, ""),
        Productor("6", "Tierra Verde", "Verduras agroecológicas", verduras, ""),
        Productor("7", "Monte Nativo", "Hierbas y especias", plantas, "")
    )
}