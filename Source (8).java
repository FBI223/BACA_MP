// Marcin Sztukowski gr. 3


import java.util.Scanner;

class kolejka_node{

    // kolejka cykliczna potrzebna do algorytmu wyswietlania LVELORDER
    int n_curr; // aktualna liczba elementow w kolejce
    int n_max; // max pojemnosc kolejki
    Node[] arr;  // tablica z wezlami

    int i_poczatku; // index front
    int i_konca; // index rear

    public kolejka_node( int maxxx)
    { // KONSTRUKTOR
        n_curr=0;
        n_max=maxxx;
        arr = new Node[n_max] ; // inicjalizacja tablicy
        i_poczatku=0;
        i_konca=-1;
    }

    public boolean isEmpty() {
        return n_curr == 0; // czu pusta
    }

    public boolean isFull() {
        return n_curr == n_max ; // czy pelna
    }

    public void enqueue(Node item) {
        if (isFull()) { // ZAKOLEJKUJ ITEM
            //System.out.println("Kolejka jest pelna. Nie mozna dodac elementu.");
            return;
        }
        // JEZELI JEST MIEJSCE W KOLEJCE TO DODAJ ELEMENT NA KONIEC
        i_konca = (i_konca + 1) % n_max;
        arr[i_konca] = item;
        n_curr++; // ZWIEKSZ ROZMIAR
    }

    public Node dequeue() {
        if (isEmpty()) {
            //System.out.println("Kolejka jest pusta. Nie mozna usunac elementu.");
            return null;
        }
        // USUN Z KOLEJKI PIERWSZY ELEMENT
        Node item = arr[i_poczatku];
        i_poczatku = (i_poczatku + 1) % n_max;
        n_curr--; // ZMNIEJSZ DLUGOSC
        return item;
    }

//    public Node peek() {
//        if (isEmpty()) {
//            //System.out.println("Kolejka jest pusta.");
//            return null;
//        }
//        return arr[i_poczatku]; // ZWROC ELEMENT Z POCZATKU KOLEJKI
//    }

    public int size() {
        return n_curr; // zwroc rozmiar
    }
}

class stak{
    // klasa stack ale tak naprawde to nie ma u mnie funkcji jak stack tylko jak tablica do przechowywania
    // porzadanego przez nas szukanego porzadku

    int n_curr;
    int n_max;
    int[] arr;

    public stak(int maxxx)
    {
        //Konstruktor
        n_curr=0;
        n_max = maxxx;
        arr = new int[maxxx]; // inicjalizacja
    }



    public int pop()
    {
        // usun z konca i zwroc element z tablicy
        if ( n_curr > 0  )
        {
            int x = arr[n_curr-1];
            n_curr--;
            return x;
        } else
        {
            return -1;
        }
    }

    public int last_return()
    {
        // zwroc ostatni element tablicy bez usuwania
        if ( n_curr > 0  )
        {
            return arr[n_curr-1];
        } else
        {
            return -1;
        }
    }

    public void add(int x_in)
    {
        // dodaj do tablicy element
        if ( n_curr < n_max )
        {
            arr[n_curr] = x_in;
            n_curr++;
        }
    }

    public boolean is_empty()
    {
        // czy pusta tablica jest
        if ( n_curr==0 )
        {
            return true;
        } else {
            return false;
        }
    }

    public void print_stak( )
    { // metoda pomocnicza drukuje nam wynik algorytmow
        // drukuje zawartosc stosu / tablicy
        if ( n_curr > 0 )
        {
            int i = 0 ;
            while ( i < n_curr - 1  )
            {

                System.out.print(arr[i]);
                System.out.print(" ");
                i++;
            }
            System.out.println(arr[i]);
        }
    }

}


class Node {
    // clasa przechowujaca wezly
    // oraz wskazniki do innych wezlow czyli potomkow lewych i prawych
    public int data;
    public Node lewa;
    public Node prawa;
    public Node(int item) {
        // konstror przyjmuje integer
        data = item;
        lewa = null;
        prawa = null;
    }
}



public class Source {


