
#include "msp430F5529.h" //Contains definitions for registers and built-in functions


void main(void)
{

WDTCTL = WDTPW + WDTHOLD; // Stop WDT

TA0CCTL0 = CCIE; // Timer A Compare Control Register enabled by the capture compare interrupt
TA0CTL = TASSEL_2 | MC_1 | ID_3; // SMCLK/8, upmode
// ID_3 means Input Divider - determines how much the input clock is divided by.
// MC Means Mode Control - determines whether you want to count up, then stop, continously count, or count up then down



TA0CCR0 = 20000; // The number the timer is counting to.

P1OUT &= 0x00; // Shut down pins on P1
P1DIR &= 0x00; // Set P1 pins as output
P1DIR |= BIT1;
P1DIR |= BIT0; // P1.0 pin set as output the rest are input
P1REN |= BIT1;
P1OUT |= BIT1;

P4OUT &= 0x00; // Shut down pins on P4
P4DIR &= 0x00; // Set P4 pins as output
P4DIR |= BIT7; // P4.7 pin set as output the rest are input

P2REN |= BIT1; // Enable internal pull-up/down resistors for P2
P2OUT |= BIT1; //Select pull-up mode for P2.1

P2IE |= BIT1; // P2.1 interrupt enabled
P2IES |= BIT1; // P2.1 Hi/lo edge
P2IFG &= ~BIT1; // P2.1 IFG cleared

_BIS_SR(CPUOFF + GIE); // Enter LPM0 w/ interrupt
while(1) //Loop forever, we work with interrupts!
{}
}

// Timer A0 interrupt service routine
#pragma vector=TIMER0_A0_VECTOR
__interrupt void Timer_A0 (void)
{
P1OUT ^= BIT0; // Toggle P1.0

}


#pragma vector=PORT1_VECTOR
__interrupt void Port_1(void)
{
P1OUT ^= BIT0; // Toggle P1.0
P1IFG &= ~BIT1; // P2.1 interrupt flag cleared
}
