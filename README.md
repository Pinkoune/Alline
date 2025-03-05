# Mouse Controller

Une application élégante permettant d'automatiser le mouvement de la souris à intervalles réguliers.


![image](https://github.com/user-attachments/assets/87382d53-67eb-4e88-b8c6-cbcbb3120607)



## 📝 Description

Mouse Controller est une application JavaFX qui déplace automatiquement le curseur de votre souris à intervalle régulier (toutes les 60 secondes).

## ✨ Fonctionnalités

- Interface utilisateur moderne avec thème sombre
- Déplacement automatique du curseur (haut/bas) toutes les 60 secondes
- Contrôles simples avec boutons Démarrer et Arrêter
- Fenêtre sans bordure avec coins arrondis
- Statut visuel de l'état de l'application

## 🚀 Installation

### Prérequis

- Java 11 ou supérieur
- JavaFX (inclus dans le JAR exécutable)

### Téléchargement

Téléchargez la dernière version depuis la section [Releases](https://github.com/Pinkoune/Alline/releases).

### Exécution

Mettre le JAR et le BAT dans le même dossier.
Double-cliquez sur le fichier JAR (ou BAT si le jar ne fonctionne pas) ou exécutez-le en ligne de commande :

```bash
java -jar Alline-1.0.jar
```

## 🛠️ Compilation depuis les sources

Le projet utilise Gradle pour la gestion des dépendances et la compilation.

```bash
# Cloner le dépôt
git clone https://github.com/Pinkoune/Alline.git
cd Alline

# Compiler le projet
./gradlew build

# Créer un JAR exécutable
./gradlew shadowJar

# Le JAR exécutable se trouve dans build/libs/
```

## 📱 Utilisation

1. Lancez l'application
2. Cliquez sur "DÉMARRER" pour activer le mouvement automatique
3. Cliquez sur "ARRÊTER" pour désactiver le mouvement
4. Cliquez sur "X" pour fermer l'application

## 🔧 Technologies utilisées

- Java
- JavaFX pour l'interface graphique
- Gradle pour la gestion du projet
- Shadow Gradle plugin pour la création du JAR exécutable

## 🤝 Contribution

Les contributions sont les bienvenues ! N'hésitez pas à ouvrir une issue ou à soumettre une pull request.

1. Forkez le projet
2. Créez une branche pour votre fonctionnalité (```git checkout -b feature/amazing-feature```)
3. Committez vos changements (```git commit -m 'Add some amazing feature'```)
4. Poussez vers la branche (```git push origin feature/amazing-feature```)
5. Ouvrez une Pull Request
