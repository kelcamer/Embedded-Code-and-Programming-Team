/*
 * Group Members: Kelsey Cameron, Justin Bates
 * Homework #3 Temperature Clock
 */


#include "msp430f5529.h"
#include "stdio.h"



void init_UART(void);
void OUTA_UART(unsigned char A);
unsigned char INCHAR_UART(void);
int main(void){

volatile unsigned char a;
volatile unsigned int i;

WDTCTL = WDTPW + WDTHOLD;
init_UART();





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

// timer interrupt
#pragma vector=TIMER0_A0_VECTOR
__interrupt void Timer_A0 (void)
{





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
    UCA1TXBUF = UCA1RXBUF;                  // TX -> RXed character
    char c = UCA1RXBUF;




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



P2IFG &= ~BIT1; // P1.1 interrupt flag cleared
}

