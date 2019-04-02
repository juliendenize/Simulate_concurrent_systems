Ce fichier contient des remarques de suivi sur votre projet. Un
nouveau suivi vous est matérialisé par une nouvelle section datée.

Certaines remarques demandent des actions de votre part, vous les
repérerez par une case à cocher.

- []  Action (à réaliser) 

Merci de nous indiquer en cochant la case quand vous avez pris ces
remarques en compte. N'hésitez pas à écrire dans ce fichier et à nous
exposer votre point de vue.

- [x] Action (réalisée)
    - REPONSE éventuelles remarques de votre part, 

# Suivi du mar. 22 janv. 2019 09:18:51 CET
Denis Conan
- [x] diagramme de cas d'utilisation
    - [x] les diagrammes sont un peu trop petits
    - [x] « modifier l'ordre des instructions d'un programme » : le pluriel
      	 dans la formulation est un indice pour la reformulation en vous
	 posant la question des arguments en entrée ; quelles instructions ? et
	 surtout combien ? et aussi qu'est-ce qui est modifié ?
- [x] préconditions et postconditions
    - vous semblez ne pas être convaincu de l'intérêt de séparer la partie
      « construction » des entités du système de la partie « utilisation »
      du système pour la simulation. On pourra en discuter ultérieurement ;
      cela ne gênera pas la suite a priori
    - [x] « créer un sémaphore » : êtes-vous sûr que la valeur initiale sera
      	 donnée dans un chaîne de caractères ? Ce serait bizarre, non ?
    - [x] « avancer l'exécution... » : la formulation exécution « en cours » est
      	 quelque peu ambigue car l'expression ne me semble pas utilisée dans
	 le cahier des charges. L'adjectif utilisé est plutôt « terminé ».
	 C'est du détail.
    - [x] « établir si le système... » : pourquoi précisez-vous un processus
      	 dans les données en entrée ?
- tables de décision : ok (à adapter lors des changements dans les préconditions)

Merci pour votre retour rapide, nous aimerions discuter avec vous lors de la prochaine séance de la séparation de la construction des entités et de l'utilisation du système pour la simulation.

---

# Suivi du ven. 01 févr. 2019 18:29:06 CET
Denis Conan
- [x] diagramme de classes
    - [x] merci de le mettre un peu plus gros
    - [x] il manque des multiplicités
    - [x] il manque des noms d'association
    - [x] ne faudrait-il pas une autre association entre SimInt et ÉtatGlobal ?
      	 actuellement, vous n'avez accès qu'au premier état global ; comment
	 ferez-vous par exemple pour indiquer l'état global à partir duquel vous
	 avancer ?
- [x] diagrammes de séquence
    - [x] « créer un sémaphore » : comment un entier peut-il être « null » ?
      	 + il manque la flêche de l'appel chercherSémaphore
    - [x] « créer un programme » : ok
    - [x] « ajouter une instruction... » : seul les appels « create », c.-à-d.
      	 les appels des constructeur sont stéréotypés (retirer les chevrons
	 des autres appels) + il manque des flêches sur des appels + à quoi
	 sert compterNbInstruction + l'appel compterNbInstruction ne débute
	 qu'après la réception de l'appel ajouterinstruction + un caractère
	 « : » en trop dans le nom de l'objet de type Programme + il manque
	 dans la précondition quelque chose contraignant aux valeurs P ou V
	 pour les types d'instructions, non ?
    - [x] « avancer... » : indice = on « part » d'un état global pour « arriver »
      	 dans un nouvel état global et on manipule les états des processus et
	 des sémaphores
---

# Suivi du mar. 12 févr. 2019 09:34:11 CET
Denis Conan
- [] diagrammes de séquence
    - [x] « ajouter une instruction... » : en programmant, vous devriez vous
      	 apercevoir qu'il faudra créer des objets de types différents selon
	 la valeur de instruction
    - [x] « créer un processus » : petite incohérence « nom programme » puis
         « nomProgramme »
    - [x] « avancer... » : le message récursif chercherÉtatProcessus dans
      	 ÉtatGlobal peut être ignoré (détail)
	 + entre le fragment ref « copie... » et le message avancerExecution
	   l'objet n'est pas activé et il n'y a donc pas de barre d'activation
	 + de manière générale, les barres d'activation débute à la réception
	   des messages
	 + pourquoi chercherÉtatProcessus puis chercherÉtatProcessusCopié ?
	 + pourquoi chercherÉtatProcessusCopié fait autre chose (chercher
	   Prochain..., etc.) ?
	 + je ne pense pas que l'exécution d'une instruction soit effectué
	   sur le programme ; et d'ailleurs, d'où « prog » ?
	 + etc.
    - [x] « établir... » : pb notation participant avec deux « : »
         + pb notation chevrons
	 + etc.
- fiche des classes
    - [x] ÉtatProcessus : pb type attribut état (revoir le cours sur les types
         énuméré ; ce sont des objets)
- invariant
    - ÉtatProcessus : idem pb type attribut -> condition sur état à revoir
