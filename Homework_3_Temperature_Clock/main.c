/*
 * Group Members: Kelsey Cameron, Justin Bates
 * Homework #3 Temperature Clock
 */


#include "msp430.h"
#include <stdio.h>

struct temperature{
	unsigned int tempInC;
	unsigned int tempInF;
	unsigned int hour;
	unsigned int min;
	unsigned int sec;


};

#define CALADC12_15V_30C  *((unsigned int *)0x1A1A)   // Temperature Sensor Calibration-30 C
                                                      //See device datasheet for TLV table memory mapping
#define CALADC12_15V_85C  *((unsigned int *)0x1A1C)   // Temperature Sensor Calibration-85 C



void init_UART(void);
void OUTA_UART(unsigned char A);
int getTemps(void);
void setTime(int h, int m, int s);
void displayTime();
void useCommands(char c);
int convertBackSingleDigit(char num);
void addToStringBuffer(char c);
unsigned char INCHAR_UART(void);
char convertSingleDigit(int num);
int convertToNum(char first, char second);
char* convertToChar(int num);
volatile unsigned int seconds;
volatile unsigned int stringindex;
volatile char string[100];
volatile unsigned int minutes;
volatile unsigned int multiplesoffive;
volatile unsigned int hours;
volatile unsigned int index;
void initializeStruct();
void displayStampedTime(int index);

unsigned int temp;

struct temperature alltemps[32];

