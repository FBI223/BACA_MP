//Marcin Sztukowski gr nr 3

import java.util.Scanner;


class stos_int{ // klasa odzwierciedlajaca rekurencje czyli stos

    int[] stak;
    int dlg_max;
    int dlg_temp;

    stos_int(int dlg_max_in ) // konstruktor klasy
    {
        stak = new int[dlg_max_in];
        dlg_max = dlg_max_in;
        dlg_temp = 0;
    }

    void push(int x ) // dodaj na koniec stosu
    {
        if ( dlg_temp < dlg_max )
        {
            stak[dlg_temp] = x;
            dlg_temp++;
        }

    }

    int pop() // usun i zwroc koniec
    {
        int temp_int = -1;
        if ( dlg_temp > 0  )
        {
            temp_int = stak[dlg_temp-1];
            stak[dlg_temp-1] = -1;
            dlg_temp--;
        }
        return temp_int;
    }
    int get_last() // zwroc koniec
    {
        int temp_int = -1;
        if ( dlg_temp > 0  )
        {
            temp_int = stak[dlg_temp-1];
        }
        return temp_int;
    }

}


public class Source {

    public static boolean czy_znaleziono_odp = false; // zmienna globalna statyczna ktora mowi nam czy znaleziono w
                                                    // w rekurencyjnej funkcji szukana sume
    public static Scanner skaner = new Scanner(System.in); // klasa skanujaca wejscie
    public static StringBuilder sb_static = new StringBuilder(); // zamiast string uzywamy sstringbuilder do konkatenacji
                                                                // jest o wiele szybciej

