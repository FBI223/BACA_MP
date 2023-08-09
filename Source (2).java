//Marcin Sztukowski gr nr 3

import java.util.Scanner;



class Stosiwo{
    // pracujemay na charach
     // klasa zawierajaca obiekt stosu oraz aktualna dlg stosu
    // z metodami odpowiednimi dla klasy stos

    char[] arr_stosu;
    int n_stosu ;


    Stosiwo( int n_stosu) // konstruktor - przy tworzeniu obiektu tworzy stos o dlg n
    {
        arr_stosu = new char[n_stosu] ;
        n_stosu = 0;
    }

    char pop() // usun i zwroc wierzcholek stosu
    {
        char temp = '_';
        if ( n_stosu > 0  )
        {
            temp = arr_stosu[n_stosu-1];
            arr_stosu[n_stosu-1] = '_';
            n_stosu--;
        }
        return temp;
    }


    void insert(char x) // wstaw x na gore stosu a jak nie ma miejsca to powieksz rozmiar stosu dwa razy
    {
        if ( n_stosu < arr_stosu.length )
        {
            arr_stosu[n_stosu] = x;
            n_stosu++;
        } else {
            n_stosu = n_stosu * 2;
            char[] arr_temp = new char[n_stosu] ;
            for (int i = 0 ; i < n_stosu ; i++)
            {
                arr_temp[i] = arr_stosu[i];
            }
            arr_stosu = arr_temp ;
            n_stosu = n_stosu / 2;
        }
    }

    int len()
    {
        return n_stosu;
    }


    char top_return() // zwroc gore
    {
        if ( n_stosu > 0 )
        {
            return arr_stosu[n_stosu-1];
        } else
        {
            return '_';
        }

    }


    void top_delete() // usun gore
    {
        if ( n_stosu > 0 )
        {
            arr_stosu[n_stosu-1] = '_';
            n_stosu--;
        }
    }


    boolean is_empty() // czy pusty jest stos
    {

        if ( n_stosu == 0 )
        {
            return true ;
        } else
        {
            return false ;
        }

    }



    void show_stack() // metoda pomocnicza
    {
        String wy = "" ;
        for ( int j = 0 ; j < n_stosu ; j++ )
        {
            wy += arr_stosu[j];
            wy +=", ";
        }

        System.out.println(wy);
    }

    void delete_wszystko(int nowy_rozmiar ) // metoda pomocnicza
    {
        arr_stosu = new char[ nowy_rozmiar ] ;
        n_stosu = 0;

    }


}


class Stosiwo_stringowe{

    // pracujemay na stringach
    // klasa zawierajaca obiekt stosu oraz aktualna dlg stosu
    // z metodami odpowiednimi dla klasy stos
    // wszystko jw tylko na stringach

    String[] arr_stosu;
    int n_stosu ;


    Stosiwo_stringowe( int n_stosu)
    {
        arr_stosu = new String[n_stosu] ;
        n_stosu = 0;
    }

    String pop()
    {
        String temp = "_" ;
        if ( n_stosu > 0  )
        {
            temp = arr_stosu[n_stosu-1];
            arr_stosu[n_stosu-1] = "_";
            n_stosu--;
        }
        return temp;
    }


    void insert(String x)
    {
        if ( n_stosu < arr_stosu.length )
        {
            arr_stosu[n_stosu] = x;
            n_stosu++;
        } else {
            n_stosu = n_stosu * 2;
            String[] arr_temp = new String[n_stosu] ;
            for (int i = 0 ; i < n_stosu ; i++)
            {
                arr_temp[i] = arr_stosu[i];
            }
            arr_stosu = arr_temp ;
            n_stosu = n_stosu / 2;
        }
    }

    int len()
    {
        return n_stosu;
    }


    String top_return()
    {
        if ( n_stosu > 0 )
        {
            return arr_stosu[n_stosu-1];
        } else
        {
            return "_" ;
        }

    }


    void top_delete()
    {
        if ( n_stosu > 0 )
        {
            arr_stosu[n_stosu-1] = "_" ;
            n_stosu--;
        }
    }


    boolean is_empty()
    {

        if ( n_stosu == 0 )
        {
            return true ;
        } else
        {
            return false ;
        }

    }



    void show_stack()
    {
        String wy = "" ;
        for ( int j = 0 ; j < n_stosu ; j++ )
        {
            wy += arr_stosu[j];
            wy +=", ";
        }

        System.out.println(wy);
    }

    void delete_wszystko(int nowy_rozmiar )
    {
        arr_stosu = new String[ nowy_rozmiar ] ;
        n_stosu = 0;

    }


}


