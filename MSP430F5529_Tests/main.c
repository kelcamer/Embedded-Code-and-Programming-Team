#include <msp430.h>
unsigned int i = 0; // Initialize variables. This will keep count of how many cycles between LED toggles
void main(void)
{
WDTCTL = WDTPW + WDTHOLD; // Stop watchdog timer. This line of code is needed at the beginning of most MSP430 projects.

P1DIR |= 0x01;
P4DIR |= 0x80;		//10000000
// This line of code turns off the watchdog timer, which can reset the device after a certain period of time. // P1DIR is a register that configures the direction (DIR) of a port pin as an output or an input.
// To set a specific pin as output or input, we write a '1' or '0' on the appropriate bit of the register. // P1DIR = <PIN7><PIN6><PIN5><PIN4><PIN3><PIN2><PIN1><PIN0>
// Since we want to blink the on-board red LED, we want to set the direction of Port 1, Pin 0 (P1.0) as an output // We do that by writing a 1 on the PIN0 bit of the P1DIR register
// P1DIR = <PIN7><PIN6><PIN5><PIN4><PIN3><PIN2><PIN1><PIN0> // P1DIR = 0000 0001
// P1DIR = 0x01 <-- this is the hexadecimal conversion of 0000 0001
// This empty for-loop will cause the lines of code within to loop infinitely // Toggle P1.0 using exclusive-OR operation (^=)
// P1OUT is another register which holds the status of the LED.
// '1' specifies that it's ON or HIGH, while '0' specifies that it's OFF or LOW
// Since our LED is tied to P1.0, we will toggle the 0 bit of the P1OUT register
// Delay between LED toggles. This for-loop will run until the condition is met. //In this case, it will loop until the variable i increments to 20000.



for (;;){
	P1OUT &= 0x00; // Shut down pins on P1
	P1DIR &= 0x00; // Set P1 pins as output
	P1DIR |= BIT1;	// this is the second button that will be used for the extra credit
	P1DIR |= BIT0; // P1.0 pin set as output the rest are input
	P1REN |= BIT1;	// this configures the extra credit second button as a pullup resistor.
	P1OUT |= BIT1;  // this allows the button to accept interrupts.


	P2IE |= BIT1; // P2.1 interrupt enabled
	P2IES |= BIT1; // P2.1 Hi/lo edge
	P2IFG &= ~BIT1; // P2.1 IFG cleared

	_BIS_SR(CPUOFF + GIE); // Enter LPM0 w/ interrupt

}
}


void OUTA_UART(unsigned char a){ //--------------------------------------------------------------- //*************************************************************** //--------------------------------------------------------------- // IFG2 register (1) = 1 transmit buffer is empty,

	  while (!(UCA1IFG & UCTXIFG)) ;
	  UCA1TXBUF = a;
 }

#pragma vector = USCI_A1_VECTOR
__interrupt void USCI_A1_ISR(void) {
	 switch(__even_in_range(UCA0IV,4))
	  {
	    case 0: break;                          // Vector 0 - no interrupt
	    case 2:                                 // Vector 2 - RXIFG
	      while (!(UCA0IFG&UCTXIFG));           // USCI_A0 TX buffer ready?

	     // if (UCA0RXBUF==SLV_Data)              // Test for correct character RX'd
	        P1OUT |= 0x01;                      // If correct, light LED
	      //else
	        //P1OUT &= ~0x01;                     // If incorrect, clear LED

  }
