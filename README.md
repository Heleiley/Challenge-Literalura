# 📚 Literalura

Literalura es una aplicación de consola desarrollada en Java con Spring Boot. Permite buscar libros desde el catálogo público de [Gutendex](https://gutendex.com/), almacenarlos en una base de datos y visualizar estadísticas por idioma o autor.

---

## ⚙️ Funcionalidades

- 🔍 Búsqueda de libros por título (vía API Gutendex)
- 🗂️ Listado de libros buscados
- 🌐 Filtro por idioma
- 📥 Persistencia en base de datos
- 👤 Listado de autores
- 🧓 Consulta de autores vivos en un año determinado
- 📊 Estadísticas por idioma

---

## 💻 Cómo ejecutar

1. Cloná el repositorio:

```bash
git clone https://github.com/tu-usuario/literalura.git
cd literalura
```
## Configurá tu base de datos en src/main/resources/application.properties

```
spring.datasource.url=jdbc:mysql://localhost:3306/literalura
spring.datasource.username=root
spring.datasource.password=tu_contraseña
```
## Ejecutá el proyecto:

```
./mvnw spring-boot:run
```