int main(void){

	volatile unsigned char a;
	volatile unsigned int i;
	volatile unsigned int currenttemp;
		index = 0;
		stringindex = 0;
		initializeStruct();
		WDTCTL = WDTPW + WDTHOLD;
		init_UART();





		REFCTL0 &= ~REFMSTR;                      // Reset REFMSTR to hand over control to
											// ADC12_A ref control registers
		ADC12CTL0 = ADC12SHT0_8 + ADC12REFON + ADC12ON;
	                                            // Internal ref = 1.5V
		ADC12CTL1 = ADC12SHP;                     // enable sample timer
		ADC12MCTL0 = ADC12SREF_1 + ADC12INCH_10;  // ADC i/p ch A10 = temp sense i/p
		ADC12IE = 0x001;                          // ADC_IFG upon conv result-ADCMEMO
		ADC12CTL0 |= ADC12ENC;


		TA0CCTL0 = CCIE; // Timer A Compare Control Register enabled by the capture compare interrupt
		TA0CTL = TASSEL_1 | MC_1 | ID_2; // SMCLK/8, upmode
// ID_3 means Input Divider - determines how much the input clock is divided by.
// MC Means Mode Control - determines whether you want to count up, then stop, continously count, or count up then down

		TA0CCR0 = 0x12000; // The number the timer is counting to.



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


ADC12CTL0 &= ~ADC12SC;
ADC12CTL0 |= ADC12SC;                   // Sampling and conversion start

__bis_SR_register(LPM3_bits + GIE);     // LPM3 with interrupts enabled
__no_operation();



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


unsigned char INCHAR_UART(void){ //--------------------------------------------------------------- //*************************************************************** //--------------------------------------------------------------- // IFG2 register (0) = 1 receive buffer is full,
	while (!(UCA1IFG & UCRXIFG)) ;
	return UCA1RXBUF;
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

int getTemps(void){
	volatile float temperatureDegF = 0;
		volatile float temperatureDegC = 0;


		    // Temperature in Celsius. See the Device Descriptor Table section in the
		    // System Resets, Interrupts, and Operating Modes, System Control Module
		    // chapter in the device user's guide for background information on the
		    // used formula.
		    temperatureDegC = (float)(((long)temp - CALADC12_15V_30C) * (85 - 30)) /
		            (CALADC12_15V_85C - CALADC12_15V_30C) + 30.0f;
		    // Temperature in Fahrenheit Tf = (9/5)*Tc + 32
		    temperatureDegF = temperatureDegC * 9.0f / 5.0f + 32.0f;

		    alltemps[index].tempInF = temperatureDegF;
		    alltemps[index].tempInC = temperatureDegC;
		    alltemps[index].hour = hours;
		    alltemps[index].min = minutes;
			alltemps[index].sec = seconds;



			index++;

			if(index == 32){
				// delete the oldest entry
				index = 31;
			}

}
void setTime(int h, int m, int s){
	hours = h;
	minutes = m;
	seconds = s;

}
void displayTime(){
	// I need a function that takes a number and returns two separate characters....
	// like 11 = 1 and 1
	// I currently have all of these numbers

	char* test = convertToChar(hours);
			int z = 0;
			for(z = 0; z < 2; z++){
				OUTA_UART(test[z]);
			}
	test = convertToChar(minutes);
			 z = 0;
			for(z = 0; z < 2; z++){
				OUTA_UART(test[z]);
			}
	 test = convertToChar(seconds);
			 z = 0;
			for(z = 0; z < 2; z++){
				OUTA_UART(test[z]);
			}




}

char convertSingleDigit(int num){

		return (char)num+48;


}
int convertBackSingleDigit(char num){
		return (char)num-48;

}
void initializeStruct(){


	int x = 0;
		for(x = 0; x < 32; x++){
			alltemps[x].hour = 0;
			alltemps[x].min = 0;
			alltemps[x].sec = 0;
			alltemps[x].tempInC = 0;
			alltemps[x].tempInF = 0;

		}
}
void displayAllTemps(void){
	int x = 0;
	int state = 0;
	for(x = 0; x < 32; x++){
		// if there is a valid time, then print it
		if(!(alltemps[x].hour == 0 && alltemps[x].min == 0 && alltemps[x].sec == 0)){
			displayStampedTime(x);
			OUTA_UART(':');
			OUTA_UART(' ');

			char* test = convertToChar(alltemps[x].tempInF);
						int z = 0;
						for(z = 0; z < 2; z++){
							OUTA_UART(test[z]);
						}
						OUTA_UART(0x0D);
						OUTA_UART(0x0A);


			state = 1;
		}


	}

	if(state == 0){
			// print no recorded temps
		char* word = "No temperatures recorded.";

		int x = 0;
		for(x = 0; x < 26; x++){
			OUTA_UART(*word);
			word++;


		}


		}


}
int convertToNum(char first, char second){
	// take '1' and '1' and convert to 11
	int num1 = convertBackSingleDigit(first);
	int num2 = convertBackSingleDigit(second);

	return 10*num1 + num2;

}

void displayStampedTime(int index){


	char* test = convertToChar(alltemps[index].hour);
			int z = 0;
			for(z = 0; z < 2; z++){
				OUTA_UART(test[z]);
			}
	test = convertToChar(alltemps[index].min);
			 z = 0;
			for(z = 0; z < 2; z++){
				OUTA_UART(test[z]);
			}
	 test = convertToChar(alltemps[index].sec);
			 z = 0;
			for(z = 0; z < 2; z++){
				OUTA_UART(test[z]);
			}



}

char* convertToChar(int num){
	// start with 72
	// return '7' and '2'
	char mynumber[2];
	if(num == 0){
		return "00";
	}
	if(num >=10 && num <=99){
	int digit1 = num / 10;
	int digit2 = num - (10*digit1);


	mynumber[0] = convertSingleDigit(digit1);
	mynumber[1] = convertSingleDigit(digit2);


	}
	else if(num <10 && num >0){

		mynumber[0] = '0';
		mynumber[1] = convertSingleDigit(num);

	}


	return &mynumber;
}
void displayOldest(void){
	int x = 31;
	int state = 0;
	for(x = 31; x >=0; x--){
		if(!(alltemps[x].hour == 0 && alltemps[x].min == 0 && alltemps[x].sec == 0)){
						displayStampedTime(x);
						OUTA_UART(':');
						OUTA_UART(' ');

						char* test = convertToChar(alltemps[x].tempInF);
									int z = 0;
									for(z = 0; z < 2; z++){
										OUTA_UART(test[z]);
									}



				state = 1;


				break;
			}
	}
	if(state == 0){
				// print no recorded temps
			char* word = "No temperatures recorded.";

			int x = 0;
			for(x = 0; x < 26; x++){
				OUTA_UART(*word);
				word++;


			}

			}
}
/*void sortStruct(){
	int x = 0;
	int y = 0;

	for(x = 0; x < 32; x++){
		for(y = 0; y < 32; y++){
			if(alltemps[x].hour > alltemps[y].hour){
				struct temperature temporary = alltemps[x];
				alltemps[x] = alltemps[y];
				alltemps[y] = temporary;

			}
			else if(alltemps[x].hour == alltemps[y].hour){
				if(alltemps[x].min > alltemps[y].min){
					struct temperature temporary = alltemps[x];
					alltemps[x] = alltemps[y];
					alltemps[y] = temporary;
				}
				else if(alltemps[x].sec > alltemps[y].sec){
					struct temperature temporary = alltemps[x];
					alltemps[x] = alltemps[y];
					alltemps[y] = temporary;
				}

			}

		}

	}


}*/


void addToStringBuffer(char c){

	if(stringindex < 63){
	string[stringindex] = c;
	stringindex++;
	}

}
void useCommands(char c){
	// tsol

	if(c == 't'){
		OUTA_UART(0x0D);
		OUTA_UART(0x0A);
		displayTime();
		OUTA_UART(0x0D);
		OUTA_UART(0x0A);
	}
	else if(c == 's'){
		// set time
		// remember that when you set the time, you gotta convertSingleDigit the character to an int by subtracting for ascii
		// idea: take the first number, multiply by 10 (via sll and add) and then add the second # to the first #
		 char h1 = INCHAR_UART();
		 char h2 = INCHAR_UART();
		 char m1 = INCHAR_UART();
		 char m2 = INCHAR_UART();
		 char s1 = INCHAR_UART();
		 char s2 = INCHAR_UART();

		 setTime(convertToNum(h1,h2), convertToNum(m1,m2), convertToNum(s1,s2));


	}
	// ideas for temp reading - use a struct
	else if(c== 'o'){
		// show oldest temp readings
		OUTA_UART(0x0D);
		OUTA_UART(0x0A);
		displayOldest();
		OUTA_UART(0x0D);
		OUTA_UART(0x0A);
	}

	else if(c == 'l'){
		// show all temp readings
		OUTA_UART(0x0D);
		OUTA_UART(0x0A);
		displayAllTemps();
		OUTA_UART(0x0D);
		OUTA_UART(0x0A);
	}

}

// timer interrupt
#pragma vector=TIMER0_A0_VECTOR
__interrupt void Timer_A0 (void)
{

	/*
	 * Ideas: Have timer interrupt once per second
	 * once it reaches 60, increment minute counter
	 * once minute counter = 60, increment hour counter
	 */



	seconds++;
	multiplesoffive++;
	if(seconds == 60){
		minutes++;

		seconds = 0;


	}
	if(minutes == 60){
		hours++;
		minutes = 0;
	}
	if(multiplesoffive == 5){
		// five minutes have passed, so you must grab a temperature reading
		multiplesoffive = 0;
		getTemps();

	}

	if(hours == 24){
		hours = 0;
	}
	// comment this out
	//if(seconds == 5){
	//	getTemps();
	//}
//P1OUT ^= BIT0;


}
// UART interrupt
#pragma vector=USCI_A1_VECTOR
__interrupt void USCI_A1_ISR(void)
{
  switch(__even_in_range(UCA1IV,4))
  {
  case 0:break;                             // Vector 0 - no interrupt
  case 2:                                   // Vector 2 - RXIFG
    while (!(UCA1IFG&UCTXIFG));             // USCI_A0 TX buffer ready?
   // UCA1TXBUF = UCA1RXBUF;                  // TX -> RXed character
    char c = UCA1RXBUF;

    if(stringindex < 100){
        	string[stringindex] = c;
        	stringindex++;
        	}


    useCommands(c);



    break;
  case 4:break;                             // Vector 4 - TXIFG
  default: break;
  }
}


// button interrupt
#pragma vector=PORT1_VECTOR
__interrupt void Port_1(void)
{



P1IFG &= ~BIT1; // P1.1 interrupt flag cleared
}

#pragma vector=PORT2_VECTOR
__interrupt void Port_2(void)
{
useCommands('t');

useCommands('l');


P2IFG &= ~BIT1; // P2.1 interrupt flag cleared
}

#pragma vector=ADC12_VECTOR
__interrupt void ADC12ISR (void)
{
  switch(__even_in_range(ADC12IV,34))
  {
  case  0: break;                           // Vector  0:  No interrupt
  case  2: break;                           // Vector  2:  ADC overflow
  case  4: break;                           // Vector  4:  ADC timing overflow
  case  6:                                  // Vector  6:  ADC12IFG0
    temp = ADC12MEM0;                       // Move results, IFG is cleared
    __bic_SR_register_on_exit(LPM3_bits);   // Exit active CPU

    break;
  case  8: break;                           // Vector  8:  ADC12IFG1
  case 10: break;                           // Vector 10:  ADC12IFG2
  case 12: break;                           // Vector 12:  ADC12IFG3
  case 14: break;                           // Vector 14:  ADC12IFG4
  case 16: break;                           // Vector 16:  ADC12IFG5
  case 18: break;                           // Vector 18:  ADC12IFG6
  case 20: break;                           // Vector 20:  ADC12IFG7
  case 22: break;                           // Vector 22:  ADC12IFG8
  case 24: break;                           // Vector 24:  ADC12IFG9
  case 26: break;                           // Vector 26:  ADC12IFG10
  case 28: break;                           // Vector 28:  ADC12IFG11
  case 30: break;                           // Vector 30:  ADC12IFG12
  case 32: break;                           // Vector 32:  ADC12IFG13
  case 34: break;                           // Vector 34:  ADC12IFG14
  default: break;
  }
}




