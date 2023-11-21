[TOC]

# HISTORIAS DE USUARIO (Comunes)



## HU-C1. Login de usuarios (pendiente)

### Descripción

Como usuario registrado en la aplicación desde la página principal será posible loguearse en la aplicación mediante un nombre de usuario (login) y una contraseña (password).

### Criterios de aceptación

- Un usuario registrado aportando las credenciales correctas accederá al *home* que corresponda con su *rol* de usuario (ADMINISTRADOR, MÉDICO, PACIENTE, FARMACIA)
- Un usuario que no aporte un nombre de usuario o una contraseña válida volverá a presentársele la página principal para introduzca sus credenciales.

### Detalles

| **Prioridad** | **Estado** | **Sprint** | **Estimación** | **Dedicación** |
| ------------- | ---------- | ---------- | -------------- | -------------- |
| Baja          | Pendiente  | 2          | __ horas       | __ horas       |

---



# HISTORIAS DE USUARIO (Administradores)



## HU-A1. [Administrador]	"Home" de administradores
### Descripción
Como usuario logueado con el *rol* ADMINISTRADOR se tendrá acceso a al *home* de administradores, desde el cual se podrá acceder a las funcionalidades de *Gestión de Administradores*, *Gestión de Centros de Salud*, *Gestión de Médicos*, *Gestión de Pacientes*,  *Gestión de Farmacias*, *Gestión de Citas* y *Gestión de Medicamentos*. También se tendrá una acción *Desconectar* con la que cerrar la sesión. en el sistema con su nombre de usuario y su contraseña

### Criterios de aceptación
* Se mostrará a los usuarios con rol ADMINISTRADOR un menú con las opciones
  - Administradores
  - Centros de salud
  - Médicos
  - Pacientes
  - Farmacias
  - Citas
  - Medicamentos
  - Desconectar
* Desde la opción *Desconectar* se finalizará la sesión, retornando a la página inicial de la aplicación

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|  |     |           |  __ horas      |  __ horas      |

---

## HU-A2. [Administrador]	Gestión de _administradores_
### Descripción
Como usuario _administrador_ logueado se podrá acceder a las funcionalidades básicas de mantenimiento de _administradores_ (altas, bajas, modificaciones, consultas/búsquedas).

Datos a editar de cada _administrador_:
- login (sólo en el caso de usuarios nuevos)
- password (sólo en el caso de usuarios nuevos)
- nombre
- email
- activo [`true`|`false`]

### Criterios de aceptación
* Se mostrará una lista con los _administradores_ actualmente registrados, indicando su datos esenciales (login, nombre del usuario, email, fecha de registro/creación, fecha de último acceso, activo [`true`|`false`])
* Se podrá seleccionar un _administrador_ de esa lista y mediante un botón *Editar* acceder a la edición de los datos del usuario seleccionado. Una vez completada esa edición se actualizará la lista de usuarios.
* Se podrá seleccionar un usuario de esa lista y mediante un botón *Borrar* realizar la eliminación lógica de ese usuario en la aplicación, establiendo el valor de `activo` a `false`. Una vez completada esa edición se actualizará la lista de usuarios.
* Mediante un botón *Nuevo* se accederá a la creación de un nuevo usuario _administrador_, asignándosele manualmente un password inicial. Una vez completada esa edición se actualizará la lista de usuarios.

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|           |     |           |  __ horas      |  __ horas      |

---

## HU-A3. [Administrador]	Gestión de _centros de salud_

### Descripción

Como usuario _administrador_ logueado se podrá acceder a las funcionalidades básicas de mantenimiento de _centros de salud_ (altas, bajas, modificaciones, consultas/búsquedas).

Datos a editar de cada _centro de salud_:
- nombre
- dirección (domicilio, localidad, código postal, provincia)
- teléfono
- e-mail
- activo [`true`|`false`]

### Criterios de aceptación

