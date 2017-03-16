;-------------------------------------------------------------------------------
; MSP430 Assembler Code Template for use with TI Code Composer Studio
;
;
;-------------------------------------------------------------------------------
            .cdecls C,LIST,"msp430.h"       ; Include device header file
            
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

		.cdecls C, LIST, "msp430.h"		; cdecls tells the assembler to allow the C header file


; MAIN


SetupP1 bis.b #0001b, &P1DIR				; sets port 1 bit 0 as an output
 mov.w #010b, &P1REN
 mov.w #010b, &P1OUT
 mov.w #010b, &P1IE
 mov.w #000b, &P1IFG
 bis.w #1000b, SR							; sets the bit to enable the SR register
 bis.w #GIE, SR							; set the global interrupt enable to turn interrupts on

label jmp label

BUTTON	 xor.b #001b, &P1OUT				; blink the light
		mov.w #000b, &P1IFG
	reti





                                            

;-------------------------------------------------------------------------------
; Stack Pointer definition
;-------------------------------------------------------------------------------
            .global __STACK_END
            .sect   .stack
            
;-------------------------------------------------------------------------------
; Interrupt Vectors
;-------------------------------------------------------------------------------
			.sect ".int47"
			.word BUTTON
            .sect   ".reset"                ; MSP430 RESET Vector
            .short  RESET
            