class INF_ONP {
    // public char[][] operatory_data = {  {'!','p','8'} ,{'~','p','8'}  ,{'^','p','7'} ,{'*','l','6'} ,{'/','l','6'} ,{'%','l','6'} ,{'+','l','5'} ,{'-','l','5'} ,{'<','l','4'} ,{'>','l','4'} ,{'?','l','3'} ,{'&','l','2'} ,{'|','l','1'} ,{'=','p','0'}   } ;
    // operatory i ich priorytety i lacznosci


    public char jaki_priorytet_chara( char wejsciowy_char  )
    { // metoda zwracajaca priorytet chara na wejsciu
        // dodatkowo dla operandu zwraca '9'
        // a dla nawiasow zwraca 10 czyli A w 0x
        // a jak x nie jest operandem , nawiasem , operatorem to dostaje priorytet zerowy czyli NULL

        char priorytet_wynik = 0;

        if ( (wejsciowy_char >= 97) && (wejsciowy_char <= 122) )
        {
            priorytet_wynik = '9' ;
        } else if ( (wejsciowy_char == '!') || ( wejsciowy_char == '~' ) )
        {
            priorytet_wynik = '8' ;
        } else if ( wejsciowy_char == '^'  )
        {
            priorytet_wynik = '7' ;
        } else if ( (wejsciowy_char == '*' ) || (wejsciowy_char == '/' ) ||(wejsciowy_char == '%' )  )
        {
            priorytet_wynik = '6' ;
        } else if ( (wejsciowy_char == '+' ) || (wejsciowy_char == '-' ) )
        {
            priorytet_wynik = '5' ;
        } else if ( (wejsciowy_char == '<' ) || (wejsciowy_char == '>' ) )
        {
            priorytet_wynik = '4' ;
        } else if ( wejsciowy_char == '?' )
        {
            priorytet_wynik = '3' ;
        } else if ( wejsciowy_char == '&' )
        {
            priorytet_wynik = '2' ;
        } else if ( wejsciowy_char == '|' )
        {
            priorytet_wynik = '1' ;
        } else if ( wejsciowy_char == '=' )
        {
            priorytet_wynik = '0' ;
        } else if ( (wejsciowy_char == '(' ) || (wejsciowy_char == ')' ) )
        {
            priorytet_wynik = 'A' ;
        }





        return priorytet_wynik ;

    }

    public char automat_sprawdzarka(char stan_wejscie  , char input_znak)
    {
        // automat podany w tresci zadania
        // dodatkowy stan '3' ktory obsluguje gdy jest blad
        //
        // error sie wyswietla przy     wejscie : zmienna    , stan wejsciowy : 1
        // error sie wyswietla przy     wejscie : op1       ,  stan wejsciowy : 1
        // error sie wyswietla przy     wejscie : op2       ,  stan wejsciowy : 1
        // error sie wyswietla przy     wejscie : op2       ,  stan wejsciowy : 0
        // error sie wyswietla przy     wejscie : op1       ,  stan wejsciowy : 2
        // error sie wyswietla przy     wejscie :  (       ,   stan wejsciowy : 1
        // error sie wyswietla przy     wejscie :  )       ,   stan wejsciowy : 0
        // error sie wyswietla przy     wejscie :  )       ,   stan wejsciowy : 2

        char output_stanu = '4' ; // 4 to nieokreslony stan // 3 to error
        char input_znak_klasyfikacja = '5' ;

        // klasyfikowanie wejscia
        //
        // zmienna to 0
        // op1 to 1
        // op 2 to 2
        // ( to 3
        // ) to 4
        // 5 to blad

        if (  (input_znak >= 97) && (input_znak <= 122) )
        {
            input_znak_klasyfikacja = '0' ;
        } else if (  ( input_znak == '~' ) || (input_znak == '!') )
        {
            input_znak_klasyfikacja = '1';
        }else if ( (input_znak == '^' ) || (input_znak == '*' ) ||(input_znak == '/' ) ||(input_znak == '%' ) ||(input_znak == '+' ) ||(input_znak == '-' ) ||(input_znak == '<' ) ||(input_znak == '>' ) ||(input_znak == '?' ) ||(input_znak == '&' ) ||(input_znak == '|' ) ||(input_znak == '=' )  )
        {
            input_znak_klasyfikacja = '2' ;
        } else if ( input_znak == '(' )
        {
            input_znak_klasyfikacja = '3' ;
        } else if ( input_znak == ')' )
        {
            input_znak_klasyfikacja = '4' ;
        } else {

            input_znak_klasyfikacja = '5' ; // znak 6 to znak nieokreslony - trzeba skipowac automat
        }



        if ( input_znak_klasyfikacja == '0' )
        {

            if ( stan_wejscie == '0' )
            {
                output_stanu ='1' ;
            } else if ( stan_wejscie == '1' )
            {
                output_stanu = '3' ;
            } else if ( stan_wejscie == '2' )
            {
                output_stanu = '1' ;
            }


        } else if ( input_znak_klasyfikacja == '1' )
        {

            if ( stan_wejscie == '0' )
            {
                output_stanu ='2' ;
            } else if ( stan_wejscie == '1' )
            {
                output_stanu = '3' ;
            } else if ( stan_wejscie == '2' )
            {
                output_stanu = '2' ;
            }

        } else if ( input_znak_klasyfikacja == '2' )
        {

            if ( stan_wejscie == '0' )
            {
                output_stanu ='3' ;
            } else if ( stan_wejscie == '1' )
            {
                output_stanu = '0' ;
            } else if ( stan_wejscie == '2' )
            {
                output_stanu = '3' ;
            }

        } else if ( input_znak_klasyfikacja == '3' )
        {

            if ( stan_wejscie == '0' )
            {
                output_stanu ='0' ;
            } else if ( stan_wejscie == '1' )
            {
                output_stanu = '3' ;
            } else if ( stan_wejscie == '2' )
            {
                output_stanu = '0' ;
            }

        } else if ( input_znak_klasyfikacja == '4' )
        {

            if ( stan_wejscie == '0' )
            {
                output_stanu ='3' ;
            } else if ( stan_wejscie == '1' )
            {
                output_stanu = '1' ;
            } else if ( stan_wejscie == '2' )
            {
                output_stanu = '3' ;
            }

        } else if ( input_znak_klasyfikacja == '5' )
        {
            output_stanu = stan_wejscie ;
        }


        return output_stanu ;



    }

