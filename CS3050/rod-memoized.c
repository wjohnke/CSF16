#include <stdio.h>
#include <stdlib.h>
#include <time.h>


/* rodcut  computes the optimal price that can be obtained by cutting a rod of 
 length n given the prices. The prices are stored in an array: p[0] stores
 the price of piece of length 1, p[1] stores the price of piece of length 2, and so on..
  This is the iterative solution to the rodcut problem wit M storing the optimal revenues*/

int rodcut (int *p, int n, int *M)

{
//Check if p has the prices
if (!p) {printf ("No prices given\n"); return -1;}

// No rod present
M[0]=0;

//max will be used to store intermediate values of p[i]+ M[j-i]
int max; 

// cutatk will store the  optimal value give that the left most cut is at position i
int cutati;



/*    The following loop computes the optimal values of cutting a rod of length j using the 
      dynamic approach described in class/book. The optimal values computed are stored in the array M
*/

for (int j=1; j<=n; j++)
  {       
               

               max =-1;

    /*    The following loop iterates over all possible leftmost cuts and  computes the maximum
          revenue that can be generated amongst all possible cuts. */


               for (int i=1; i<=j; i++)
               {
                //Compute the optimal price when the leftmost cut is at position k
                  cutati = p[i-1] + M[j-i];
                  if (cutati >max) {max=cutati;}}
                 
                M[j]= max;
                
  }

  printf("\n");
  for (int i=1; i<=n; i++)
    printf("M[%d]=%d\n", i, M[i]);

  return M[n];
}


//intsgets reads an integer until the first newline or end of file is reached
int intsgets(size_t size)
{
size_t i;
char maxstr[size +1];


for ( i = 0; i < size-1; ++i )
{
int ch = fgetc(stdin);

if ( ch == '\n' || ch == EOF )
{
break;
}

if  (ch=='0' || ch=='1'|| ch=='2' || ch=='3' || ch=='4'|| ch=='5' || ch=='6' || ch=='7' || ch=='8' || ch=='9')
     {maxstr[i] = ch;}
  else {printf("Wrong input \n"); return -1;}
   

}

maxstr[i] = '\0';

return (atoi(maxstr));

}


int main()
{
// Read the length of the rod from stdin
int n = intsgets(4);
if (n<0) {return -1;}
printf("\nn=%d\n",n);



// Array of prices. Price of rod of length i is stored at i-1
int p[n];


//Generate the prices randomly
srand(time(NULL));
p[0]= (rand()%100);
for (int i=1; i< n; i++)
  p[i] =  p[i-1] + (rand()%100);

for (int i=0; i< n; i++)
  printf("The price of piece of length %d is %d\n", i+1, p[i]);


// M will store the optimal price for rods of length <=n
int M[n+1];

//Compute the optimal revenue generated
int max = rodcut(p,n,M);
if (max>=0)
printf("\n The maximum revenue from a piece of length %d is %d\n",n , max);

}
