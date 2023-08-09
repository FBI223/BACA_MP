// Marcin Sztukowski gr. 3

import java.util.Scanner;

public class Source {

        /*
    Algorytm "Magicznych Piatek" (Magic Fives), znany rowniez jako Mediana Median, to algorytm do znajdowania n-tego elementu (lub mediany) w nieposortowanej liscie w liniowym czasie.
    Algorytm ten jest rowniez znany jako algorytm Blyskawicznej Selekcji (QuickSelect), ktory jest specjalnym przypadkiem algorytmu QuickSort.

    Ponizej znajduje się opis dzialania algorytmu Magicznych Piatek:

    1) Podzial na grupy: Dzielisz liste na podgrupy po piec elementow. Moze być kilka grup, ktore maja mniej niz piez elementow, ale nie wiecej.

    2) Znalezienie mediany kazdej grupy: Dla kazdej z tych grup, sortujesz grupe i wybierasz mediane. Dla grupy piecio elementowej, mediana to trzeci element.

    3) Znalezienie mediany median: Jezeli masz mniej niż piec median, sortujesz je i wybierasz mediane bezposrednio. W przeciwnym razie, uzywasz algorytmu Magicznych Piatek rekurencyjnie, aby znalezc mediane median.

    4) Podzial na podlisty: Uzywasz mediany median jako pivota, aby podzielic oryginalną liste na trzy podlisty: jedna z elementow mniejszych, jedna z elementow rownych i jedna z elementow wiekszych niz pivot.

    5) Wybor odpowiedniej podlisty: Teraz, na podstawie rozmiaru podlist, możesz okreslic, w ktorej liscie znajduje sie szukany n-ty element. Jesli n jest mniejsze niz rozmiar listy elementow mniejszych,
    kontynuujesz szukanie na tej liscie. Jesli n jest wieksze niz suma rozmiarow listy mniejszych i rownej, kontynuujesz szukanie na liscie wiekszych. W przeciwnym razie, pivot jest n-tym elementem, ktorego szukasz.

    W skrocie, algorytm Magicznych Piątek sklada się z podzialu listy na grupy po piec, wybrania mediany z kazdej grupy, a nastepnie wybrania mediany tych median jako pivota do podziału oryginalnej listy.
    Przeszukuje tylko odpowiednie podlisty, co pozwala na znalezienie n-tego elementu w liniowym czasie.

     */



    public static void swapp(int[] arr, int i, int j) {
        int temp = arr[i]; // swapuj dwa elementy w tablicy
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partition(int arr[], int lewa, int prawa , int x_szukane ) {
        int i;
        int index_pivot = -1; // SWAPUJEMY az dojdziemy do pivota
        int czy_znaleziono_pivot = 0 ; // wtedy jak dojdziemy do pivota to trzeba przeniesc pivota na koniec tablicy i swapowac dalej

        i = lewa;
        for (int j = lewa; j <= prawa - 1; j++) {
            if (arr[j] < x_szukane) { // swapujesz i przesuwasz wskaznik w prawo
                swapp(arr, i, j);
                i++;
            } else if ( arr[j] == x_szukane)
            { // jezeli mediana rowna sie medianie median to
                if ( czy_znaleziono_pivot == 0 )
                { // jesli nie znalazles pivota to natrafiles na pierwszy taki pivot w tablicy i zamien miejscami z koncem
                    czy_znaleziono_pivot = j;
                    index_pivot=j;
                    swapp(arr, j, prawa);

                    if ( arr[j] <= x_szukane)
                    {
                        swapp(arr , i ,j);
                        i++;
                    }

                } else { // jezeli juz masz pivota ( indeks to po prostu lecisz dalej ze swapowaniem )
                    swapp(arr, i, j);
                    i++;
                }
            }
        }
        swapp(arr, i, prawa); // zamien miejscami koniec z aktualnym miejscem indeksu i
        return i; // zwroc indeks pivota
    }


    public static void sort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) { // funckja sortujaca
            int key = arr[i];  // fragment kodu reprezentuje algorytm sortowania przez wstawianie (Insertion Sort).
            int j = i - 1;
            while (j >= left && arr[j] > key) { // przesuwasz az dotrzesz na odpowiednie miejsce
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key; // wstaw na dobre miejsce
        }
    }
    public static int find_median(int arr[], int lewa, int prawa) {
        int n = prawa - lewa + 1; // zwracasz srodkowy indeks posortowanej tablicy
        sort(arr, lewa, prawa); // sorting
        return  lewa + (n / 2); // lewa plus srodek rowna sie mediana ( indeks )
    }