    String konwertuj_na_onp( String wejsciowy_string , Stosiwo stak  )
    {

        // algorytm konwersji z inf na onp
        // uzywamy 1 stosu , charow ktory jest stosem operatorow

        stak.arr_stosu[0] = '_' ;
        stak.arr_stosu[1] = '_' ;
        stak.n_stosu = 0;

        String wyjsciowy_string = "ONP:" ;
        int i = 5;
        char czy_koniec = '0' ;
        int dlg_wejscioweg_str = wejsciowy_string.length() ;

        int ile_nawiasow_lewa = 0; // ta zmienna okresla ile jest sparowanych nawiasow i czy jest ich dobra kolejnosc i ile ogolnie jest nawiasow
                                    // jezeli napotkasz na nawias lewy to zwieksz zmienna
                                    // jesli napotkasz nawias prawy to zmiejsz ta zmienna o jeden
                                    // jezeli wynik bedzie mniejszy od zera to masz blad bo masz niesparowane nawiasy
                                    // jezeli konczy ci sie program i masz w tej zmiennej inna liczbe to wywal blad i zmien stan maszyny na 3



        //int ile_operandow = 0 ;
        char temp_priorytet = 0 ; // kolejny znak ze stringa wejsciowego
        char temp_char_wejsciowy = 0;
        char temp_stan = '0' ;


        while ( (i < dlg_wejscioweg_str ) && ( temp_stan != '3' ) )
        { // dopoki nie ma bledu w maszynie turinga to kontynuuj

            //System.out.println(" wejscie char char char : "+ temp_char_wejsciowy);
            temp_char_wejsciowy = wejsciowy_string.charAt(i) ;
            temp_priorytet = jaki_priorytet_chara( temp_char_wejsciowy ) ;

            if (temp_priorytet != 0)
            {
                // jezeli char jest zdatny to przetwarzania to idz dalej
                temp_stan = automat_sprawdzarka(temp_stan,temp_char_wejsciowy ) ; // sprawdz w automacie czy sie zgadza
                if ( temp_stan == '3' )
                {
                    // blad i koniec
                    wyjsciowy_string = "ONP: error" ;
                    break;
                }

                if ( temp_char_wejsciowy == '(' )
                {
                    ile_nawiasow_lewa += 1;
                }
                if ( temp_char_wejsciowy == ')' )
                {
                    ile_nawiasow_lewa -= 1 ;
                }

                if ( ile_nawiasow_lewa < 0  )
                {
                    // blad i koniec
                    wyjsciowy_string = "ONP: error" ;
                    temp_stan = '3' ;
                    break ;
                }


                if ( temp_priorytet == 'A' )
                { // A - to nawiasy
                    if ( temp_char_wejsciowy == '(' )
                    { // wloz nawias na stos
                        stak.insert('(');
                    } else if ( temp_char_wejsciowy == ')' )
                    {
                        while ( stak.top_return() != '(' )
                        {
                            // dopoki nie dotrzesz do nawiasu ( na stosie to zdejmuj operatory i kladz na wyjscie i potem usun ze stosu (
                            wyjsciowy_string += ' ' ;
                            wyjsciowy_string += stak.pop() ;
                        }
                        stak.pop();
                    }
                } else if ( temp_priorytet == '9' )
                {
                    // operand zapisz na wyjscie
                    wyjsciowy_string += ' ' ;
                    wyjsciowy_string += temp_char_wejsciowy ;
                    //ile_operandow += 1;

                } else if ( (temp_priorytet == '8') || ( temp_priorytet == '0' ) || ( temp_priorytet == '7' ) )
                { // operator prawostronny
                    while ( (jaki_priorytet_chara(stak.top_return()) > temp_priorytet)  && ( jaki_priorytet_chara(stak.top_return()) != 'A'  )  ) // char nie moze byc nawiasem !
                    { // dopoki na szczycie stosu znajduje sie oprator o priorytecie wiekszym to zapisz go na wyjscie a wejsciowy char daj na szczyt stosu
                        wyjsciowy_string += ' ' ;
                        wyjsciowy_string += stak.pop() ;
                    }
                    stak.insert(temp_char_wejsciowy) ;
                }
                else
                { // operator lewostronny
                    while ( (jaki_priorytet_chara(stak.top_return()) >= temp_priorytet) && ( jaki_priorytet_chara(stak.top_return()) != 'A'  ) )
                    {  // dopoki na szczycie stosu znajduje sie oprator o priorytecie wiekszym badz rownym to zapisz go na wyjscie a wejsciowy char daj na szczyt stosu
                        wyjsciowy_string += ' ' ;
                        wyjsciowy_string += stak.pop() ;
                    }
                    stak.insert(temp_char_wejsciowy) ;

//                    if ( temp_priorytet == '8' )
//                    {
//                        ile_operatorow_unarnych += 1;
//                    } else
//                    {
//                        ile_operatorow_dualnych += 1 ;
//                    }



                }




            }


            //stak.show_stack();
            //System.out.println( "char temp : "+temp_char_wejsciowy+"    to i : " +  i  + "   to wyjsciowy string "+wyjsciowy_string  );




            i++;
        }





        if ( ( i == dlg_wejscioweg_str ) && ( temp_stan != '3' ) && ( (ile_nawiasow_lewa > 0) || ( temp_stan == '0' ) || (temp_stan == '2' ) )  )
        { // jezeli dojdziemy do konca i stan maszyny jest rozny od blednego to sprawdz czy maszyna turinga zatrzymala sie w dobrym stanie czyli "1" oraz czy sa sparowane nawiasy // jak nie to daj blad
            temp_stan = '3' ;
            wyjsciowy_string = "ONP: error" ;
        }

        if ( temp_stan != '3' )
        {

            // jezeli nie ma bledu to zapisz reszte operatorow ze stosu na wyjscie
            while ( stak.n_stosu != 0 )
            {
                wyjsciowy_string += ' ' ;
                wyjsciowy_string += stak.pop() ;
            }

        }

        //System.out.println("-------------------------------");

        return wyjsciowy_string ;
    }


