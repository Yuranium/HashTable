package SiAOD.HashTable;

public class Main
{
    public static void main(String[] args)
    {
        HashTable hashTable = new HashTable();
        System.out.println("Учащийся добавлен в хеш-таблицу (true / false): " + hashTable.insert(220252, 12_22, "Карабанов Семен Евгеньевич"));
        System.out.println("Учащийся добавлен в хеш-таблицу (true / false): " + hashTable.insert(220276, 27_22, "Кочелаев Денис Олегович"));
        System.out.println("Учащийся добавлен в хеш-таблицу (true / false): " + hashTable.insert(222072, 36_22, "Утенков Юрий Юрьевич"));
        System.out.println("Учащийся добавлен в хеш-таблицу (true / false): " + hashTable.insert(220492, 27_22, "Шихов Даниил Денисович"));
        System.out.println("Учащийся добавлен в хеш-таблицу (true / false): " + hashTable.insert(220395, 8_22, "Пюрецкий Егор Николаевич"));
        System.out.println("Учащийся добавлен в хеш-таблицу (true / false): " + hashTable.insert(220383, 9_22, "Покшиванов Дмитрий Сергеевич"));
        System.out.println("Учащийся добавлен в хеш-таблицу (true / false): " + hashTable.insert(220406, 5_22, "Аквамен Семен Олегович"));
        System.out.println("Учищийся с ключём " + 220383 + " найден, его ФИО: " + hashTable.getListItem(220383).getFullName());
        System.out.println("Удалён учащийся (true / false): " + hashTable.deleteItem(220251));
        System.out.println("Таблица до рехэширования");
        hashTable.print();
        System.out.println("Учащийся добавлен в хеш-таблицу (true / false): " + hashTable.insert(220601, 11_22, "Эримов Кемиль Расимович"));
        System.out.println("Табилца после рехэширования");
        hashTable.print();
        System.out.println("Удалён учащийся (true / false): " + hashTable.deleteItem(220406));
    }
}