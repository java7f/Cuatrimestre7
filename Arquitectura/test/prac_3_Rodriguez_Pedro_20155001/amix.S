.section .data
	MenuMessage:
    .string "MENU:\n1.Fibonacci\n2.Upper case (desde text.txt)\nElija una opcion\n"

	IntroMessageToFib:
    .asciz "Introduzca el numero de elementos a imprimir en formato XXX:\n"

	IntroMessageTheFib:
    .asciz "Los primeros elementos de la serie son:\n"

	format:
     .string "\n"

	BufferForFormat:
    .string ""

    BufferForFib:
   	 .string ""

    BufferForA:
   	 .string ""

    BufferForB:
   	 .string ""

	BufferForC:
    .asciz ""

	BufferForS:
   	.asciz ""

   	NameOfFile:
   	.asciz "text.txt"

   	InputForFile:
   	.string ""

   	OutputForFile:
   	.string ""

   	newLine:
   	.string "\n"

	.text
	.global _start

#Starts Program
_start:
	call .menu

	.globl	.menu
	.type 	.menu, @function
.menu:
	mov $1, %rax
	mov $1, %rdi
	mov $MenuMessage, %rsi
	mov $66, %rdx
	syscall

	#Read the Option you want
	mov $0,%rax
	mov $0,%rdi
	mov $BufferForC,%rsi
	mov $1,%rdx
	syscall
    mov $BufferForC,%rdi  #Moves the number in buffer to rdi

    #Dereferencing rdi
    mov (%rdi),%rdi

    and $0xFF,%rdi
    sub $0x30,%rdi

	cmp $2, %rdi
	je .SecondOption
    cmp $1,%rdi
    je .FibonacciMain
    jge .menu

	mov $0,%rax
	mov $0,%rdi
	mov $BufferForA,%rsi
	mov $1,%rdx
	syscall
    jmp .menu

.exit:
	mov $60,%rax
	mov $0,%rdi
	syscall

 .FibonacciMain:
	mov $0,%rax
	mov $0,%rdi
	mov $BufferForB,%rsi
	mov $1,%rdx
	syscall

	#Num of Elements to include
	mov $1, %rax
	mov $1, %rdi
	mov $IntroMessageToFib, %rsi
	mov $60, %rdx
	syscall

	mov $0,%rax
	mov $0,%rdi
	mov $BufferForFib,%rsi
	mov $3,%rdx
	syscall

	#Additional Space is Added to a Buffer
	mov $0,%rax
	mov $0,%rdi
	mov $BufferForS,%rsi
	mov $1,%rdx
	syscall

    mov $BufferForFib,%rdi

    mov (%rdi),%rdi

    mov $0,%r12

    #3 Digits for Fibonacci
    mov %rdi,%r11
    and $0xFF,%r11 # toma de primero digito, almacenamiento en r12
    sub $0x30,%r11 #banda a hex, usar 47
    imul $100,%r11

    add %r11,%r12

    mov %rdi,%r11
    and $0xFF00,%r11 # toma de segundo digito, almacenamiento en r12
    shr $8,%r11
    sub $0x30,%r11
    imul $10,%r11

    add %r11, %r12
    mov %rdi,%r11
    and $0xFF0000,%r11
    shr $16,%r11
    sub $0x30,%r11
    add %r11, %r12
#------------------Fibonacci Starts

	mov $1, %rax
	mov $1, %rdi
	mov $IntroMessageTheFib, %rsi
	mov $42, %rdx
	syscall

	push	%rbx
	mov 	%r12d, %ecx
	xor 	%rax, %rax
	xor 	%rbx, %rbx
	inc 	%rbx
	jmp 	.Fibonacci

.Fibonacci:
	push	%rax
	push	%rcx

	mov 	%rax,%r12
    mov   	%rax,%rsi
    push    %r12
    call    .ToCountLength
    pop   	 %r12
    mov   	 $0, %rax
    mov   	 %r14,%r8

	call 	.OneDigitPrint
	call     .MultipleDigitPrint

	#New Line
	mov 	$1,	%rax
	mov 	$1,	%rdi
	mov 	$format, %rsi
	mov 	$1,	%rdx
	syscall

	#Actual Fibonacci Algorithm
	pop 	%rcx
	pop 	%rax
	mov 	%rax, %rdx
	mov 	%rbx, %rax
	add 	%rdx, %rbx
	dec 	%ecx
	jnz 	.Fibonacci
	pop 	%rbx

	mov 	$1,	%rax             	#Write
	mov 	$1,	%rdi            	#File Handle 1
	mov 	$format, %rsi
	mov 	$1,	%rdx
	syscall
	movq $0,BufferForC
	jmp .menu

