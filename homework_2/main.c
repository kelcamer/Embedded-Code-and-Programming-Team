/*
 * Group Members: Kelsey Cameron, Justin Bates, Christian Theriot
 * Homework #2 Morse Code Translator for Embedded
 */


void init_UART(void);
void OUTA_UART(unsigned char A);
void addToStringBuffer(char c);
void printString(void);

char* getPattern(char c);
void blinkLightFromPattern(int num);
unsigned char INCHAR_UART(void);

#include "msp430f5529.h"
#include "stdio.h"


volatile char *currentpattern;
volatile int stringindex;
volatile char string[63];
volatile unsigned int printingindex;
int main(void){

volatile unsigned char a;
volatile unsigned int i;

WDTCTL = WDTPW + WDTHOLD;
init_UART();





while(1==1){
	printingindex = 0;
	int x = 0;
	int n = 0;
	for(x = 0; x < 63; x++){
		a=INCHAR_UART();
		OUTA_UART(a);
		if(a==0x0A || a==0x0D){
			break;
		}
		n++;
		string[n] = a;
	}


	char *pattern = getPattern(string[stringindex]);
	currentpattern = pattern;


/*while(*pattern != '\0'){
	OUTA_UART(*pattern);
	pattern++;
}
*/


TA0CCTL0 = CCIE; // Timer A Compare Control Register enabled by the capture compare interrupt
TA0CTL = TASSEL_2 | MC_1 | ID_3; // SMCLK/8, upmode
// ID_3 means Input Divider - determines how much the input clock is divided by.
// MC Means Mode Control - determines whether you want to count up, then stop, continously count, or count up then down

TA0CCR0 = 0x8000; // The number the timer is counting to.



P1REN |= BIT1; // Enable pullup resistor of P2.1 (default: GND)
P1OUT |= BIT1; // Set pullup resistor to active (+3.3V) mode

P1IE |= BIT1; // P2.1 interrupt enabled
P1IFG &= ~BIT1; // P2.1 interrupt flag cleared


P1OUT &= 0x00; // Shut down pins on P1
P1DIR &= 0x00; // Set P1 pins as output
P2DIR &= ~BIT1; // Set P2.1 to input this is second button for extra credit
P1DIR |= BIT0; // P1.0 pin set as output the rest are input
P2REN |= BIT1;	// this configures the extra credit second button as a pullup resistor.
P2OUT |= BIT1;  // this allows the button to accept interrupts.


P2IE |= BIT1; // P1.1 interrupt enabled
P2IES |= BIT1; // P1.1 Hi/lo edge
P2IFG &= ~BIT1; // P1.1 IFG cleared
UCA1IE |= UCRXIE;					// THIS ENABLES INTERRUPTS FOR UART DO NOT REMOVE OR INTERRUPT WILL NOT WORK

_BIS_SR(CPUOFF + GIE); // Enter LPM0 w/ interrupt

}

}
// Set P1.0 to output direction
// Use The LED as an indicator
// Toggle P1.0 using exclusive-OR
// SW Delay
// volatile to prevent optimization
// Stop watchdog timer
  void OUTA_UART(unsigned char a){ //--------------------------------------------------------------- //*************************************************************** //--------------------------------------------------------------- // IFG2 register (1) = 1 transmit buffer is empty,

	  while (!(UCA1IFG & UCTXIFG)) ;
	  UCA1TXBUF = a;
  }
  void printString(void){
	  int y = 0;
	  for(y = 0; y < 63; y++){
		  if((string[y]!=0x0D && string[y]!=0x0A) && (string[y] != 0)){
			  OUTA_UART(string[y]);
		  }
	  }

  }

unsigned char INCHAR_UART(void){ //--------------------------------------------------------------- //*************************************************************** //--------------------------------------------------------------- // IFG2 register (0) = 1 receive buffer is full,
	while (!(UCA1IFG & UCRXIFG)) ;
	return UCA1RXBUF;
}

