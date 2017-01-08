<h1>BrainFuck</h1>

<h2>Le programme exécuté est un tuple, <strong>Cp</strong>, composé de 3 "variables" :</h2>
<ol>
	<li><strong>M</strong>, 30 000 cases mémoires indexées de 0 à 29 999 (chacune pouvant stocker un entier de 0 à 255).</li>
 	<li><strong>p</strong>, pointeur sur la case mémoire actuelle.</li>
	<li><strong>i</strong>, instruction (1 caractère) suivante du programme exécuté.</li>
</ol>
<br/>
<h2>Il existe 8 opérations :</h2>
<ul>
	<li><b>L'incrémentation :</b> Let C<SUB>p</SUB> = (M, p, i); Increment(C<SUB>p</SUB>) = (M', p, i+1); d'<SUB>p</SUB>=d<SUB>p</SUB> + 1<br/>
		 &emsp;La case courante, <em>p</em>, va incrémenter sa valeur, <em>dp</em>, (initialisée à 0) de 1. Suite à cette opération on passe à l'instruction suivante sans changer de case mémoire.
		<table>
			<tr>
				<td>[&rarr;0,0,0,0,0,0]</td><td>0</td><td>+</td>
			</tr>
			<tr>
				<td colspan="3"><b>Instruction : +</b></td>
			</tr>
			<tr>
				<td>[&rarr;1,0,0,0,0,0]</td><td>0</td><td><em>...</em></td>
			</tr>
		</table>
		</li>
	<li><b>La décrémentation :</b> Let C<SUB>p</SUB> = (M, p, i); Decrement(C<SUB>p</SUB>) = (M', p, i+1); d'<SUB>p</SUB>=d<SUB>p</SUB> - 1<br/>
		&emsp;La case courante va décrémenter sa valeur de 1. Suite à cette opération on passe à l'instruction suivante sans changer de case mémoire.
		<table>
			<tr>
				<td>[&rarr;3,0,0,0,0,0]</td><td>0</td><td>-</td>
			</tr>
			<tr>
				<td colspan="3"><b>Instruction : -</b></td>
			</tr>
			<tr>
				<td>[&rarr;2,0,0,0,0,0]</td><td>0</td><td><em>...</em></td>
			</tr>
		</table>
		</li>
	<li><b>"Left" :</b> Let C<SUB>p</SUB> = (M, p, i); Left(C<SUB>p</SUB>) = (M, p', i+1); p'=p - 1<br/>
		&emsp;Change le case mémoire pointée par p. "Décalle" le pointeur d'une case de M vers la gauche (-1).
		<table>
			<tr>
				<td>[0,&rarr;0,0,0,0,0]</td><td>1</td><td><</td>
			</tr>
			<tr>
				<td colspan="3"><b>Instruction : <</b></td>
			</tr>
			<tr>
				<td>[&rarr;0,0,0,0,0,0]</td><td>0</td><td><em>...</em></td>
			</tr>
		</table>
		</li>
	<li><b>"Right" :</b> Let C<SUB>p</SUB> = (M, p, i); Right(C<SUB>p</SUB>) = (M, p', i+1); p'=p + 1<br/>
		&emsp;Change le case mémoire pointée par p. "Décalle" le pointeur d'une case de M vers la droite (+1).
		<table>
			<tr>
				<td>[0,&rarr;0,0,0,0,0]</td><td>1</td><td>></td>
			</tr>
			<tr>
				<td colspan="3"><b>Instruction : ></b></td>
			</tr>
			<tr>
				<td>[0,0,&rarr;0,0,0,0]</td><td>2</td><td><em>...</em></td>
			</tr>
		</table>
		</li>
	<li><b>"Out" :</b> Let C<SUB>p</SUB> = (M, p, i); Out(C<SUB>p</SUB>) = (M, p, i+1) = ^ out &larr; d<SUB>p</SUB><br/>
		&emsp;Affiche la valeur du code ASCII contenue dans la case mémoire courante, <em>p</em>.
		<table>
			<tr>
				<td>[0,&rarr;97,0,0,0,0]</td><td>1</td><td>.</td>
			</tr>
			<tr>
				<td colspan="3"><b>Instruction : .</b><br/>La machine affiche "<em>a</em>".</td>
			</tr>
			<tr>
				<td>[0,&rarr;97,0,0,0,0]</td><td>1</td><td><em>...</em></td>
			</tr>
		</table>
		</li>
	<li><b>"In" :</b> Let C<SUB>p</SUB> = (M, p, i) ^ Read(in) = v; In(C<SUB>p</SUB>) = (M', p, i); d'<SUB>p</SUB>=v<br/>
		&emsp;Lit une valeur entrée et la stocke dans la case mémoire courante, <em>p</em>.
		<table>
			<tr>
				<td>[0,&rarr;97,0,0,0,0]</td><td>1</td><td>,</td>
			</tr>
			<tr>
				<td colspan="3"><b>Instruction : ,</b><br/>Entre "<em>22</em>".</td>
			</tr>
			<tr>
				<td>[0,&rarr;22,0,0,0,0]</td><td>1</td><td><em>...</em></td>
			</tr>
		</table>
		</li>
	<li><b>"Jump to" :</b> Let C<SUB>p</SUB> = (M, p, i); 
		<table border="0">
			<tr><td rowspan="2">Jumpto(C<SUB>p</SUB>) = </td><td>d<SUB>p</SUB> = 0 &rArr; (M, p, i' + 1) ^ <em>bound</em>(i, i')</td></tr>
			<tr><td>d<SUB>p</SUB> &ne; 0 &rArr;  (M, p, i' + 1)</td></tr>
		</table><br/>
		&emsp;Controle si la case mémoire courante, <em>p</em>, vaut 0. Dans le cas où p vaut 0 avance jusqu'à l'instruction "]" (back to), sinon continue normalement.
	</li>
	<li><b>"Back to" :</b> Let C<SUB>p</SUB> = (M, p, i); 
		<table border="0">
			<tr><td rowspan="2">Backto(C<SUB>p</SUB>) = </td><td>d<SUB>p</SUB> = 0 &rArr; (M, p, i' + 1)</td></tr>
			<tr><td>d<SUB>p</SUB> &ne; 0 &rArr; (M, p, i' + 1) ^ <em>bound</em>(i', i)</td></tr>
		</table><br/>
		&emsp;Controle si la case mémoire courante, <em>p</em>, vaut 0. Dans le cas où p vaut 0 continue normalement, sinon recule jusqu'à l'instruction "[" (jump to), .
	</li>
</ul>

<br/>
<h2>Exemple de programme en <em>BrainFuck</em> :</h2>
<table>
	<tr><td>++++++++++</td><td><em>Donne la valeur 10 à la case mémoire qui va stocker le nombre d'itérations</em></td></tr>
	<tr><td>[</td><td><em>Boucle initiale qui affecte des valeurs utiles au tableau</em></td></tr>
	<tr><td>>+++++++>++++++++++>+++>+<<<<-</td><td><em>Ajoute 7 à la case suivante p+1, 10 à la case p+2, 3 à p+3, 1 à p+4, puis décrémente le nombre d'itérations de 1 (jusqu'à ce que d<SUB>p</SUB> = 0)</em></td></tr>
	<tr><td>]</td><td></td></tr>
	<tr><td></td><td><em>A la sortie de la boucle le tableau contient :</em></td></tr>
	<tr><td>>++.</td><td>'H'    	<em>= 72  (70  + 2)</em></td></tr>
	<tr><td>>+.</td><td>'e'    		<em>= 101 (100 + 1)</em></td></tr>
	<tr><td>+++++++.</td><td>'l'    <em>= 108 (101 + 7)</em></td></tr>
	<tr><td>.</td><td>'l'    		<em>= 108</em></td></tr>
	<tr><td>+++.</td><td>'o'    	<em>= 111 (108 + 3)</em></td></tr>
	<tr><td>>++.</td><td>espace 	<em>= 32  (30  + 2)</em></td></tr>
	<tr><td><<+++++++++++++++.</td><td>'W'    <em>= 87  (72  + 15)</em></td></tr>
	<tr><td>>.</td><td>'o'    		<em>= 111</em></td></tr>
	<tr><td>+++.</td><td>'r'    	<em>= 114 (111 +  3)</em></td></tr>
	<tr><td>------.</td><td>'l'    	<em>= 108 (114 - 6)</em></td></tr>
	<tr><td>--------.</td><td>'d'   <em>= 100 (108 - 8)</em></td></tr>
	<tr><td>>+.</td><td>'!'    		<em>= 33  (32  +  1)</em></td></tr>
	<tr><td>>.</td><td>nouvelle ligne <em>= 10</em></td></tr>
</table>
	