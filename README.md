# Tickets

# Шаг 1: Клонирование репозитория с GitHub
```
git clone https://github.com/ziplla/Tickets.git
```
```
cd Tickets
```


# Шаг 2: Компиляция Java и создание JAR файла
```
javac TicketAnalyzer.java Ticket.java TicketsWrapper.java
```
```
jar cvf app.jar *.class
```


# Шаг 3: Запуск программы из JAR файла с JSON файлом
```
java -jar app.jar tickets.json
```

