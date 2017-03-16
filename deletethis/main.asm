;-------------------------------------------------------------------------------
; MSP430 Assembler Code Template for use with TI Code Composer Studio
;
;
;-------------------------------------------------------------------------------
            .cdecls C,LIST,"msp430f5529.h"       ; Include device header file
            
;-------------------------------------------------------------------------------
            .def    RESET                   ; Export program entry-point to
                                            ; make it known to linker.
;-------------------------------------------------------------------------------
            .text                           ; Assemble into program memory.
            .retain                         ; Override ELF conditional linking
                                            ; and retain current section.
            .retainrefs                     ; And retain any sections that have
                                            ; references to current section.

;-------------------------------------------------------------------------------
RESET       mov.w   #__STACK_END,SP         ; Initialize stackpointer
StopWDT     mov.w   #WDTPW|WDTHOLD,&WDTCTL  ; Stop watchdog timer


;-------------------------------------------------------------------------------
; Main loop here
;-------------------------------------------------------------------------------

START 		mov.w #300h,SP

StopWDT 	mov.w #WDTPW+WDTHOLD,&WDTCTL
			call #Init_UART
Mainloop
			call #INCHAR_UART
			call #OUTA_UART




EXIT jmp Mainloop







 ;
OUTA_UART

;----------------------------------------------------------------
; prints to the screen the ASCII value stored in register 4 and
; uses register 5 as a temp value
;----------------------------------------------------------------
; IFG2 register (1) = 1 transmit buffer is empty,
; UCA0TXBUF 8 bit transmit buffer
; wait for the transmit buffer to be empty before sending the
; data out
 		push R5
lpa 	mov.b &UCA1IFG,R5
 		and.b #0x02,R5
 		cmp.b #0x00,R5
 		jz lpa
	; send the data to the transmit buffer UCA0TXBUF = A;
 		mov.b R4,&UCA1TXBUF
 		pop R5
 		ret
INCHAR_UART
;----------------------------------------------------------------
; returns the ASCII value in register 4
;----------------------------------------------------------------
; IFG2 register (0) = 1 receive buffer is full,
; UCA0RXBUF 8 bit receive buffer
; wait for the receive buffer is full before getting the data
 		push R5				; you can store values with push and pop :D cool!
lpb 	mov.b &UCA1IFG,R5
		and.b #0x01,R5
 		cmp.b #0x00,R5
 		jz lpb
 		mov.b &UCA1RXBUF,R4
 		pop R5
; go get the char from the receive buffer
 		ret
Init_UART
;----------------------------------------------------------------
; Initialization code to set up the uart on the experimenter board to 8 data,
; 1 stop, no parity, and 9600 baud, polling operation
;----------------------------------------------------------------
;P2SEL=0x30;
; transmit and receive to port 4 b its 4 and 5
 mov.b #110000b,&P4SEL
; Bits p2.4 transmit and p2.5 receive UCA0CTL0=0
 ; 8 data, no parity 1 stop, uart, async
 mov.b &UCSWRST,&UCA1CTL1
; (7)=1 (parity), (6)=1 Even, (5)= 0 lsb first,
; (4)= 0 8 data / 1 7 data, (3) 0 1 stop 1 / 2 stop, (2-1) --
; UART mode, (0) 0 = async
; UCA0CTL1= 0x41;
 add.b &UCSSEL_2,&UCA1CTL1
; select ALK 32768 and put in software reset the UART
; (7-6) 00 UCLK, 01 ACLK (32768 hz), 10 SMCLK, 11 SMCLK
; (0) = 1 reset
;UCA0BR1=0;
; upper byte of divider clock word
 mov.b #109,&UCA1BR0
;UCA0BR0=3; ;
; clock divide from a clock to bit clock 32768/9600 = 3.413
 mov.b #0x0,&UCA1BR1
; UCA0BR1:UCA0BR0 two 8 bit reg to from 16 bit clock divider
; for the baud rate
;UCA0MCTL=0x06;
; low frequency mode module 3 modulation pater used for the bit
; clock
 mov.b &UCBRS_2,&UCA1MCTL
 add.b &UCBRF_0, &UCA1MCTL

; (7) = 1 echo back trans to rec
; (6) = 1 framing, (5) = 1 overrun, (4) =1 Parity, (3) = 1 break
; (0) = 2 transmitting or receiving data
;UCA0CTL1=0x40;
; take UART out of reset
 mov.b #0x40,&UCA1CTL1
;IE2=0;
; turn transmit interrupts off

; (0) = 1 receiver buffer Interrupts enabled
; (1) = 1 transmit buffer Interrupts enabled
;----------------------------------------------------------------
;****************************************************************
;----------------------------------------------------------------
; IFG2 register (0) = 1 receiver buffer is full, UCA0RXIFG
; IFG2 register (1) = 1 transmit buffer is empty, UCA0RXIFG
; UCA0RXBUF 8 bit receiver buffer, UCA0TXBUF 8 bit transmit
; buffer
 ret
;----------------------------------------------------------------
; Interrupt Vectors
;----------------------------------------------------------------


                                            

;-------------------------------------------------------------------------------
; Stack Pointer definition
;-------------------------------------------------------------------------------
            .global __STACK_END
            .sect   .stack
            
;-------------------------------------------------------------------------------
; Interrupt Vectors
;-------------------------------------------------------------------------------
            .sect   ".reset"                ; MSP430 RESET Vector
            .short  RESET
            
