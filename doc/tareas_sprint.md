# SPRINT
**Objetivo**: Desarrollar los componentes JPA y Spring responsables de la implementación del _backend_ REST del proyecto "Receta electrónica"

**Inicio**:  15/11/2023

**Duración**: 4-? semanas (hasta 22/1/2024, día del examen)

**Proyecto de partida:** [GitHub: recetas-23]() (Proyecto Spring Boot de partida, con la jerarquía de entidades incompletas encargada de almacenar los diferentes tipos de usuario de la aplicación) 



## TAREAS SPRINT (organización orientativa, no obligatoria)

### Semana 1 (hasta 22/11/2023)
* Estudio de la descripción del proyecto y las historias de usuario incluidas en el Sprint backlog
* Modelo E-R con las entidades y relaciones necesarias para dar soporte a las historias del Sprint
* Implementar las entidades JPA correspondientes, junto con sus relaciones 


### Semana 2 (hasta 29/11/2023)
* Implementar los DAOs (_data access object_)/Repository de Spring Data JPA necesarios, definiendo las consultas (mediante JPQL en los casos que corresponda) necesarias para dar soporte a las funcionalidaes a usar en las historias de usuario incluidas en el Sprint.
* Implementación de la capa de servicios (`@Service`) para dar soporte a las historias que los necesiten.

### Semana 3 (hasta 13/12/2023)
* Listado de los _endpoint_ REST a implementar y primer esbozo de su implementación con Spring MVC (y/o con la generación automática de _endpoints_ proporcionada por Spring Data REST).

### "Semana" 4 (hasta 22/1/2024, día del examen)
* Implementación del API REST.
* (opcional) Inclusión de tests automáticos (`@SpringBootTest`, `@DataJpaTest`, `@WebMvcTest`)
* (opcional) Adición de autenticación y control de acceso mediante Spring Security y JWT (_JSON Web Token_)
* Redacción del documento entregable



## ENTREGA

* Proyecto en grupos de **3 personas**
* Entrega en MOOVI (hasta 22/1/2024, fecha del examen) 

### Contenidos de los documentos entregables

#### Informe del proyecto

##### Material a entregar (en un único ZIP)

* Carpeta con código fuente del proyecto  (haced antes `mvn clean` para que sólo se incluya el código fuente)
* Memoria del proyecto (ver estructura propuesta)



##### Estructura propuesta para la memoria del proyecto (extensión orientativa:  hasta 7-8 páginas)

**Parte 1: Descripción técnica**
1. Breve descripción general del proyecto
2. Descripción de las funcionalidades cubiertas
3. Descripción de los elementos que conforman la implementación realizada:
    - entidades JPA (acompañadas de modelo ER)
    - repositorios/DAOs, servicios
    - "controllers" REST de Spring MVC
      - **Importante:** detallar los EndPoints REST y las operaciones implementandas sobre ellos
    - otros detalles de configuración relevantes
4. Instrucciones de compilación y uso (si fueran necesarias)
5. Detalle del reparto de tareas entre los miembros del grupo

**Parte 2: Revisión crítica de la arquitectura del proyecto Spring**
* Se trata de realizar una revisión de la arquitectura de la aplicación y de las cuestiones de diseño que pudieran ser incorrectas o mejorables en la solución propuesta (duplicidad de código, mejor uso de herencia, ''sobreingeniería'' provocada por el uso de Spring, etc), así como las impresiones personales sobre las soluciones adoptadas.
* El formato es libre, como orientación se pueden aborar los siguientes puntos:
   * Conclusiones, problemas y/o mejoras respecto a la arquitectura general del proyectoy de la idoneidad del empleo de las tecnologías de Spring.
   * Conclusiones, problemas y/o mejoras respecto al diseño del modelo de datos (entidades JPA) propuesto [aspectos poco claros, mejoras en la modelización, relaciones supérfluas, diseños alternativos, etc]
   * Conclusiones, problemas y/o mejoras respecto al diseño de la capa de lógica [limitaciones y mejoras de la arquitectura empleada, ideas para la mejora de la organziación y reutilización del código, dificultades de las tecnologías empleadas, etc]
   * Conclusiones, problemas y/o mejoras respecto al diseño de la capa REST [limitaciones y mejoras de la arquitectura empleada, ideas para la mejora de la organziación y reutilización del código, dificultades de las tecnologías empleadas, etc]
   * Conclusiones generales de la práctica y de la tecnologías empleadas
