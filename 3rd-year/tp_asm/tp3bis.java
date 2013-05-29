	.model SMALL
	.586


	.DATA
A	DB ?	; A,B,C chiffres à trouver
B	DB ?	; 
C	DB ?	; 
N1	DW ?	; N1,N2,N3 nombres inclus dans l'addition
N2	DW ?	; 
N3	DW ?	; 
res	DB ?	; booléen, vrai ssi l'opération admet une solution



	.CODE
	
debut:
		STARTUPCODE		
		
		mov res,0	; res := faux

		mov B,1		; pour B := 1 à 9 faire
pour1:
		cmp B,10
		je finpour1

		mov ax,B	; 
		add ax,B	;
		div ax,10	;
		















		mov B,1		; pour B := 1 à 9 faire
 pour2:
		cmp B,10
		je finpour2
		
  si1:
		mov al,A	; si A<>B
		cmp al,B	; 
		je fsi1
		
  alors1:
		mov ax,A	; N1 := A*10 + B 
		mul ax,10	;
		add ax,B	;
		mov N1,ax	;
		
		mov C,1		; pour C := 1 à 9 faire
   pour3:
		cmp C,10
		je finpour3
		
    si2:
		mov al,A	; si C<>A et C<>B alors
		mov bl,B	; 
		*********		
		je fsi2

    alors2:
		mov ax,C	; N2 := C*10 + B
		mul ax,10	;
		add ax,B	;
		mov N2,ax	;
		mov ax,B	; N3 := B*10 + A
		mul ax,10	;
		add ax,A	;
		mov N3,ax	;
		
     si3:
		mov ax,N1	; si N3 = N2 + N1 alors
		add ax,N2	;
		cmp ax,N3	;
		jne fsi3
		
		mov res,1	; res := vrai
		jmp fait	; sortir par succes
		
     fsi3:

    fsi2:

		inc C		; C++
		jmp pour3	; 
   finpour3:
		
  fsi1:
		
		inc B		; B++
		jmp pour2	;
 finpour2:		












		
		inc B		; B++
		jmp pour1	;
finpour1:
		
		

fait:


	EXITCODE
	END debut