class Coins {
	public static void main(String[] s) {
            /**
             *  takes: [-c] number(1-2000)
             *  the argument -c toggles caching on so the values will be
             *  generated much faster
             */

            String errorMessageNumber = "I think you made a mistake in the number you gave me. Remember that I am only able to calculate with numbers between 0 and 2000 which I will interprete as a value in cents.";
            String errorMessageArgument = "You may have forgotten to give me a value between 0 and 2000 which I may use to calculate the different variants you have do give someone this value in coins. By the way: I'll take the value as a value in cents, so 100 cents are one euro.";
            if(s.length == 2 && s[0].matches("-c") && s[1].matches("2000|1?[0-9]{1,3}")){
                System.out.print(euro(Integer.valueOf(s[1])));
                System.out.print(" kann auf ");
                System.out.print(payCache(Integer.valueOf(s[1]),0));
                System.out.println(" verschiedene Arten passend bezahlt werden");
            }
            if(s.length == 1 && s[0].matches("2000|1?[0-9]{1,3}")){
                System.out.print(euro(Integer.valueOf(s[0])));
                System.out.print(" kann auf ");
                System.out.print(pay(Integer.valueOf(s[0]),0));
                System.out.println(" verschiedene Arten passend bezahlt werden");
            }
            if(s.length <= 0 || s.length > 2){
                System.out.println(errorMessageArgument);
            }
            if((s.length == 1 && !s[0].matches("2000|1?[0-9]{1,3}")) || (s.length == 2 && s[0].matches("-c") && !s[1].matches("2000|1?[0-9]{1,3}"))){
                System.out.println(errorMessageNumber);
            }
        }

        //public static long [][] cache = new long[10001][8];
        public static long [][] cache = new long[2001][8];
            /*
             *  The cache of exercise part 2.
             *  In here every calculated value can be found.
             *  The size is because the maximum value is 2000 and there are 8
             *  types of coins.
             */

        public static long pay (int m, int n){
            /**
             *  Recursive method to find every possible distribution of
             *  coins to match the inserted value
             *  takes: (int m, int n) where m is the value and n is the
             *  position of the biggest coin
             */
            int value = m;
            int maxCoin = n;
            int[] coins = {200,100,50,20,10,5,2,1};
            if(value == 1 || maxCoin == 7){
                //you get here if you have either 1cent left, or your coin
                //value is 1cent
                return(1);
            }
            if(value - coins[maxCoin] < 0){
                //here you get by having a value smaller than the coin value
                return(pay(value , (maxCoin +1)));
            }
            return(pay(value - coins[maxCoin] , maxCoin) + pay(value , (maxCoin + 1)));
        }

        public static long payCache (int m, int n){
            /**
             *  the structure is similar to pay, but the caluclation contains
             *  a cache so the results can be looked up and don't have to
             *  be calculated over and over
             *  takes: (int m, int n) where m is the value and n is the
             *  position of the biggest coin
             */
            int value = m;
            int maxCoin = n;
            int[] coins = {200,100,50,20,10,5,2,1};
            if(cache[value][maxCoin] <= 0){
                //first check if value has been calculated before
                if(value == 1 || maxCoin == 7){
                    //is the value bigger than one cent?
                    cache[value][maxCoin] = 1;
                    return(cache[value][maxCoin]);
                }
                if(value - coins[maxCoin] < 0){
                    //are the convalues bigger then the value?
                    cache[value][maxCoin] = payCache(value , (maxCoin +1));
                    return(cache[value][maxCoin]);
                }
                //else calculate normally
                cache[value][maxCoin] = payCache(value - coins[maxCoin] , maxCoin) + payCache(value , (maxCoin + 1) );
                return(cache[value][maxCoin]);
            }
           // System.out.println("in");
            return(cache[value][maxCoin]);
        }

	public static String million() {
            long counter = 0;
            int value = 0;
            while(counter < 1000000){
                counter = payCache(++value,0);
            }
            return(euro(value));
        }

	public static String billion() {
            long counter = 0;
            int value = 0;
            while(counter < 1000000000){
                value += 1;
                counter = payCache(value,0);
            }
            return(euro(value));
        }

	public static String euro(int cent) {
                /**
                 *  a funcion to convert a value in cent to a String in euro
                 *  takes: (int m) where m is a value in cent with 100 cent =
                 *  1 euro
                 */
                String euroString = String.valueOf(cent / 100);
                String centString;
                if(cent % 100 < 10){
                    centString = ("0" + String.valueOf(cent % 100));
                }
                else{
                    centString = String.valueOf(cent % 100);
                }
                String euro = (euroString + "," + centString + " Euro");
                return(euro);
        }
}
