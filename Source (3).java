//Marcin Sztukowski gr nr 3

import java.util.Scanner;

class Wagon{

    String name_wagon;
    Wagon next_wagon = null ; // referencja na nastepny wagon
    Wagon prev_wagon = null ; // referencja na poprzedni wagon
}
class Pociag{
    String name_pociag ; // nazwa pociagu
    Pociag next_pociag = null ; // wskaznik na kolejny pociag
                                // jak jest rowny null to dotarles do konca listy pociagow
    Wagon first_wagon = null ; // poczatek listy wagonow
    Wagon last_wagon = null ; // koniec listy wagonow

    // ta klasa zawiera metody lokalne ktore dzialaja na danych pociagach a nie globalnie na calym zestawie pociagow
    // proste krotkie metody

    void InsertFirst(  String w1 )
    { // insert first wstawia na poczatek Pociagu wagon o danej nazwie
        // nie ma pociagow ktore nie maja wagonow
        // tworzy wagon i poprawia wskazniki / referencje zeby wskaznik first_Wagon.prev
        // byl skierowany na null a to oznacza ze jest poczatek wagonow

        Wagon temp_wagon = first_wagon ;
        first_wagon = new Wagon();
        first_wagon.next_wagon = temp_wagon;
        temp_wagon.prev_wagon = first_wagon;
        first_wagon.name_wagon = w1 ;
    }

    void InsertLast( String w1 )
    {// insert first wstawia na koniec Pociagu wagon o danej nazwie
        // tworzy wagon i poprawia wskazniki / referencje zeby wskaznik last_Wagon.next
        // byl skierowany na null a to oznacza ze jest koniec wagonow

        Wagon temp_wagon = last_wagon;
        last_wagon = new Wagon() ;
        last_wagon.prev_wagon = temp_wagon ;
        temp_wagon.next_wagon = last_wagon ;
        last_wagon.name_wagon = w1 ;
    }

    void Display(  )
    {

        StringBuilder wyjsciowy_string = new StringBuilder(); // string builder zeby szybciej sie laczylo stringi (append)
        // stringbuilder robi za string ktory lepiej dziala
        // na koniec wywolujemy metode ktora konwertuje stringbuildera na string
        // i dajemy ten string na wyjscie
        wyjsciowy_string.append(this.name_pociag) ; // na poczatku dodajemy nazwe pociagu w ktorym jestesmy
        wyjsciowy_string.append(":" )  ;


        if ( first_wagon == last_wagon  )
        { // jezeli pociag ma jeden wagon to dodaj nazwe do stringbuildera i zakoncz

            wyjsciowy_string.append(" ") ;
            wyjsciowy_string.append(first_wagon.name_wagon) ;

        } else if (first_wagon.next_wagon == last_wagon ) {

            // jezeli pociag ma dwa wagony to dodaj 2 nazwy do stringbuildera i zakoncz

            wyjsciowy_string.append(" ") ;
            wyjsciowy_string.append(first_wagon.name_wagon) ;

            wyjsciowy_string.append(" ") ;
            wyjsciowy_string.append(last_wagon.name_wagon) ;


        } else {

            Wagon temp_wagon = first_wagon;
            Wagon temp_Wagon_poprzedni = first_wagon; // ta zmienna przechowuje poprzedni wago
                                                // ta zmienna ma KLUCZOWE znaczeie
            // pozw2ala nam sie dorbze poruszac po pociagu
            // czasami kierunek ulozenia wagonow ulega zmianie
            // trzeba zobazcyc czy sie cofasz czy idziesz do przodu i w zaleznosci od wyniku porownania przejdz do kolejnego wagonu (next or prev)
            // jezeli temp.next wskazuje na poprzedni wagon to wiadomo ze trzeba przejsc do temp.prev zeby pojsc dalej


            wyjsciowy_string.append(" ") ;
            wyjsciowy_string.append( temp_wagon.name_wagon ) ;

            temp_wagon = temp_wagon.next_wagon ;

            while ( temp_wagon != last_wagon  ) // kontynuuj dopoki nie dojdziesz do koncowego wagonu i dodaj kolejne nazwy do stringbuildera
            {

                wyjsciowy_string.append(" ") ;
                wyjsciowy_string.append( temp_wagon.name_wagon ) ;


                if ( temp_wagon.next_wagon == temp_Wagon_poprzedni)
                { // jezeli nastepny wagon jest wagonem poprzednim to idz do prev wagonu
                    temp_Wagon_poprzedni = temp_wagon;
                    temp_wagon = temp_wagon.prev_wagon ;
                } else if ( temp_wagon.prev_wagon == temp_Wagon_poprzedni )
                {// jezeli poprzeedni wagon jest wagonem poprzednim to idz do next wagonu
                    temp_Wagon_poprzedni = temp_wagon;
                    temp_wagon = temp_wagon.next_wagon ;
                }
            }

            wyjsciowy_string.append(" ") ;
            wyjsciowy_string.append( temp_wagon.name_wagon ) ;


        }

        String wyjsciowy_string_wlasciwy = "" ;
        wyjsciowy_string_wlasciwy = wyjsciowy_string.toString() ; // metoda stringbuildera ktory konwertuje ten typ na string

        System.out.println(wyjsciowy_string_wlasciwy);
    }