#-----------------------------------------------------------
.ToCountLength:
    cmp $10, %r12
    jl    .ToReturn
    mov $0, %r12 #Clears result for next Loop
    add $1, %r14 #length counter
    jge .Loop

.Loop:
    sub $10, %rsi
    add $1, %r12
    cmp $10, %esi
    jge .Loop

    #Remainder is r13
    mov %rsi,%r13
    #Division Result
    mov %r12, %rsi
    jmp .ToCountLength
#-----------------------------------------------------------
.OneDigitPrint:
    cmp $10, %r12
    jge .ToReturn

	mov  %r12, %rax
	add  $0x30, %rax
	mov  %rax, BufferForFormat

	# Printf Implementation
	mov 	$1,	%rax
	mov 	$1,	%rdi
	mov 	 $BufferForFormat,%rsi
	mov 	$2,	%rdx
	syscall
	ret

.MultipleDigitPrint:
    cmp $10, %r12
    jl .ToReturn
    mov %r12, %rsi
    mov $0, %r12
    call .FirstPartSecondLoop

    add $1, %r8
    mov %rax, BufferForFormat

    #Printf Implementation
    mov 	$1,	%rax
	mov 	$1,	%rdi
	mov 	 $BufferForFormat,%rsi
	mov 	%r8,	%rdx
	syscall
    ret

.FirstPartSecondLoop:
	#R13 is number to print
    cmp $10, %esi
    jl .ToReturn
    call .SecondLoop
    add $0x30, %r13
    mov %r14, %r12 #For Shifting
    sub $1, %r14
    lea (,%r12,8), %r12
    mov %r12b, %cl
    shl %cl, %r13
    add %r13, %rax
    mov $0, %r12
    call .FinalMorePrint
    jmp .FirstPartSecondLoop

.SecondLoop:
    #Division
    sub $10, %rsi
    add $1, %r12
    cmp $10, %esi
    jg .SecondLoop

    mov %rsi,%r13
    mov %r12, %rsi
    mov $0, %r12
    ret

.FinalMorePrint:
    cmp $0, %r14
    jne .ToReturn
    add $0x30, %rsi
    add %rsi, %rax
    mov $0, %r12
    cmp $1, %r14
    sub $0x30, %rsi

.ToReturn:
    ret

#---------------------------L2U Option
.SecondOption:
	mov $0,%rax
	mov $0,%rdi
	mov $BufferForB,%rsi
	mov $1,%rdx
	syscall

    mov $2,%rax
    mov $NameOfFile,%rdi
    mov $0,%rsi
    mov $0,%rdx
    syscall

    mov $0, %rax
    mov $3, %rdi
    mov $InputForFile, %rsi
    mov $1,%rdx
    syscall

    mov $InputForFile, %rdi
    mov (%rdi),%rdi
    and $0xFF,%rdi
    jmp .CheckifValid


.CheckifValid:
  	cmp $0x61, %rdi
  	jl .printValue
  	jg .changeValue

.changeValue:
  	sub $0x20, %rdi
  	jmp .printValue

.printValue:
   	mov %rdi, OutputForFile
   	mov %rdi, %r12

# Printf Implementation
	mov 	$1,	%rax
	mov 	$1,	%rdi
	mov 	$OutputForFile,%rsi
	mov 	$1,	%rdx
	syscall

	movq $0,OutputForFile
	movq $0,InputForFile

	 mov $0, %rax
	 mov $3, %rdi
	 mov $InputForFile, %rsi
	 mov $1,%rdx
	 syscall
	 mov $InputForFile, %rdi
	 mov (%rdi),%rdi
	 cmp %rdi,%r12
	 je .ExitSecondOption
	 jmp .CheckifValid


.ExitSecondOption:

	mov $3,%rax
	mov $3,%rdi
	syscall

	mov 	$1,	%rax
	mov 	$1,	%rdi
	mov 	$format, %rsi
	mov 	$1,	%rdx
	syscall

	movq $0,BufferForC
	jmp .menu