* Se mostrará una lista con los _centros de salud_ actualmente registrados, indicando su datos esenciales (nombre, localidad, provincia, activo [`true`|`false`]). 
* La lista de _centros de salud_ podrá filtrarse por nombre o por localidad, permitiéndose en todos estos casos búsquedas aproximadas (tipo `LIKE` en SQL)
* Se podrá seleccionar un centro de esa lista y mediante un botón *Editar* acceder a la edición de los datos del _centro de salud_ seleccionado. Una vez completada esa edición se actualizará la lista de _centros de salud_.
* Se podrá seleccionar un centro de esa lista y mediante un botón *Borrar* realizar la eliminación lógica de ese _centro de salud_ en la aplicación, establiendo el valor de `activo` a `false`. Una vez completada esa edición se actualizará la lista de _centros de salud_.
* Mediante un botón *Nuevo* se accederá a la creación de un nuevo  _centro de salud_. Una vez completada esa edición se actualizará la lista de _centros de salud_.

### Detalles

| **Prioridad** | **Estado** | **Sprint** | **Estimación** | **Dedicación** |
| ------------- | ---------- | ---------- | -------------- | -------------- |
|               |            |            | __ horas       | __ horas       |

---
## HU-A4. [Administrador]	Gestión de _médicos_
### Descripción
Como usuario _administrador_ logueado se podrá acceder a las funcionalidades básicas de mantenimiento de _médicos_ (altas [estableciendo como contraseña inicial el nº de colegiado], bajas, modificaciones, consultas/búsquedas).  

Datos a editar de cada _médico_:
- nombre
- apellidos
- dni
- número de colegiado (como String)
- teléfono
- e-mail
- _centro de salud_ al que está asignado
- activo [`true`|`false`]

### Criterios de aceptación
* Se mostrará una lista con los _médicos_ actualmente registrados, indicando su datos esenciales (nombre y apelidos, centro de salud, localidad, provincia, activo [`true`|`false`]). 
* La lista de _médicos_ podrá filtrarse por nombre o por localidad, permitiéndose en todos estos casos búsquedas aproximadas (tipo `LIKE` en SQL). 
  * Esta lista también podrá filtrarse por _centro de salud_ asignado, seleccionando un _centro de salud_ de una lista desplegable con todos los centros registrados.
* Se podrá seleccionar un _médico_ de esa lista y mediante un botón *Editar* acceder a la edición de los datos del _médico_ seleccionado, incluyendo la modificación del _centro de salud_ al que está asignado. Una vez completada esa edición se actualizará la lista de _médicos_.
* Se podrá seleccionar un _médico_ de esa lista y mediante un botón *Borrar* realizar la eliminación lógica de ese usuario en la aplicación, establiendo el valor de `activo` a `false`. Una vez completada esa edición se actualizará la lista de _médicos_.
* Mediante un botón *Nuevo* se accederá a la creación de un nuevo  _médico_, vinculándolo a un _centro de salud_ y asignándosele como password inicial su número de colegiado. Una vez completada esa edición se actualizará la lista de _médicos_.
* La vinculación de _centro de salud_ a un _médico_ se hará seleccionándolo de una lista desplegable mostrando todos los _centros de salud_ registrados.

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|           |     |           |  __ horas      |  __ horas      |

---

## HU-A5. [Administrador]	Gestión de _pacientes_
### Descripción
Como usuario _administrador_ logueado se podrá acceder a las funcionalidades básicas de mantenimiento de _pacientes_ (altas [estableciendo como contraseña inicial su DNI], bajas, modificaciones, consultas/búsquedas).  

Datos a editar de cada _paciente_:
- nombre
- apellidos
- dni
- número de tarjeta sanitaria (como String)
- número de la Seguridad Social (como String)
- dirección (domicilio, localidad, código postal, provincia) 
- teléfono
- e-mail
- fecha de naciemiento
- _centro de salud_ que tiene asignado
- _médico_ que tiene asignado (que a su vez debe estar vinculado al _centro de salud_ anterior)
- activo [`true`|`false`]