    static void print_level_order(Node root , stak stakk) {
        // algorytm do wyswietlania levelorder ( wszerz )
        // tworzysz kolejke wezlow i dodajesz w odpowiedniej kolejnosci wezly

        // argumenty to korzen oraz tablia przechowujaca wynik

        kolejka_node queue = new kolejka_node(10000);
        queue.enqueue(root); // zakolejkuj

        while (!queue.isEmpty()) { // dopoki kolejka nie jest pusta to powtarzaj

            Node tempNode = queue.dequeue(); // zdejmij wezel z kolejki i dodaj do wyniku
            //System.out.print(tempNode.data + " ");
            stakk.add( tempNode.data );

            if (tempNode.lewa != null) { // jezeli potomek lewy istnieje to zakolejkuj tego potomka
                queue.enqueue(tempNode.lewa);
            }

            if (tempNode.prawa != null) { // jezeli potomek prawy istnieje to zakolejkuj tego potomka
                queue.enqueue(tempNode.prawa);
            }
        }
    }



    public static void prin_preorder_from_tree(Node node , stak stakk) {
        if (node == null)
            return; // jezeli dotarles do pustego wezla to koniec

        //System.out.print(node.data + " ");
        stakk.add( node.data ); // na poczatek dodaj do wyniku integer
        prin_preorder_from_tree(node.lewa, stakk); // odwiedz potem lewego potomka (rekurencja)
        prin_preorder_from_tree(node.prawa, stakk); // odwiedz potem prawego potomka (rekurencja)
    }

    public static void print_postorder_from_tree(Node node, stak stakk) {
        if (node == null)
            return; // jezeli dotarles do pustego wezla to koniec

        print_postorder_from_tree(node.lewa, stakk); // odwiedz najpierw lewego potomka (rekurencja)
        print_postorder_from_tree(node.prawa, stakk); // odwiedz najpierw prawego potomka (rekurencja)
        //System.out.print(node.data + " ");
        stakk.add( node.data ); // dopiero na koniec dodaj do wyniku integer
    }

    static int pre_index_do_pre_in = 0; // ustaw zero zawsze
    // zmienna statyczna ktora jest widoczna z metody ktora tworzy drzewo
    // przechodzisz pokolei od lewej do prawej strony
    // gdyz w preorer najpierw korzen jest na poczatku i dopiero potem idziesz dalej ( do potomkow )

    static Node build_tree_pre_in(int in[], int pre[], int inStart, int inEnd) {
        if (inStart > inEnd)
            return null; // jezeli wyjdziesz poza zakres tablicy inorder lub rozpatrzysz juz wszytkie poddrzewa

        Node node = new Node(pre[pre_index_do_pre_in]); // stworz wezel z kolejnym danym integerem w tablicy preorder ( pokolei kolejne )
        pre_index_do_pre_in++; // lecisz dalej z kolejna wartoscia tablicy preorder

        if (inStart == inEnd)
            return node; // jezeli trafiles do skrajnego wezla w inorder to znaczy ze nie musisz dalej wykonywac operacji rekurencyjnych bo nie ma juz potomkowdo rozpatrzenia

        int inIndex = search(in, node.data, inStart, inEnd); // szukasz danego integera z pre[preIndex_do_pre_in] w inorder tablicy i zwracasz index


        // najpierw idziesz na lewo potem na prawo bo tworzymy postorder

        node.lewa = build_tree_pre_in(in, pre, inStart, inIndex - 1); // wiesz ze na lewo od indexu szukanego pre[preIndex_do_pre_in] w inorder sa potomki tego korzenia
        node.prawa = build_tree_pre_in(in, pre, inIndex + 1, inEnd); // wiesz ze na prawo od indexu szukanego pre[preIndex_do_pre_in] w inorder sa potomki tego korzenia
        // dla obu przypadkow wykonaj rekurencyjnie metode

        return node; // zwroc nowo stworzony wezel
    }



    static int post_index_do_in_post = 1; // ustaw (( post.lenght -1 ))
    // zmienna statyczna ktora jest widoczna z metody ktora tworzy drzewo
    // przechodzisz pokolei od prawej do lewej strony
    // gdyz w postorder najpierw korzen jest na koncu i dopiero potem idziesz dalej ( do potomkow )

    static Node buildTree_in_post(int in[], int post[], int inStart, int inEnd ) {
        if (inStart > inEnd) // jezeli wyjdziesz poza zakres tablicy inorder lub rozpatrzysz juz wszytkie poddrzewa
            return null;

        int rootValue = post[post_index_do_in_post];
        Node root = new Node(rootValue); // stworz wezel z kolejnym danym integerem w tablicy postorder ( pokolei kolejne )

        int inIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            // szukasz wartosci post[postIndex_do_in_post] w tablicy inorder i zwracasz index
            if (in[i] == rootValue) {
                inIndex = i;
                break;
            }
        }

