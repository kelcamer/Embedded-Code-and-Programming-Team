#include <msp430.h> 

void OUTA_UART(unsigned char a);
unsigned char INCHAR_UART(void);
void init_UART(void);
char convertToChar(char first, char second);
unsigned char getASCIIValue(char c);
void allparts(void);
void printThreeCharFromNum(int num);
char convertToAsci(char first);
 int getShiftedMult(int firstnum, int secnum);
/*
 * main.c
 */
int main(void) {
    WDTCTL = WDTPW | WDTHOLD;	// Stop watchdog timer
	init_UART();
    while(1==1){
	allparts();

    }

}

// 43 = +, 45 = -, 42 = *, 47 = /
void allparts(void){

	char c1 = INCHAR_UART();
	char c2 = INCHAR_UART();
	int showneg = 0;
	OUTA_UART(c1);
	OUTA_UART(c2);
	OUTA_UART(0x20);

	char firstnum = convertToChar(c1, c2);


	volatile unsigned char operation = INCHAR_UART();

	char c3 = INCHAR_UART();
	char c4 = INCHAR_UART();
	char secnum = convertToChar(c3, c4);

	volatile unsigned int result = 0;
	if(operation == '+'){
		OUTA_UART('+');
		result = firstnum + secnum;

	}
	else if(operation == '-'){
		OUTA_UART('-');

		if(firstnum > secnum){
			result = firstnum - secnum;
		}
		else if(secnum > firstnum){
			showneg = 1;
			result = secnum - firstnum;
		}

	}
	else if(operation == '*'){
		// must do shift and add, unfortunately...
		OUTA_UART('*');
		// result = firstnum * secnum; if you do this, it cuts off the first number.
		// 11001010 * 00000001
		volatile unsigned int total = 0;
		total = getShiftedMult(firstnum, secnum);
		OUTA_UART(0x20);

			OUTA_UART(c3);
			OUTA_UART(c4);

			OUTA_UART(0x20);
			OUTA_UART('=');
			OUTA_UART(0x20);
			printThreeCharFromNum(total);
		return;

	}

	OUTA_UART(0x20);

	OUTA_UART(c3);
	OUTA_UART(c4);

	OUTA_UART(0x20);
	OUTA_UART('=');
	OUTA_UART(0x20);

	if(showneg == 1){
		OUTA_UART('-');
	}
	printThreeCharFromNum(result);
	OUTA_UART(0x0A);
	OUTA_UART(0x0D);

}
void printThreeCharFromNum(int num){

		 	 volatile unsigned int first= num & 0xF0;		// look at only the first four bits
		  	 volatile unsigned int second= num & 0x0F;		// look at only the last four bits
		  	 volatile unsigned int third = num & 0xF00;
		  	 volatile unsigned int fourth = num & 0xF000;
		  	 volatile unsigned int fifth = num & 0xF0000;
		  	 first=first>>4;			// shift first by 4 right so the extra zero goes away
		  	 third=third>>8;
		  	 fourth=fourth>>8;
		  	 fourth = fourth>>4;
		  	 // converts the actual letters and numbers to asci letters and numbers
		  	 fourth = convertToAsci(fourth);
		  	first = convertToAsci(first);
		  	second = convertToAsci(second);
		  	third = convertToAsci(third);


		  	if(fourth != '0'){
		  		OUTA_UART(fourth);
		  			  	}
		  	 // prints
		  	if(third != '0'){
		  		OUTA_UART(third);
		  	}
		  	 OUTA_UART(first);
		  	 OUTA_UART(second);



		 OUTA_UART(0x0A);			//newline
		 OUTA_UART(0x0D);


}
char convertToAsci(char first){
			if(first>=0xA && first <=0xF)
			  	 {
			  		 first+=0x37;
			  	 }
			  	 else if (first>=0x0 && first<=0x9)
			  	 {
			  		 first+=0x30;
			  	 }
	return first;
}
char convertToChar(char first, char second){

			first = getASCIIValue(first);
			second = getASCIIValue(second);
			first = first <<4;
			char c = first+second;
			return c;
}
 int getShiftedMult(int firstnum, int secnum){

	volatile unsigned int x = 0x01;
			volatile unsigned int y = 0;
			int z = 0;
			volatile unsigned int firstbit = 0x01;
			volatile unsigned int total = 0x0;
			volatile unsigned int zeros = 0;			// this will add zeros in front
			volatile unsigned int temp = 0x00;
			while(z<9){

				firstbit = x & secnum;			// this loops through each bit of second number.

				if(firstbit != 0x00){
					temp = firstnum;
				}
				else if(firstbit == 0x00){
					temp = 0x00;
				}

				while(y < zeros){
					temp = temp<<1;
					y+=0x01;
				}
				y = 0x00;
				total+=temp;
				x=x<<0x01;

				z++;
				zeros++;

				if(z==1){
					z+=0;
				}

			}

			return total;


}
unsigned char getASCIIValue(char c){

	if(c >= 0x30 && c <= 0x39){
		c-=0x30;
	}

	else if(c >= 0x41 && c <= 0x46){
		c-=0x37;

	}

	return c;

}
void OUTA_UART(unsigned char a){ //--------------------------------------------------------------- //*************************************************************** //--------------------------------------------------------------- // IFG2 register (1) = 1 transmit buffer is empty,

	  while (!(UCA1IFG & UCTXIFG)) ;
	  UCA1TXBUF = a;
  }

unsigned char INCHAR_UART(void){ //--------------------------------------------------------------- //*************************************************************** //--------------------------------------------------------------- // IFG2 register (0) = 1 receive buffer is full,
	while (!(UCA1IFG & UCRXIFG)) ;
	return UCA1RXBUF;
}

void init_UART(void) {
        P4SEL           |=      BIT4 | BIT5;
        UCA1CTL1        |=      UCSWRST;
        UCA1CTL1        |=      UCSSEL_2;
        /** I just noticed in your code these are UCA0BRn */
        UCA1BR0         =       109;
        UCA1BR1         =       0;
        /* modulation */
        UCA1MCTL        =       UCBRS_2 | UCBRF_0;
        UCA1CTL1        &=      ~UCSWRST;
}
