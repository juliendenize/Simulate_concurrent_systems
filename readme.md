Group:
- DENIZE Julien
- CHAFFARDON Pierre

Copyright (C) 2016-2017
Contact: Denis.Conan[at]telecom-sudparis.eu

License: See file LICENSE.txt

================================================================================

##  But du projet


Il est récurrent en informatique que différents processus partagent des ressources. Cependant, ces ressources doivent être protégés pour que deux processus n'y accèdent pas en même temps. Il apparait des cas où ce blocage des ressources entrainent une situation d'interblocage qui empêche l'exécution des processus.

Suivons cet exemple de programme qui bloque deux cabines puis les relache:
1. Prendre une cabine
2. Prendre une cabine
3. Libérer une cabine
4. Libérer une cabine

Maintenant prenons deux processus P1 et P2 qui exécutent ce programme comme ceci:
1. P1 exécute la ligne 1 // 1 cabine
2. P2 exécute la ligne 1 // 0 cabine
3. P1 bloqué à la ligne 2 // 0 cabine
4. P2 bloqué à la ligne 2 // 0 cabine
=> Interbloquage

Il est donc utile de pouvoir modéliser des programmes et processus pour vérifier qu'il n'y est pas de solution d'interblocage. C'est ce que fait le programme finalement développé.

## Architecture des fichiers
### Code

Dans le dossier `Code/`, toutes les classes permettant de faire fonctionner le programme et de le tester sont présentes.

### Modélisation

Dans le dossier `Modelisation/`, il y a tous les diagrammes de cas d'utilisation, de séquence et de classes ainsi qu'un rapport détaillant la modélisation du projet.

### Suivi

Dans le dossier `Suivi/`, il y a tous les échanges entre le groupe faisant le projet et le professeur, client et expert pour ce projet agile.
