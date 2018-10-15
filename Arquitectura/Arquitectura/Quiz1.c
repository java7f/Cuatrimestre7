//Ejercicio 3.58 

long decode2(long x, long y, long z)
{
    y = y-z;
    x = x*y;
    y << 63;
    y >> 63;

    return x ^ y;
}

//Ejercicio 3.60
long loop(long x, int n)
{
    long result = 0;
    long mask;
    for(mask = 1; mask != 0; mask = mask<<n)
    {
        result |= (mask & x);
    }

    return result;
}

//Ejercicio 3.62
typedef enum { MODE_A, MODE_B, MODE_C, MODE_D, MODE_E } mode_t;
long switch3(long *p1, long *p2, mode_t action)
{
    switch(action)
    {
        case MODE_A:
        result = *p2;
        *p2 = *p1;
        break;
        case MODE_B:
        result = *p1 + *p2;
        *p1 = result;
        break;
        case MODE_C:
        *p1 = 59;
        result = *p2;
        break;
        case MODE_D:
        *p1 = *p2;
        result = 27;
        break;
        case MODE_E:
        result = 27;
        break;
        default: 
        result = 12;
        break;

    }

    return result;
}

// Ejercicio 3.63
long switch_prob(long x, long n)
{
    long result = x;

    switch(n)
    {
        case 61:
        result = 8*x;
        break;
        case 62:
        result = 8*x;
        break;
        case 63:
        result = x>>3;
        break;
        case 64:
        result = (x<<4) - x;
        x = result;
        case 65:
        x *= x;
        default:
        result = x +75;
    }

    return result;
}

//Ejercicio 3.65
/** 
 * %RDX apunta a A[i][j]
 * %RAX apunta a A[j][i]
 * M =15
 */