### Criterios de aceptación
* Se mostrará una lista con los _pacientes_ actualmente registrados, indicando su datos esenciales (nombre y apelidos, centro de salud, localidad, provincia, activo [`true`|`false`]). 
* La lista de _pacientes_ podrá filtrarse por nombre o por localidad, permitiéndose en todos estos casos búsquedas aproximadas (tipo `LIKE` en SQL). 
* También podrá filtrarse la lista de _pacientes_ por _centro de salud_ asignado (seleccionando un _centro de salud_ de una lista desplegable con todos los centros registrados) y por _médico_ asignado (seleccionando un _médico_ de una lista desplegable con todos los _médicos_ disponibles en el _centro de salud_ indicado).
* Se podrá seleccionar un _paciente_ de esa lista y mediante un botón *Editar* acceder a la edición de los datos del _paciente_ seleccionado, incluyendo la modificación del _centro de salud_ al que está asignado y del _médico_ que corresponde a ese _paciente_. Una vez completada esa edición se actualizará la lista de _médicos_.
* Se podrá seleccionar un _paciente_ de esa lista y mediante un botón *Borrar* realizar la eliminación lógica de ese usuario en la aplicación, establiendo el valor de `activo` a `false`. Una vez completada esa edición se actualizará la lista de _pacientes_.
* Mediante un botón *Nuevo* se accederá a la creación de un nuevo  _paciente_, donde además de introducir sus datos se le asignará un _centro de salud_ y un _médico_ asignado a dicho _centro de salud_. Una vez completada y confirmada esa edición se actualizará la lista de _pacientes_.
* La vinculación de _centro de salud_ a un _paciente_ se hará seleccionándolo de una lista desplegable mostrando los _centros de salud_ de la provincia de residencia del _paciente_.
* La vinculación de _médico_ a un _paciente_ se hará seleccionándolo de una lista desplegable mostrando los _médicos_ asignados al _centro de salud_ del _paciente_.

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|           |     |           |  __ horas      |  __ horas      |

---

## HU-A6. [Administrador]	Gestión de _farmacias_
### Descripción
Como usuario _administrador_ logueado se podrá acceder a las funcionalidades básicas de mantenimiento de _farmacias_ (altas [estableciendo como contraseña inicial el nº de colegiado del farmaceútico], bajas, modificaciones, consultas/búsquedas).

Datos a editar de cada _farmacia_:
- nombre establecimiento
- nombre farmaceútico
- apellidos farmaceútico
- dni/nif
- número de colegiado farmaceútico (como String)
- dirección (domicilio, localidad, código postal, provincia) 
- teléfono
- e-mail
- activo [`true`|`false`]

### Criterios de aceptación
* Se mostrará una lista con las _farmacias_ actualmente registradas, indicando su datos esenciales (nombre establecimiento, localidad, provincia, activo [`true`|`false`]). 
* La lista de _farmacias_ podrá filtrarse por nombre de establecimiento o por localidad, permitiéndose en todos estos casos búsquedas aproximadas (tipo `LIKE` en SQL). 
* Se podrá seleccionar una _farmacia_ de esa lista y mediante un botón *Editar* acceder a la edición de los datos de la _farmacia_ seleccionada. Una vez completada esa edición se actualizará la lista de _farmacias_.
* Se podrá seleccionar una _farmacia_ de esa lista y mediante un botón *Borrar* realizar la eliminación lógica de ese usuario en la aplicación, establiendo el valor de `activo` a `false`. Una vez completada esa edición se actualizará la lista de _farmacias_.
* Mediante un botón *Nueva* se accederá a la creación de una nueva  _farmacia_, asignándose como password inicial el número de colegiado del farmaceútico. Una vez completada esa edición se actualizará la lista de _farmacias_.


### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|           |     |           |  __ horas      |  __ horas      |

---

