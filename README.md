# Proyecto de Automatización QA con Selenium y TestNG

Este proyecto es una automatización de pruebas para la tienda en línea OpenCart utilizando **Java, Selenium, TestNG y Maven**.

## 📌 **Requisitos previos**
Antes de ejecutar el proyecto, se debe asegurar de tener instalados:

- **Java 17 o superior** ([Descargar](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html))
- **Maven** ([Descargar](https://maven.apache.org/download.cgi))
- **Git** ([Descargar](https://git-scm.com/downloads))
- **Google Chrome** y su **Chromedriver** ([Descargar](https://sites.google.com/chromium.org/driver/))

### 📌 **Configuración del proyecto**
1. **Clona el repositorio**  
   ```bash
   git clone https://github.com/JAIVERAGREDA/pruebasAutomatizacionQA.git
   

##############################################################
Compila el proyecto con Maven

bash
Copiar
Editar
mvn clean install

###############################################################
Ejecuta las pruebas
Para ejecutar todas las pruebas:
mvn clean test
Para ejecutar una prueba específica (Ejemplo: BuscarProductoTest):

mvn clean test -Dtest=BuscarProductoTest
Generar y visualizar reportes de pruebas

Para ejecutar por cada clase ejemplo
mvn clean test -Dtest=BuscarProductoTest > logs/registro.log 2>&1

##############################################################
Tecnologías utilizadas
Java 17
Selenium WebDriver
TestNG
Maven

