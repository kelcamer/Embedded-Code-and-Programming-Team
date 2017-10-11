 ;.cdecls C,LIST,"msp430fg4618.h"

; .sect ".const"

 ;.bss label, 4

 ;.word 0x1234

; .text
; .global _START

;----------------------------------------------------------------
;START 		mov.w #300h,SP

;StopWDT 	mov.w #WDTPW+WDTHOLD,&WDTCTL
; uncomment the lab stuff above this line
; delete below this line for lab
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




;;;;;;;;;;;;;;;;;;;


			call #Init_UART
Mainloop
			;call #part1
			;call #part2
			;call #part3
			call #part4



EXIT jmp Mainloop




part1

		call #INCHAR_UART

		mov.b R4, R7	; R7 will represent first
		call #INCHAR_UART
	;	call #OUTA_UART
		mov.b R4, R8	; R7 will represent second
	;	call #OUTA_UART


convertFirst		cmp.b #0x30, R7
					jge next11

next11			cmp.b #0x39, R7
				jl addop1

				cmp.b #0x39, R7
				jeq addop1
				jmp next21

addop1		sub.b #0x30, R7
			jmp cont

next21		cmp.b #0x41, R7
			jge next31

next31		cmp.b #0x46, R7
			jl subtract1

next51		cmp.b #0x46, R7
			jeq subtract1

subtract1	sub.b #0x37, R7

			jmp cont

cont


convertSec		cmp.b #0x30, R8
					jge next112

next112			cmp.b #0x39, R8
				jl addop12

				cmp.b #0x39, R8
				jeq addop12
				jmp next212

addop12		sub.b #0x30, R8
			jmp cont2

next212		cmp.b #0x41, R8
			jge next312

next312		cmp.b #0x46, R8
			jl subtract12

next512		cmp.b #0x46, R8
			jeq subtract12

subtract12	sub.b #0x37, R8

			jmp cont2

cont2

 rla R7
 rla R7
 rla R7
 rla R7


 add.b R7, R8

 mov.b R8, R4



 call #OUTA_UART


Exit1 ret
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; input S output 53
part2


;char second = c & 0x0F;		//0x15 = 1111
;	char first = c;
	;first = c & 0xF0;
	;first = first >> 4;

	;OUTA_UART(getHexVal(first));
	;OUTA_UART(getHexVal(second));

	call #INCHAR_UART
	mov.b R4, R7	; R7 will represent second, R8 will represent first
	mov.b R7, R8
	and.b #0x0F,R7
	and.b #0xf0, R8
  rra R8
  rra R8
  rra R8
  rra R8

;;; good until this

convertFirst2		cmp.b #0x30, R7
					jge next1123

next1123			cmp.b #0x39, R7
				jl addop123

				cmp.b #0x39, R7
				jeq addop123
				jmp next2123

addop123		add.b #0x37, R7
				jmp cont3

next2123		cmp.b #0x41, R7
			jge next3123

next3123		cmp.b #0x46, R7
			jl subtract123

next5123		cmp.b #0x46, R7
			jeq subtract123

subtract123	add.b #0x37, R7

			jmp cont3

cont3


convertSec3		cmp.b #0x30, R8
					jge next11234

next11234			cmp.b #0x39, R8
				jl addop1234

				cmp.b #0x39, R8
				jeq addop1234
				jmp next21234

addop1234		add.b #0x30, R8
			jmp cont234

next21234		cmp.b #0x41, R8
			jge next31234

next31234		cmp.b #0x46, R8
			jl subtract1234

next51234		cmp.b #0x46, R8
			jeq subtract1234

subtract1234	add.b #0x37, R8

			jmp cont234

cont234


	mov.b R8, R4
 call #OUTA_UART

	mov.b R7, R4
 call #OUTA_UART

part2exit ret
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
part3
		call #INCHAR_UART

		call #OUTA_UART


CHECKUPPER		cmp.b #0x41, R4
				jge next1

next1			cmp.b #0x5A, R4
				jl addop

				cmp.b #0x5A, R4
				jeq addop
				jmp next2

addop		add.b #0x20, R4
		call #OUTA_UART
		jmp Exit
next2		cmp.b #0x61, R4
			jge next3

next3		cmp.b #0x7A, R4
			jl subtract

next5		cmp.b #0x7A, R4
			jeq subtract

subtract	sub.b #0x20, R4
			call #OUTA_UART
			jmp Exit




Exit ret


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; part4
part4

		mov.b #0x0, R10			; the counter for the loop
		mov.b #0x07, R11				; shift amount for each character
		mov.b #0x0, R12			; R12 stores the binary number