    public static int n_ty_element(int arr[], int lewa, int prawa, int n_ty) {
        if ( (n_ty > 0) && (n_ty <= prawa - lewa + 1) ) { // jezeli n_ty element miesci sie w tabloicy to wykonuj rekurencyjnie :
            int n = prawa - lewa + 1; // ile elementow ma talbica
            int i; // iterowanie po podgrupach 5-elementowych
            for (i = 0; i < n / 5; i++) { // dopoki sa pelne 5 elementowe kubelki to
                int index_mediany = find_median(arr, lewa + i * 5, lewa + i * 5 + 4); // zwroc index mediany
                swapp(arr , lewa +i , index_mediany) ; // zamien miejscami z poczatkowym elementem w tablicy
                                                    // przez to unikniesz utraty danych

                // na poczatku znajduja sie mediany potem zostana i tak przesuniete na wlasciwe miejsce bo pivot je przeniesie na dobre miejsce ( podczas partition )
            }
            if (n % 5 > 0) {
                // jezeli na koncu jest niepelny "kubelek" to wykonaj to samo co u gory
                int index_mediany = find_median(arr, lewa + i * 5, prawa);
                swapp(arr , lewa +i , index_mediany) ;
                i++; // zwieksz ilosc kubelkow
            }

            int mediana_mediany =0;

            if ( i == 1 ) // jesli jest tylko jeden kubelek
            {
                mediana_mediany = arr[lewa] ; // lewa to indeks mediany gdyz mediana jest przenoszona na poczatek zawsze

            } else {
                // w innym przypadku szukaj dalej mediane wywolujac rekurencyjnie metode dla odpowiednich indeksow ( mediany sa na poczatku tablicy )
                mediana_mediany = n_ty_element(arr, lewa, lewa + (i - 1) / 2, (i + 1) / 2);
            }

            int index_pivota = partition(arr, lewa, prawa, mediana_mediany); // sortuj cala tablice przy uzycia mediany median ( mozliwie rowne polowki )

            if (index_pivota - lewa == n_ty - 1) // jesli index pivota to n_ty najwiekszy eleemnt to zwroc i zakoncz
            {
                return arr[index_pivota];
            }

            if (index_pivota - lewa > n_ty - 1) // jesli n_ty najwiekszy element jest w lewej polowce to wywolac rekurencyjnie metode dla lewej polowki
            {
                return n_ty_element(arr, lewa, index_pivota - 1, n_ty);
            }
            // jesli n_ty najwiekszy element jest w prawej polowce to wywolac rekurencyjnie metode dla prawej  polowki
            return n_ty_element(arr, index_pivota + 1, prawa, n_ty - index_pivota + lewa - 1);
        }
        return -1; // jesli sie nie udalo znalezc n_tego elementu wywal blad czyli -1
    }



    public static Scanner skaner = new Scanner(System.in); // klasa skanujaca wejscie


    public static void main(String[] args)
    {
        int ile_zestawow = skaner.nextInt(); // ile zestawow do rozpatzrenai
        for ( int i = 0 ; i < ile_zestawow ; i++ )
        {

            int n_arr_temp = skaner.nextInt(); // rozmiar tablicy
            int[] arr_temp = new int[n_arr_temp]; // tworzysz tablice
            for ( int j = 0 ; j < n_arr_temp ; j++ )
            {
                arr_temp[j] = skaner.nextInt(); // wczytujesz dane
            }

            int ile_zapytan_temp = skaner.nextInt(); // ile roznych zapytan o n_ty element
            for ( int j = 0 ; j < ile_zapytan_temp ; j++ )
            {
                int n_ty_temp = skaner.nextInt() ;
                if ( n_ty_temp <= 0 || n_ty_temp > arr_temp.length ) // jezeli n_ty element wykracza poza granice indexow to wywal blad czyli brak
                {
                    System.out.print( n_ty_temp );
                    System.out.print( " " );
                    System.out.println("brak");
                } else { // w innym przypadku wywolaj rekursywna funckje quickselect ktora szuka n_ty element w tablicy
                    int nthLargest = n_ty_element(arr_temp, 0, arr_temp.length - 1,  n_ty_temp);
                    System.out.print( n_ty_temp ); // printuj wynik
                    System.out.print( " " );
                    System.out.println(nthLargest);
                }

            }

        }

    }
}



/*

in

3
5
1 2 3 4 5
3
1 2 3
5
5 3 4 4 3
5
2 5 1 3 4
10
1 1 1 1 1 1 1 1 1 1
5
1 10 0 2 11


3
1
9990
5
1 2 3 4 5
52
54 34 8 30 80 37 7 71
14 94 12 81 25 43 98 50
92 82 91 74 18 43 6 20 98
63 24 6 34 76 44 3 49 70
18 55 31 54 32 64 27 61
79 8 43 22 52 25 80 62
30 16
10
53 1 2 3 4 5 6 7 51 52
5
32132 1 1231 21 1
7
1 2 3 4 5 8 12345



1
12
6 6 5 4 4 4 4 6 7 7 100 1000
12
1 2 3 4 5 6 7 8 9 10 11 12



1
100
100 99 98 97 96 95 94 93 92 91 90 89 88 87 86 85 84 83 82 81 80 79 78 77 76 75 74 73 72 71 70 69 68 67 66 65 64 63 62 61 60 59 58 57 56 55 54 53 52 51 50 49 48 47 46 45 44 43 42 41 40 39 38 37 36 35 34 33 32 31 30 29 28 27 26 25 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
5
1 10 100 5 50



out


1 1
2 2
3 3
2 3
5 5
1 3
3 4
4 4
1 1
10 1
0 brak
2 1
11 brak

1 9990
2 brak
3 brak
4 brak
5 brak
53 brak
1 3
2 6
3 6
4 7
5 8
6 8
7 12
51 98
52 98
1 1
2 1
3 21
4 1231
5 32132
8 brak
12345 brak



1 4
2 4
3 4
4 4
5 5
6 6
7 6
8 6
9 7
10 7
11 100
12 1000


1 1
10 10
100 100
5 5
50 50





        // Tworzenie duzej tablicy o rozmiarze 1 000 000
        int[] arr = new int[1000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1; // Wypelnienie tablicy wartościami od 1 do 1 000 000
        }

        // Mieszanie elementow tablicy
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < arr.length; i++) {
            int randomIndex = rand.nextInt(arr.length);
            swap(arr, i, randomIndex);
        }

        // Wyszukiwanie n-tego co do wielkosci elementu
        int k = 100001; // Wyszukaj k-ty co do wielkosci element
        int nthLargest = quickSelect(arr, 0, arr.length - 1, arr.length - k) ;
        System.out.println(nthLargest);


 */

