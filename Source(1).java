// Marcin Sztukowski - nr grupy 3

import java.util.Scanner;

class Int_klasa // ta klasa robi za inta ktory mozna wrzucac do funkcji jako referencja i modyfikowac
{               // int zwykly to immutable object a to jest mutable object czyli mozna modyfikowac
                // i mozna tworzyc obiekty
    public int value; // int
    Int_klasa(int value)
    {
        this.value = value ; // konstruktor ktory ustawia inta ktory sie znajduje w nawiasach
    }
}

class Kadane_klasa // glowna klasa ktora zawiera dwie metdody : 1) kadane_1d dla tablicy jednowymiarowej i 2) kadane_2d dla tablicy dwuwymiarowej
{


    public int kadane_1d( int [] a, int n_a , Int_klasa poczatek_max, Int_klasa koniec_max  ) // przekazujesz odpowiednio : tablice przeszukiwana , dlg tej tablicy oraz
    {                                                                                          // obiekt Inkt_klasa ktory przechowuje na zewnatrz metody wartosci indeksu maksymalnej podtablicy  ( poczatek i koneic podtablicy )


        // algorytm dziala na zasadzie : przechodz pokolei przez tablice i szukaj maksymalnej podtablicy ( suma )
        // jezeli natrafisz na pojedynczy element tablicy ktory jest wiekszy od tymczasowej podtablicy to ten element tablicy jest teraz tymczasowa podtablica
        // jezeli tymczasowa podtablica jst wieksza od najwiekszej podtablicy to ustaw tymczasowa podtablice jako najwieksza podtablice
        //

        int suma_najwieksza = a[0] ; // ustaw sume najwieksza na pierwszy element tablicy
        int suma_temp = a[0] ; // ustaw sume tymczasowa na pierwszy element tablicy
                                // suma tymczasowa

                                // ogolnie n_a >= 1
        poczatek_max.value = 0; // ustawiamy poczatek maksymalnej podbtablicy na 0
        koniec_max.value = 0;   // ustawiamy koniec maksymalnej podbtablicy na 0

        int temp_poczatek = 0; // zmienna przechowujaca indeks poczatku tymczasowej podtablicy
        int temp_koniec = 0; // zmienna przechowujaca indeks konca tymczasowej podtablicy
        int temp_dlg = 1 ;  // dlg tymczasowej podtablicy
        int max_dlg = 1 ;   // dlg maksymalnej podtablicy


        if ( n_a == 1 ) // jezeli dlg tablicy to jeden to :
        {
            if ( a[0] >= 0  ) // jezeli pod a[0] znajduje sie liczba >= 0 to zwroc ja i przypisz indeksy maksymalne jako 0 ( poczatek i koniec maks_podtablicy)
            {
                poczatek_max.value = 0;
                koniec_max.value = 0;
                return a[0];
            } else // inaczej zwroc -1 i to bedzie sygnalizowac brak nieujemnego elementu w tablicy co sie rowna z bledem
            {
                poczatek_max.value = -1;
                koniec_max.value = -1;
                return -1;
            }

        } else if ( n_a > 1 )
        {

            for (int i = 1 ; i < n_a ; i++) // zaczynamy iteracje od drugiego elementu w tablicy bo juz ustawilismy pierwszy element tablicy jako sume najwieksza
            {
                if ( a[i] >= (a[i] + suma_temp)  ) // jezeli pojedynczy element tablicy jest wiekszy od dotychczasowej podtablicy,
                {                                  // to przypisz zmienne suma_temp wartosc tego pojedynczego elementu
                    suma_temp = a[i];
                    temp_dlg = 1 ; // ustawianie dlugosc podtablicy_temp na wartosc 1
                    temp_poczatek = i; // ustawianie poczatku podtablicy_temp na wartosc i, czyli indeks pod ktorym sie kryje wieksza wartosc w szukanej tablicy
                    temp_koniec = i; // ustawianie konca podtablicy_temp na wartosc i, czyli indeks pod ktorym sie kryje wieksza wartosc w szukanej tablicy

                } else if  (a[i] < (a[i] + suma_temp) ) // jezeli suma tymczasowa dodac element kryjacy sie pod indeksem i w tablicy to :
                {                                       // dodaj ten element do sumy tymczasowej oraz zwieksz dlugosc tymczasowego ciagu o jeden oraz przesun koniec tymczasowego ciagu o 1
                    suma_temp = suma_temp + a[i];
                    temp_koniec += 1 ;
                    temp_dlg += 1 ;
                }

                if ( suma_temp > suma_najwieksza )  // jezeli tymczasowa suma jest wieksza od sumy najwiekszej to
                {                                   // przypisz poczatek tymczasowej tablicy do  obiektu poczatek_max ktory odpowiada za przechowywanie wartosci poczatku indeksu maksymalnej podtablicy w danej tablicy
                                                    //przypisz koniec tymczasowej tablicy do  obiektu koniec_max ktory odpowiada za przechowywanie wartosci konca indeksu maksymalnej podtablicy w danej tablicy
                    suma_najwieksza = suma_temp ;   // przypisz dlugosc tymczasowej podtablicy do dlugosci maksymalnej podtablicy
                    poczatek_max.value = temp_poczatek ;
                    koniec_max.value = temp_koniec ;
                    max_dlg = temp_dlg ;

                }else if ( suma_temp == suma_najwieksza )
                {

                    if ( temp_dlg <  max_dlg ) // jezeli suma tymczasowa rowna sie sumie tymczasowej to porownaj dlugosci tych oto sum i
                    {                           // zdecyduj ktora jest krotsza ( bardziej korzystna )
                        poczatek_max.value = temp_poczatek ; // jezeli tymczasowa suma jset wieksza i ma krotsza dlugosc to tymczasowa suma ze swoimi zmiennymi pomocniczymi jest teraz suma najwieksza
                        koniec_max.value = temp_koniec ;
                        max_dlg = temp_dlg ;
                    }
                }
            }

        }


        if ( suma_najwieksza >= 0  ) // jezeli suma najwieksza daje 0 lub wiecej ostatecznie to po prostu zwroc sume najwieksza
        {
            return suma_najwieksza ;
        } else
        { // w innym przypadku zwroc -1 ktora oznacza ze w calej tablicy nie ma ani jednego nieujemnego wyrazu i to jest moj odpowiednim bledu
            return -1;
        }

    }

