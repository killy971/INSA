i	EQU -2
j	EQU -4
k	EQU -6
T	EQU 4
TL	EQU 6

n	EQU 5 ; const n = 5

L0	DW 5,0,0,0,0
L1	DW 42,7,0,0,0
L2	DW 6,12,2,0,0
L3	DW 8,13,3,38,0
L4	DW 9,42,1,99,21

T1LIG	DW L0,L1,L2,L3,L4

T1	DW T1LIG

mov bx,n	; m = (n*(n+1)) div 2
mov ax,bx
inc bx
mul bx
mov ax,bx
mov bx,2
div bx		; // pas besoin de remettre ah à 0 étant
mov m,bx	; // donné que ax était pair avant la division

TL1	DW m DUP (?)	; // ??


copie:				; rien fonction copie(ref T: matrice d'entiers ; ref TL : tableau d'entiers)
	enter 6,0		; var i,j,k: entier;
					; debut
	mov [bp+k],0	; 	k:=0

	mov [bp+i],0
pour1:				; 	pour i:=0 à n-1 faire
	mov ax,[bp+i]
	mov bx,n
	cmp ax,bx
	jea fp1

	mov [bp+j],0
pour2:				; 		pour j:=0 à i faire
	mov ax,[bp+j]
	mov bx,[bp+i]
	cmp ax,bx
	jea fp2

					; 		debut
	mov bx,[bp+T]
	sal si,1
	mov si,[bp+i]
	mov bx,[bx+si]
	mov si,[bp+j]
	sal si,1
	mov ax,[bx+si]
	
	mov bx,[bp+TL]
	mov si,[bp+k]
	sal si,1
	mov [bx+si],ax	; 			TL[k] := T[i,j]

	inc [bp+k]		; 			k:=k+1
	
	inc [bp+j]
	jmp pour2
fp2:				; 		fin

	inc [bp+i]
	jmp pour1
fp1;				; 	fin
fincopie			; fin



					; var T1: matrice d'entiers:={{5,0,0,0,0},
					; 							  {42,7,0,0,0},
					; 							  {6,12,2,0,0},
					; 							  {
					; 
					; var TL1: tableau [m] d'entiers;
					; 
debut:				; debut
	STARTUPCODE
	
	lea ax,T1
	push ax
	lea ax,TL1
	push ax
	
	call copie		; copie(T1,TL1);

	nop
	END debut		; fin;