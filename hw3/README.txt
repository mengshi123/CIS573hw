 #CIS hw3 Mengshi.
Line 102-105 in MyPrime.java cannot be covered.
Reason: isPrime(n) can only be called by nextPrime(n). In nextPrime(n): 
    if n<0, it will raise IllegalArgumentException so that isPrime(n) will not be called. 
    If n = 0 => n = 1 and if n = 1, nextPrime(n) will immeditely return 2 and isPrime(n) will not be called.
So as we can see, when n < 2, isPrime(n) will not be called by nextPrime(n). So that this piece of code:
 if (n < 2) {
            return false;
        }
is unreachable.

    Line 148 in MyPrime.java cannot be covered.
In order to cover this line, y must be 0; So (y * y) mod n = 0; we now y = a ^ r mod n. We know 
(a ^ br) ^ 2 mod n ^ 3 = 0. n = a^(br*2/3). And br = n-1 mod 2^s. and s = numberOfTrailingZeros(n-1);
We know this chance would be very tiny. In fact, after running all integer. j++ still cannot be reached.
So this piece of code is still unreachable.