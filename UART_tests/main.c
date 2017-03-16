void init_UART(void);
void OUTA_UART(unsigned char A);
int getPattern(char c);
unsigned char INCHAR_UART(void);
#include "msp430f5529.h"
#include "stdio.h"


int main(void){
volatile unsigned char a;
volatile unsigned int i;
WDTCTL = WDTPW + WDTHOLD;
init_UART();
while(1==1){
a=INCHAR_UART();
OUTA_UART(a);
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


// IFG2 register (0) = 1 receiver buffer is full,
// UCA0RXIFG
// IFG2 register (1) = 1 transmit buffer is empty,
// UCA0RXIFG
// UCA0RXBUF 8 bit receiver buffer
// UCA0TXBUF 8 bit transmit buffer
