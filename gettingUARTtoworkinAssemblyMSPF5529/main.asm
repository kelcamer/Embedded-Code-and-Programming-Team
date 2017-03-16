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
;------------
			call #uart_init
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

uart_init:      mov.b #(BIT4 | BIT5), &P4SEL    ;; pin function select
                mov.b #UCSWRST, &UCA1CTL1       ;; reset the USI
                bis.b #UCSSEL_2, &UCA1CTL1      ;; select clock source
                mov.b #109, &UCA1BR0            ;; set baud
                mov.b #0, &UCA1BR1
                mov.b #(UCBRS_2 | UCBRF_0), &UCA1MCTL   ;; modulation
                bic.b #UCSWRST, &UCA1CTL1       ;; release the USI
                ret

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