## HU-A7. [Administrador]	Gestión "manual" de _citas_
### Descripción
Como usuario _administrador_ se podrá acceder y editar la lista de _citas_ en vigor.

Datos disponibles de cada _cita_:
- _paciente_ citado
- _médico_ que atenderá la _cita_
- fecha y hora 
- duración (por defecto 15 min.)
- estado (`PLANIFICADA`, `ANULADA`, `COMPLETADA`, `AUSENTE`)

### Criterios de aceptación
* Se mostrará una lista ordenada por hora de inicio con las _citas_ actualmente registradas, indicando su datos esenciales (_paciente_, _médico_, _centro de salud_, fecha y hora, estado). 
* La lista de _citas_ se deberá limitar por fecha (indicando el día sobre el que se realizará la búsqueda) y podrá filtrarse por _médico_ o por  _paciente_ (seleccionándose ambos de una lista). 
* Se podrá seleccionar una _cita_ de esa lista y mediante un botón *Anular*  modificar el estado de la _cita_, marcándola como `ANULADA`. Una vez completada esa edición se actualizará la lista de _citas_.
* No se contempla, de momento, la creación manual de _citas_ por parte de los _administradores_.

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|           |     |           |  __ horas      |  __ horas      |

---

## HU-A8. [Administrador]	Gestión de _medicamentos_
### Descripción
Como usuario _administrador_ logueado se podrá acceder a las funcionalidades básicas de mantenimiento de _medicamentos_ (altas, bajas, modificaciones, consultas/búsquedas).

Datos a editar de un  _medicamento_:
- nombre comercial (Ejemplo: "Aspirina 500, 20 comprimidos")
- principio activo (Ejemplo: "ácido acetisalicílico")
- fabricante (como String, Ejemplo: "NoEsDeBayer S.A.")
- familia (como String, Ejemplo: "analgésicos")
- número de dosis (Ejemplo: 20) [núm. de dosis en el envase, se asumirá siempre un valor entero]
- activo [`true`|`false`]

### Criterios de aceptación
* Se mostrará una lista con los _medicamentos_ actualmente registrados, indicando su datos esenciales (nombre comercial, principio activo, fabricante, familia). 
* La lista de _medicamentos_ podrá filtrarse por nombre comercial, principio activo, fabricante o famila, permitiéndose en todos estos casos búsquedas aproximadas (tipo `LIKE` en SQL).
* Se podrá seleccionar un _medicamento_ de esa lista y mediante un botón *Editar* acceder a la edición de los datos del _medicamento_ seleccionado. Una vez completada esa edición se actualizará la lista de _medicamentos_.
* Se podrá seleccionar un _medicamento_ de esa lista y mediante un botón *Borrar* realizar la eliminación lógica de ese _medicamento_ en la aplicación, establiendo el valor de `activo` a `false`. Una vez completada esa edición se actualizará la lista de _medicamentos_.
* Mediante un botón *Nuevo* se accederá a la creación de un nuevo  _medicamento_. Una vez completada esa edición se actualizará la lista de _medicamentos_.

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|           |     |           |  __ horas      |  __ horas      |

---


# HISTORIAS DE USUARIO (Médicos)

## HU-M1. [Médico]	"Home" de _médico_
### Descripción
Como usuario logueado con el *rol* MEDICO se tendrá acceso a al *home* de _médicos_, desde el cual se podrá acceder a las funcionalidades de *Visualización de agenda* y *Gestión de perfil*. 
También se tendrá una acción *Desconectar* con la que cerrar la sesión. 

### Criterios de aceptación
* Se mostrará a los usuarios con rol MEDICO un menú con las opciones
  - Mi agenda
  - Mi perfil
  - Desconectar
* Desde la opción *Desconectar* se finalizará la sesión, retornando a la página inicial de la aplicación

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|  |     |           |  __ horas      |  __ horas      |

---

