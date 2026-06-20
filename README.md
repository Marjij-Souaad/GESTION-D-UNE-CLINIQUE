# Gestion d'une Clinique

> Application de bureau développée dans le cadre du module **Développement Java IHM** — ENSAO GI3

---

## Description

Application JavaFX/MySQL permettant la gestion administrative d'une clinique :
gestion des patients et planification des rendez-vous, avec une interface
graphique intuitive et une base de données relationnelle.

---

##  Fonctionnalités

-  **Authentification** — accès sécurisé via page de connexion
-  **Gestion des patients** — ajout, modification, suppression, affichage
-  **Gestion des rendez-vous** — planification avec niveau d'urgence et statut
-  **Navigation dynamique** — chargement des vues via FXMLLoader

---

##  Technologies utilisées

| Technologie | Version |
|-------------|---------|
| Java | 17 |
| JavaFX | SDK 17 |
| MySQL | 8.0 |
| JDBC Connector | 9.7.0 |
| VS Code | latest |


---

## Structure du projet
GESTION-D-UNE-CLINIQUE/
|-- bin/
|-- demonstration/
|   |-- Rapport_IHM.pdf
|   └-- demo.mp4
|-- lib/
|   └-- mysql-connector-j-9.7.0.jar
|-- src/
|   |-- application/
|   |   |-- LoginController.java
|   |   └-- Main.java
|   |-- controller/
|   |   |-- DashboardController.java
|   |   |-- MainController.java
|   |   |-- PatientController.java
|   |   └-- RendezVousController.java
|   |-- css/
|   |   └-- style.css
|   |-- dao/
|   |   |-- Database.java
|   |   |-- PatientDAO.java
|   |   └-- RendezVousDAO.java
|   |-- model/
|   |   |-- Patient.java
|   |   └-- RendezVous.java
|   └-- view/
|       |-- DashboardView.fxml
|       |-- LoginView.fxml
|       |-- MainView.fxml
|       |-- patient.fxml
|       └-- rendezvous.fxml
└-- schema.sql
---

##  Prérequis

- JDK 17
- JavaFX SDK 17
- MySQL sur port 3308
- VS Code avec extension Java

---

## Installation

**1. Cloner le dépôt**
```bash
git clone https://github.com/Marjij-Souaad/GESTION-D-UNE-CLINIQUE.git
```

**2. Importer la base de données**
```sql
mysql -u root -p < schema.sql
```

**3. Configurer la connexion** dans `dao/Database.java`
```java
private static final String URL = "jdbc:mysql://localhost:3308/gestion_clinique";
private static final String USER = "root";
private static final String PASSWORD = "root";
```

**4. Lancer l'application**
Ouvrir `src/application/Main.java` dans VS Code et cliquer sur **Run Java**

---

## Démonstration

 [Voir la vidéo de démonstration](demonstration/demo.mp4) ou https://drive.google.com/file/d/1f0l1xhxu_tpjauNMvzu9e1eWU7oJXNds/view?usp=sharing

 [Voir le rapport PDF](demonstration/Rapport_IHM.pdf)

---

## Auteurs

| Nom | Rôle |
|-----|------|
| **Souaad Marjij** | Base de données, DAO, Modèles|
| **Rayhane Mokhtari** | Interface JavaFX, Contrôleurs, CSS|

---

##  Encadrante

**Mme Douae EL HILA** — ENSAO, Université Mohammed Premier

---

##  Contexte académique

- **Établissement :** École Nationale des Sciences Appliquées d'Oujda
- **Filière :** Génie Informatique — GI3
- **Module :** Développement Java IHM
- **Année académique :** 2025/2026
