//Marcin Sztukowski gr nr 3

import java.util.Scanner;


class Inversion_class {

    long scalanie(long tablica[], int lewa, int srodek, int prawa) {

        // scalanie
        // podczas scalania dwoch pod tablic posortowanych
        //

        // prawa to koniec podtablicy (index)
        // lewa to poczatek podtablicy (index)
        // srodek to prawy kraniec lewej podtablicy (index)

        // argumenty to
        // array , lewy skrajny index , srodkowy index ktory mowi gdzie konczy sie lewa podtablica  a zaczyna druga podtablica  , oraz prawy to skrajny prawy index

        long wynik = 0; // zmienna przechowujaca wynik inversjii

        int dlg1 = srodek - lewa + 1; // len of first array
        int dlg2 = prawa - srodek; // dlg drugiej podtablicy


        long temp[] = new long[dlg1]; // tworzymy jedna tablice pomocnicza o maksymalnej dlugosci n/2

        for (int i = 0; i < dlg1; i++)
        {
            temp[i] = tablica[lewa + i]; // kopiujemy zawartosc lewej podtablicy do tablicy " temp "
        }

        int i = 0; // zmienna i jest potrzebna zeby zapamietac aktualny index lewej podtablicy
        int j = 0;// zmienna j jest potrzebna zeby zapamietac aktualny index prawej podtablicy
        int k = lewa; // zmienna k jest do wstawiania na odpowiednie miejsce elementow tablicy "tablica" - do sortowania potrzebbne


        while ((i < dlg1) && (j < dlg2)) // dopoki nie dojdziemy do konca jakiejsc podtablicy to wykonuj
        {
            if (temp[i] <= tablica[lewa + dlg1 + j]) // jesli aktualny element lewej podtablicy jest mniejszy badz rowny od elementu aktualnego prawej podtablicy to
            {   // umiesc aktualny element lewej podtablicy na odpowiednie miejsce w wynikowej talbicy
                tablica[k] = temp[i];
                i++; // oraz zwieksz index aktualnego elementu lewej podtablicy
            } else { // jesli aktualny element prawej podtablicy jest mniejszy  od elementu aktualnego lewej podtablicy to
                tablica[k] = tablica[lewa + dlg1 + j]; // wstaw aktualny element prawej podtablicy na odpowiednie miejsce w wynikowej talbicy
                j++; // oraz zwieksz index aktualnego elementu rpawej podtablicy
                wynik += lewa + dlg1 + j - k - 1; // od tylu elementow lewej podtablicy jest mniejszy element prawej podtablicy
            }
            k++; // zwiekszamy aktualne miejsce wynikowej tablicy
        }


        while (i < dlg1) { // kopiujemy pozostale elemety lewej podtablicy do wynikowej tablicy i zwiekszamy zmienne

            tablica[k] = temp[i];
            k++;
            i++;

        }


        while (j < dlg2) { // kopiujemy pozostale elemety prawej podtablicy do wynikowej tablicy i zwiekszamy zmienne

            tablica[k] = tablica[lewa + dlg1 + j];
            k++;
            j++;

        }

        return wynik;
    }


    long Inversion(long arr[], int l, int p) {
        // to metoda jest rownowazna sortowaniu przez scalanie
        // klasa z soprtowanie przez scalanie
        // metoda klasy sortuje i zlicza ile jest elementow i < j takich ze a[i] > a[j]

        long wynik = 0; // zmienna do przechowywania wyniku
        if (l < p) {

            int sr = l + (p - l) / 2; // wyznaczamy srodek ktory dzieli dwie podtablice

            long t1 = Inversion(arr, l, sr); // wykonujemy rekurencyjne wywolanie funkcji dla lewej polowki i wynik zapisujemy do zmiennej
            long t2 = Inversion(arr, sr + 1, p);  // wykonujemy rekurencyjne wywolanie funkcji dla prawej polowki i wynik zapisujemy do zmiennej

            // Merge the sorted halves
            long t3 = scalanie(arr, l, sr, p); // scalamy dwie polowki i wynik funkcji zapisujemy do zmiennej

            wynik = t1 + t2 + t3; // wynik
        }
        return wynik;
    }

}


class Source {

        public static Scanner skaner = new Scanner(System.in); // klasa skanujaca wejscie