    String konwertuj_na_inf( String  wejsciowy_string , Stosiwo_stringowe stak_string , Stosiwo stak_priorytetow )
    {
        // onp na inf
        // uzywamy dwoch stosow 1wszy od priorytetow (stos charow ) 2gi jako stos stringow tam sa przechowywane podwyrazenia
        // na koncu zostaje jedno podwyrazenie na stosie ktore jest wyrazeniem calym i daje je na wyjscie


        String wyjscie_string = "INF:" ;
        int liczba_podwyrazen = 0; // liczba podwyrazen obsluguje bledy
                                    // jezeli znak jest operandem to zwieksz liczbe podwyrazen o 1
                                    // jezeli znak jest opeatorem dwuargumentowym to zmniejsz liczbe o 1
                                    // jezeli liczba podwyrazen jest mneijsza badz rowna zero to daj blad

                                    // na koncu jezeli liczba podwyrazen jest rozna od 1 to zakoncz z bledem
                                    // w innym wypadku zaakceptuj

        stak_priorytetow.n_stosu = 0;
        stak_priorytetow.arr_stosu[0] = '_' ;
        stak_priorytetow.arr_stosu[1] = '_' ;

        stak_string.n_stosu = 0;
        stak_string.arr_stosu[0] = "_";
        stak_string.arr_stosu[1] = "_" ;

        char temp_char = '\0' ;
        String temp_string = "" ;
        char temp_stan = '0' ; // stan , jezeli jesst rowny 3 to blad
        char temp_priorytet = '0' ; // priorytetm podwyrazenia temp


        int i = 5;
        while ( ( i < wejsciowy_string.length() ) && ( temp_stan != '3' ) )
        {

            temp_string = "" ;

            temp_char = wejsciowy_string.charAt(i) ;
            temp_priorytet = jaki_priorytet_chara( temp_char ) ;

            //System.out.println("char : " + temp_char + "     jaki proiorytet : " + temp_priorytet  );

            if ( (temp_priorytet != 0) && ( temp_priorytet != 'A' ) )
            { // jezeli wyrazenie nalezy do zbioru obslugiwanych znakow to idz dalej
                // i nie moze byc nawiasem rowniez ten znak


                if ( temp_priorytet == '9' )
                {
                    liczba_podwyrazen += 1 ;

                } else if ( temp_priorytet != '8' )
                {
                    liczba_podwyrazen -= 1 ;
                }
                if ( liczba_podwyrazen <= 0 )
                {
                    wyjscie_string = "INF: error" ;
                    temp_stan = '3' ;
                    break;
                }




                if ( temp_priorytet == '9' )
                {   // jezeli to opearand to daj na stos strignow
                    // daj na stos priorytetow '9'

                    temp_string = " " ;
                    temp_string += temp_char ;

                    stak_priorytetow.insert( '9' );
                    stak_string.insert( temp_string );


                } else {

                    // jezeli trafisz na operator to wiesz ze musisz zdjac ze stosu 1 albo 2 podwyrazenia i scalic je w calosc
                    // musisz takze zdjac operatory ze stosu i wstawic nowy priorytet na stos

                    temp_string = " " ;
                    temp_string += temp_char ;


                    if ( (temp_priorytet == '8' ) || ( temp_priorytet == '7' ) || ( temp_priorytet == '0' ) )
                    {
                            // jezeli lacznosc prawostronna


                            // tak jak nizej ale tylko nie wystepuje znak rownosci nigdzie zeby byla lacznosc lewostronna
                        // modyfikacja uwzglednia rozne lacznosci
                        // jezeli jest lacznosc lewostronna na wejsciowym opeatorze oraz lacznosc lewostronna na stringach stosu to nie dawaj nawiasow


                        if ( temp_priorytet == '8' )
                        { // operator unarny
                            if ( stak_priorytetow.pop() < temp_priorytet ) // ewentualnie <=
                            {
                                temp_string = temp_string + " (" +stak_string.pop()+ " )" ;
                            } else {
                                temp_string = temp_string +stak_string.pop();
                            }


                            stak_string.insert( temp_string );
                            stak_priorytetow.insert(temp_priorytet);


                        } else if ( temp_priorytet == '7' )
                        {

                            if ( stak_priorytetow.pop() < temp_priorytet )
                            {
                                temp_string = temp_string + " (" +stak_string.pop() + " )";
                            } else {
                                temp_string = temp_string +stak_string.pop();
                            }

                            if ( stak_priorytetow.pop() < temp_priorytet )
                            {
                                temp_string = " (" + stak_string.pop() + " )" + temp_string ;

                            } else {
                                temp_string = stak_string.pop() + temp_string ;
                            }

                            stak_string.insert( temp_string );
                            stak_priorytetow.insert(temp_priorytet);


                        } else if ( temp_priorytet == '0' )
                        {

                            if ( stak_priorytetow.pop() < temp_priorytet )
                            {
                                temp_string = temp_string + " (" +stak_string.pop()+ " )";
                            } else {
                                temp_string = temp_string +stak_string.pop();
                            }

                            if ( stak_priorytetow.pop() < temp_priorytet )
                            {
                                temp_string = " (" + stak_string.pop() + " )" + temp_string ;

                            } else {
                                temp_string = stak_string.pop() + temp_string ;
                            }

                            stak_string.insert( temp_string );
                            stak_priorytetow.insert(temp_priorytet);

                        }


                    } else
                    {
                        /*
                        gdy priorytet operatora w korzeniu poddrzewa jest wiekszy od priorytetu operatora w lewym potomku to podwyrazenie z lewego potomka bierzemy w nawias,
                        gdy priorytet operatora w korzeniu poddrzewa jest wiekszy lub rowny od priorytetu operatora z prawego potomka to podwyrazenie znajdujace sie w prawym potomku bierzemy w nawias.
                        */



                        if ( stak_priorytetow.pop() <= temp_priorytet )
                        { // jezeli gora czyli operator na ktorym teraz dzialamy ( rodzic ) jest wiekszy badz rowny od priorytetu PRAWEGO ( czyli gornego stringa stosu ) potomka to bierzemy potomka w nawias

                            temp_string = temp_string + " ("+stak_string.pop()+ " )" ;
                        } else {
                            temp_string = temp_string +stak_string.pop();
                        }

                        if ( stak_priorytetow.pop() < temp_priorytet )
                        { // jezeli gora czyli operator na ktorym teraz dzialamy ( rodzic ) jest wiekszy  od priorytetu LEWEGO ( czyli drugiego gornego stringa stosu ) potomka to bierzemy potomka w nawias

                            temp_string = " (" + stak_string.pop() + " )" + temp_string ;

                        } else {
                            // inaczej nie dawaj w nawiasy
                            temp_string = stak_string.pop() + temp_string ;
                        }

                        //System.out.println("         temp string : " + temp_string );
                        stak_string.insert( temp_string );
                        stak_priorytetow.insert(temp_priorytet);


                    }

                }


                //System.out.println("------------------------------");
                //stak_string.show_stack();
                //System.out.println("------------------------------");
                //stak_priorytetow.show_stack();

                //System.out.println("------------------------------");



            }

            //System.out.println("         temp string : " + temp_string );
            //System.out.println("////////////////////////////////////////");


            i++;
        }




        if ( liczba_podwyrazen != 1 )
        {       // jezeli liczba podwyrazen jest inna od 1 to daj blad
            wyjscie_string = "INF: error" ;
            temp_stan = '3' ;
        }


        if ( temp_stan != '3' )
        {
            // jezeli jest koniec dzialania programu i nie ma bledu to zdejmij ze stosu jedyny string i daj go na wyjscie
            wyjscie_string += stak_string.pop() ;

        }



        return wyjscie_string ;
    }


}