    public int kadane_2d( int [][] a, int n_a_wierszy, int n_a_kolumn , Int_klasa poczatek_max_gora , Int_klasa koniec_max_dol , Int_klasa poczatek_max_lewo , Int_klasa koniec_max_prawo    )
    {
        // glowna metoda : przekazujemy odpowiednio : tablice dwuwymiarowa, ile ma wierszy , ile ma kolumnt , i indeksy poczatku i konca maksymalnej podtablicy ( poczatek w wierszach , koniec w wierszach , poczatek w kolumnach , koniec w kolumnach )
        // tworzymy osobna tablica ktora przetrzymuje dane podtablicy
        // do tej podtablicy dodajemy kolejne wiersze az skoncza nam sie mozliwosci

        // przechodzimy od gory do dolu dwoma petlami i oraz j
        // i onzacza od ktorego wiersza zaczynamy szukanie maksymalnej podtablicy
        // j oznacza ktory wiersz dodamy do osobnej tablicy
        // dodajemy pokolei rowne wiersze do siebie oraz po kazdym jednorazowym dodaniu wykonujemy algorytm kadane_1d i dla kazdej osobnej tablicy ustalamy maksymalna podtablice
        // jezeli ktoras osobna tablica ma najwieksza sume to przypisujemy do najwiekszej sumy wynik wykonania algorytmu kadane dla tej osobnej tablicy oraz przypisujemy kolejne indeksy poczatkow i koncow (indeksy) tej tablicy

        // jezeli tymczasowa osobna tablica ma taki sam wynik algorytmu kadane_1d jak maksymalna osobna tablica to porownujemy ilosci_elementow w tych dwoch tablic
        // wybieramy ta o mniejszej ilosci elementow i przypisujemy kolejne indeksy poczatkow i koncow (indeksy) maksymalnej tablicy


        int suma_najwieksza = -2 ;
        int suma_tymczasowa = -2;

        int dlg_temp = 0; // okresla ile elementow znajduje sie w tymczasowej podtablicy podanej tablicy
        int dlg_max = 0;// okresla ile elementow znajduje sie w maksymalnej podtablicy podanej tablicy

        int dlg_szer = 0; // ile elementow znajduje sie w szerokosci ( ile kolumn ) w tymczasowej podtablicy
        int dlg_wys = 0;  // ile elementow znajduje sie w wysokosci ( ile wierszy ) w tymczasowej podtablicy


        Int_klasa temp_poczatek = new Int_klasa(0) ; // obiekty klasy int_klasa ktore bedziemy wkladac w metode kadane_1d zeby przechowywaly dane poczatku i konca maksymalnej podtablicy w tymczasowej tablicy
        Int_klasa temp_koniec = new Int_klasa(0) ;

        int temp_arr[] = new int[n_a_kolumn]; // OSOBNA TABLICA na ktorej bedzie dzialac algorytm kadane_1d oraz beda przechowywane i dodawane rozne wartosci oryginalnej tablicy

        for (int i = 0 ; i < n_a_wierszy ; i++) // tyle wykonan petli co liczba wierszy
        {
            for (int k = 0 ; k < n_a_kolumn ; k++)
            {
                temp_arr[k] = 0 ; // zerujemy wszystkie elementy osobnej tablicy
            }

            for ( int j = i; j < n_a_wierszy ; j++) // tyle wykonan petli co liczba wierszy
            {

                for (int k = 0 ; k < n_a_kolumn ; k++) // dodajemy kolejne wiersze do osobnej tablicy
                {
                    temp_arr[k] += a[j][k] ;
                }

                suma_tymczasowa = kadane_1d(temp_arr , n_a_kolumn , temp_poczatek , temp_koniec) ; // wywolujemy kadane_1d zeby poznac maksymalna podtablice tej tablicy oraz przypisac
                                                                                                    // indeksy poczatku konca tej maksymalnej tablicy ( szerokosc )

                dlg_szer = temp_koniec.value - temp_poczatek.value + 1; // ile elementow w kolumnach w tej maksymalnej podtablicy w tej osobnej tablicy
                dlg_wys = j-i + 1 ;                                     // ile elementow w wierszach w tej maksymalnej podtablicy w tej osobnej tablicy
                dlg_temp = dlg_szer * dlg_wys ;                         // ile elementow lacznie jest w tej tablicy

                if ( suma_tymczasowa > suma_najwieksza )
                { // znaleziono nowa najwieksza podtablice tej tablicy dwuwymiarowej
                    // przypisz odpowiednie indeksy i wymiary
                    // oraz ustaw nowa sume najwieksza
                    suma_najwieksza = suma_tymczasowa ;

                    poczatek_max_lewo.value = temp_poczatek.value ;
                    koniec_max_prawo.value = temp_koniec.value ;

                    poczatek_max_gora.value = i ;
                    koniec_max_dol.value = j;

                    dlg_max = dlg_temp ; // ilosc elementow maksymalnej podtablicy to teraz ilosc elementow osobnej tablicy


                } else if ( suma_tymczasowa == suma_najwieksza )
                { // jezeli sumy maja taka sama wartosc to bierz sume o mniejszej ilosci elementow
                    if ( dlg_temp < dlg_max )
                    {

                        // znaleziono nowa najwieksza podtablice tej tablicy dwuwymiarowej
                        // przypisz odpowiednie indeksy i wymiary
                        // oraz ustaw nowa sume najwieksza

                        suma_najwieksza = suma_tymczasowa ;

                        poczatek_max_gora.value = i ;
                        koniec_max_dol.value = j;

                        poczatek_max_lewo.value = temp_poczatek.value ;
                        koniec_max_prawo.value = temp_koniec.value ;

                        dlg_max = dlg_temp ;



                    }
                }
            }
        }




        // jezeli suma najwieksza jest ujemna to znaczy ze najwieksza podtablica jest pusta i zwracasz -1 na wyjscie, u mnie to jest flaga ktora oznacza ze podtablica jest empty
        if ( suma_najwieksza < 0  )
        {
            suma_najwieksza = -1 ;
        }
        return suma_najwieksza;
    }
}
class Source {


