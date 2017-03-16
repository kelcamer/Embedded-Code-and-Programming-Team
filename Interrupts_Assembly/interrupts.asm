		.cdecls C, LIST, "msp430.h"		; cdecls tells the assembler to allow the C header file


; MAIN


		.text ; program start
		.global _START 		; name entry point

START	mov.w #0x300, SP			; Initialize x1121 Stack pointer
StopWDT mov.w #WDTPW+WDTHOLD, &WDTCTL		; move the watchdog password and watchdog hold into the watchdog control. This basically disables the watchdog.
SetupP1 bis.b #0001b, &P1DIR				; sets port 1 bit 0 as an output
 mov.w #010b, &P1REN
 mov.w #010b, &P1OUT
 mov.w #010b, &P1IE
 mov.w #010b, &P1IFG
; bis.w #1000b, SR							; sets the bit to enable the SR register
 ;bis.w #GIE, SR							; set the global interrupt enable to turn interrupts on

label jmp label

BUTTON	 xor.b #001b, &P1OUT				; blink the light
	reti


	.sect ".int47"
	.word BUTTON
	.sect "reset"
	.short START
	.end

