# CRUD Produits — API REST Spring Boot

Projet personnel réalisé pour monter en compétences sur **Java / Spring Boot**, en complément d'une expérience existante en développement backend avec Node.js/Express. L'objectif était de construire, en partant de zéro, une API REST CRUD complète avec une architecture en couches propre et les bonnes pratiques associées (séparation des responsabilités, DTO, validation, gestion des codes de statut HTTP).

## Stack technique

- **Java 25**
- **Spring Boot 4.1** (Spring Web, Spring Data JPA, Bean Validation)
- **Hibernate** (implémentation JPA)
- **PostgreSQL**
- **Maven**

## Fonctionnalités

API de gestion d'un catalogue de produits (`Product`), avec les 5 opérations CRUD standards :

| Méthode | Endpoint | Description | Codes de statut |
|---|---|---|---|
| `GET` | `/api/products` | Liste tous les produits | `200` |
| `GET` | `/api/products/{id}` | Récupère un produit par son id | `200` / `404` |
| `POST` | `/api/products` | Crée un nouveau produit | `201` / `400` |
| `PUT` | `/api/products/{id}` | Met à jour un produit existant | `200` / `404` / `400` |
| `DELETE` | `/api/products/{id}` | Supprime un produit | `204` / `404` |

### Modèle `Product`

| Champ | Type | Contraintes |
|---|---|---|
| `id` | `Long` | Généré automatiquement (clé primaire) |
| `name` | `String` | Obligatoire |
| `description` | `String` | Obligatoire |
| `price` | `BigDecimal` | Obligatoire, doit être positif |

La validation des données entrantes (`POST`/`PUT`) est faite via **Bean Validation** (`@NotBlank`, `@NotNull`, `@Positive`) — toute requête invalide renvoie un `400 Bad Request` détaillant les champs en erreur.

## Architecture

Le projet suit une architecture en couches classique :

```
Controller → Service → Repository → Base de données
                ↑
              DTO (contrat d'entrée/sortie de l'API)
```

- **Controller** (`controllers/`) : expose les routes HTTP, délègue à la couche métier, gère les codes de statut.
- **Service** (`services/`) : logique métier, orchestration des appels au Repository, conversion DTO ↔ Entité.
- **Repository** (`repositories/`) : accès aux données via Spring Data JPA (`JpaRepository`).
- **Model** (`models/`) : entité JPA mappée à la table PostgreSQL.
- **DTO** (`dto/`) : objets de transfert utilisés en entrée d'API, indépendants de la structure interne de la base.

```
src/main/java/com/mathieu/crud/
├── controllers/
├── services/
├── repositories/
├── models/
└── dto/
```

## Lancer le projet en local

### Prérequis

- Java 25
- Maven (ou le wrapper `./mvnw` fourni)
- PostgreSQL installé et lancé

### 1. Créer la base de données

```bash
sudo -u postgres psql
```
```sql
CREATE DATABASE crud_db;
CREATE USER crud_user WITH PASSWORD 'votre_mot_de_passe';
ALTER DATABASE crud_db OWNER TO crud_user;
```

### 2. Configurer les variables d'environnement

La connexion à la base est paramétrée via deux variables d'environnement, `DB_USERNAME` et `DB_PASSWORD` (lues dans `src/main/resources/application.properties`), afin de ne jamais committer d'identifiants en clair.

En local avec VS Code, elles sont définies dans `.vscode/launch.json` (non versionné) :

```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Spring Boot-CrudApplication",
            "request": "launch",
            "mainClass": "com.mathieu.crud.CrudApplication",
            "env": {
                "DB_USERNAME": "crud_user",
                "DB_PASSWORD": "votre_mot_de_passe"
            },
            "projectName": "crud"
        }
    ]
}
```

Pour un lancement en ligne de commande, exportez-les dans votre shell avant de démarrer l'application :

```bash
export DB_USERNAME=crud_user
export DB_PASSWORD=votre_mot_de_passe
```

### 3. Lancer l'application

```bash
./mvnw spring-boot:run
```

L'API est alors disponible sur `http://localhost:8080/api/products`.

## Exemple de requêtes

```bash
# Créer un produit
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name": "Chaise", "description": "Chaise en bois", "price": 49.99}'

# Lister les produits
curl http://localhost:8080/api/products

# Récupérer un produit par id
curl http://localhost:8080/api/products/1

# Mettre à jour un produit
curl -X PUT http://localhost:8080/api/products/1 \
  -H "Content-Type: application/json" \
  -d '{"name": "Chaise design", "description": "Chaise en bois massif", "price": 59.99}'

# Supprimer un produit
curl -X DELETE http://localhost:8080/api/products/1
```
