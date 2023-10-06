package SiAOD.HashTable;
public class ListNode // Класс, представляющий собой элемент односвязанного списка в хеш-таблице
{
    ListNode next; // Ссылка на следующий элемент в односвязанном списке
    private final int key; // Ключ - уникальное значение для каждого элемента
    private final int group_number; // Переменная, хранящая номер группы студента
    private final String fullName; // Переменная, хранящая ФИО студента
    public ListNode(int key, int group_number, String fullName) // Конструктор, инициализирующий поля класса
    {
        this.key = key;
        this.group_number = group_number;
        this.fullName = fullName;
    }
    public int getKey()
    {
        return key;
    }
    public int getGroup_number()
    {
        return group_number;
    }
    public String getFullName()
    {
        return fullName;
    }
}
