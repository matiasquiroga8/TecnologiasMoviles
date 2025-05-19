Aplicación móvil desarrollada en Jetpack Compose que conecta a consumidores con productores locales de alimentos y productos artesanales. Permite explorar productores por categoría, buscar por nombre o rubro, y acceder a sus productos detallados.

Características:
Exploración por categorías: Mermeladas, Plantas (hierbas y especias), Lácteos, Verduras, entre otros.

Buscador inteligente: Autocompletado con sugerencias dinámicas.

Favoritos: Marcar y gestionar productores y productos favoritos.

Navegación fluida: Detalles de productores y productos con Jetpack Navigation.

Diseño moderno: Basado en Material 3 (Material You).

Tecnologías utilizadas:
Lenguaje: Kotlin

UI: Jetpack Compose

Navegación: Jetpack Navigation Compose

Gestión de estado: ViewModel + State

Persistencia: MutableStateList (simulación de favoritos)

Recursos: Imágenes de productos desde Pexels

Estructura del proyecto

├── data/
│   └── FakeData.kt          # Datos simulados de productores y productos
├── model/
│   ├── Productor.kt         # Modelo de productor
│   └── Producto.kt          # Modelo de producto
├── navigation/
│   ├── AppNavigation.kt     # Navegación principal
│   └── AppScreens.kt        # Definición de rutas
├── screens/
│   ├── HomeScreen.kt        # Pantalla principal
│   ├── ProductorDetailScreen.kt  # Detalle de productor
│   └── Screen.kt            # Componente base
├── ui/
│   └── TopBar.kt            # Barra superior con buscador
├── viewmodel/
│   ├── ProductoViewModel.kt # Lógica de productos favoritos
│   └── ProductorViewModel.kt# Lógica de productores favoritos
└── MainActivity.kt          # Punto de entrada de la aplicación
