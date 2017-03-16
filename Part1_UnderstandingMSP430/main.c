#include "msp430F5529.h" //Contains definitions for registers and built-in functions

int main(void) //Main program
{
WDTCTL = WDTPW + WDTHOLD; // Stop watchdog timer

P1DIR |= BIT0; // Set P1.0 to output
P1OUT &= ~BIT0; // set P1.0 to Off

P1DIR &= ~BIT1; // Set P2.1 to input
P1REN |= BIT1; // Enable pullup resistor of P2.1 (default: GND)
P1OUT |= BIT1; // Set pullup resistor to active (+3.3V) mode

P1IE |= BIT1; // P2.1 interrupt enabled
P1IFG &= ~BIT1; // P2.1 interrupt flag cleared

__bis_SR_register(GIE); // Enable all interrupts

while(1) //Loop forever, we'll do our job in the interrupt routine...
{}
}


#pragma vector=PORT1_VECTOR
__interrupt void Port_1(void)
{
P1OUT ^= BIT0; // Toggle P1.0
P1IFG &= ~BIT1; // P2.1 interrupt flag cleared
}