        post_index_do_in_post--; // lecisz dalej z kolejna wartoscia tablicy postorder


        // najpierw idziesz na prawo dopiero potem na lewo bo tworzymy preorder

        root.prawa = buildTree_in_post(in, post, inIndex + 1, inEnd);  // wiesz ze na prawo od indexu szukanego pre[preIndex_do_pre_in] w inorder sa potomki tego korzenia
        root.lewa = buildTree_in_post(in, post, inStart, inIndex - 1); // wiesz ze na lewo od indexu szukanego pre[preIndex_do_pre_in] w inorder sa potomki tego korzenia
        // dla obu przypadkow wykonaj rekurencyjnie metode

        return root;  // zwroc nowo stworzony wezel
    }




    // Prints preorder traversal from given inorder and postorder traversals

    // funkcja drukujaca preorder jezeli mamy tylko tablice postorder i inorder i zapisujesz wynik w klasie stak
    // operujesz na odpowiednich indexach w obu tablicach zeby potrem rekurencyjnie wywolac metode dla odpowiedniej ilosci wezlow oraz odpowiednich wezlow


    // /////////////////////////////////////////////////////////////

    // A utility function to search x in arr[] of size n
    static int search(int arr[], int x, int start, int end) {
        // metoda pomocnicza ktora szuka nam indexu w tablicy oraz  wartosci zadanej w argumencie
        for (int i = start; i <= end; i++)
            if (arr[i] == x)
                return i;
        return -1;
    }

    // Prints postorder traversal from
    // given inorder and preorder traversals

    // funkcja drukujaca preorder jezeli mamy tylko tablice postorder i inorder i zapisujesz wynik w klasie stak
    // operujesz na odpowiednich indexach w obu tablicach zeby potrem rekurencyjnie wywolac metode dla odpowiedniej ilosci wezlow oraz odpowiednich wezlow


    public static Scanner skaner = new Scanner(System.in); // klasa skanujaca wejscie

    public static void main(String[] args) {

        stak stosiwo = new stak(10000); // wyjsciowa tablica ( dla zmylenia ma nazwe stack ale to nie jest stack )
        int ile_zestawow = skaner.nextInt();

        for (int i = 0; i < ile_zestawow; i++) {
            int n_arr = skaner.nextInt(); // dlugosc tablic
            skaner.nextLine();
            int[] arr_1 = new int[n_arr]; // preorder lub postorder
            int[] arr_2 = new int[n_arr]; // inorder
            String s1 = skaner.nextLine();
            for (int j = 0; j < n_arr; j++) {
                arr_1[j] = skaner.nextInt(); // wczytywanie danych
            }
            skaner.nextLine();
            String s2 = skaner.nextLine();
            for (int j = 0; j < n_arr; j++) {
                arr_2[j] = skaner.nextInt(); // wczytywanie danych
            }

            Node root_drzewo = null; // korzen drzewa


            System.out.println("ZESTAW " + (i +1));

            if ( s1.charAt(0) == 'P' )
            {

                if ( s1.charAt(1) == 'R' ) // pre in
                {
                    pre_index_do_pre_in = 0;
                    stosiwo.n_curr=0;
                    root_drzewo = build_tree_pre_in(arr_2,arr_1,0,arr_1.length-1); // wywolanie metody do zbudowania drzewa
                    // korzen przechowujemy w zmiennej
                    print_postorder_from_tree(root_drzewo,stosiwo); // drukujemy wynik w tablicy stak
                    System.out.println("POSTORDER");
                    stosiwo.print_stak();

                } else if ( s1.charAt(1) == 'O' ) // in post
                {
                    post_index_do_in_post = arr_2.length-1;
                    stosiwo.n_curr=0;
                    root_drzewo = buildTree_in_post(arr_2,arr_1,0,arr_1.length-1); // wywolanie metody do zbudowania drzewa
                    prin_preorder_from_tree(root_drzewo,stosiwo); // drukujemy wynik w tablicy stak
                    System.out.println("PREORDER");
                    stosiwo.print_stak();

                }

                System.out.println("LEVELORDER");
                stosiwo.n_curr=0;
                print_level_order(root_drzewo,stosiwo);
                stosiwo.print_stak();

            }

        }
    }

}