void addToStringBuffer(char c){

	if(stringindex < 63){
	string[stringindex] = c;
	stringindex++;
	}

}
void init_UART(void) {
        P4SEL           |=      BIT4 | BIT5;			// the control signal for the multiplexor that sets P4.4 and 4.5 to control UART
        UCA1CTL1        |=      UCSWRST;
        UCA1CTL1        |=      UCSSEL_2;

        UCA1BR0         =       109;
        UCA1BR1         =       0;
        /* modulation */
        UCA1MCTL        =       UCBRS_2 | UCBRF_0;
        UCA1CTL1        &=      ~UCSWRST;
}
char* getPattern(char c){


	static char* athruz[26] = {"10111000", "111010101000",
			"11101011101000", "1110101000", "1000", "101011101000",
			"111011101000", "1010101000", "101000", "1011101110111000",
			"111010111000", "101110101000", "1110111000", "11101000",
			"11101110111000", "10111011101000", "1110111010111000",
			"1011101000", "10101000", "111000", "1010111000", "101010111000",
			"101110111000", "11101010111000", "1110101110111000",
			"11101110101000"};
	static char* array0thru9[10] = {"1110111011101110111000",
			"10111011101110111000", "101011101110111000", "1010101110111000",
			"10101010111000", "101010101000", "11101010101000", "1110111010101000",
			"111011101110101000", "11101110111011101000"};
	// 0 comes first
	unsigned int num = (int)c;

	// because we are adding 3 zeros after each letter, we only need 4 for a space.   (3+4 = 7)
	if(num==32){
		return "0000";
	}
	// for capital letters
	if(num >= 65 && num <= 90){
		num-=65;

		return athruz[num];

	}
	// for lowercase letters
	else if(num >= 97 && num <= 122){
		num-=97;
		return athruz[num];
	}
	else if(num >= 48 && num <= 57){

		num-=48;
		return array0thru9[num];
	}

	return 0;

}


/*
 * Increment current pattern's address at the end of each timer.
 *  the timer will take care of that every 250ms (or whatever it is atm)
 * If it is \0, then check to see if there's a second pattern in the queue.
 * If there is, add it and update current pattern's address.
 *
 *
 *
 */
// Timer A0 interrupt service routine
#pragma vector=TIMER0_A0_VECTOR
__interrupt void Timer_A0 (void)
{

	int number = 0;
	// looping through the pattern isn't going to be good enough, because there's not enough of a delay.
	// perhaps a good solution is to only pass it the current number, and somehow update this number??

	if(*currentpattern != '\0'){		// current pattern is the character 1 or zero depending on where you are in your pattern

		number = (int)*currentpattern;	// either a 1 or a zero
		P1OUT = (BIT0 & (number-48)); // Toggle P1.0
		currentpattern++;
	}
	else{
		if(stringindex < 63){
			stringindex++;
			currentpattern = getPattern(string[stringindex]);
			number = (int)*currentpattern;	// either a 1 or a zero
			P1OUT = (BIT0 & (number-48)); // Toggle P1.0
			currentpattern++;
		}
	}




}
#pragma vector=USCI_A1_VECTOR
__interrupt void USCI_A1_ISR(void)
{
  switch(__even_in_range(UCA1IV,4))
  {
  case 0:break;                             // Vector 0 - no interrupt
  case 2:                                   // Vector 2 - RXIFG
    while (!(UCA1IFG&UCTXIFG));             // USCI_A0 TX buffer ready?
    UCA1TXBUF = UCA1RXBUF;                  // TX -> RXed character
    char c = UCA1RXBUF;
    if(stringindex < 63){
    	string[stringindex] = c;
    	stringindex++;
    	}



    break;
  case 4:break;                             // Vector 4 - TXIFG
  default: break;
  }
}



#pragma vector=PORT1_VECTOR
__interrupt void Port_1(void)
{



P1IFG &= ~BIT1; // P1.1 interrupt flag cleared
}

#pragma vector=PORT2_VECTOR
__interrupt void Port_2(void)
{
printString();


P2IFG &= ~BIT1; // P1.1 interrupt flag cleared
}

