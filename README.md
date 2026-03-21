# Commandes OSGI pour Apache Felix

| Commande | Description | Exemple d'usage | 
|-----|-------------|--------------| 
| lb| **L**iste **B**undles, Listez tous les bundles installés et leur état  | lb
| install <url> | Installer un bundle partir d'un jar |  install file:C:/Users/IA91/Desktop/djafri/plugins/IHMConvertisseur_1.0.0.jar
| start <id> | Démarrez un bundle spécifique | start 15
| update <id> | Mettre à jour un bundle  | update 23 file:C:/Users/IA91/Desktop/djafri/plugins/BibliothequeExt_1.0.0.jar
| uninstall <id> | Supprime un bundle du framework | uninstall 9
| stop <id> | Arrrete un bundle (passage à l'état résolved) | stop 15



## Prérequis

Avoir le dossier felix https://felix.apache.org/documentation/downloads.html

Avoir une version java dans ces "PATH" en tant que variable d'envrionnement

verification : 

```bash
java -version
```

### Lancement D'apache Felix

Ouvrez un terminal puis déplacez-vous dans le dossier felix et exécutez la commande 


```bash
java -jar .\bin\felix.jar
```

exemple 
```bash
C:\Users\IA91\Desktop\org.apache.felix.main.distribution-7.0.5> java -jar .\bin\felix.jar
```