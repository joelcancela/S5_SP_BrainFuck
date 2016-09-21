<h1>BrainFuck</h1>

Le programme exécuté est un tuple, <strong>Cp</strong>, composé de 3 "variables" :
<ol>
	<li><strong>M</strong>, 30 000 cases mémoires indexées de 0 à 29 999 (chacune pouvant stocker un entier de 0 à 255).</li>
 	<li><strong>p</strong>, pointeur sur la case mémoire actuelle.</li>
	<li><strong>i</strong>, instruction (1 caractère) suivante du programme exécuté.</li>
</ol>
<br/>
Il existe 8 opérations :
<ul>
	<li><b>L'incrémentation :</b> Let C<SUB>p</SUB> = (M, p, i); Increment(C<SUB>p</SUB>) = (M', p, i+1); d'<SUB>p</SUB>=d<SUB>p</SUB> + 1<br/>
		 &emsp;La case courante, <em>p</em>, va incrémenter sa valeur, <em>dp</em>, (initialisée à 0) de 1. Suite à cette opération on passe à l'instruction suivante sans changer de case mémoire.
		<table>
			<tr>
				<td>1</td><td>2</td>
			</tr>
			<tr>
				<td colspan="2">3</td>
			</tr>
		</table>
		</li>
	<li><b>La décrémentation :</b> Let C<SUB>p</SUB> = (M, p, i); Decrement(C<SUB>p</SUB>) = (M', p, i+1); d'<SUB>p</SUB>=d<SUB>p</SUB> - 1<br/>
		&emsp;La case courante va décrémenter sa valeur de 1. Suite à cette opération on passe à l'instruction suivante sans changer de case mémoire.</li>
	<li><b>"Left" :</b> Let C<SUB>p</SUB> = (M, p, i); Left(C<SUB>p</SUB>) = (M, p', i+1); p'=p - 1<br/>
		&emsp;Change le case mémoire pointée par p. "Décalle" le pointeur d'une case de M vers la gauche (-1).</li>
	<li><b>"Right" :</b> Let C<SUB>p</SUB> = (M, p, i); Right(C<SUB>p</SUB>) = (M, p', i+1); p'=p + 1<br/>
		&emsp;Change le case mémoire pointée par p. "Décalle" le pointeur d'une case de M vers la droite (+1).</li>
</ul>

	