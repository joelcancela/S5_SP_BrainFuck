#include <stdio.h>

int		main(void)
{
	int		c[30000] = {};
	int		p = 0;

	c[p] = c[p] + 10;
while (c[p]){

		p = p + 1;
		c[p] = c[p] + 7;
		p = p + 1;
		c[p] = c[p] + 10;
		p = p + 1;
		c[p] = c[p] + 3;
		p = p + 1;
		c[p] = c[p] + 1;
		p = p - 4;
		c[p] = c[p] - 1;
		}
	p = p + 1;
	c[p] = c[p] + 2;
	putchar(c[p]);
	p = p + 1;
	c[p] = c[p] + 1;
	putchar(c[p]);
	c[p] = c[p] + 7;
	for (int i = 0; i < 2; i++) { putchar(c[p]); }
	c[p] = c[p] + 3;
	putchar(c[p]);
	p = p + 1;
	c[p] = c[p] + 2;
	putchar(c[p]);
	p = p - 2;
	c[p] = c[p] + 15;
	putchar(c[p]);
	p = p + 1;
	putchar(c[p]);
	c[p] = c[p] + 3;
	putchar(c[p]);
	c[p] = c[p] - 6;
	putchar(c[p]);
	c[p] = c[p] - 8;
	putchar(c[p]);
	p = p + 1;
	c[p] = c[p] + 1;
	putchar(c[p]);
	p = p + 1;
	putchar(c[p]);
	return (0);
}