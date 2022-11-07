### API - Challenge Tenpo

# Readme

- Api para calcular el porcentaje dado de la suma de dos valores y sumarlo al  resultado total.


### Tecnologias

- JAVA 11
- Spring Boot V2.7.0
- Spring fox swagger V2.9.2
- spring cache
- spring security
- retry
- posgrest V8.0.24
- JPA
- kafka
-Junit + Mockito
- gradle 


### Docker Composer
Tecnologias

    -kafka:2.8.1
    -zookeeper:3.4.13
    -postgres:14.1-alpine 

se encuentra en la raiz del proyecto un docker-composer levantar con el comand.

    docker compose up

#Swagger

se podra encontrar los endpoints del servicio con mas documentacion y models.
URL : http://localhost:8080/swagger-ui.html#/

##### ` user-rest-controller` 
    POST /api/v1/user/sign-up  este endpoint es para poder crear usuarios dado el request.
    NOTA: el email debe ser unico.

    POST /api/v1/user/login login este endpoint es para poder generar un token JWT dado el email y 
                            password de un usuario existente.
    NOTA: el Token tiene tiempo de expiracion y es necesario para poder realizar cualquier request 
    del controller sum-rest-controller
.

##### `sum-rest-controller` (previa autenticacion)
    POST /api/v1/tenpo/sum  el cual recibe dos valores, los cuales los suma y calcula el porcentaje 
    segun la api Call que realiza al servicio externo, luego el resultado del porcentaje es sumado 
    a la suma inicial.

    GET /api/v1/tenpo/sum/history Muestra el historico Paginado, con todos los movimientos sus request,response
    y posibles errores.

NOTA: El token devuelto en el endpoint /login copiarlo y pegarlo en el swagger en la
pestaña que dice ¨authorized¨ para poder hacer los request y que viajen en el header.

#### Dependency
    API Externa:  http://www.randomnumberapi.com/api/v1.0/random?min=1&max=100&count=1 El cual 
    nos devuelve el porcentage para poder hacer el calculo.

##### PD: Tomar en cuenta a la hora de usar el servicio.

    - El llamado a la API Externa se hace cada 30 min por ende esta en Cache se hace el Flush
    a traves de un CRON, y se guarda en el historico.
    - Se manejan los Retry con spring retry, intenta tres veces en la llamada al API Externa y si no realiza el calculo 
    con el ultimo porcentaje devuelto segun el historico el cual tambien cuenta con una cache, por si el servicio se 
    mantiene mucho tiempo caido no sature nuestra bd.
    -Se utiliza kafka para manejar en manera de cola todo lo que va a la tabla de historico depende de las peticiones 
    puede existir un tiempo de procesamiento.
    
    











