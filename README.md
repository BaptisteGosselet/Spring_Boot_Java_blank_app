# Spring Blank App

# Objectif

L'objectif de ce projet est de créer une application permettant un démarrage rapide d'un nouveau développement.

Où les configurations sont prêtes à l'emploi (WebService, Postgres, Swagger).

Avec un exemple de WebService CRUD et de ses tests, avec des étudiants (Student), basé sur l'architecture constitutive de Spring : Controller, Service, Repository, Model. Et la mise en place de la pagination et du tri.


# Avant de commencer

Lors d'un nouveau projet, il est nécessaire d'éditer le fichier `application properties` selon ses propres configurations.

D'abord les accès à la base de données (dans ce cas, Postgres) : 
```sh
spring.datasource.url=jdbc:postgresql://localhost:5432/<DATABASE>
spring.datasource.username=<USERNAME>
spring.datasource.password=<PASSWORD>
```

Puis, l'initialisation de la base de données au lancement du projet :
```sh
data.sql + spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always
```

Ces lignes permettent l'exécution du script contenu dans `data.sql` à chaque démarrage de l'application.
Il suffit de les commenter ou de les supprimer pour ne plus faire cette initialisation.

# Démarrer

La commande de lancement de l'application est celle-ci : 
```sh
mvn spring-boot:run 
```

Une page Swagger avec tous les endpoints est disponible à l'adresse : `http://localhost:8080/swagger-ui/index.html` (adapter le port).

Enfin, l'exécution des tests se fait par la commande : 
```
mvn test
```