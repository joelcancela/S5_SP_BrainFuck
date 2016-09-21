<h1>BrainFuck</h1>

Le programme exécuté est un tuple, <strong>Cp</strong>, composé de 3 "variables" :
<ol>
	<li>M, 30 000 cases mémoires indexées de 0 à 29 999 (chacune pouvant stocker un entier de 0 à 255).</li>
 	<li>p, pointeur sur la case mémoire actuelle.</li>
	<li>i, instruction (1 caractère) suivante du programme exécuté.</li>
</ol>
<br/>
Il existe 8 opérations :
<ul>
	<li>L'incrémentation : Let Cp = (M, p, i); Increment(Cp) = (M', p, i+1); d'<SUB>p</SUB>=dp + 1<br/>
		La case courante, <em>p</em>, va incrémenter sa valeur, <em>dp</em>, (initialisée à 0) de 1. Suite à cette opération on passe à l'instruction suivante sans changer de case mémoire.
		</li>
	<li>La décrémentation : Let Cp = (M, p, i); Decrement(Cp) = (M', p, i+1); d'p=dp - 1<br/>
		La case courante va décrémenter sa valeur de 1. Suite à cette opération on passe à l'instruction suivante sans changer de case mémoire.</li>
	<li>"Left" : Let Cp = (M, p, i); Left(Cp) = (M', p, i+1); d'p=dp + 1<br/>
		</li>
	<li></li>
</ul>

	