#include <msp430.h>

// packed struct
#pragma pack(push, 1)
struct my_struct_1
{
  unsigned char port1_bits;
  unsigned int delay;
  unsigned char port2_bits;
};
#pragma pack(pop)

// normal struct
struct my_struct_2
{
  unsigned char port3_bits;
  unsigned int delay;
  unsigned char port4_bits;
};

struct my_struct_1 my_data_1;
struct my_struct_2 my_data_2;
volatile unsigned int delay;

void main(void)
{

  // stop WDT
  WDTCTL = WDTPW + WDTHOLD;

  // initialize structures
  my_data_1.port1_bits = BIT0;
  my_data_1.delay = 100;
  my_data_1.port2_bits = BIT1;

  my_data_2.port3_bits = BIT2;
  my_data_2.delay = 200;
  my_data_2.port4_bits = BIT3;

  // initialize hardware using data structure
  P1DIR = my_data_1.port1_bits;
  P2DIR = my_data_1.port2_bits;
  P3DIR = my_data_2.port3_bits;
  P4DIR = my_data_2.port4_bits;

  while(1)
  {
    P1OUT ^= my_data_1.port1_bits;
    P2OUT ^= my_data_1.port2_bits;
    for(delay=my_data_1.delay;delay;delay--);

    P3OUT ^= my_data_2.port3_bits;
    P4OUT ^= my_data_2.port4_bits;
    for(delay=my_data_2.delay;delay;delay--);
  }
}
