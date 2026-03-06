📱 Proyecto Tecnologías Móviles - App de Productores y Consumidores
Esta es una aplicación nativa de Android desarrollada en Kotlin utilizando Jetpack Compose. Su objetivo principal es conectar a consumidores con productores locales (de alimentos orgánicos, textiles), permitiendo visualizar catálogos, guardar favoritos y gestionar notificaciones.

El proyecto implementa prácticas modernas de desarrollo Android, arquitectura MVVM y persistencia de datos local y remota.

🚀 Características Principales
🔐 Autenticación y Seguridad
Login y Registro: Integración con Firebase Authentication para gestión de usuarios.

Autenticación Biométrica: Capa de seguridad adicional utilizando huella dactilar o reconocimiento facial para acceder a la app (BiometricAuthScreen).

Persistencia de Sesión: Gestión inteligente del estado del usuario.

🛒 Gestión de Productos y Productores
Catálogo: Visualización de listas de productores y sus productos asociados.

Detalle: Pantallas detalladas para cada producto y perfil de productor.

Favoritos: Funcionalidad para marcar productos y productores como favoritos, guardados localmente para acceso rápido.

⚙️ Funcionalidades del Sistema
Notificaciones: Sistema de notificaciones programadas utilizando WorkManager (NotificationWorker), configurables desde la pantalla de ajustes.

Base de Datos Local: Uso de Room Database para almacenar notificaciones y favoritos de manera persistente en el dispositivo.

Configuración: Pantalla de ajustes (SettingsScreen) para personalizar la experiencia del usuario (frecuencia de notificaciones, categorías, etc.).

🛠️ Stack Tecnológico
El proyecto utiliza las últimas librerías y herramientas recomendadas por Google:

Lenguaje: Kotlin (100%)

UI Toolkit: Jetpack Compose (Material Design 3)

Arquitectura: MVVM (Model-View-ViewModel)

Inyección de Dependencias: Integración manual mediante ViewModels y Repositorios.

Librerías Clave:
Navegación: androidx.navigation:navigation-compose

Red (Networking): Retrofit + GSON (para consumo de APIs REST).

Base de Datos Local: Room (SQLite abstraction).

Backend / Auth: Firebase (Authentication & Firestore).

Carga de Imágenes: Coil (Carga asíncrona de imágenes).

Tareas en Segundo Plano: WorkManager.

Biometría: androidx.biometric.

Concurrencia: Kotlin Coroutines.

📂 Estructura del Proyecto
El código está organizado siguiendo la arquitectura MVVM para asegurar la escalabilidad y mantenibilidad:

com.example.proyectotecnomovil
├── components/        # Componentes UI reutilizables (Ej: FechaNacimiento)
├── data/
│   ├── local/         # Base de datos Room, DAOs, Entidades y SettingsManager
│   ├── network/       # Configuración de Retrofit y Endpoints de API
│   ├── repository/    # Repositorios (fuente única de verdad de datos)
│   └── FakeData.kt    # Datos de prueba para desarrollo
├── model/             # Modelos de datos (Producto, Productor, User)
├── navigation/        # Grafo de navegación y definición de rutas
├── screens/           # Pantallas Composable (Home, Login, Profile, etc.)
├── services/          # Servicios lógicos (Auth, Mapas)
├── utils/             # Utilidades generales (Schedulers)
├── viewmodel/         # StateHolders para las pantallas (Lógica de negocio)
├── workers/           # Workers para tareas en segundo plano
├── MainActivity.kt    # Punto de entrada principal
└── AuthActivity.kt    # Actividad de gestión de autenticación
🔧 Configuración e Instalación
Para ejecutar este proyecto en tu entorno local:

Clonar el repositorio:

git clone <URL_DEL_REPOSITORIO>
Abrir en Android Studio:
Asegúrate de tener la última versión estable de Android Studio (Ladybug o superior recomendado).

Configurar Firebase:

Este proyecto requiere un archivo google-services.json.

Debes crear un proyecto en la consola de Firebase.

Descarga tu propio google-services.json y colócalo en la carpeta app/.

Sincronizar Gradle:
Deja que Android Studio descargue todas las dependencias.

Ejecutar:
Conecta un dispositivo físico o utiliza un emulador.

Nota: Para probar la autenticación biométrica en el emulador, asegúrate de configurar una huella en la configuración de seguridad del dispositivo virtual.

✅ Permisos Requeridos
La aplicación solicita los siguientes permisos en el AndroidManifest.xml:

INTERNET: Para conectar con la API y Firebase.

ACCESS_NETWORK_STATE: Para verificar la conectividad.

POST_NOTIFICATIONS: Para mostrar notificaciones en Android 13+.

USE_BIOMETRIC: Para el inicio de sesión seguro.

👥 Autores
Matías Quiroga - Desarrollador Principal

Tomás - Desarrollador / Colaborador

Este proyecto fue desarrollado como parte de la materia Tecnologías Móviles.

https://github.com/user-attachments/assets/dc634019-0c2f-4825-bd50-853767463de6