## HU-M2. [Médico]    Visualización de "agenda"
### Descripción
Como usuario _médico_, una vez logueado, desde la opción "Mi agenda" podrá acceder a su agenda de _citas_ para el día en curso. En esta agenda se mostrará la información de las _citas_ previstas para el día actual, con indicación del _paciente_, hora prevista y estado de las mismas (`PLANIFICADA`, `ANULADA`, `COMPLETADA`, `AUSENTE`)
### Criterios de aceptación
* Se mostrará una lista con las _citas_ registradas para el día de hoy, indicando su datos esenciales (nombre de _paciente_, fecha y hora, duración, estado). 
* Seleccionando una de las _citas_ con estado `PLANIFICADA` se accede al formulario de "Atención de _cita_" (ver historia HU-M3).
* Se monstrará un botón "Ausente" para cada _cita_ con estado `PLANFICADA`, mediante el cuál se marcará esa cita como `ASUENTE` en caso de que no se presente el _paciente_. Actualizando el listado de _citas_ de la agenda del _médico_.

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|           |     |           |  __ horas      |  __ horas      |

---

## HU-M3. [Médico]	Gestión de _cita_ actual (atención al paciente)
### Descripción
Como usuario _médico_ desde el formulario de "Atención al paciente", se mostrará la información relevante del _paciente_ de la _cita_ actualmente seleccionada, junto con las _prescripciones_ que tenga en vigor dicho _paciente_, dando opción a eliminar una de esas _prescripciones_ o a crear una nueva.

### Criterios de aceptación
* Se mostrará la información relevante del _paciente_ de la _cita_ actual (nombre y apellidos, fecha de nacimiento, dirección)
* Se mostrará la lista de _prescripciones_ en vigor para el _paciente_ actual (su "fecha de fin" es igual o posteriorla fecha actual). Para cada _prescripción_ se mostrarán los datos del _medicamento_ prescrito, la dosis establecida y las fechas de inicio y fin de la _prescripción_. La lista estará ordena por fecha de inicio de la _prescripción_.
* Para cada _prescripción_ de ese listado se mostrará un boton "Anular" con el que se elimina (1) la correspondiente _prescripción_ (marcándola en estado `INACTIVA`) y (2) la lista de _recetas_ previstas para la misma (marcando cada una de ellas como `ANULADA`). Se actualizará la lista de _prescripciones_ tras estos cambios.
* Se podrá acceder a la opción confeccionar una nueva _prescripción_ para el _paciente_ concreto atendido en la _cita_ actual pasando al formulario "Creación de prescripciones" (historia HU-M5)
* Se mostrará un botón "Cita completada" mediante el cuál, una vez finalizada la _cita_actual_, esta se marca con el estado `COMPLETADA`.

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|           |     |           |  __ horas      |  __ horas      |

---

## HU-M5. [Médico]	Creación de _prescripciones_
### Descripción
Como usuario _médico_, desde el formulario "Creación de prescripciones" se podrá crear una nueva _prescripción_. 
### Criterios de aceptación
* Se dispondrá de un buscador de _medicamentos_ (que soportará como criterios de búsqueda el nombre comercial, principio activo, fabricante o familia), que mostrará los resultados de la búsqueda en forma de tabla. En todos estos casos se realizarán búsquedas aproximadas (tipo `LIKE` en SQL).
* Seleccionando el _medicamento_ a prescribir de esa tabla, se establecerá la _prescripción_ de ese _medicamento_ para el _paciente_ actual, pudiendo indicar la "dosis" y las "indicaciones" para la administración del mismo, junto con el periodo de medicación, con fechas de principio (=fecha actual) y final.
* Al pulsar el botón "Crear prescripción" se registra en la BD una nueva _prescripción_ con la siguiente información:
  - _medicamento_ prescrito
  - _paciente_ al que se le prescribe el _medicamento_
  - _médico_ que ha realizado la _prescripción_
  - dosis diaria (como Double, indicando el número de unidades del _medicamento_ (píldoras, ml, etc) a tomar por día)
  - indicaciones (como String de texto libre, indicando, por ejemplo, nº de tomas por día, toma en ayunas o no, etc)
  - fecha de inicio de la _prescripción_ (que sera la fecha actual)
  - fecha de fin de la _prescripción_
  - estado de la _prescripción_ [`ACTIVO`/`INACTIVO`], con el valor inicial a `ACTIVO`