- tables de décision des tests unitaires
    - [x] revoir le cours car par exemple le « test » de l'invariant n'est pas
         dans les préconditions
---

Pour les invariants et en particuliers les tests unitaires, on aimerait bien en discuter demain afin de mieux comprendre.

# Suivi du mar. 19 févr. 2019 10:01:03 CET
Denis Conan
- ok pour discussion sur les invariants et les tests unitaires ;
  voir aussi mes commentaires ci-dessous
- [x] code
    - [x] dans la mesure du possible, faites en sorte que le code compile
         toujours en utilisant des commentaires, etc.
    - [x] ÉtatProcessus : je suggère que le commentaire de l'attribut « état »
         soit reformulé pour être compréhensible par rapport au nom de la
	 classe
    - [x] ne mettez des « getters » et des « setters » que lorsqu'ils sont
         nécessaire, c'est-à-dire lorsqu'un cas d'utilisation le demande
    - [x] Instruction : l'utilisation de plusieurs assert est bizarre ; il me
         semble que vous confondez un peu les postconditions avec les
	 préconditions (les assert dans le constructeur sont présents pour
	 vérifier que la programmation est « correcte » ; ce sont donc des
	 éléments de la postcondition qui se « testent » avec des
	 Assert.assertEquals... dans les tests unitaires JUnit ; c'est le
	 sujet du cours de la séance 6)
    - [x] Processus : je ne crois pas que l'attribut « programme » entre dans
      	 le calcul de equals
    - [x] SimInt::avancerExecutionProcessus : la programmation est différente
         du diagramme de séquence ; l'alignement vaut le coût pour bien fixé
	 les correspondances entre les concepts de modélisation et ceux de
	 programmation et pour bien fixé la méthodologie afin d'être
	 autonome lors du second sprint
    - [x] ÉtatGlobal::avancerExecutionProcessus : je ne comprends pas les
      	 lignes 219—220 avec valeurCompteur ==
	 instruction.getSemaphore().getValeurInitiale()
	 car conceptuellement on peut toujours faire un V sur un sémaphore
- [x] diagramme de séquence « avancer... »
    - [x] le début ne correspond pas au code : chercherProcessus, etc.
    - [x] il manque le participant au dessus du fragment ref de copie et de
         la barre d'activation ; on en discute en séance car si mes souvenirs
	 sont bons, vous m'avez posé une question en séance et ma réponse
	 n'était pas pertinente
    - [x] quel est le participant sur lequel est fait l'appel du fragment
         ref d'exécution de l'instruction ?
	 c'est la même question qu'au précédent commentaire
- j'ai uniquement parcouru un peu votre code ; bonne continuation avec les
  tests qui permettront de valider la solution programmée
---
------------------------------------
lun. 11 mars 2019 13:55:18 CET
Denis Conan

------------------------------------
lun. 11 mars 2019 14:15:14 CET
Denis Conan
# Évaluation du logiciel livré à la fin du Sprint 1
## modélisation du logiciel = A
- Spécification et préparation des tests de validation : 
    - Diagrammes de cas d'utilisation = a
    - 3 tables de décision des tests de validation = a
      (avec les précondition et postcondition)
        - cas d'utilisation « créer un sémaphore » : ok
        - cas d'utilisation « ajouter une instruction à un programme » : ok
        - cas d'utilisation « avancer l'exécution d'un processus d'un pas » :
            - manque la condition « processus non terminé »
- Conception préliminaire :
    - Diagramme de classes = a
    - 3 diagrammes de séquence = a
        - cas d'utilisation « créer un sémaphore » : ok
        - cas d'utilisation « ajouter une instruction à un programme » : ok
            - ne mélanger pas les conditions des fragments avec les messages
        - cas d'utilisation « avancer l'exécution d'un processus d'un pas » : ok
- Conception détaillée et de la préparation des tests unitaires : 
    - Raffinement du diagramme de classes = a
      (avec la fiche de la classe ÉtatProcessus)
    - Invariant de la classe ÉtatProcessus = a
    - 2 tables de décision de tests unitaires = a
## Programmation
- Utilisation des outils de programmation = A
    - Module Maven et tests avec JUnit = a
- Programmation de la solution = A
    - Classes du diagramme de classes avec leurs attributs = a
    - Méthodes des cas d'utilisation de base = a
        - cas d'utilisation « créer un sémaphore » : ok
        - cas d'utilisation « ajouter une instruction à un programme » : ok
        - cas d'utilisation « avancer l'exécution d'un processus d'un pas » : ok
- Cohérence entre le code et le modèle = A
    - Cohérences du code avec le diagramme de classes = a
    - Cohérences du code avec les diagrammes de séquence de base = a
- Programmation et exécution des tests de validation et unitaires = A
    - Tests de validation de 3 cas d'utlisation = a
        - cas d'utilisation « créer un sémaphore » : 
        - cas d'utilisation « ajouter une instruction à un programme » : 
        - cas d'utilisation « avance l'exécution d'un processus d'un pas » : 
    - Tests unitaires de 2 méthodes d'une classe = a
        - constructeur de la classe ÉtatProcessus : ok 
        - méthode avanceDUnPas de la classe ÉtatProcessus :
            - il y a plus d'un cas pour non vivant : terminé et bloqué (2
              fois avancer sur le même sémaphore initialement à 1)