    void Reverse()
    {
        if ( first_wagon != last_wagon )
        {
            if (first_wagon.next_wagon == last_wagon) // has 2 elements
            { // odwroc first z last kolejnoscia
                first_wagon = last_wagon;
                last_wagon = last_wagon.prev_wagon;

                first_wagon.prev_wagon = null;
                first_wagon.next_wagon = last_wagon;

                last_wagon.next_wagon = null;
                last_wagon.prev_wagon = first_wagon;


            } else {
                // zamien laast z first i popraw wskazniki tych wagonow zeby
                // first next wskazywal na null co swiadczy o koncu pociagu
                // analogicznie z poczatkiem pociagu
                first_wagon.prev_wagon = first_wagon.next_wagon;
                first_wagon.next_wagon = null;

                last_wagon.next_wagon = last_wagon.prev_wagon;
                last_wagon.prev_wagon = null;

                Wagon temp_gon = first_wagon;
                //temp_gon = first_wagon;
                first_wagon = last_wagon;

                last_wagon = temp_gon;

            }

        }


    }


    void DelFirst()
    {
        if ( first_wagon != null )
        {

            if ( first_wagon == last_wagon )
            { // jezeli jest 1 wagon to ustaw referencje pociagu na ten wagon na null
                // tracisz dostep do tego wagonu
                first_wagon = null;
                last_wagon = null;
            }  else { // jezeli pociag ma wiecej niz jeden element // nie usunie to nam tego pociagu


                if (first_wagon.next_wagon.prev_wagon == first_wagon)
                {  // jezeli poczatek pociagu nie jest odwrocony to po prostu przesun referencje poczatkowa na kolejny wagon
                    first_wagon = first_wagon.next_wagon;
                    first_wagon.prev_wagon = null;
                } else { // jezeli poczatek pociagu  jest odwrocony to po prostu przesun referencje poczatkowa na kolejny wagon i musisz
                        // i musisz uwzglednic to ze wskaznik poprzedni i nastepny jest zamieniony kolejnoscia i musisz je zamienic
                        // zamienic zeby first_Wagon byl first czyli zeby first next to byl normalny (nie - null ) pointer a first prev to byl null pointer
                    first_wagon = first_wagon.next_wagon ;
                    first_wagon.next_wagon = first_wagon.prev_wagon;
                    first_wagon.prev_wagon = null;
                }


            }

        }

    }


    void DelLast()
    {
        if ( last_wagon != null )
        {

            if ( first_wagon == last_wagon )
            {
                // jezeli jest 1 wagon to ustaw referencje pociagu na ten wagon na null
                // tracisz dostep do tego wagonu
                first_wagon = null;
                last_wagon = null;
            }  else {
                // jezeli pociag ma wiecej niz jeden element // nie usunie to nam tego pociagu

                if (last_wagon.prev_wagon.next_wagon == last_wagon )
                { // jezeli koniec pociagu nie jest odwrocony to po prostu przesun referencje koncowa na poprzedni wagon
                    last_wagon = last_wagon.prev_wagon ;
                    last_wagon.next_wagon = null;
                } else {
                    // jezeli koniec pociagu  jest odwrocony to po prostu przesun referencje koncowa na poprzedni wagon i musisz
                    // i musisz uwzglednic to ze wskaznik poprzedni i nastepny jest zamieniony kolejnoscia i musisz je zamienic
                    // zamienic zeby last_Wagon byl last czyli zeby last prev to byl normalny (nie - null ) pointer a last next to byl null pointer

                    last_wagon = last_wagon.prev_wagon ;
                    last_wagon.prev_wagon = last_wagon.next_wagon;
                    last_wagon.next_wagon = null ;

                }


            }

        }



    }



}


class Dworzec {
    Pociag first_pociag = null;
    // klasa dworzec posiada tylko jedna zmienna jest to referencja do poczatkowego pocaigu
    // posiada ta klasa metody takie globalne ktore dzialaja na calym zestawie pociagow ( mozliwe ze trzeba usuwac albo doawac pociagi do dworca )

    void New( String t1, String w1 )
    {
        // dodaj nowy pociag na poczatek dworca
        // zawsze sie dodaje nowe na poczatek bo tak najszybciej jest dla programu ( bez dodawania na koniec dworca )

        // tworzymy nowa zmienna i w tej nowej zmiennej wskazujemy na poprzedni poczatkowy pociag
        // w poprzednik poczatkowym pociagu tez zmieniami referencje zeby prev wskazywalo na nowa zmienna ( first pociag )
        // oczywiscie musimy ztworzyc nowy wagon w ramach tego pociagu i nadac nazwy pociagowi i wagonowi zgodnie z poleceniem

        Pociag temp_pociag = first_pociag ;
        first_pociag = new Pociag();
        first_pociag.next_pociag = temp_pociag;
        first_pociag.name_pociag = t1;
        first_pociag.first_wagon = new Wagon() ;
        first_pociag.last_wagon = first_pociag.first_wagon;
        first_pociag.first_wagon.name_wagon = w1 ;

    }