public class Source {

    public static Scanner skaner = new Scanner(System.in);



    public static void main(String[] args) {

        INF_ONP klasa_do_zadania = new INF_ONP() ; // obiekt klasy zeby moc korzystac z metod w statycznej klasie main
        Stosiwo_stringowe stak_str = new Stosiwo_stringowe(150 );
        Stosiwo stak = new Stosiwo(300) ; // rozmiar na sztywno 300 jak bedzie za malo to program poprawi


        String temp_string = "" ; // wczytywanie linii
        String temp_odpowiedz = "" ; // wyjsciowa odpowiedz
        int ile_wejsc = 0;
        temp_string = skaner.nextLine() ;
        ile_wejsc = Integer.parseInt( temp_string ) ;

        for (int i = 0 ; i < ile_wejsc ; i++ )
        {

            temp_string = skaner.nextLine() ;


            if ( temp_string.charAt(0) == 'I' )
            {
                temp_odpowiedz = klasa_do_zadania.konwertuj_na_onp( temp_string, stak )  ;

            } else if ( temp_string.charAt(0) == 'O' )
            {
                temp_odpowiedz = klasa_do_zadania.konwertuj_na_inf( temp_string , stak_str , stak ) ;
            }

            System.out.println(temp_odpowiedz);


        }



        // ponizej
        // testy inf - > onp
        // testy onp - > ing=f







        /*

        String test_1_inf = "INF: x=~~a+b*c" ;
        String test_2_inf = "INF: t=~a<x<~b" ;
        String test_3_inf = "INF: ( a,+ b)/..[c3" ;
        String test_4_inf = "INF: x=a=b=c^d^e" ;
        String test_5_inf = "INF: (r+y)=a=(b+c)+d" ;
        String test_6_inf = "INF: x=!(c>a & c<b)" ;


        String test_7_inf = "INF: x = a * ( b * c )" ;
        String test_8_inf = "INF: a + b + ( ~ a - a )" ;
        String test_9_inf = "INF: a * ( b - c )" ;


        String test_10_inf = "INF: ! ( a + b ) * c" ;
        String test_11_inf = "INF: ! a * ( b * c )";


        String test_12_inf = "INF: ((a";
        String test_13_inf = "INF: d/~p";
        String test_14_inf = "INF: x=a*(b*c)";
        String test_15_inf = "INF: (a)" ;
        String test_16_inf = "INF: a+b+" ;
        String test_17_inf = "INF: y-a*(b + x^v - e) / c + d / (~ p)" ;
        String test_18_inf = "INF: y-a*(b + x^v - e) / c + d / ~ p" ;

        String test_19_inf = "INF: (a*b +g ../ h)&(j'*s+a?l)" ;
        String test_20_inf = "INF: a * b + g / h & j * s + a ? l" ;
        String test_21_inf ="INF: x=$(a+b)?(c/,d -e)" ;

        String test_22_inf = "INF: y-a*(b+x^v-e)/c+d/(~p)" ;
        String test_23_inf = "INF: y-a*(b + x^v - e) / c + d / ~ p" ;
        String test_24_inf = "INF: ~(~(~((~a+~~b)/~c)*~d)^~e)" ;
        String test_25_inf = "INF: y-a*(b + x^v - e) / c + d / (~ p)" ;
        String test_26_inf = "INF: ((m+n+(b-v+(c*x+z/a)))^s^d=f)^g=h" ;
        String test_27_inf = "INF: a+b*(c^d-e)^(f+g*h)-i" ;
        String test_28_inf = "INF: ~a-~~b<c+d&!p|!!q" ;
        String test_29_inf = "INF: (x)" ;




        String test_30_inf ="INF: ~(~(~((~a+~~b)/~c)*~d)^~e)" ;
        String test_31_inf ="INF: ( a + b * c ) ^ ( x / y - z )" ;
        String test_32_inf ="INF: y-a*(b + x^v - e) / c + d / (~ p)" ;
        String test_33_inf ="INF: b=(a+b)*c^d" ;
        String test_34_inf ="INF: ((m+n+(b-v+(c*x+z/a)))^s^d=f)^g=h" ;
        String test_35_inf ="INF: a~+b" ;
        String test_36_inf ="INF: a~~" ;
        String test_37_inf ="INF: a+b~" ;
        String test_38_inf ="INF: ()a+b" ;
        String test_39_inf ="INF: (a+b)+()" ;
        String test_40_inf ="INF: ~()a" ;
        String test_41_inf ="INF: d=f>g<h/j^(k*(l-(q+(w=e>r<t/y^(u*(i-(o+p)))))))" ;
        String test_42_inf ="INF: a*b" ;
        String test_43_inf ="INF: ~~~~(a*b)" ;
        String test_44_inf ="INF: a*(b*c)" ;
        String test_45_inf ="INF: a*(b*(c*d))" ;
        String test_46_inf ="INF: a=b=c=d" ;
        String test_47_inf ="INF: a=b^c^d" ;
        String test_48_inf ="INF: a+b=c^d" ;
        String test_49_inf ="INF: a*(b-(c+d))"  ;
        String test_50_inf ="INF: a-b*(c+d)" ;
        String test_51_inf ="INF: s*(e=r)" ;
        String test_52_inf ="INF: a~b" ;
        String test_53_inf ="INF: (a)~(b)" ;
        String test_54_inf ="INF: (a)*~(b)" ;
        String test_55_inf ="INF: (a)~*(b)" ;
        String test_56_inf ="INF: (a)*(~b)" ;
        String test_57_inf ="INF: (a)*(~b)~" ;
        String test_58_inf ="INF: x=a+(((a-b)+c))" ;
        String test_59_inf ="INF: (a +b))(" ;




        String test_1_onp = "ONP: xabc**=" ;


        String test_2_onp = "ONP: ab+a~a-+" ;
        String test_3_onp = "ONP: x a ~ ~ b c * + =" ;
        String test_4_onp = "ONP: t a ~ x < b ~ < =" ;
        String test_5_onp = "ONP: a b + c /" ;
        String test_6_onp = "ONP: ( a,b,.).c;-,*" ;
        String test_7_onp = "ONP: abc++def++g+++" ;
        String test_8_onp = "ONP: abc++def++g+++" ;
        String test_9_onp = "ONP: x a b c d e ^ ^ = = =" ;
        String test_10_onp = "ONP: r y + a b c + d + = =" ;
        String test_11_onp = "ONP: x c a > c b < & ! =" ;

        String test_12_onp = "ONP: abcdef+=+=+" ;
        String test_13_onp = "ONP: ab+cd+ef+==" ;
        String test_14_onp = "ONP: abcd+ef+==+" ;


        */


        /*


        System.out.println( "-------------------------------------------------->>>>>>" +klasa_do_zadania.konwertuj_na_inf(test_1_onp,stak_str,stak)  );
        System.out.println( "-------------------------------------------------->>>>>>" + klasa_do_zadania.konwertuj_na_inf(test_2_onp,stak_str,stak)  );
        System.out.println("-------------------------------------------------->>>>>>" + klasa_do_zadania.konwertuj_na_inf(test_3_onp,stak_str,stak)  );
        System.out.println("-------------------------------------------------->>>>>>" +klasa_do_zadania.konwertuj_na_inf(test_4_onp,stak_str,stak)  );
        System.out.println( "-------------------------------------------------->>>>>>" +klasa_do_zadania.konwertuj_na_inf(test_5_onp,stak_str,stak)  );
        System.out.println("-------------------------------------------------->>>>>>" + klasa_do_zadania.konwertuj_na_inf(test_6_onp,stak_str,stak)  );
        System.out.println( "-------------------------------------------------->>>>>>" +klasa_do_zadania.konwertuj_na_inf(test_7_onp,stak_str,stak)  );


        System.out.println( "-------------------------------------------------->>>>>>" +klasa_do_zadania.konwertuj_na_inf(test_8_onp,stak_str,stak)  );
        System.out.println("-------------------------------------------------->>>>>>" + klasa_do_zadania.konwertuj_na_inf(test_9_onp,stak_str,stak)  );
        System.out.println("-------------------------------------------------->>>>>>" + klasa_do_zadania.konwertuj_na_inf(test_10_onp,stak_str,stak)  );
        System.out.println("-------------------------------------------------->>>>>>" + klasa_do_zadania.konwertuj_na_inf(test_11_onp,stak_str,stak)  );


        System.out.println("-------------------------------------------------->>>>>>" +klasa_do_zadania.konwertuj_na_inf(test_12_onp,stak_str,stak) );
        System.out.println("-------------------------------------------------->>>>>>" +klasa_do_zadania.konwertuj_na_inf(test_13_onp,stak_str,stak) );
        System.out.println("-------------------------------------------------->>>>>>" +klasa_do_zadania.konwertuj_na_inf(test_14_onp,stak_str,stak) );

        */




        /*

        30


String test_30_inf ="INF: ~(~(~((~a+~~b)/~c)*~d)^~e)"
String test_31_inf ="INF: ( a + b * c ) ^ ( x / y - z )"
String test_32_inf ="INF: y-a*(b + x^v - e) / c + d / (~ p)"
String test_33_inf ="INF: b=(a+b)*c^d"
String test_34_inf ="INF: ((m+n+(b-v+(c*x+z/a)))^s^d=f)^g=h"
String test_35_inf ="INF: a~+b"
String test_36_inf ="INF: a~~"
String test_37_inf ="INF: a+b~"
String test_38_inf ="INF: ()a+b"
String test_39_inf ="INF: (a+b)+()"
String test_40_inf ="INF: ~()a"
String test_41_inf ="INF: d=f>g<h/j^(k*(l-(q+(w=e>r<t/y^(u*(i-(o+p)))))))"
String test_42_inf ="INF: a*b"
String test_43_inf ="INF: ~~~~(a*b)"
String test_44_inf ="INF: a*(b*c)"
String test_45_inf ="INF: a*(b*(c*d))"
String test_46_inf ="INF: a=b=c=d"
String test_47_inf ="INF: a=b^c^d"
String test_48_inf ="INF: a+b=c^d"
String test_49_inf ="INF: a*(b-(c+d))"
String test_50_inf ="INF: a-b*(c+d)"
String test_51_inf ="INF: s*(e=r)"
String test_52_inf ="INF: a~b"
String test_53_inf ="INF: (a)~(b)"
String test_54_inf ="INF: (a)*~(b)"
String test_55_inf ="INF: (a)~*(b)"
String test_56_inf ="INF: (a)*(~b)"
String test_57_inf ="INF: (a)*(~b)~"
String test_58_inf ="INF: x=a+(((a-b)+c))"
String test_59_inf ="INF: (a +b))("




ONP: a ~ b ~ ~ + c ~ / ~ d ~ * ~ e ~ ^ ~
ONP: a b c * + x y / z - ^
ONP: y a b x v ^ + e - * c / - d p ~ / +
ONP: b a b + c d ^ * =
ONP: m n + b v - c x * z a / + + + s d ^ ^ f = g ^ h =
ONP: error
ONP: error
ONP: error
ONP: error
ONP: error
ONP: error
ONP: d f g > h j k l q w e r > t y u i o p + - * ^ / < = + - * ^ / < =
ONP: a b *
ONP: a b * ~ ~ ~ ~
ONP: a b c * *
ONP: a b c d * * *
ONP: a b c d = = =
ONP: a b c d ^ ^ =
ONP: a b + c d ^ =
ONP: a b c d + - *
ONP: a b c d + * -
ONP: s e r = *
ONP: error
ONP: error
ONP: a b ~ *
ONP: error
ONP: a b ~ *
ONP: error
ONP: x a a b - c + + =
ONP: error


         */




        /*

        String temp_s = "ONP: xya+*" ;
        System.out.println( "-------------------------------------------------->>>>>>" +klasa_do_zadania.konwertuj_na_inf(temp_s,stak_str,stak)  );


        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_7_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_8_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_9_inf, stak )  );

        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_10_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_11_inf, stak )  );

        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_12_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_13_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_14_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_15_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_16_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_17_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_18_inf, stak )  );

        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_19_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_20_inf, stak )  );

        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_21_inf, stak )  );

        System.out.println("************************************************************");

        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_22_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_23_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_24_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_25_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_26_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_27_inf, stak )  );

        System.out.println(klasa_do_zadania.konwertuj_na_onp(test_28_inf,stak));
        System.out.println(klasa_do_zadania.konwertuj_na_onp(test_29_inf,stak));


        */



        /*
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_30_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_31_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_32_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_33_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_34_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_35_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_36_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_37_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_38_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_39_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_40_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_41_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_42_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_43_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_44_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_45_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_46_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_47_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_48_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_49_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_50_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_51_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_52_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_53_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_54_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_55_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_56_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_57_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_58_inf, stak )  );
        System.out.println( klasa_do_zadania.konwertuj_na_onp( test_59_inf, stak )  );

        */


    }
}