* Junto con la creación de la _prescripción_ en la BD, al pulsar el botón "Crear prescripción" se registrará en la BD el conjunto de _recetas_ ("plan de recetas") que se emiten para que el _paciente_ vaya recogiendo las "cajas" del _medicamento_ en las fechas correspondientes (ver historia HU-M6)

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|           |     |           |  __ horas      |  __ horas      |

---

## HU-M6. [Médico]	Generación de "planes de _recetas_" para una _prescripción_

### Descripción
El **sistema**, como resultado de la confección de una nueva _prescripción_ por parte de un _médico_, creará de forma automática un "plan de recetas" para el correspondiente _paciente_, que consistirá en una serie de _recetas_ para que, periodicamente, recoja en las _farmacias_ las "cajas" necesarias para completar el peridodo de medicación previsto en cada _prescripción_ concreta.

**Nota:** Este es un procesamiento automático que se ejecutará en el momento de crear una _prescripción_ de un _medicamento_ dado para un determinado _paciente_ por parte de su _médico_ (ver hostoria HU-M5)

### Criterios de aceptación
* Este plan de recetas incluirá la lista de futuras _recetas_ (junto con sus fechas de validez) de "cajas" de los _medicamentos_ que necesitará el paciente en función de la duración de la _prescripción_. 

* Para cada _receta_ se mantendrá la siguiente información:
    - _prescripción_ a la que pertenece la _receta_
    - fecha de validez inicial, a partir de la cúal el _paciente_ puede pasar por una _farmacia_ a recojer "cajas" del _medicamento_
    - fecha de validez final, pasada la cuál no será posible recoger "cajas" del _medicamento_
    - número de unidades del _medicamento_ (= "cajas") a servir por parte de la _farmacia_ donde se presente la _receta_
    - estado de la _receta_ [`PLANIFICADA`, `SERVIDA`, `ANULADA`], inicialmente con valor `PLANIFICADA`
    - _farmacia_ que sirvió la _receta_, inicialmente tendrá valor NULL (se establecerá la relación cuando se se haga efectiva la entrega de las "cajas")

* Para el cálculo del número de _recetas_ planificadas que darán lugar una _prescripción_ (1) se asumirá  que se sirve una única unidad del _medicamento_ (="caja") en cada receta y (2) se tendrá en cuenta la "dosis diaria" recetada en la _prescripción_ y el "número de dosis" del envase en el que se distribuye el _medicamento_. 
* El cálculo de las fechas de validez de las  _recetas_ "programadas” será semanal, incorporando a las "fechas de validez" de cada _receta_ resultante un margen de una semana “antes” (excepto en la primera _receta_) y una semana “después” de las fechas "exactas" calculadas en función del "número de dosis" de la "caja" del medicamento y la duración de la _prescripción_. 

**Nota**: Esta planificación de _recetas_ puede estar sujeta a cambios futuros y hacerse con otras estrategias (minimizando "visitas a las _farmacias_" con varias cajas de _medicamento_ en cada _receta_ en lugar de minimizando "cajas" como el esquema indicado, con márgenes de recogidas más amplios, etc)


### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|           |     |           |  __ horas      |  __ horas      |

---


## HU-M10. [Médico]	Perfil del _médico_
### Descripción
Como usuario _médico_, una vez logueado, desde la opción "Mi perfil" podrá (1) modificar sus credenciales de acceso (contraseña) y (2) actualizar sus datos personales básicos (nombre, apellidos, teléfono, email, etc). En ningún caso podrá modificar las _citas_ de su "agenda" ni su vinculación a un _centro de salud_.

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|           |     |           |  __ horas      |  __ horas      |