    public static String iter_pakuj( int[] A , int suma_exact_in )
    {
        // suma_exact_in szukana wartosc
        // funkcja zwraca kombinacje elementow tablicy ktore sie sumuja do exact sumy

        // glowna funkcja do symulacji rekursji
        // dziala na zasadzie ze idziemy po elementach tablicy pokolei
        // i dodajemy wszystko na stos
        // rozpatrujemy wszystkie kombinacje wystepowan elementow z glownej talbicy
        // poodbnie jak w funkcji rekurencyjnej idziemy pokolei od wystepowania wszystkich elementow az po brak wystapien
        // gdy na stosie jest rowna ilosc elementow z iloscia elementow tablicy
        // to mozemy ta kombinacje sprawdzic
        // jezeli kombinacja okaze sie dobra to zakonczymy program
        // jesli nie to szukamy dalej az do wyczerpania wszystkich kombinacji

        String temp_str ; // tymczasowy string do
        String wynikowy_str = ""; // wynik
        int czy_znaleziono = 0 ;// zmienna lokalna ktora mowi czy koniec programu
        int flaga = -1 ; // flaga do poruszania sie po programie
                        // dzialamy odpoki nie wyczepiemy wszystkich mozliwosci albo nie znajdziemy sumy wtedy
                        // wtedy flaga osiaga wartosc 20

        int suma_temp = 0 ; // ta wartosc odzwierciedla aktualna sume na stosie - czyli
                            //  czyli sume wszystkich elementow na stosie
        //int suma_exact = suma_exact_in; // szukana suma
        stos_int stosiwo_int = new stos_int(A.length+5); // stos do obliczen
        for ( int i = 0 ; i < A.length; i++ )
        { // wstawiamy wszystkie elementy na stos arraya
            stosiwo_int.push( A[i] );
            suma_temp += A[i] ;
        }

        if ( A.length == 1 )
        { // warunek skrajny sprawdzamy
            suma_temp = A[0];
            if ( suma_temp == suma_exact_in )
            {
                sb_static.setLength(0);
                sb_static.append(" ");
                sb_static.append(A[0]);
                wynikowy_str = sb_static.toString();
                //wynikowy_str = " " + String.valueOf(A[0]);
            }
            flaga = 20; // koniec
        }


        while ( flaga != 20  )
        { // dopoki nie ma konca to rob

            switch (flaga){ // switch ktory kieruje ruchem

                case -1: // sprawdzanie czy suma tymcasowa to suma exact i ewentualkny koniec
                    // sprawdzanie warunku tylko gdy jests na czubku
                    if ( suma_temp == suma_exact_in ) // jezeli znaleziona suma to konczymy i flaga = 20
                    {
                        if ( czy_znaleziono == 0 )
                        {
                            temp_str = "";
                            for ( int i = 0 ; i < stosiwo_int.dlg_temp ; i++)
                            {
                                if ( (stosiwo_int.stak[i] != 0) && (stosiwo_int.stak[i] != -1 ) )
                                {
                                    // wszystko ladujemy do stringbuildera i zamieniamy na stringa
                                    // wsystko czyli wszystkie elementy stosu ktore sa rozne od 0
                                        // jezeli sa rowne zero to pomijamy
                                    sb_static.setLength(0);
                                    sb_static.append(temp_str);
                                    sb_static.append(" ");
                                    sb_static.append( stosiwo_int.stak[i] );
                                    temp_str = sb_static.toString();
                                    //temp_str = temp_str + " " + String.valueOf( stosiwo_int.stak[i] );
                                }

                            }
                            wynikowy_str = temp_str;
                            czy_znaleziono = 1;

                        }
                    }
                    flaga = 1 ; // jezeli nie znaleziono to idz do flagi 1

                    if ( czy_znaleziono == 1  )
                    {
                        flaga = 20;
                    }
                    break;
                case 10: // case niepotrzebny
                    while (  ( stosiwo_int.dlg_temp > 0 ) && (suma_temp > suma_exact_in) )
                    {
                        suma_temp -= stosiwo_int.pop();
                    }
                    flaga = 1;
                    if ( stosiwo_int.dlg_temp == 0 )
                    {
                        stosiwo_int.push( A[0] );
                    }

                    break;

                case 1: // sluzy do aktualizacji stosu i temp sumy - do usuwania
                    if ( stosiwo_int.get_last() != 0 ) // jestes na gorze stosu
                    {
                        // jezeli na gorze stosu jest liczba to usun ja i idz do flagi 2
                        suma_temp -= stosiwo_int.pop();
                        flaga = 2;
                        // dodaj na koniec zero
                    } else {
                        // usun zero
                        // jezeli jest zero to idz do case 3

                        flaga = 3;
                    }

                    break;

                case 2: // schodzenie o 1 nizej
                    // dodawanie zera czyli zastepujesz wczesniej wyeliminowana liczbe zerem
                    stosiwo_int.push(0);
                    flaga = -1 ; // sprawdz czy znalazles cos


                    break;


                case 3: // jest zero na gorze i pozbywamy sie go
                        // idziemy tutaj do dolu stosu
                    stosiwo_int.pop(); // usun ostatnia liczbe na stosie
                    // suma_temp -= stosiwo_int.pop()
                    if ( stosiwo_int.get_last() != 0 )
                    {
                        flaga = 4; // zamiana na zero
                    } else {
                        flaga = 6; // przekerowanie do case 6 ktory usuwa zera
                    }

                    break;

                case 4:
                    // daj zero na czubek i zaktualzuj sume i idz do 5
                    suma_temp -= stosiwo_int.stak[stosiwo_int.dlg_temp-1] ;
                    stosiwo_int.stak[stosiwo_int.dlg_temp-1] = 0 ;
                    flaga = 5;
                    break;


                case 5:
                    // dodajesz pokolei wszystko do gory - tyle ile trzeba
                    int dlg_petli = A.length - stosiwo_int.dlg_temp;
                    for ( int i = 0 ; i < dlg_petli ; i++ )
                    {
                        int temp_i = stosiwo_int.dlg_temp; // ewnetualne szybsze dzialanie programu
                        if ( suma_temp + A[temp_i] > suma_exact_in  ) // jezeli suma przewyzsza sume exact to nie dodawaj do stosu elementu tablic tytylko zero
                        {
                            stosiwo_int.push(0);
                        } else {
                            stosiwo_int.push(A[ temp_i ]);
                            suma_temp += A[ temp_i] ; // aktualizacja sumy
                        }


                    }
                    flaga = -1 ; // doszedles do konca i trzeba sprawdzic ( dodales wszystko co sie dalo )

                    break;

                case 6: // usuwa zera i to nam oczyscza stak i mozemy dzialac dalej i wstawiac kolejne kombinacje
                    // usuwanie zer do konca
                    while ( ( stosiwo_int.dlg_temp != 0 ) && (stosiwo_int.get_last() == 0) )
                    {   // dopoki sa zera lub jest co usuwac to usuwaj
                        stosiwo_int.pop();
                    }
                    //stosiwo_int.push(-1);

                    if ( stosiwo_int.dlg_temp == 0 )
                    {
                        // doszedles do konca czyli usunales wszystkie zera i wybadales wszystkie kombinacje
                        flaga = 20;
                    } else {
                        // jezeli nie zbadales to idz do 4
                        flaga = 4;
                    }


                    break;

            }

        }

        return wynikowy_str; // zwroc pusty string ktory mowi ze nie zlaleziono sumy lub cos zwroc
    }


