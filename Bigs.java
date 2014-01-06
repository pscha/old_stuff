class Bigs {

        //test a single Array
	static void check(int[] n) {
		if (!ok(n)) {
			System.out.println("Zahl kaputt");
		}
	}

        //test the arrays
	static void check(int[]a, int[]b) {
		check(a);
		check(b);
	}

	// addiert die Ziffernfelder a und b
	public static int[ ] add (int[ ] a, int[ ] b)
	{
            int c;

            if(a.length <= b.length){
                c = b.length;
            }
            else{
                c = a.length;
            }
            int[] result = new int[c+1];
            int[] anew = new int[c+1];
            int[] bnew = new int[c+1];

            System.arraycopy(a, 0, anew, 0, a.length );
            System.arraycopy(b, 0, bnew, 0, b.length );
            int test;
            int ubertrag = 0;
            int i;
            for(i = 0; i < c; i++){
                test = anew[i]+bnew[i]+ubertrag;
                ubertrag = test / 10;
                test = test % 10;
                result[i] = test;
            }
            result[i] = ubertrag;
            return(leadingZero(result));
        }

	// gibt das Ziffernfeld n in lesbarer dezimaler Form (ohne Newline) aus
	static void print (int[ ] n)
	{
            for(int i = n.length -1; i >= 0; i--){
                System.out.print(n[i]);
            }
        }

	// gibt das Ziffernfeld n in lesbarer dezimaler Form (mit Newline) aus
	static void println (int[ ] n)
	{
            for(int i = n.length -1; i >=0 ;i--){
                System.out.print(n[i]);
            }
            System.out.println("");
        }

	// konstruiert ein einstelliges Ziffernfeld aus d
        static int[ ] digit(int d)
	{
            int[] result = new int[1];
            result[0] = d;
            return(result);
        }

	// konstruiert das Ziffernfeld, welches den Wert Null repraesentiert
	static int[ ] Null()
	{
            int[] result = new int[1];
            result[0] = 0;
            return(result);
        }

	// konstruiert das Ziffernfeld, welches den Wert Eins repraesentiert
	static int[ ] One()
	{
            int[] result = new int[1];
            result[0]= 1;
            return(result);
        }

	// Rest des Ziffernfeldes n bei Division durch 10 (eine int-Zahl!)
	static int mod10(int[ ] n)
	{
            return(n[0]);
        }

	// ganzzahliger Quotient bei Division durch 10
	static int[ ] div10(int[ ] n)
	{
            int[] result= new int[n.length -1];
            for(int i = 0; i < result.length; i++){
                result[i] = n[i+1];
            }
            return(result);
        }

        //delete leading zeroes
        static int[] leadingZero(int[] n){
            int i;

            for(i = n.length -1; n[i] == 0 && i > 0 ; i--);

            int[] result = new int[i+1];

            for(;i >= 0; i--){
                result[i] = n[i];
            }

            return(result);
        }

	// Umwandlung einer int-Zahl in ein Ziffernfeld
	static int[ ] fromInt(int n)
	{
            int length = String.valueOf(n).length();
            int[] result = new int[length];
            int i = 0;
            while(n > 0){
                result[i++]= n % 10;
                n /= 10;
            }
            return(result);
        }

	// kopiert den Wert von n
	static int[ ] copy(int[ ] n)
	{
            return(n);
        }

	// multipliziert das Ziffernfeld n mit einer int-Zahl
	static int[ ] times(int[ ] n, int d)
	{
            int[] result = new int[n.length+1];
            int ubertrag = 0;
            int i;
            for(i = 0; i < n.length; i++){
                result[i] = n[i] * d + ubertrag;
                ubertrag = result[i] / 10;
                result[i] = result[i] % 10;
            }
            result[i] = ubertrag;
            return(leadingZero(result));
        }

	// multipliziert das Ziffernfeld n mit 10
	static int[ ] times10(int[ ] n)
	{
            int[] result = new int[n.length+1];
            result[0] = 0;
            for(int i = 1; i < result.length; i++){
                result[i] = n[i-1];
            }
            return(result);
        }

	// multipliziert zwei Ziffernfelder
	static int[ ] times(int[ ]a, int[ ] b)
	{
            int ubertrag = 0;
            int[] result = new int [(a.length + b.length)];
            for(int i = b.length-1; i>=0; i--){
                result = add(times10(result),times(a,b[i]));
            }
            return(leadingZero(result));
        }

	// Test auf kleiner-Relation zweier Ziffernfelder: a < b ?
	static boolean less (int[ ] a, int[ ] b)
	{
            if(a.length < b.length){
                return(true);
            }
            else if(b.length < a.length){
                return(false);
            }
            else{ // a und b glich lang
                int i;
                for(i = a.length-1;( a[i] == b[i] && i != 0); i--){

                }
                if(a[i]<b[i]){
                    return(true);
                }
                else{
                    return(false);
                }
            }
        }

	// Test auf Gleichheit zweier Ziffernfelder
	static boolean equal (int[ ] a, int[ ] b)
	{
            if(a.length < b.length){
                return(false);
            }
            else if(b.length < a.length){
                return(false);
            }
            else{ // a und b glich lang
                int i;
                for(i = a.length;( a[i] == b[i] && i != 0 ); i--){

                }
                if(a[i] == b[i]){
                    return(true);
                }
                else{
                    return(false);
                }
            }

        }

	// Test auf Korrektheit eines Ziffernfeldes: Fled existieert, alle Positionen zwischen 0 und 9, keine fuehrenden Nullen (ausser bei Null selbst)
	static boolean ok (int[ ] n)
	{
            for(int i = 0; i < n.length; i++){
                if(n[i] >= 10){
                    return(false);
                }
            }
            if(n.length >=2 ){
                if(n[n.length-1] == 0){
                    return(false);
                }
            }
            return(true);
        }

	public static void main (String[ ] s) {
        int[] a = One();
		for (int i=0; i<9989; ++i) {
                    a = times(a, 2);
		}
        	System.out.println("hat " + a.length + " Stellen");
		println(a); // 2 hoch 9989

		int[] b = fromInt(12345679);
		int[] c = copy(b);

		for (int i=1; i<424; ++i) {
                    c=times(c, b);
        //           println(c);
		}

		System.out.println("hat " + c.length + " Stellen");
		println(c);  // 12345679 hoch 424

		System.out.println(less(a, c)); // beantwortet die Frage aus der Aufgabenueberschrift
        }
}
