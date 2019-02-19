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
- [] code
    - [] dans la mesure du possible, faites en sorte que le code compile
         toujours en utilisant des commentaires, etc.
    - [x] ÉtatProcessus : je suggère que le commentaire de l'attribut « état »
         soit reformulé pour être compréhensible par rapport au nom de la
	 classe
    - [] ne mettez des « getters » et des « setters » que lorsqu'ils sont
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
    - [] SimInt::avancerExecutionProcessus : la programmation est différente
         du diagramme de séquence ; l'alignement vaut le coût pour bien fixé
	 les correspondances entre les concepts de modélisation et ceux de
	 programmation et pour bien fixé la méthodologie afin d'être
	 autonome lors du second sprint
    - [x] ÉtatGlobal::avancerExecutionProcessus : je ne comprends pas les
      	 lignes 219—220 avec valeurCompteur ==
	 instruction.getSemaphore().getValeurInitiale()
	 car conceptuellement on peut toujours faire un V sur un sémaphore
- [] diagramme de séquence « avancer... »
    - [] le début ne correspond pas au code : chercherProcessus, etc.
    - [] il manque le participant au dessus du fragment ref de copie et de
         la barre d'activation ; on en discute en séance car si mes souvenirs
	 sont bons, vous m'avez posé une question en séance et ma réponse
	 n'était pas pertinente
    - [] quel est le participant sur lequel est fait l'appel du fragment
         ref d'exécution de l'instruction ?
	 c'est la même question qu'au précédent commentaire
- j'ai uniquement parcouru un peu votre code ; bonne continuation avec les
  tests qui permettront de valider la solution programmée
---
Dans quel cas le code n'a-t-il pas compilé ? Pour les commentaires, souhaitez-vous qu'on commente toutes les fonctions, cela nous paraissait assez claire au niveau des noms des fonctions et variables.

Pour les getter et setter nous avons programmé ceux dont nous avions besoin durant le développement du coup on ne comprend pas lesquels ne sont pas utiles.

Concernant le diagramme de séquence, faut-il vraiment que dans le code soit écrit exactement la même chose ?par exemple pour chercherProcessus() nous avons dans le code if(!processus.contains(key)) ce qui revient au même.

Pour le fragment et la barre de vie effectivement on en avait discuté en séance et nous n'avions pas dû comprendre à ce moment là.