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



SetupP1 bis.b #001b,&P1DIR



Mainloop
 bis.b #001b,&P1OUT
; Toggle P2.2
; Move the value of 0xA000 into register 7 so to create a delay


 mov.w #0xA000,R7

 ; Delay with a loop is not
 ; the best way; interrupts
 ; are better Given in Lab 6
; Decrement register 7 until it’s zero. Stay in this loop until
; register 7 is zero

;On bic.b #0x04, &P2OUT
L1 dec.w R7

; Decrement R7
 jnz L1 ; Delay over?
; Let’s run the program forever
                                            

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
            