/*





in

6
15
PREORDER
25 15 10 4 12 22 18 24 50 35 31 44 70 66 90
INORDER
4 10 12 15 18 22 24 25 31 35 44 50 66 70 90
15
POSTORDER
4 12 10 18 24 22 15 31 44 35 66 90 70 50 25
INORDER
4 10 12 15 18 22 24 25 31 35 44 50 66 70 90
11
PREORDER
1 2 4 8 5 9 3 6 7 10 11
INORDER
8 4 2 5 9 1 6 3 10 7 11
11
POSTORDER
8 4 9 5 2 6 10 11 7 3 1
INORDER
8 4 2 5 9 1 6 3 10 7 11
9
POSTORDER
4 2 7 5 9 8 6 3 1
INORDER
4 2 1 5 7 3 6 8 9
9
PREORDER
1 2 4 3 5 7 6 8 9
INORDER
4 2 1 5 7 3 6 8 9


out

ZESTAW 1
POSTORDER
4 12 10 18 24 22 15 31 44 35 66 90 70 50 25
LEVELORDER
25 15 50 10 22 35 70 4 12 18 24 31 44 66 90
ZESTAW 2
PREORDER
25 15 10 4 12 22 18 24 50 35 31 44 70 66 90
LEVELORDER
25 15 50 10 22 35 70 4 12 18 24 31 44 66 90
ZESTAW 3
POSTORDER
8 4 9 5 2 6 10 11 7 3 1
LEVELORDER
1 2 3 4 5 6 7 8 9 10 11
ZESTAW 4
PREORDER
1 2 4 8 5 9 3 6 7 10 11
LEVELORDER
1 2 3 4 5 6 7 8 9 10 11
ZESTAW 5
PREORDER
1 2 4 3 5 7 6 8 9
LEVELORDER
1 2 3 4 5 6 7 8 9

ZESTAW 6
POSTORDER
4 2 7 5 9 8 6 3 1
LEVELORDER
1 2 3 4 5 6 7 8 9





IN

2
14
PREORDER
3 4 11 7 13 99 100 6 12 50 60 14 51 66
INORDER
7 11 4 99 13 100 3 50 12 60 6 51 14 66
14
POSTORDER
7 11 99 100 13 4 50 60 12 51 66 14 6 3
INORDER
7 11 4 99 13 100 3 50 12 60 6 51 14 66


OUT


ZESTAW 1
POSTORDER
7 11 99 100 13 4 50 60 12 51 66 14 6 3
LEVELORDER
3 4 6 11 13 12 14 7 99 100 50 60 51 66

ZESTAW 2
PREORDER
3 4 11 7 13 99 100 6 12 50 60 14 51 66
LEVELORDER
3 4 6 11 13 12 14 7 99 100 50 60 51 66



 stak stosiwo = new stak(300);


        int[] in1 = {4, 2, 5, 1, 3, 6};
        int[] pre1 = {1, 2, 4, 5, 3, 6};
        int n = in1.length;



        int[] pre2 = {1, 2, 4, 8, 5, 9, 3, 6, 7, 10, 11};
        int[] in2 = {8, 4, 2, 5, 9, 1, 6, 3, 10, 7, 11};

        int[] post3 = {4, 2, 7, 5, 9, 8, 6, 3, 1};
        int[] in3 = {4, 2, 1, 5, 7, 3, 6, 8, 9};


        stosiwo.n_curr=0;
        preIndex_do_pre_in = 0;
        Node root2 = build_tree_pre_in(in2, pre2, 0, in2.length - 1 );
        print_postorder_from_tree(root2,stosiwo);
        stosiwo.print_stak();
        System.out.println();


        stosiwo.n_curr=0;
        postIndex_do_in_post = post3.length - 1;
        Node root3 = buildTree_in_post(in3, post3, 0, in3.length - 1  );
        printPreorder_from_tree(root3,stosiwo);
        stosiwo.print_stak();
        System.out.println();


        System.out.println();
        System.out.println();
        stosiwo.n_curr=0;
        print_level_order(root2, stosiwo);
        stosiwo.print_stak();

        System.out.println();
        System.out.println();
        stosiwo.n_curr=0;
        print_level_order(root3, stosiwo);
        stosiwo.print_stak();


 */