    public static Scanner skaner = new Scanner(System.in); // wczytywanie danych - obiekt


    public static void main(String[] args)
    {

        Kadane_klasa nowy_kadan = new Kadane_klasa() ; // nowy obiekt klasy kadane_klasa w ktorym znajduja sie metody
                                                        // tak trzeba zrobic zeby uzywac tego w main

        int ile_wejsc = 0;
        ile_wejsc = skaner.nextInt() ;

        int temp_wiersze = 0;
        int temp_kolumny = 0;

        int[][] arr_in = new int[100][100] ; // zrob maksymalne wymiary tablicy dwuwymiarowej
                                            // jezeli ktorys zestaw bedzie sie roznil wymiarami to nie szkodzi
                                            // program nie bedzie czytal danych spoza zakresu wymiaru tablicy

                                                // indeksy poczatkow i konca
        Int_klasa y1 = new Int_klasa(0) ; // obiekt : indeks wspolrzednej gornej maksymalnej podtablicy tablicy
        Int_klasa y2 = new Int_klasa(0) ; // obiekt : indeks wspolrzednej dolnej maksymalnej podtablicy tablicy
        Int_klasa x1 = new Int_klasa(0) ; // obiekt : indeks wspolrzednej lewej maksymalnej podtablicy tablicy
        Int_klasa x2 = new Int_klasa(0) ; // obiekt : indeks wspolrzednej prawej maksymalnej podtablicy tablicy

        Int_klasa max_suma = new Int_klasa(0) ; // obiekt : maksymalna suma tablicy

        for ( int ijk = 0 ; ijk < ile_wejsc ; ijk++ ) // petla idaca po zestawach danych
        {
            skaner.nextInt(); // przechodzenie do interesujacyh nas danych
            skaner.next();     // jw pominiecie
            temp_wiersze = skaner.nextInt(); // wczytanie wymiarow tablicy
            temp_kolumny = skaner.nextInt() ;  // dlg x szerokosc

            for ( int j_1 = 0 ; j_1 < temp_wiersze ; j_1++ )
            {
                for( int j_2 = 0 ; j_2 < temp_kolumny ; j_2++ )
                {
                    arr_in[j_1][j_2] = skaner.nextInt() ; // wczytywanie danych wejsciowych ( wartosci tablicy ) do odpowiednich indeksow glownej tablicy
                }
            }


            max_suma.value = nowy_kadan.kadane_2d(arr_in,temp_wiersze,temp_kolumny,y1,y2,x1,x2) ; // glowna metoda klasy kadane_klasa

            if ( max_suma.value < 0 ) // jezeli max suma jest mniejsza od 0 u mnie to bedzie -1 to wyswietl ze tablica jest empty
            {
                max_suma.value = 0;
                System.out.println( (ijk+1) + ": n = "+temp_wiersze+" m = "+temp_kolumny+", ms = "+max_suma.value+", mst is empty"  );
            } else
            {       // w innym wypadku jest wszystko dobrze i przekaz na wyjscie dane : nr zestawu , ile wierszy , ile kolumn, suma maksymalnej podtablicy oraz indeksy pcozatkow i koncow
                System.out.println(  (ijk+1) + ": n = "+temp_wiersze+" m = "+temp_kolumny+", ms = "+max_suma.value+", mst = a["+y1.value+".."+y2.value+"]["+ x1.value +".."+ x2.value+"]"  );
            }

        }


    }

}



