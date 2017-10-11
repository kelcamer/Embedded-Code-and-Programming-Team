/*
 * main.c
 */
#include "msp430fg4618.h"
#include "stdio.h"

void Init_UART(void);
void printOpposite(char c);
void OUTA_UART(unsigned char A);
unsigned char getASCIIValue(char c);
unsigned char getHexVal(char c);

unsigned char INCHAR_UART(void);
void printHexValue(char c);
void printUART(char* firstletter);

//  prints one character at a time via the UART from a string of characters to
//the hyperterminal display. This function should continue printing characters until the
//null character is reached (0x00) indicating an end of a string.

int main(void) {
	WDTCTL = WDTPW + WDTHOLD;		// passes the locking password to the watchdog and then holds it.
	Init_UART();

	for(;;){// 110 sets both P2 bit 1 and P2 bit 2 to an output

		// Part 1 - uncomment to test
		/*
		volatile unsigned char first = INCHAR_UART();
		volatile unsigned char second = INCHAR_UART();


		first = getASCIIValue(first);
		second = getASCIIValue(second);

		first = first <<4;

		OUTA_UART(first+second);
		OUTA_UART(0x0A);
		OUTA_UART(0x0D);
		 */

		// Part 2 - uncomment to test
		/*volatile unsigned char c = INCHAR_UART();
		printHexValue(c);
		*/

		// Part 3 - uncomment to test
		/*printOpposite(INCHAR_UART());
		OUTA_UART(0x0A);
		OUTA_UART(0x0D);
		*/
		// Part 4 - uncomment to test

		 int x = 0;
		volatile unsigned int bin_num = 0x00;
		for(x = 0; x < 8; x++){
			char mychar = INCHAR_UART();
			bin_num &= (x&mychar);
		}
		OUTA_UART(bin_num);


	}




}
void printOpposite(char c){

	if(c >= 0x41 && c <= 0x5A){
		c+=0x20;
		}

	else if(c >= 0x61 && c <= 0x7A){
		c-=0x20;
	}
	OUTA_UART(c);

}
void printHexValue(char c){
	char second = c & 0x0F;		//0x15 = 1111
	char first = c;
	first = c & 0xF0;
	first = first >> 4;


	OUTA_UART(getHexVal(first));
	OUTA_UART(getHexVal(second));

}

// 3F = 3*16 + F
unsigned char getASCIIValue(char c){



	if(c >= 0x30 && c <= 0x39){
		c-=0x30;
	}

	else if(c >= 0x41 && c <= 0x46){
		c-=0x37;

	}

	return c;

}
unsigned char getHexVal(char c){



	if(c >= 0x00 && c <= 0x09){
		c+=0x30;
	}

	else if(c >= 0x0A && c <= 0x0F){
		c+=0x37;

	}

	return c;

}

void printUART(char * firstletter){


			while(*firstletter!='\0'){

			OUTA_UART(*firstletter);


			firstletter++;

			}

			OUTA_UART(0x0D);


}

// 1.0 SW1 1.2 SW2
// remember that 0 means button is pressed.



void OUTA_UART(unsigned char A){
//---------------------------------------------------------------
//***************************************************************
//---------------------------------------------------------------
// IFG2 register (1) = 1 transmit buffer is empty,
// UCA0TXBUF 8 bit transmit buffer
// wait for the transmit buffer to be empty before sending the
// data out
do{
 }while ((IFG2&0x02)==0);
// send the data to the transmit buffer
UCA0TXBUF =A;
}

// while bit 2 of the IFG2 register is set to zero,
	// this is a transmitter flag that sees if the transmitter is free to transfer or not.
	// wait for the transmission to be complete.  This flag = 1 when bits are on the output lines.

	// send the data to the transmit buffer
	// so this takes the data from A, and sends it
	// the buffer that stores data.  This UCA0TXBUF is a variable
							// that stores the address of a buffer register on the chip.
							// this data transfer is hard wired, and data from A is placed on the
							// output lines that are placed into the register.
							// basically UART works by shifting the bits right, and the rightmost bit is sent to the reciever


unsigned char INCHAR_UART(void){

	do{

	}while((IFG2&0x01) == 0);		// IFG2 = Interrupt FlaG Second Register
	// First bit of IFG2 recieves, and the second bit transfers.

	return (UCA0RXBUF);

}


void Init_UART(void){
//---------------------------------------------------------------
// Initialization code to set up the uart on the experimenter
// board to 8 data,
// 1 stop, no parity, and 9600 baud, polling operation
//---------------------------------------------------------------
P2SEL=0x30; // transmit and receive to port 2 bits 4 and 5
 // sets Bits p2.4 transmit and p2.5 receive, similar to how we set bits as outputs
// or inputs
// 30 = 110000


UCA0CTL0=0; // 8 bits of data, no parity 1 stop, uart, async
 // (7)=1 (parity), (6)=1 Even, (5)= 0 lsb first,
 // (4)= 0 8 data / 1 7 data,
 // (3) 0 1 stop 1 / 2 stop, (2-1) -- UART mode,
 // (0) 0 = async
// this basically initializes the flag.


UCA0CTL1= 0x41;
 // select ALK 32768 and put in
 // software reset the UART
 // (7-6) 00 UCLK, 01 ACLK (32768 hz), 10 SMCLK,
 // 11 SMCLK
 // (0) = 1 reset
UCA0BR1=0; // upper byte of divider clock word
UCA0BR0=3; // clock divide from a clock to bit clock 32768/9600
 // = 3.413
 // UCA0BR1:UCA0BR0 two 8 bit reg to from 16 bit
 // clock divider
 // for the baud rate
UCA0MCTL=0x06;
 // low frequency mode module 3 modulation pater
 // used for the bit clock
UCA0STAT=0; // do not loop the transmitter back to the
 // receiver for echoing
 // (7) = 1 echo back trans to rec
 // (6) = 1 framing, (5) = 1 overrun, (4) =1 Parity,
 // (3) = 1 break
 // (0) = 2 transmitting or receiving data
UCA0CTL1=0x40;
 // take UART out of reset
IE2=0; // turn transmit interrupts off
//---------------------------------------------------------------
//***************************************************************
//---------------------------------------------------------------
 // IFG2 register (0) = 1 receiver buffer is full,
 // UCA0RXIFG
 // IFG2 register (1) = 1 transmit buffer is empty,
 // UCA0RXIFG
 // UCA0RXBUF 8 bit receiver buffer
 // UCA0TXBUF 8 bit transmit buffer
}
