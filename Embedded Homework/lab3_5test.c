#include <stdio.h>
#include <stdlib.h>
int main(){
	getSortArray();
	return 0;
}
int getSortArray(){
// part 5
char array[33];
int N = 0;
int x = 0;
for(x = 0; x < 32; x++){
	//char c = INCHAR_UART();
	char c;
	scanf("%c", &c);
	if((c == '\0') || (c == (char)13)){
		break;
	}
	N++;
	array[x] = c;
	
}
int i, j, temp;
for(i = 0; i < N-1; i++){
	for(j = 1; j<N-1; j++){
		int num1 = array[i];
		int num2 = array[j];
		
		if(num1 < num2){
			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		
	}
}
printf("--------\n");
for(i = 0; i < 32; i++){
	printf("%c", array[i]);
}

return 0;
}