        public static void main(String args[]) {

            Inversion_class klasa_mrg_sort = new Inversion_class(); // obiekt klasy merg sort do rorzwiazania zadania

            int ile_danych_wejsciowych = 0;
            ile_danych_wejsciowych = skaner.nextInt(); // ile zestawow danych
            long temp_int = 0;
            int ile_liczb_w_temp_arr = 0;
            long temp_wynik = 0;


            int k = 0;
            while (k < ile_danych_wejsciowych) {
                ile_liczb_w_temp_arr = skaner.nextInt();
                long[] temp_arr = new long[ile_liczb_w_temp_arr];


                for (int i = 0; i < ile_liczb_w_temp_arr; i++) {
                    temp_arr[i] = skaner.nextLong(); // skanowanie danych tymczasowej tablicy
                }


                temp_wynik = klasa_mrg_sort.Inversion(temp_arr, 0, temp_arr.length - 1);
                System.out.println(temp_wynik);


                k++;
            }

        }
}





/*

in

14
12
54 78 15 45 87 98 56 15 54 45 87 1
5
1 2 3 1 5
8
7 8 9 7 8 9 10 100
24
9 89 10 100 1 2 3 70 54 78 15 45 87 98 56 15 54 45 87 1 4 5 8 9
5
7 8 9 4 5
16
54 78 15 45 87 98 56 15 54 45 87 1 4 5 8 9
10
1 2 3 1 5 1 2 3 1 5
14
7 8 9 7 8 9 10 100 7 8 9 7 8 9 10 100
22
9 89 10 100 1 2 3 70 7 8 9 7 8 9 10 100 7 8 9 7 8 9 10 100
8
7 8 9 4 5 1 2 3
10
1 2 3 4 5 6 7 8 9 10
10
10 1 2 3 4 5 6 7 8 9
10
10 9 8 7 6 5 4 3 2 1
10
0 0 0 0 0 0 0 0 0 0



out

35
2
3
148
6
79
13
30
28
3
3
21
0
9




        long arr_1[] = { 1 ,2 ,3, 4, 5, 6, 7, 8, 9, 10 };
        long arr_2[] = { 10, 1 ,2 ,3, 4, 5, 6, 7, 8, 9 };
        long arr_3[] = { 10,9,8,7,6,5,4,3,2,1 };
        long arr_4[] = { 0,0,0,0,0,0,0,0,0,0 };

        long wynik_1 = 0;
        long wynik_2 = 0;
        long wynik_3 = 0;
        long wynik_4 = 0;

        System.out.println("---------------------");

        int arr_size_1 = arr_1.length;
        wynik_1 = inversion(arr_1, 0, arr_size_1 - 1);
        System.out.println(" TO JEST WYNIK 1 : " +  wynik_1 );


        System.out.println("---------------------");

        int arr_size_2 = arr_2.length;
        wynik_2 =  inversion(arr_2, 0, arr_size_2 - 1);
        System.out.println(" TO JEST WYNIK 2 : " +  wynik_2 );



        System.out.println("---------------------");
        int arr_size_3 = arr_3.length;
        wynik_3 = inversion(arr_3, 0, arr_size_3 - 1);
        System.out.println(" TO JEST WYNIK 3 : " +  wynik_3 );



        System.out.println("---------------------");
        int arr_size_4 = arr_4.length;
        wynik_4 = inversion(arr_4, 0, arr_size_4 - 1);
        System.out.println(" TO JEST WYNIK 4 : " +  wynik_4 );


        System.out.println("---------------------");

    }




//        MergeSort merg_obj = new MergeSort();
//        long arr1[] = { 1 ,2 ,3 ,4 ,5 ,6 ,7 ,8 ,9 ,10 };
//        long arr2[] = { 10 ,1 ,2 ,3 ,4 ,5 ,6 ,7 ,8 ,9  };
//        long arr3[] = { 10 ,9 ,8 ,7 ,6, 5 ,4 ,3, 2, 1 };
//        long arr4[] = { 0 ,0, 0, 0, 0 ,0 ,0 ,0 ,0 ,0  } ;
//
//        long wynik1=  merg_obj.sort( arr1 , 0 , arr1.length - 1 );
//        long wynik2= merg_obj.sort( arr2 , 0 , arr2.length - 1 );
//        long wynik3= merg_obj.sort( arr3 , 0 , arr3.length - 1 );
//        long wynik4= merg_obj.sort( arr4 , 0 , arr4.length - 1 );
//
//        System.out.println(wynik1);
//        System.out.println(wynik2);
//        System.out.println(wynik3);
//        System.out.println(wynik4);
//
//        System.out.println();
//
//        printArray( arr1 );
//        printArray( arr2 );
//        printArray( arr3 );
//        printArray( arr4 );



 */


