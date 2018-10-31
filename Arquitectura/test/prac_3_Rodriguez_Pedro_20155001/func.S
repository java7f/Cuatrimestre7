.data
	format: .asciz "0x%08x\n"
	format2: .asciz "%c"
	.text


	.globl	switchBytes
	.type	switchBytes, @function
switchBytes:
	#int tempx = 0xFF;
	mov $0xFF, %r12
	#tempx = tempx << 8*(x);
	lea (,%rdi,8), %r13
	mov %r13b, %cl
	shl %cl, %r12
	#tempx = tempx & i;
	andq %rdx,%r12
	#tempx = tempx << (8*(y-x));
	mov %rsi, %r14
	subq %rdi,%r14 #y-x
	lea (,%r14,8), %r14
	mov %r14b,%cl
	shl %cl,%r12
	#int tempy = 0xFF;
	mov $0xFF, %r13
	#tempy = tempy << 8*(y);
	lea (,%rsi,8), %r10
	mov %r10b, %cl
	shl %cl, %r13
	#tempy = tempy & i;
	andq %rdx, %r13
	#int tempzero = 0xFF;
	mov $0xFF, %r14
    #tempzero = ~(tempzero << 8*(x));
    lea (,%rdi,8), %r10
    mov %r10b, %cl
	shl %cl, %r14
	notq %r14
    #i = tempzero & i;
    andq %r14,%rdx
    #int tempzero1 = 0xFF;
    mov $0xFF, %r15
    #tempzero1 = ~(tempzero1 << 8*(y));
    lea (,%rsi,8), %r10
    mov %r10b, %cl
	shl %cl, %r15
	notq %r15
    #i = tempzero1 & i;
    andq %r15,%rdx
    #i = i | tempx;
    orq %r12,%rdx
    #i = i | (tempy >> (8*x));
    lea (,%rdi,8), %r10
	mov %r10b, %cl
	sar %cl, %r13
	orq %r13, %rdx
	#printf Implementation
	mov $format, %edi
	mov %rdx, %rsi
	mov $0, %eax
	call printf
	#END
	mov %rdx,%rax
	ret

	.globl	countOnes
	.type	countOnes, @function
countOnes:
	#i = i - ((i >> 1) & 0x55555555);
	mov %rdi, %r10
	mov %rdi, %r11
	shr %r10
	and $0x55555555, %r10
	sub %r10, %r11 #%r11 = i
	mov %r11,%rdi
	#i = (i & 0x33333333) + ((i >> 2) & 0x33333333);
	mov %rdi, %r11
	mov %rdi, %r10
	shr $2, %r10
	and $0x33333333, %r11
	and $0x33333333, %r10
	add %r10,%r11
	mov %r11, %rdi
	#i = (((i + (i >> 4)) & 0x0F0F0F0F) * 0x01010101) >> 24;
	#mov %rdi, %rax
	mov %rdi, %r10
	shr $4, %r10
	add %rdi, %r10
	and $0x0F0F0F0F, %r10
	imul $0x01010101, %r10
	and $0x7F000000, %r10
	shr $24, %r10
	#printf Implementation
	mov $format, %edi
	mov %r10, %rsi
	mov $0, %eax
	call printf
	mov %r10, %rax
	ret


	.globl	lowerToUpper
	.type	lowerToUpper, @function
lowerToUpper:
	  mov %rdi,%r12
  	jmp .restartLoop

  .restartLoop:
  	mov (%r12),%r13
  	and $0xFF,%r13
  	cmp $0,%r13
  	jne .isValid
      ret

  .isValid:
  	cmp $0x61, %r13
  	jb .printValue
  	jge .changeValue

  .changeValue:
  	sub $32, %r13
  	jmp .printValue

  .printValue:
  	mov $format2, %edi
  	mov %r13, %rsi
  	mov $0, %eax
  	call printf
  	add $0x1,%r12
  	jmp .restartLoop

   		 .globl	printHigher
   		 .type	printHigher, @function
printHigher:
   	 #r13 es el counter
    	mov $0,%r13
    	mov $0,%rcx
    	#rbx es MAX
    	call .Loop
    	jmp .printMax

    .Loop:
    	mov (%rdi,%r13,4), %rbx
    	add $1,%r13
    	#r12 = valor actual de rdi[r13]
    	cmp $0, %rbx
    	#si 0 != r12
    	jne .calculateMax
    	ret

    .calculateMax:
   	 cmp %ecx,%ebx
   	 jnbe .assignMax
   	 #r13 es el counter, r13++
   	 cmpq %rsi,%r13
   	 jle .Loop

    .assignMax:
   	 mov %rbx, %rcx
   	 jmp .calculateMax

    .printMax:
    	mov $format, %edi
    	mov %rcx, %rsi
    	mov $0, %eax
    	call printf
    	Ret