---



# HISTORIAS DE USUARIO (Pacientes)

## HU-P1. [Paciente]	"Home" de _paciente_
### Descripción
Como usuario logueado con el *rol* PACIENTE se tendrá acceso a al *home* de _pacientes_, desde el cual se podrá acceder a las funcionalidades de *Visualización de citas*, *Creación de cita*, *Visualización de recetas* y *Gestión de perfil*. 
También se tendrá una acción *Desconectar* con la que cerrar la sesión. en el sistema con su nombre de usuario y su contraseña

### Criterios de aceptación
* Se mostrará a los usuarios con rol PACIENTE un menú con las opciones
  - Mis citas
  - Nueva cita
  - Mis recetas
  - Mi perfil
  - Desconectar
* Desde la opción *Desconectar* se finalizará la sesión, retornando a la página inicial de la aplicación

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|  |     |           |  __ horas      |  __ horas      |

---

## HU-P2. [Paciente]	Visualización de _citas_
### Descripción
Como usuario _paciente_ logueado,  desde la opción "Mis citas", se podrá acceder y visualizar la lista de futuras _citas_ médicas
### Criterios de aceptación
* Se mostrará una lista con las _citas_ registradas para el _paciente_ logueado que tengan el estado `PLANIFICADA`, indicando su datos esenciales (nombre del _médico_, fecha y hora, duración, estado). 
* Con el botón "Anular", se marcará el estado de la _cita_ selecionada como `ANULADA`, actualizando la lista de _citas_ mostrada.

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|           |     |           |  __ horas      |  __ horas      |

---

## HU-P3. [Paciente]	Creación de _cita_
### Descripción
Como usuario _paciente_ logueado, desde la opción "Nueva cita" se accede al formulario de "Creación de cita" donde se podrá confeccionar una _cita_ con el _médico_ que tenga asignado.

### Criterios de aceptación
* El usuario indicará la fecha deseada y el sistema mostrará los huecos disponibles en la agenda del _médico_ asignado al _paciente_ actual para ese día. 
* Todos los huecos se asumen de tamaño fijo (15 minutos), iniciándose las consultas a las 8:30 y terminando a las 15:30 (no se contemplan otros horarios de atención a _pacientes_)
  * **Nota:** Dado que la información almacenada en la Base de Datos es la de las _citas_ para un _médico_ en un día determinado, para confeccionar esta lista de huecos se deberán recorrer los huecos de 15 minutos posibles entre las 8:30 y las 15:30, seleccionado aquellos huecos para los que no exista una _cita_ en estado `PLANIFICADA` con esa hora de inicio
* En el caso de aceptar alguna de las horas propuestas, se pedirá al _paciente_ que confirme la asignación de cita en la fecha y hora indicada.
* Cuando se acepte, para cada _cita_ creada se almacena la siguiente información: 
  - `paciente` que ha solicitado la _cita_
  - `médico` con el cúal se ha concertado la _cita_
  - fecha de la _cita_ (día, mes, año)
  - hora de inicio de la _cita_ (hora, minuto)
  - duración, por defecto 15 minutos
  - estado [`COMPLETADA`, `PLANIFICADA`, `ANULADA`, `AUSENTE`], incialmente con el valor `PLANIFICADA`

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|           |     |           |  __ horas      |  __ horas      |

---

## HU-P4. [Paciente]	Visualización de "planes de _recetas_"
### Descripción
Como usuario _paciente_ logueado con contraseña, desde el formulación de "Visualización de mis recetas" se podrán visualizar la lista de _recetas_ pendientes de recoger y sus fechas de validez.
### Criterios de aceptación
* Se mostrará la lista de _recetas_ pendientes de ser servidas ordenada por fecha, de más próxima a más lejana.
* Para cada _receta_ se mostrará los datos del _medicamento_ de la correspondiente _prescripción_ (principio activo, fabricante), los datos del _médico_ que hizo la _prescripción_ las fechas de validez de cada _receta_ (inical y final) y el número de unidades ("cajas") que se servirán en la _receta_ 

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|           |     |           |  __ horas      |  __ horas      |

