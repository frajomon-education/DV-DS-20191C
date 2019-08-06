# Clima
App de consulta de clima para final de diseño de sistemas

Utiliza Java 1.8 + Spring Boot para el backend, y Angular 8 para el frontend. 
La api utilizada para consultar el clima es provista por OpenWeatherMap.

Para levantar el proyecto completo:
 #Asegurarse de tener el JAVA_HOME apuntando al jdk 1.8 y de poder usar Maven desde consola.
 #Dentro de clima-api abrir un cmd y ejecutar mvn spring-boot:run. Con eso la app compila y levanta.
 #Alternativa sin el plugin de spring-boot-mvn-build: mvn clean package && java -jar target/clima-0.0.1.jar
 
El frontend ya esta compilado e incluido dentro de resources/static (js y css) y dentro de resouces/templates (index.html) -todo dentro de clima-api-, por lo que no es necesario compilarlo. 

Para levantar el front aparte:
 #Asegurarse de tener node + npm y angular-cli instalados y de poder accederlos por consola.
 #Dentro de clima-front ejecutar npm install. Con esto se instalan todas las dependencias del front declaradas en el package.json, solo se   ejecuta una vez.
 #Ejecutar ng-serve. Con esto la app levanta.

Para compilar el front e incluirlo en api:
 #Dentro de clima-front ejecutar npm build --prod.
 #Se crea la carpeta dist/clima. Copiar el index.html y pegarlo dentro de clima-api en resources/templates, y copiar todo el resto de los   js y css y pegarlos en resources/static.