    void TrainsList() {

        if ( first_pociag != null )
        {

            StringBuilder wyjsciowy_string = new StringBuilder();
            // tworzymy stringbuildera zeby leopie konkatenowac stringi ( szybciej )
            // na koniec uzyjemy metody ktora nam skonwertuje stringbuilder na string
            // a narazie uzywamy metody append do poszerzania stringa
            wyjsciowy_string.append("Trains:") ;

            Pociag temp_pociag = first_pociag;
            while (temp_pociag != null) {

                wyjsciowy_string.append(" ") ;
                wyjsciowy_string.append(temp_pociag.name_pociag) ;

                temp_pociag = temp_pociag.next_pociag;
            }

            String wyjsciowy_string_wlasciwy;
            wyjsciowy_string_wlasciwy = wyjsciowy_string.toString() ; // konwersja

            System.out.println(wyjsciowy_string_wlasciwy);
        }


    }


    void Union( String t1 , String t2 )
    {

        Pociag usuwany_pociag = first_pociag; // zmienna pomocnicza wskaznik do usuwanego pociagu
        Pociag ogromny_pociag = first_pociag; // zmienna pomocnicza wskaznik do pociagu ktory sie powieksza rozmiarowo
        Pociag temp_pociag = first_pociag ; // po tej zmiennej chodzimy po liscie pociagow
        Pociag przed_usuwanym_pociag = first_pociag ; // pomocnicza zmienna // lista jest jednostronna takze potrzebujemy zmiennej ktora wskazuje pooprzedni element w liscie


        // union dziala na zasadzie :
        // przerzucamy wskazniki / referencje na skrajach obu pociagow
        // w usuwanym to first
        // w ogromnym to last
        // laczymy te wagony
        // przesuwamy pociag ogromny.last na usuwany last
        // i mamy liste powiekszona o rozmiar usuwanej listy

        int czy_znaleziono_przed_usuwanym = 0;

        int czy_koniec = 0 ; // musi miec 2 - to znaczy ze znaleziono oba pociagi i mozna zaczynac

        if ( first_pociag.name_pociag.equals(t2)  )
        {
            przed_usuwanym_pociag = null;
            czy_znaleziono_przed_usuwanym = 1 ;
        }

        if ( !t1.equals(t2)  )
        {


            while ( (czy_koniec < 2) && ( temp_pociag != null ) )
            {

                if ( temp_pociag.name_pociag.equals(t1) )
                { // znaleziono  ogromny pociag
                    ogromny_pociag = temp_pociag ;
                    czy_koniec += 1;
                }

                if ( czy_znaleziono_przed_usuwanym != 1 )
                {

                    if ( temp_pociag.next_pociag.name_pociag.equals(t2) )
                    {
                        przed_usuwanym_pociag = temp_pociag ; // pociag przed usuwanym pociagiem znaleziono
                        // posluzy nam to do usuniecia pociagu i zadbanie o referencje odpowiednia
                        czy_znaleziono_przed_usuwanym = 1;
                    }

                }


                if ( temp_pociag.name_pociag.equals(t2) )
                {
                    usuwany_pociag = temp_pociag;
                    czy_koniec += 1;
                }

                temp_pociag = temp_pociag.next_pociag ;
            }

            if ( ogromny_pociag.first_wagon == ogromny_pociag.last_wagon )
            { // jezeli ogromny pociag ma 1 element

                if ( usuwany_pociag.first_wagon == usuwany_pociag.last_wagon )
                { // jezeli usuwany pociag ma jeden element

                    ogromny_pociag.last_wagon = usuwany_pociag.first_wagon;
                    ogromny_pociag.last_wagon.next_wagon = null;
                    ogromny_pociag.first_wagon.next_wagon = ogromny_pociag.last_wagon ;
                    ogromny_pociag.last_wagon.prev_wagon = ogromny_pociag.first_wagon;


                } else {

                    if (    usuwany_pociag.first_wagon.next_wagon.prev_wagon == usuwany_pociag.first_wagon   )
                    { // jezeli poczatek usuwanego pociagu nie jest w odwroconej kolejnosci i ma przynajmniej 2 wagony

                        ogromny_pociag.first_wagon.next_wagon = usuwany_pociag.first_wagon;
                        usuwany_pociag.first_wagon.prev_wagon = ogromny_pociag.first_wagon;
                        ogromny_pociag.last_wagon = usuwany_pociag.last_wagon ;


                    } else
                    {
                        // jezeli pociag jest odwrocony

                        ogromny_pociag.first_wagon.next_wagon = usuwany_pociag.first_wagon;
                        usuwany_pociag.first_wagon.prev_wagon = usuwany_pociag.first_wagon.next_wagon ;
                        usuwany_pociag.first_wagon.next_wagon = ogromny_pociag.first_wagon;
                        ogromny_pociag.first_wagon.prev_wagon = null ;
                        ogromny_pociag.last_wagon = usuwany_pociag.last_wagon;


                    }

                }



            } else
            {

                if ( ogromny_pociag.last_wagon.prev_wagon.next_wagon == ogromny_pociag.last_wagon )
                { // ogromny pociag ma nieodwrocony koniec

                    if ( usuwany_pociag.first_wagon == usuwany_pociag.last_wagon )
                    { // jw

                        usuwany_pociag.first_wagon.prev_wagon = ogromny_pociag.last_wagon;
                        ogromny_pociag.last_wagon.next_wagon = usuwany_pociag.first_wagon;
                        ogromny_pociag.last_wagon = ogromny_pociag.last_wagon.next_wagon ;
                        ogromny_pociag.last_wagon.next_wagon = null;

                    } else {
                        // jw

                        ogromny_pociag.last_wagon.next_wagon = usuwany_pociag.first_wagon;
                        usuwany_pociag.first_wagon.prev_wagon = ogromny_pociag.last_wagon;
                        ogromny_pociag.last_wagon = usuwany_pociag.last_wagon;

                    }



                } else {
                    // odwrocony koniec ma ogromny pociag

                    if ( usuwany_pociag.first_wagon == usuwany_pociag.last_wagon )
                    { // jw


                        ogromny_pociag.last_wagon.next_wagon = ogromny_pociag.last_wagon.prev_wagon ;
                        ogromny_pociag.last_wagon.prev_wagon = usuwany_pociag.first_wagon;
                        usuwany_pociag.first_wagon.prev_wagon = ogromny_pociag.last_wagon;

                        ogromny_pociag.last_wagon = usuwany_pociag.first_wagon ;


                    } else {

                        if (    usuwany_pociag.first_wagon.next_wagon.prev_wagon == usuwany_pociag.first_wagon   )
                        { // jw


                            ogromny_pociag.last_wagon.next_wagon = ogromny_pociag.last_wagon.prev_wagon ;
                            ogromny_pociag.last_wagon.prev_wagon = usuwany_pociag.first_wagon;
                            usuwany_pociag.first_wagon.prev_wagon = ogromny_pociag.last_wagon;

                            ogromny_pociag.last_wagon = usuwany_pociag.last_wagon ;

                        } else
                        { // jw


                            usuwany_pociag.first_wagon.prev_wagon = usuwany_pociag.first_wagon.next_wagon ;
                            usuwany_pociag.first_wagon.next_wagon = ogromny_pociag.last_wagon;
                            ogromny_pociag.last_wagon.next_wagon = ogromny_pociag.last_wagon.prev_wagon ;
                            ogromny_pociag.last_wagon.prev_wagon = usuwany_pociag.first_wagon;

                            ogromny_pociag.last_wagon = usuwany_pociag.last_wagon ;


                        }

                    }


                }


            }


            if ( przed_usuwanym_pociag == null )
            { // jezeli usuwany pociag jest pierwszy to first pociag przesuwasz o jeden w prawo
                first_pociag = first_pociag.next_pociag ;

            } else
            {

                if ( usuwany_pociag.next_pociag == null )
                {// jezeli usuwany pociag jest ostatni to first pociag przesuwasz o jeden w lewo
                    przed_usuwanym_pociag.next_pociag = null;
                } else {
                    // usuwasz usuwany pociag pointerami tak zeby pointer next w poprzedeni pociag wksazywal na usuwany pociag. nexyt
                    przed_usuwanym_pociag.next_pociag = usuwany_pociag.next_pociag ;

                }
            }

        }

    }


}

    public class Source {

        public static Scanner skaner = new Scanner(System.in); // klasa skanujaca wejscie


        public static void main(String[] args) {


            // tworzymy stringbuildera zeby leopie konkatenowac stringi ( szybciej )
            // na koniec uzyjemy metody ktora nam skonwertuje stringbuilder na string
            // a narazie uzywamy metody append do poszerzania stringa


            int ile_zestawow_danych = 0 ;
            String ile_zestawow_danych_str = "" ;
            ile_zestawow_danych_str = skaner.nextLine(); // zmienna pokazuje ile przypadkow dworcow trzeba rozpatrzec
            ile_zestawow_danych = Integer.parseInt(ile_zestawow_danych_str); // metoda statyczna klasy integer pozwala na konwersje szybka na int
            int k = 0;
            Dworzec dworzec_krakow = new Dworzec() ; // obiekt klasy dworzec - wskaznik na first tam jset i globalne metody


            while (k<ile_zestawow_danych)
            {

                dworzec_krakow.first_pociag = null ; // zerujemy wskaznik na first pociag zeby moc dzialac od nowa na nowych danych
                                                    // garbage collector zadba o przydziolona wczesniej pamiecd
                int ile_polecen = 0; //
                String ile_polecen_str = skaner.nextLine() ;
                ile_polecen = Integer.parseInt(ile_polecen_str) ; // szybka konwersja metoda statyczna klasy integer
                int i = 0;
                int j  = 0;
                // 'o'  ":"
                String temp_polecenie = "" ;  // cala linia polecenia tutaj bedzie
                String temp_parametr_1 = "" ; // rozbijamy ewentualne parametry polecenia zeby bylo latwiej
                String temp_parametr_2 = "" ;

                while ( i < ile_polecen )
                {

                    temp_polecenie = skaner.nextLine(); // kolejne polecenia

                    if ( temp_polecenie.charAt(0) != 'T'  )
                    { // jezeli polecenie to nie trainslist

                        if ( (temp_polecenie.charAt(0) == 'D' ) && ( temp_polecenie.charAt(1) == 'i' )  )
                        { // jezeli polecenie to Display TRAin

                            temp_parametr_1 = "";
                            StringBuilder temp_parametr_1_builder = new StringBuilder() ;
                            j = 8 ; // wszedzie na dole ustawiamy j na odpowiednia wartosc
                            // zeby program czytal w odpowiednim momencie ewentualny parametr
                            // czyta do momentu konca lub spacji i pote
                            // i potem program czyta ewentualny kolejny parametr az napotka koniec linii
                            // wszystko odbywa sie za pomoca string buildera ktorego na koniec zmieniamy na string

                            while ( j<temp_polecenie.length() )
                            {
                                temp_parametr_1_builder.append(temp_polecenie.charAt(j)) ;
                                j++;
                            }
                            temp_parametr_1 = temp_parametr_1_builder.toString() ;



                            char czy_znaleziono_pociag = 0; // zmienna pomocnicza okreslaca czy juz znalezlismy pociag // warunek stopu petli
                            Pociag pcg_temp = dworzec_krakow.first_pociag;
                            if ( pcg_temp != null  )
                            {

                                while ( ( pcg_temp != null ) && ( czy_znaleziono_pociag == 0 ) ) // albo nie znajdziesz zadanego pociagu to szukaj dalej
                                {
                                    // dopoki nie napotksz konca listy pociagow
                                    // albo nie znajdziesz zadanego pociagu to szukaj dalej

                                    if (  pcg_temp.name_pociag.equals( temp_parametr_1 ) )
                                    {
                                        czy_znaleziono_pociag = 1; // warunek stopu
                                        pcg_temp.Display(); // finalne zakonczenie polecenia

                                    }

                                    pcg_temp = pcg_temp.next_pociag ; // szukaj dalej przesun wskaznik na kolejny pociag

                                }
                            }

                        } else if ( temp_polecenie.charAt(0) == 'R'   )
                        { // jezeli polecenie to Reverse train

                            StringBuilder temp_parametr_1_builder = new StringBuilder() ;
                            temp_parametr_1 = "";
                            j = 8 ;
                            while ( j<temp_polecenie.length() )
                            {
                                temp_parametr_1_builder.append(temp_polecenie.charAt(j)) ;
                                j++;
                            }
                            temp_parametr_1 = temp_parametr_1_builder.toString() ;


                            char czy_znaleziono_pociag = 0;
                            Pociag pcg_temp = dworzec_krakow.first_pociag;
                            if ( pcg_temp != null  )
                            {

                                while ( ( pcg_temp != null ) && ( czy_znaleziono_pociag == 0 ) )
                                {

                                    if (  pcg_temp.name_pociag.equals( temp_parametr_1 ) )
                                    {
                                        czy_znaleziono_pociag = 1;
                                        pcg_temp.Reverse(); ;

                                    }
                                    pcg_temp = pcg_temp.next_pociag ; // lecimy dalej

                                }
                            }


                        } else {

                            if  ((temp_polecenie.charAt(0) == 'N' ) && ( temp_polecenie.charAt(1) == 'e' ) )
                            { // jezeli polecenie to New


                                StringBuilder temp_parametr_1_builder = new StringBuilder() ;// tutaj jest nazwa pocoagu
                                StringBuilder temp_parametr_2_builder = new StringBuilder() ; // tutaj jest nazwa wagonu

                                temp_parametr_1 = "";
                                temp_parametr_2 = "";

                                j = 4 ;
                                while ( temp_polecenie.charAt(j) != ' ' )
                                {
                                    temp_parametr_1_builder.append(temp_polecenie.charAt(j)) ;

                                    j++;
                                }
                                temp_parametr_1 = temp_parametr_1_builder.toString() ;

                                j++;

                                while ( j < temp_polecenie.length() )
                                {
                                    temp_parametr_2_builder.append(temp_polecenie.charAt(j)) ;

                                    j++;
                                }
                                temp_parametr_2 = temp_parametr_2_builder.toString() ;

                                dworzec_krakow.New(temp_parametr_1,temp_parametr_2); // wywolanie metody obiektu klasy dworzec

                            } else if ( (temp_polecenie.charAt(0) == 'U' ) && ( temp_polecenie.charAt(1) == 'n' ))
                            { // Jezeli polecenie to Union train1 train2

                                StringBuilder temp_parametr_1_builder = new StringBuilder() ; // nazwa pociagu 1
                                StringBuilder temp_parametr_2_builder = new StringBuilder() ; // nazwa pociagu 2

                                temp_parametr_1 = "";
                                temp_parametr_2 = "";
                                j = 6 ;

                                while ( temp_polecenie.charAt(j) != ' ' )
                                {
                                    temp_parametr_1_builder.append(temp_polecenie.charAt(j)) ;

                                    j++;
                                }
                                temp_parametr_1 = temp_parametr_1_builder.toString() ;

                                j++;

                                while ( j < temp_polecenie.length() )
                                {
                                    temp_parametr_2_builder.append(temp_polecenie.charAt(j)) ;

                                    j++;
                                }
                                temp_parametr_2 = temp_parametr_2_builder.toString() ;

                                dworzec_krakow.Union(temp_parametr_1,temp_parametr_2); // wywolanie metody obiektu klasy dworzec

                            } else if ( (temp_polecenie.charAt(0) == 'I' ) && ( temp_polecenie.charAt(6) == 'F' ) )
                            { // jezeli polecenie to insert first

                                StringBuilder temp_parametr_1_builder = new StringBuilder() ; // nazwa poaciagu
                                StringBuilder temp_parametr_2_builder = new StringBuilder() ; // nazwa wagonu

                                temp_parametr_1 = "";
                                temp_parametr_2 = "";
                                j = 12 ;

                                while ( temp_polecenie.charAt(j) != ' ' )
                                {
                                    temp_parametr_1_builder.append(temp_polecenie.charAt(j)) ;

                                    j++;
                                }

                                temp_parametr_1 = temp_parametr_1_builder.toString() ;

                                j++;

                                while ( j < temp_polecenie.length() )
                                {
                                    temp_parametr_2_builder.append(temp_polecenie.charAt(j)) ;

                                    j++;
                                }

                                temp_parametr_2 = temp_parametr_2_builder.toString() ;

                                char czy_znaleziono_pociag = 0;
                                Pociag pcg_temp = dworzec_krakow.first_pociag;
                                if ( pcg_temp != null  ) // jezeli dworzec nie jest pusty
                                {

                                    while ( ( pcg_temp != null ) && ( czy_znaleziono_pociag == 0 ) )
                                    {

                                        if (  pcg_temp.name_pociag.equals( temp_parametr_1 ) )
                                        {
                                            czy_znaleziono_pociag = 1; // koniec petli
                                            pcg_temp.InsertFirst(temp_parametr_2); // wtaw na poczatek pociagu wagon w temp_parametr_2

                                        }

                                        pcg_temp = pcg_temp.next_pociag ; // szukamy dalej

                                    }
                                }


                            } else if ( (temp_polecenie.charAt(0) == 'I' ) && ( temp_polecenie.charAt(6) == 'L' ) )
                            { // jezeli polecenie to insert last

                                StringBuilder temp_parametr_1_builder = new StringBuilder() ;
                                StringBuilder temp_parametr_2_builder = new StringBuilder() ;

                                temp_parametr_1 = "";
                                temp_parametr_2 = "";
                                j = 11 ;

                                while ( temp_polecenie.charAt(j) != ' ' )
                                {
                                    temp_parametr_1_builder.append(temp_polecenie.charAt(j)) ;

                                    j++;
                                }
                                temp_parametr_1 = temp_parametr_1_builder.toString() ;

                                j++;

                                while ( j < temp_polecenie.length() )
                                {
                                    temp_parametr_2_builder.append(temp_polecenie.charAt(j)) ;

                                    j++;
                                }

                                temp_parametr_2 = temp_parametr_2_builder.toString() ;

                                char czy_znaleziono_pociag = 0;
                                Pociag pcg_temp = dworzec_krakow.first_pociag;
                                if ( pcg_temp != null  )
                                {

                                    while ( ( pcg_temp != null ) && ( czy_znaleziono_pociag == 0 ) )
                                    {

                                        if (  pcg_temp.name_pociag.equals( temp_parametr_1 ) )
                                        {
                                            czy_znaleziono_pociag = 1; // koniec petli
                                            pcg_temp.InsertLast(temp_parametr_2); // wtaw na koniec pociagu wagon w temp_parametr_2

                                        }

                                        pcg_temp = pcg_temp.next_pociag ;

                                    }
                                }

                            } else if ( (temp_polecenie.charAt(0) == 'D' ) && ( temp_polecenie.charAt(3) == 'F' ) )
                            { // jezeli polecenie to dell first

                                StringBuilder temp_parametr_1_builder = new StringBuilder() ; // pociag
                                StringBuilder temp_parametr_2_builder = new StringBuilder() ; // wagon

                                temp_parametr_1 = "";
                                temp_parametr_2 = "";
                                j = 9 ;

                                while ( temp_polecenie.charAt(j) != ' ' )
                                {

                                    temp_parametr_1_builder.append(temp_polecenie.charAt(j)) ;

                                    j++;
                                }

                                temp_parametr_1 = temp_parametr_1_builder.toString() ;
                                j++;

                                while ( j < temp_polecenie.length() )
                                {
                                    temp_parametr_2_builder.append(temp_polecenie.charAt(j)) ;

                                    j++;
                                }

                                temp_parametr_2 = temp_parametr_2_builder.toString() ;


                                Pociag poprzedni_pociag = null ;// poprzedni pociag pomaga nam gdy usuwany jest pociag
                                // lista jest jednostronna dlatego potzebna jest pomocnicza zmienna ktora wskazuje na poprzedni element
                                char czy_znaleziono_pociag = 0;
                                Pociag pcg_temp = dworzec_krakow.first_pociag;
                                if ( pcg_temp != null  )
                                {
                                    if (  pcg_temp.name_pociag.equals(temp_parametr_1) )
                                    {
                                        poprzedni_pociag = null ;
                                    }

                                    while ( ( pcg_temp != null ) && ( czy_znaleziono_pociag == 0 ) )
                                    {


                                        if (  pcg_temp.name_pociag.equals( temp_parametr_1 ) )
                                        {
                                            String temp_name_of_wagon = pcg_temp.first_wagon.name_wagon ; // znaleziono pociag
                                            czy_znaleziono_pociag = 1;
                                            pcg_temp.DelFirst(); // usun pierwszy

                                            if ( (pcg_temp.first_wagon == pcg_temp.last_wagon) && ( pcg_temp.first_wagon == null )  )
                                            { // jezeli usuniety wagon byl jedynym to musizs usunac pociag
                                                // rozpatrujesz przypadki
                                                // jezeli porzedni wagon byl null to znaczy ze usuwasz first pociag
                                                // jzeli byl na koncu to prev pociag jest ostanim pociagiem listy
                                                // jezeli w srodku to omijasz wskaznikime usuwany pociag czyli dajes referencje na nastepny pociag
                                                if ( poprzedni_pociag == null )
                                                {
                                                    dworzec_krakow.first_pociag = dworzec_krakow.first_pociag.next_pociag ;
                                                } else if ( pcg_temp.next_pociag == null )
                                                {
                                                    poprzedni_pociag.next_pociag = null;
                                                } else
                                                {
                                                    poprzedni_pociag.next_pociag = pcg_temp.next_pociag ;
                                                }
                                            }

                                            dworzec_krakow.New(temp_parametr_2,temp_name_of_wagon); // dodaj usuniety wagon na poczatek dworca i stworz nowy pociag o danym imieniu


                                        } else {

                                            poprzedni_pociag = pcg_temp ;
                                            pcg_temp = pcg_temp.next_pociag ;
                                        }



                                    }
                                }


                            } else if ( (temp_polecenie.charAt(0) == 'D' ) && ( temp_polecenie.charAt(3) == 'L' ) )
                            { // jezeli polecenie to dell last

                                StringBuilder temp_parametr_1_builder = new StringBuilder() ; // pociag
                                StringBuilder temp_parametr_2_builder = new StringBuilder() ; // wagon

                                temp_parametr_1 = "";
                                temp_parametr_2 = "";
                                j = 8 ;


                                while ( temp_polecenie.charAt(j) != ' ' )
                                {
                                    temp_parametr_1_builder.append(temp_polecenie.charAt(j)) ;

                                    j++;
                                }
                                temp_parametr_1 = temp_parametr_1_builder.toString() ;

                                j++;

                                while ( j < temp_polecenie.length() )
                                {
                                    temp_parametr_2_builder.append(temp_polecenie.charAt(j)) ;

                                    j++;
                                }
                                temp_parametr_2 = temp_parametr_2_builder.toString() ;


                                Pociag poprzedni_pociag = null ; // poprzedni pociag pomaga nam gdy usuwany jest pociag
                                                                // lista jest jednostronna dlatego potzebna jest pomocnicza zmienna ktora wskazuje na poprzedni element
                                char czy_znaleziono_pociag = 0;
                                Pociag pcg_temp = dworzec_krakow.first_pociag;
                                if ( pcg_temp != null  )
                                {
                                    if (  pcg_temp.name_pociag.equals(temp_parametr_1) )
                                    {
                                        poprzedni_pociag = null ;
                                    }

                                    while ( ( pcg_temp != null ) && ( czy_znaleziono_pociag == 0 ) )
                                    {


                                        if (  pcg_temp.name_pociag.equals( temp_parametr_1 ) )
                                        {
                                            String temp_name_of_wagon = pcg_temp.last_wagon.name_wagon ;
                                            czy_znaleziono_pociag = 1;
                                            pcg_temp.DelLast();

                                            if ( (pcg_temp.first_wagon == pcg_temp.last_wagon) && ( pcg_temp.first_wagon == null )  )
                                            {
                                                // jezeli usuniety wagon byl jedynym to musizs usunac pociag
                                                // rozpatrujesz przypadki
                                                // jezeli porzedni wagon byl null to znaczy ze usuwasz first pociag
                                                // jzeli byl na koncu to prev pociag jest ostanim pociagiem listy
                                                // jezeli w srodku to omijasz wskaznikime usuwany pociag czyli dajes referencje na nastepny pociag

                                                if ( poprzedni_pociag == null )
                                                {
                                                    dworzec_krakow.first_pociag = dworzec_krakow.first_pociag.next_pociag ;
                                                } else if ( pcg_temp.next_pociag == null )
                                                {
                                                    poprzedni_pociag.next_pociag = null;
                                                } else
                                                {
                                                    poprzedni_pociag.next_pociag = pcg_temp.next_pociag ;
                                                }
                                            }

                                            dworzec_krakow.New(temp_parametr_2,temp_name_of_wagon);  // dodaj usuniety wagon na poczatek dworca i stworz nowy pociag o danym imieniu


                                        } else {

                                            poprzedni_pociag = pcg_temp ; // lecisz dalej // poprzedni pociag to aktualny pociag
                                            pcg_temp = pcg_temp.next_pociag ; // aktualny pociag to nastepny pcoiag
                                        }


                                    }
                                }


                            }

                        }

                    } else {

                        // polecenie to trainslist
                        dworzec_krakow.TrainsList();

                    }

                    i++;
                }

                k++;
            }

            //System.out.println("Hello world!");
        }
    }


    /*

IN

1
32
New A W1
InsertFirst A W0
InsertLast A W2
InsertLast A W3
New B W7
InsertFirst B W6
InsertFirst B W5
InsertFirst B W4
Trains
Display A
Display B
Union A B
Trains
Display A
DelLast A B
DelFirst A C
DelLast A D
DelFirst A E
DelLast A F
DelFirst A G
DelLast A H
DelFirst A I
Trains
Display A
Display B
Display C
Display D
Display E
Display F
Display G
Display H
Display I


out

Trains: B A
A: W0 W1 W2 W3
B: W4 W5 W6 W7
Trains: A
A: W0 W1 W2 W3 W4 W5 W6 W7
Trains: I H G F E D C B
B: W7
C: W0
D: W6
E: W1
F: W5
G: W2
H: W4
I: W3






in

1
43
New A W1
InsertFirst A W0
InsertLast A W2
InsertLast A W3
New B W7
InsertFirst B W6
InsertFirst B W5
InsertFirst B W4
Trains
Display A
Display B
Union A B
Trains
Display A
Reverse A
Display A
DelLast A B
Trains
Display A
DelFirst A C
Display A
Trains
DelLast A D
Trains
DelFirst A E
Trains
DelLast A F
Trains
DelFirst A G
Trains
Display A
DelLast A H
Trains
DelFirst A I
Trains
Display B
Display C
Display D
Display E
Display F
Display G
Display H
Display I



OUT

Trains: B A
A: W0 W1 W2 W3
B: W4 W5 W6 W7
Trains: A
A: W0 W1 W2 W3 W4 W5 W6 W7
A: W7 W6 W5 W4 W3 W2 W1 W0
Trains: B A
A: W7 W6 W5 W4 W3 W2 W1
A: W6 W5 W4 W3 W2 W1
Trains: C B A
Trains: D C B A
Trains: E D C B A
Trains: F E D C B A
Trains: G F E D C B A
A: W4 W3
Trains: H G F E D C B A
Trains: I H G F E D C B
B: W0
C: W7
D: W1
E: W6
F: W2
G: W5
H: W3
I: W4



IN
1
27
New T1 W2
InsertLast T1 W3
InsertFirst T1 W1
New T2 W6
InsertLast T2 W5
InsertLast T2 W4
Reverse T2
Union T1 T2
Reverse T1
Reverse T1
InsertFirst T1 W0
InsertLast T1 W9
Reverse T1
Reverse T1
New T3 W10
InsertLast T3 W11
InsertLast T3 W12
InsertLast T3 W13
Union T3 T1
Reverse T3
DelFirst T3 T1
DelLast T3 T2
Union T1 T3
Union T2 T1
Reverse T2
TrainsList
Display T2

OUT
Trains: T2
T2: W11 W12 W13 W0 W1 W2 W3 W4 W5 W6 W9 W10


in
1
23
New T1 W1
InsertLast T1 W2
Display T1
InsertFirst T1 W0
Display T1
DelFirst T1 T2
Display T1
Display T2
DelLast T1 T3
Display T1
Display T3
TrainsList
New T4 Z1
InsertLast T4 Z2
Reverse T4
Display T4
Union T3 T4
Display T3
TrainsList
Union T3 T2
Display T3
Reverse T3
Display T3


out
T1: W1 W2
T1: W0 W1 W2
T1: W1 W2
T2: W0
T1: W1
T3: W2
Trains: T3 T2 T1
T4: Z2 Z1
T3: W2 Z2 Z1
Trains: T3 T2 T1
T3: W2 Z2 Z1 W0
T3: W0 Z1 Z2 W2

     */