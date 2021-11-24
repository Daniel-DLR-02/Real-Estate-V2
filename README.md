# Real estate V.2 | Daniel de Luna Rodríguez
___


Este proyecto consiste en la creación de una API rest hecha en Java que tiene como finalidad la administración de inmuebles.Esta version contiene la implementación de Spring Security.

### Inicio

Para iniciar este programa hay que ejercutarlo desde el mismo ide , en el caso de IntellIJ hay que darle al botón superior de 'add configuration' y en la pestaña de 'Command line' 'Spring-boot:run' para así poder ejecutarlo.


## Entidades
___

Las entidades que conformán esta API son las siguientes:

### Inmobiliaria

Esta cuenta con una lista de vivienda. Las inmobiliarias pueden ser listadas,registrada,borrada,editada o asociadas.

Los atributos de esta son los siguientes:

- Long id
- String nombre
- String email
- String telefono
- List<Vivienda> viviendas
- List<Usuario> gestores
  
  
### Vivienda
  
Esta cuenta con una lista de intereses, que son la asociación con interesado, un propietario y una inmobiliaria asociada. Las inmobiliarias pueden ser listadas,registrada,borrada,editada o asociadas.
  
Los atributos de este son:
- Long id
- String titulo
- String descripcion
- String avatar
- String lating
- String direccion
- String codigoPostal
- String poblacion
- String provincia
- String tipo
- double precio
- int numHabitaciones
- double metrosCuadrados
- numBanios
- tienePiscina
- tieneAscensor
- boolean tieneGaraje
- Usuario propietario
- Inmobiliaria inmobiliaria
- List<Interesa> interesa
 
### Usuario
  
Esta entidad cuenta con tres roles distintos: Propietario, ADMIN o gestor.
  
Atributos: 
  #### Persona
  
  - Long id
  - String nombre
  - String apellidos
  - String direccion
  - String email
  - String telefono
  - String avatar
  - UsuarioRole role
  - Inmobiliaria inmobiliaria
  
  
 Entre Usuario y Vivienda, tenemos esta entidad encargada de añadir atributos extra a la asociacón de interés, estos atributos extras son:

- LocalDate createdDate
- String mensaje

  
  Este proyecto contiene una colección de Postman que puede ser importada para probar los endpoints.

   


