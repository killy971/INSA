;//-----------------------------//

	this_	EQU 0
	binf	EQU 0
	bsup	EQU 1
	t_param	EQU 2

;//--- Définition des macros ---//

aload macro i
	mov si,i
	sal i,1
	push word ptr [bp+si]
	endm

iload macro i
	mov si,i
	sal i,1
	push word ptr [bp+si]
	endm
	
istore macro i
	mov si,i
	sal si,1
	pop word ptr [bp+si]
	endm

getfield_ macro i
	mov si,i
	sal si,1
	mov bx,[bp+this_]
	push word ptr [bx+si]
	endm

ireturn macro
	pop ax
	leave
	ret t_param

iinc macro i step
	mov si,i
	sal si,1
	mov ax,[bp+si]
	add ax,step
	mov [bp+si],ax
	endm

iadd macro
	pop ax
	pop bx
	add ax,bx
	push ax
	endm

imul_ macro 
	pop bx
	pop ax
	mul bl
	push ax
	endm

goto_ macro label
	jmp label
	endm

ifne macro label
	pop ax
	cmp ax,0
	jnz label
	endm

if_icmple macro label
	pop bx
	pop ax
	cmp ax,bx
	jbe label
	endm

;//-- Code Jasmin sans modifs --//

	.CODE
calcul:
	aload 0
	getfield_ binf
	istore 3
	iload 1
	istore 2
	goto_ incfait
faire:
	iload 1
	ifne sinon
	iload 2
	iload 3
	iadd
	istore 2
	goto_ fsi
sinon:
	iload 2
	iload 3
	imul_
	istore 2
fsi:
	iinc 3 1
incfait:
	iload 3
	aload 0
	getfield_ bsup
	if_icmple faire
	iload 2
	ireturn
;.end method


;public staic void main(String[] args)

;//-----------------------------//

	.DATA

ia	DW	1,3
ib	DW	2,4
locauxa	DW	ia,0,?,?
locauxb	DW	ib,1,?,?

;//-----------------------------//

	.CODE
debut:
	STARTUPCODE
;ia.calcul(0);
	lea bp,locauxa
	call calcul
	
;ib.calcul(1);
	lea bp,locauxb
	call calcul
	
	nop
	EXITCODE
	END debut