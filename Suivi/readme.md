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
- [] diagramme de classes
    - [] merci de le mettre un peu plus gros
    - [] il manque des multiplicités
    - [] il manque des noms d'association
    - [] ne faudrait-il pas une autre association entre SimInt et ÉtatGlobal ?
      	 actuellement, vous n'avez accès qu'au premier état global ; comment
	 ferez-vous par exemple pour indiquer l'état global à partir duquel vous
	 avancer ?
- [] diagrammes de séquence
    - [] « créer un sémaphore » : comment un entier peut-il être « null » ?
      	 + il manque la flêche de l'appel chercherSémaphore
    - [] « créer un programme » : ok
    - [] « ajouter une instruction... » : seul les appels « create », c.-à-d.
      	 les appels des constructeur sont stéréotypés (retirer les chevrons
	 des autres appels) + il manque des flêches sur des appels + à quoi
	 sert compterNbInstruction + l'appel compterNbInstruction ne débute
	 qu'après la réception de l'appel ajouterinstruction + un caractère
	 « : » en trop dans le nom de l'objet de type Programme + il manque
	 dans la précondition quelque chose contraignant aux valeurs P ou V
	 pour les types d'instructions, non ?
    - [] « avancer... » : indice = on « part » d'un état global pour « arriver »
      	 dans un nouvel état global et on manipule les états des processus et
	 des sémaphores
---
