//Marcin Sztukowski

import java.util.Scanner;



class Szukanie_klasa
{
    public int[] arr_wejsciowa ;
    Szukanie_klasa(int len_arr)
    {
        this.arr_wejsciowa = new int[len_arr] ;
    }


    int ile_roznych_elementow_w_arr(int[] arr_wejsciowa )
    {
        // startujemy od drugiego elementu
        // jezeli poprzedzedni elemet tablicy jest taki sam to nic nie robimy
        // jezeli te dwa sasiednie elementy sa rozne od siebie to zwieksz wynik

        int wyjscie = 1;

        if ( arr_wejsciowa.length == 1 )
        {
            wyjscie = 1;
        } else {
            for(int i = 1 ; i < arr_wejsciowa.length ; i++)
            {

                if ( arr_wejsciowa[i] != arr_wejsciowa[i-1] )
                {
                    wyjscie += 1 ;
                }

            }

        }


        return wyjscie ;
    }



    int SearchBinFirst(int x  )
    {
        // szukamy indexu pierwszego wystapienia danego elementu
        // jak nie ma takiego elementu to zatrzymujemy sie w okolicy gdzie ten element by byl w tablicy
        // + / - 1 pozycja indexu

        int wynik = -1;


        // zmienne do warunku poszukiwania sa warunkami petli
        // dopoki podtablice da sie dzielic na o polowe mniejsze tablice to wykonuj algorytm


        // skrajne indexy
        int lewa = 0;
        int prawa = arr_wejsciowa.length - 1 ;
        int srodek = 0;

        int czy_znaleziono_pierwszy_element = 0; // warunek stopu


        if ( !(arr_wejsciowa[0] == x) )
        {

            while ( (lewa <= prawa) && ( wynik == -1 ) && ( czy_znaleziono_pierwszy_element == 0 )  )
            {

                srodek = (lewa + prawa ) / 2 ;


                if ( arr_wejsciowa[srodek] == x )
                {
                    if ( srodek == 0 )
                    {
                        // jezeli trafimy na nasz element na poczatku to jest koniec
                        wynik = 0;
                        czy_znaleziono_pierwszy_element = 1 ;
                    } else
                    {
                        if ( arr_wejsciowa[srodek-1] != x )
                        {
                            // jezeli poprzedni element tablicy jest rozny od dotychczasowego elementu to koniec
                            wynik = srodek;
                            czy_znaleziono_pierwszy_element = 1;

                        } else
                        {
                            // w innym przypadku szukamy dalej i zaciesniamy poszukiwania
                            prawa = srodek-1 ;
                        }
                    }

                    // zaciesnienie poszukiwan

                } else if (  arr_wejsciowa[srodek] > x )
                {
                    prawa = srodek - 1 ;

                } else if ( arr_wejsciowa[srodek] < x )
                {

                    lewa = srodek + 1;

                }
            }
        }



        return srodek ;
    }


}




public class Source {


    static Scanner skaner = new Scanner(System.in) ;



    public static void main(String[] args) {




        // obiekt zawierajacy metody potrzebne do zadania
        // zmienne wczytujace
        int temp_int ;
        int ile_zestawow;
        ile_zestawow = skaner.nextInt() ;

        int ile_zapytan_temp ;

        for (int count_zestaw = 0; count_zestaw < ile_zestawow;count_zestaw++)
        {

            int ile_int_ma_zestaw_temp;
            ile_int_ma_zestaw_temp = skaner.nextInt() ;

            Szukanie_klasa obiekt_do_szukania = new Szukanie_klasa(ile_int_ma_zestaw_temp);
            //int[] zestaw_arr_temp = new int[ile_int_ma_zestaw_temp] ;

            // wczytanie danych to tablicy
            for (int i = 0 ; i < ile_int_ma_zestaw_temp ; i++)
            {
                temp_int = skaner.nextInt() ;
                obiekt_do_szukania.arr_wejsciowa[i] = temp_int ;
            }

            ile_zapytan_temp = skaner.nextInt() ;


            int przedzial_od; // dolna granica przedzialu szukanego
            int przedzial_do;

            int przedzial_od_index ; // index w tablicy ww zmiennej
            int przedzial_do_index ;


            int wynik_ile_w_przedziale ;


            for ( int i = 0 ; i < ile_zapytan_temp ; i++)
            {
                przedzial_od = skaner.nextInt();
                przedzial_do = skaner.nextInt() ;

                wynik_ile_w_przedziale = 0;

                przedzial_od_index = 0;
                przedzial_do_index = 0;


                if ( przedzial_do >= przedzial_od )
                {

                    przedzial_od_index = obiekt_do_szukania.SearchBinFirst(przedzial_od ) ;
                    // zwykle wywolanie metody
                    przedzial_do_index = obiekt_do_szukania.SearchBinFirst(przedzial_do+1 ) ;
                    // wywolanie metody dla wartosci przedzialu gornej granicy zwiekszonej o jeden zeby nie uzywac petli i zeby wyszukac ostatnie wystapienie gornej granicy


                    // ewentualne korygowanie indexu wystepowania dolnej granicy zeby mozna bylo wykorzystac jednoznaczny wzor
                    if (  obiekt_do_szukania.arr_wejsciowa[przedzial_od_index]  < przedzial_od )
                    {
                        przedzial_od_index += 1 ;
                    }

                    // ewentualne korygowanie indexu wystepowania gornej granicy zeby mozna bylo wykorzystac jednoznaczny wzor
                    if ( obiekt_do_szukania.arr_wejsciowa[przedzial_do_index] > przedzial_do )
                    {
                        przedzial_do_index -= 1 ;
                    }

                    wynik_ile_w_przedziale = przedzial_do_index - przedzial_od_index + 1 ; // osatateczny wynik

                }

                System.out.println(wynik_ile_w_przedziale);
            }

            System.out.println(obiekt_do_szukania.ile_roznych_elementow_w_arr(obiekt_do_szukania.arr_wejsciowa)); // liczba roznych elemetnow w podanej temp tablicy
        }


    }
}

