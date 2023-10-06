package SiAOD.HashTable;
public class HashTable // Класс, предоставляющий функционал хеш-таблицы с цепочным хешированием (по заданию)
{
    private ListNode[] listNodes; // Массив для хранения элементов хеш-таблицы
    private int counter = 0; // Счётчик добавленных элементов в хеш-таблицу
    private final double load_factor = 0.75; // Коэффициент нагрузки по достижении которого,
    // хеш-таблица автоматически увеличит размер и рехеширует элементы
    private int sizeTable = 10; // Начальный размер хеш-таблицы
    public HashTable()
    {
        listNodes = new ListNode[sizeTable];
    }
    public boolean insert(int key, int group_number, String fullName) // Метод добавления элемента в хеш-таблицу
    {
        for (ListNode node : listNodes) // Перебираем каждый элемент в хеш таблице
        {
            while (node != null) // Если этот элемент представляет собой односвязанный список, то двигаемся по нему
            {
                if (key == node.getKey()) // Поскольку ключ каждого элемента должен быть уникальным, здесь идёт проверка на это
                {
                    System.out.println("Вставка не удалась. Одинаковые ключи элементов");
                    return false;
                }
                node = node.next; // Движение по односвязанному списку
            }
        }
        ListNode listNode = new ListNode(key, group_number, fullName); // Создаём новый элемент на основе исходных данных
        counter++;
        if (((double) counter / sizeTable) > load_factor) // Проверка на коэффициент нагрузки (заполненность хеш-таблицы)
            resize();
        int index = getIndex(key); // Получаем индекс элемента в массиве для использования в хеш-таблице
        if (listNodes[index] == null) // Если раньше в позиции с заданным индексом не было элементов (не было коллизии),
            // то мы добавляем его сюда
            listNodes[index] = listNode;
        else // Иначе идём в конец списка (по заданию - цепочное хеширование)
        {
            ListNode temp = listNodes[index];
            while (temp.next != null) // Проход к концу списка в одном элементе массива
                temp = temp.next;
            temp.next = listNode; // Когда добрались до конца, добавляем в конец списка элемент
        }
        System.out.println("Вставка прошла успешно.");
        return true;
    }
    public boolean deleteItem(int key) // Метод удаления элемента из хеш-таблицы
    {
        if (getListItem(key) == null) // Если удаляемого элемента нет в таблице, удаление невозможно
        {
            System.out.println("Удаляемый элемент не найден в хэш-таблице");
            return false;
        }
        else
        {
            int index = getIndex(key); // Получаем индекс элемента в хеш-таблице
            ListNode target = getListItem(key); // Временная переменная, хранящая ссылку на удаляемый элемент
            ListNode temp = target.next; // Временная переменная для замены связей в списке
            ListNode current = listNodes[index]; // Стартовый элемент, голова списка в данном индексе хеш-таблицы
            while (current != null) // Если есть список в данном индексе хеш-таблицы - идём по нему
            {
                if (current == target) // Если удаляемый элемент является "головой"
                {
                    listNodes[index] = current.next; // Просто вместо головы ставим следующий элемент списка
                    return true;
                }
                else if (current.next == target) // Если удаляемый элемент расположен в центре или конце списка
                {
                    target.next = null; // Замена и стирание связей между элементами
                    current.next = temp;
                }
                current = current.next; // Движение вперед
            }
            return true;
        }
    }
    public void print() // Метод, выводящий все элементы хеш-таблицы на экран
    {
        for (ListNode node : listNodes) // Движение по массиву в хеш-таблице
        {
            while (node != null) // Движение по списку в каждом элементе массива
            {
                System.out.println("Группа учащегося (без пробелов): " + node.getGroup_number());
                node = node.next;
            }
        }
        System.out.println();
    }
    private void resize() // Метод, автоматически увеличивающий размер исходной хеш-таблицы
    {
        System.out.println("Происходит рехэширование таблицы.");
        int tempSizeTable = sizeTable; // Сохранение текущей длины
        sizeTable *= 2; // Увеличение размера в два раза
        ListNode[] listes = new ListNode[sizeTable]; // Создание нового, пустого массива с новым размером
        for (int i = 0; i < tempSizeTable; i++) // Поэлементное копирование из старого массива в новый
            if (listNodes[i] != null) // Копирование только значений в массив
                listes[i] = listNodes[i];
        listNodes = new ListNode[sizeTable]; // Обновляем старый массив
        ListNode current; // Для прохода по списку
        for (int i = 0; i < tempSizeTable; i++)
        {
            current = listes[i]; // Поэлементный перебор в массиве
            while (current != null) // Движение уже внутри каждого элемента массива по списку
            {
                insert(current.getKey(), current.getGroup_number(), current.getFullName()); // Рехеширование данных в новый массив хеш-таблицы
                current = current.next; // Движение вперед по спику
            }
        }
    }
    public ListNode getListItem(int key) // Метод, находящий элемент в хеш-таблице
    {
        int index = getIndex(key); // Получаем индекс элемента
        ListNode current = listNodes[index]; // Получаем сам элемент с заданным индексом в хеш-таблице
        if (current == null) // Если элемент с заданным индексом еще не добавлен в хеш-таблицу, возвращаем пустоту
        {
            System.out.println("Элемента с ключём " + key + " нет в хэш-таблице");
            return null;
        }
        while (current != null) // Движение по списку, если он есть в элементе массива. Если в заданном индексе массива есть коллизия - двигаемся по списку
        {
            if (current.getKey() == key) // Если мы находим заданный элемент, то возвращаем его
                return current;
            current = current.next; // Движение вперед по списку
        }
        System.out.println("Элемента с ключём " + key + " нет в хэш-таблице");
        return null; // В случае чего-то ещё возврат null
    }
    private int getIndex(int key) // Метод, генерирующий индекс в хеш-таблице на основе переданного ключа
    {
        return key % sizeTable;
    }
}