---

Mar. 12 mars 2019
Julien DENIZE, Pierre CHAFFARDON
- Pour la modélisation des nouveaux cas d'utilisations, devons-nous intégrer cette modélisation dans le fichier que nous avions déjà ou devenons créer un nouveau odt ?
  - Pour le moment nous avons juste écrit deux cas d'utilisation issus de l'énoncé du TP mais pas fait de travail dessus
- Devons-nous aussi faire la doc pour les tests, sachant que nous trouvons qu'ils sont assez clairs ?
  - Trouvez-vous que la doc que nous avons réalisé est assez claire ?
- Nous avons changé quelques exceptions de telle sorte que les exceptions sont nommées uniquement lorsque c'est simint qui les produit (ex: ProgrammeExistant) et lorsque les exceptions sont levées dans les constructeurs, nous avons considérer que ce sont des IllegalStateException. Trouvez-vous cela satisfaisant ?

---

# Suivi du mer. 13 mars 2019 12:30:28 CET
Denis Conan
- question modélisation fichier: de quel fichier parlez-vous ? vous devez
  soit mettre à jour votre diagramme de cas d'utilisation soit en créer un
  nouveau pour les cas d'utilisation du sprint ; mettre à jour votre diagramme
  de classes ; mettre à jour vos diagrammes de séquence en cas d'impact et
  créer le/les diag. de séquence pour le/les nouveaux cas d'utilisation, etc.
- doc pour les tests : parlez-vous de la documentation du code avec javadoc ?
  seule la classe EtatGlobal est obligée de passer la vérification de code
  Checkstyle ; la documentation javadoc des tests est sans doute la dernière
  tâche car pas très utile (vous avez les tables de décision correspondantes
  et vous nommez vos méthodes pour faire l'appariement)
- IllegalStateException, comme IllegalArgumentException, sont des
  RuntimeExceptions, donc hors contrôle ; en termes génie logiciel, on les
  utilisent sur cas d'erreur de programmation (tel attribut ne devrait pas
  avoir telle valeur => exception hors contrôle) ; par contre, les exceptions
  sous-contrôle telles que ProgrammeExistant sont des exceptions
  « fonctionnelles » : c'est l'acteur qui est en cause
---

dim. 17 mars 2019

- On a codé les nouveaux cas d'utilisation, cela fonctionne pour le programme 1, 
  cependant lorsque l'on test avec le programme 3, le temps d'exécution est bien trop long.
  
  On est d'abord parti sur une arraylist pour stocker les états globaux
  sur la quelle on itère mais cela semble être mauvais en complexité.
  On se demandait donc si vous aviez un conseil de stockage des états globaux, on a pensé à la Hashmap, est-ce que vous trouvez cela pertinent ?

---

# Suivi du mer. 20 mars 2019 13:22:12 CET
Denis Conan
- temps d'exécution bien trop long => erreur ?
    - [x] EtatGlobal::etablirSystemeEnInterbloquage : à quoi sert terminé ?
         pas sûr que ce soit utile
    - [x] SimInt::valider : pourquoi affectation ligne 284 + affectation ligne
         294 ?
         + pourquoi avancerExecution n'est pas modifiée pour retourner le nouvel
         état global ? et où est ajouté le nouvel état global dans
         etatsGlobauxAtteignables ?
         + le foreach de la ligne 297 est-il utile ?
         + je trouve l'utilisation de l'attribut dernierEtatGlobal très
           dangereuse
         + pourquoi pas addEtatGlobalAtteignable() qui re-calcule la chaîne
         de caractères à chaque demande d'accès => en attribut recalculé
         à chaque fois qu'il y a une modification
         - c'est cela qui posait problème
- en cherchant j'ai modifié votre code ; j'ai laissé ce que j'obtiens
Validation du système: 22852 états globaux différents ont été générés en 15426ms.
interbloquage trouvé dans état: EtatGlobal [#=28442
---

# Suivi du sam. 23 mars 2019 07:29:23 CET
Denis Conan
- [x] réponse question post-it transmise pas Chantal Taconet
    - [x] les fiches des classes servent à transmettre les informations aux
         programmeurs ; dans le module, ces fiches permettent de faire
         pédagogiquement la transition entre la conception et la programmation ;
         aussi, une seule fiche est demandée pour vérifier cette transition, et
         on y met ce qui vient de la conception : donc, pas besoin de mettre
         les méthodes mises en exergue dans la suite du module
    - [x] les stratégies sont à mettre dans le diagramme de classes car il
         faut montrer explicitement que le patron de conception est insérée
         dans la solution
    - [x] modélisation des UC4 et 5 : cf. la liste des tâches ; dire si ce n'est
         pas clair
         http://www-inf.telecom-sudparis.eu/COURS/CSC4102/?page=liste_recapitulative_des_taches
---