---

## HU-P5. [Paciente]	Perfil del _paciente_
### Descripción
Como usuario _paciente_ una vez logueado con contraseña, se podrá (1) modificar las credenciales de acceso (contraseña) y (2) actualizar los datos básicos (nombre, dirección, teléfono, etc). En ningún caso se podrá modificar la asignación de _centro de salud_ y de _médico_.

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|           |     |           |  __ horas      |  __ horas      |

---



# HISTORIAS DE USUARIO (Farmacias)


## HU-F1. [Farmacia]	"Home" de _farmacia_
### Descripción
Como usuario logueado con el *rol* FARMACIA se tendrá acceso al *home* de _farmacias_, desde el cual se podrá acceder a las funcionalidades de *Gestión de receta* y *Gestión de perfil*. 
También se tendrá una acción *Desconectar* con la que cerrar la sesión. en el sistema con su nombre de usuario y su contraseña

### Criterios de aceptación
* Se mostrará a los usuarios con rol FARMACIA un menú con las opciones
  - Gestión de _receta_
  - Mi perfil
  - Desconectar
* Desde la opción *Desconectar* se finalizará la sesión, retornando a la página inicial de la aplicación

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|  |     |           |  __ horas      |  __ horas      |

---

## HU-F2. [Farmacia]	Consulta de _recetas_ de un _paciente_
### Descripción
Como usuario _farmacia_, una vez logueado, desde la opción  "Gestión de receta" se accede a un formulario, donde introduciendo el nº de tarjeta sanitaria de un _paciente_ se accederá a la lista de _recetas_ que este estén en vigor para ese _paciente_, visualizándose las _recetas_ existentes en el sistema disponibles para ser servidas al _paciente_ indicado desde la fecha actual.
### Criterios de aceptación
* Para cada _receta_ con estado `PLANIFICADA` se mostrará su información (medicamento, médico que la generó, fechas de validez) y, en función de sus "fechas de validez" y la fecha actual del sistema, se indicará si la receta puede ser servida o no.
* Con el botón "Anotar _receta_ servida" se selecciona una de las recetas de lista de _recetas_ disponibles para ser servidas (la fecha actual está dentro del periodo de validez de la _receta_) y se anota como servida (ver historia HU-F3)

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|           |     |           |  __ horas      |  __ horas      |

---

## HU-F3. [Farmacia]	Anotación de _recetas_ "servidas"
### Descripción
Como usuario _farmacia_, desde la "lista de recetas" disponibles para ser servidas a un _paciente_ determinado, se podrá seleccionar una _receta_ con estado `PLANIFICADA` y disponible para ser servida (la fecha actual está dentro del periodo de validez de la _receta_) para anotarla como `SERVIDA`. 
### Criterios de aceptación
* En ningún caso se podrá "servir" una _receta_ fuera de sus "fechas de validez". 
* Sobre esta _receta_ servida se marcará su estado como `SERVIDA` y antes de escribirla en la BD se vinculará con la _farmacia_ que la haya servido (la actualmente logueada)

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|           |     |           |  __ horas      |  __ horas      |

---


## HU-F4. [Farmacia]   Perfil de _farmacia_
### Descripción
Como usuario _farmacia_, una vez logueado, podrá (1) modificar sus credenciales de acceso (contraseña) y (2) actualizar los datos básicos (dirección, nombre de la farmacia, etc) de su _farmacia_.

### Detalles
| **Prioridad** |    **Estado**   | **Sprint** | **Estimación** | **Dedicación** |
|---------------|-----------------|------------|----------------|----------------|
|           |     |           |  __ horas      |  __ horas      |

---