loop
		cmp #0x08, R10
		jge exitloop
		call #INCHAR_UART
		mov.b R4, R7				; R7 now stores each character
		call #OUTA_UART			; echo back what you recieve

		sub #0x30, R7		; subtract 30 from R7 to change ascii value into 1 or 0
		rla R7, R11				; shift the 1 or zero over based on how many times you've looped
		sub #0x01, R11			; decrement the shift amount each time you loop
		add R7, R12				; add the 1 or zero to whatever was previously stored in R12

		add #0x01, R10
 jmp loop

exitloop
	mov.b R12, R13	; R12 will store upperhalf of number, R13 stores lower half.
	and.b #0xF0, R12		; first bits
	and.b #0x0F, R13		; second bits
	rra R12
	rra R12
	rra R12
	rra R12				; shifts the bits in R12 to the right 4 times to get rid of zeros




convert			cmp.b #0x0A, R12
					jge checkNext

checkNext		cmp.b #0x0F, R12
				jl addConvert

				cmp.b #0x0F, R12
				jeq addConvert
				jmp checkNext2

addConvert		add.b #0x37, R12
				jmp dontadd				; finish and compare second num

checkNext2		cmp.b #0x00, R12
				jge checkNext3

checkNext3		cmp.b #0x09, R12
				jl convertAdd

				cmp.b #0x09, R12
				jeq convertAdd
				jmp dontadd

convertAdd		add.b #0x30, R12

dontadd

				cmp.b #0x0A, R13
				jge checkNext23

checkNext23		cmp.b #0x0F, R13
				jl addConvert2

				cmp.b #0x0F, R13
				jeq addConvert2
				jmp checkNext22

addConvert2		add.b #0x37, R13
				jmp finish2

checkNext22		cmp.b #0x00, R13
				jge checkNext33

checkNext33		cmp.b #0x09, R13
				jl convertAdd2

				cmp.b #0x09, R13
				jeq convertAdd
				jmp finish2

convertAdd2 		add.b #0x30, R13




; Now you've converted everything and you can print


finish2 mov.w R12, R4
 	call #OUTA_UART
 	mov.w R13, R4
 	call #OUTA_UART


	mov.w #0x0A, R4
	call #OUTA_UART

	mov.w #0x0D, R4
	call #OUTA_UART


Exit2 ret

; MY CHIP CODE DELETE BEFORE SUBMITTING

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

Init_UART:      mov.b #(BIT4 | BIT5), &P4SEL    ;; pin function select
                mov.b #UCSWRST, &UCA1CTL1       ;; reset the USI
                bis.b #UCSSEL_2, &UCA1CTL1      ;; select clock source
                mov.b #109, &UCA1BR0            ;; set baud
                mov.b #0, &UCA1BR1
                mov.b #(UCBRS_2 | UCBRF_0), &UCA1MCTL   ;; modulation
                bic.b #UCSWRST, &UCA1CTL1       ;; release the USI
                ret






 ; LAB UART CODE UNCOMMENT TO TEST IN Lab

;OUTA_UART

;----------------------------------------------------------------
; prints to the screen the ASCII value stored in register 4 and
; uses register 5 as a temp value
;----------------------------------------------------------------
; IFG2 register (1) = 1 transmit buffer is empty,
; UCA0TXBUF 8 bit transmit buffer
; wait for the transmit buffer to be empty before sending the
; data out
; 		push R5
;lpa 	mov.b &IFG2,R5
 ;		and.b #0x02,R5
 ;		cmp.b #0x00,R5
 ;		jz lpa
;	; send the data to the transmit buffer UCA0TXBUF = A;
 ;		mov.b R4,&UCA0TXBUF
 ;		pop R5
 ;		ret
;INCHAR_UART

; 		push R5
;lpb 	mov.b &IFG2,R5
;		and.b #0x01,R5
 ;		cmp.b #0x00,R5
 ;		jz lpb
 ;		mov.b &UCA0RXBUF,R4
 ;		pop R5
; ;go get the char from the receive buffer
 	;	ret
;Init_UART
;;----------------------------------------------------------------
; ;Initialization code to set up the uart on the experimenter board to 8 data,
; 1; stop, no parity, and 9600 baud, polling operation
;----------------------------------------------------------------
;mov.b #0x30,&P2SEL

 ;mov.b #0x00,&UCA0CTL0

 ;mov.b #0x41,&UCA0CTL1
 ;mov.b #0x00,&UCA0BR1
 ;mov.b #0x03,&UCA0BR0
 ;mov.b #0x06,&UCA0MCTL
 ;mov.b #0x00,&UCA0STAT
 ;mov.b #0x40,&UCA0CTL1
 ;mov.b #0x00,&IE2

; ret
;----------------------------------------------------------------
; Interrupt Vectors
;----------------------------------------------------------------

 .sect ".reset" ; MSP430 RESET Vector
 .short START ;
 .end