    public static void rec_pakuj(int[] A , boolean[] A_bool , int currSum, int index, int sum)
    {


        // 2 ^ N mozliwosci

        // funkcja rekurencyjna
        // A glowna talbica z danymi
        // A bool pomocnicza tablica  z boolami ktora nam mowi ktore indeksy tablicy uzywac a ktore nie dodawac
        // index - aktualny index po ktorym sie poruszamy w tab
        // sum - exact sum
        // curr sum = szukana suma

        if ( currSum == sum )
        {
            // jezeli znalazles to daj flage na turue
            // zeby nie wykonywac juz innych funkcji rekurencyjnych oraz zeby zatrzymac dostep funkcji innych do tablicy bool
            // tablica bool ma wyniki
            czy_znaleziono_odp = true;

        } else if ( !czy_znaleziono_odp  ) // jezeli nieznaleziono
        {

            if ( index < A.length ) // jezeli index miesci sie w tablicy
            {
                if ( currSum < sum ) // jezeli nie przekroczyles sumy szukanej to ewentualnie wyszukaj inne kombinacje
                {                   // oraz dodaj inne kombinacje

                    A_bool[index] = true ; // dodajesz A[index] WIEC daj A_bool[index] = true
                                            // to nam mowi ze uzyles ten element do szukanej sumy
                    currSum += A[index]; // dodaj element tablicy do szukanej sumy
                    rec_pakuj( A, A_bool ,  currSum , index + 1  , sum ); // wywolane rekurencyjne szukajace sumy
                                                                            // dodalismy zaktualizowane dane oraz rozpatrujemy kolejne przypadki

                    if ( czy_znaleziono_odp == false ) // jezeli nie znaleziono odpowiedzi ( w poprzednim wywolaniu funkcji ) to rozpatrz przypadek bez dodawania elementu tablicy
                    {
                        currSum -= A[index];
                        A_bool[index] = false ;
                        rec_pakuj( A, A_bool, currSum , index + 1  , sum ); // zaktualizowane wywolanie fukncji
                    }

                }
            }


        }


    }


    public static void main(String[] args) {

        sb_static.setLength(0); // ewnetualnie wyzerowanie buffora SB
        //int suma_wlasciwa = 0;

        int ile_tablic = skaner.nextInt(); // zmiennna przechowujaca ilosc zestawow dancyh do rozpatrzenia
        int szukana_pojemnosc = 0; // szukana pojemnosc plecaka
        int n_a = 0 ; // len tablicy

        for ( int i = 0 ; i < ile_tablic ; i++)
        {
            szukana_pojemnosc = skaner.nextInt();
            n_a = skaner.nextInt(); // len
            int[] A = new int[n_a];  // glowna tablica
            boolean[] A_Bool = new boolean[n_a]; // pomocnicza tablica
            for ( int j = 0 ; j < n_a ; j++ )
            {
                A[j] = skaner.nextInt(); // skanowanie do talbicy
            }



            //String wyjscie_str_2 = find_subset_sum_rec( A,0,0,szukana_pojemnosc ) ;
            //czy_znaleziono_odp = false ;

            String nowa_rekursja = ""; // zmienna przechowujaca dane z funcji rekurencyjnej
                                        // jezeli jest pusta to znaczy ze trzeba zakonczyzc program i wypisac BRAK
            String nowa_iteracja = ""; // zmienna przechowujaca dane z funcji rekurencyjnej
            sb_static.setLength(0);
            czy_znaleziono_odp = false;
            rec_pakuj( A,A_Bool , 0 , 0 , szukana_pojemnosc); // wywolanie rekurencji

            if ( czy_znaleziono_odp )
            {
                for ( int k = 0 ; k < A.length ; k++)
                {
                    if (  A_Bool[k] ) // w A bool wiadomo ktore elementy tablicy A brac
                                        // skanujesz do wyjsciowego stringu tam gdzie jest true
                    {
                        sb_static.append(" "); // dodajesz do SB
                        sb_static.append( A[k] );
                    }
                }
                nowa_rekursja = sb_static.toString(); // konwersja do stringa ( szybsze )
                String temp = nowa_rekursja;

                sb_static.setLength(0);
                sb_static.append("REC: ");
                sb_static.append(szukana_pojemnosc);
                sb_static.append(" =");
                sb_static.append(nowa_rekursja);
                nowa_rekursja = sb_static.toString(); // konwersja do stringa ( szybsze )


                nowa_iteracja = iter_pakuj(A,szukana_pojemnosc);
                sb_static.setLength(0);
                sb_static.append("ITER: ");
                sb_static.append(szukana_pojemnosc);
                sb_static.append(" =");
                sb_static.append(nowa_iteracja);
                nowa_iteracja = sb_static.toString();

                System.out.println(nowa_rekursja);
                System.out.println(nowa_iteracja); // wyswietlanie wyniku


            } else { // nie znaleziono w f rek wiec wyswietl BRAK
                System.out.println("BRAK");
            }


            sb_static.setLength(0);

        }


    }
}

/*
in
10
10
4
2 5 6 4
15
5
2 5 2 4 3
20
7
2 5 6 4 3 2 7
17
5
2 5 6 4 7
33
6
7 3 16 15 2 21
5
4
2 6 7 3
8
4
1 5 2 3
15
6
5 3 2 1 6 4
100
5
21 29 22 30 28
4
1
6

out
REC:  10 = 6 4
ITER: 10 = 6 4
BRAK
REC:  20 = 2 5 6 4 3
ITER: 20 = 2 5 6 4 3
REC:  17 = 2 5 6 4
ITER: 17 = 2 5 6 4
REC:  33 = 7 3 2 21
ITER: 33 = 7 3 2 21
REC:  5 = 2 3
ITER: 5 = 2 3
REC:  8 = 1 5 2
ITER: 8 = 1 5 2
REC:  15 = 5 3 2 1 4
ITER: 15 = 5 3 2 1 4
REC:  100 = 21 29 22 28
ITER: 100 = 21 29 22 28
BRAK
 */