/*
 testy.in :

18
1 : 1 6
-2 7 -4 8 -5 4
2 : 2 5
1 1 -1 -1 0
1 1 -1 -1 4
3 : 2 5
0 -1 -1 1 1
4 -2 -2 1 1
4 : 2 5
0 -1 -1 4 0
4 -2 -2 0 0
5 : 2 5
-1 -2 -3 -1 -2
-1 -1 -1 -1 -5
6 : 2 5
0 0 0 0 0
0 0 0 0 0
7 : 1 6
-1 -2 -3 0 -5 0
8 : 4 4
-1 -1 -1 -1
-1  1  1 -1
-1  1  1 -1
-1 -1 -1 -1
9 : 6 1
-2
-2
-3
4
3
-10
10 : 7 1
-2
-2
-3
4
3
-10
20
11 : 4 4
-1 -1 -1 1
-1  1  1 1
-1  1  1 2
-1 -1 -1 1
12 : 5 4
-1 -1 -1 1
-1  1  1 1
-1  1  1 2
-1 -1 -1 1
10 -10 10 10
13 : 5 4
-1 -1 -1 1
-1  11  1 1
-1  15  1 2
-1 -15 -1 1
15 -10 10 10
14 : 1 8
-2 7 -4 8 -5 4 -10 20
15 : 4 4
-1 -1 -1 -1
-1  2  1 -1
-1  2  1 -1
-1  2 -1 -1
16 : 2 5
0 4 -1 -1 2
4 0 -2 -1 1
17 : 2 5
0 10 -1 -1 20
10 0 -2 -1 0
18 : 10 10
-1 -1 -1 1 1 0 0 0 0 0
-1  1  1 1 1 1 -5 -6 0 0
-1  1  1 2 1 1 -3 -5 0 0
-1 -1 -1 1 1 -1 -1 -1 0 0
-1 -1 -1 1 1 -2 -2 -2 0 -10
-1  1  1 1 1 -3 -3 -3 0 -20
-1  1  1 2 1 -4 -4 -4 0 -1
-1 -1 -1 1 1 -5 -5 -5 0 5
-1 -1 -1 1 1 -5 -5 -5 0 1
-1 -1 -1 1 1 -5 -5 -5 0 2



testy.out :
1: n = 1 m = 6, ms = 11, mst = a[0..0][1..3]
2: n = 2 m = 5, ms = 4, mst = a[1..1][4..4]
3: n = 2 m = 5, ms = 4, mst = a[1..1][0..0]
4: n = 2 m = 5, ms = 4, mst = a[0..0][3..3]
5: n = 2 m = 5, ms = 0, mst is empty
6: n = 2 m = 5, ms = 0, mst = a[0..0][0..0]
7: n = 1 m = 6, ms = 0, mst = a[0..0][3..3]
8: n = 4 m = 4, ms = 4, mst = a[1..2][1..2]
9: n = 6 m = 1, ms = 7, mst = a[3..4][0..0]
10: n = 7 m = 1, ms = 20, mst = a[6..6][0..0]
11: n = 4 m = 4, ms = 7, mst = a[1..2][1..3]
12: n = 5 m = 4, ms = 25, mst = a[1..4][2..3]
13: n = 5 m = 4, ms = 38, mst = a[1..4][0..3]
14: n = 1 m = 8, ms = 20, mst = a[0..0][7..7]
15: n = 4 m = 4, ms = 7, mst = a[1..3][1..2]
16: n = 2 m = 5, ms = 8, mst = a[0..1][0..1]
17: n = 2 m = 5, ms = 35, mst = a[0..1][0..4]
18: n = 10 m = 10, ms = 22, mst = a[0..9][3..4]


 */
