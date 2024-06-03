# Tickets

# Клонирование репозитория с GitHub
```
git clone https://github.com/ziplla/Tickets.git
```
# Запуск программы из JAR файла с JSON файлом
```
java -jar Tickets-0.0.1-SNAPSHOT-jar-with-dependencies.jar tickets.json
```
Если запускать через IDE, то нужно в файле TicketAnalyzer раскомментировать 18 строку и закомментировать 17

# Результат работы программы:
```
Minimum flight times by carrier:
Carrier: SU, Min Flight Time: 360 minutes
Carrier: S7, Min Flight Time: 390 minutes
Carrier: TK, Min Flight Time: 350 minutes
Carrier: BA, Min Flight Time: 485 minutes
Statistics for flights from Vladivostok to Tel Aviv:
Carrier: SU
Average Price: 14816.666666666666
Median Price: 15300.0
Difference between Average and Median: -483.33333333333394

Carrier: S7
Average Price: 15250.0
Median Price: 15250.0
Difference between Average and Median: 0.0

Carrier: TK
Average Price: 12812.5
Median Price: 13000.0
Difference between Average and Median: -187.5

Carrier: BA
Average Price: 13400.0
Median Price: 13400.0
Difference between Average and Median: 0.0

Overall Statistics for all carriers:
Average Price: 13960.0
Median Price: 13500.0
Difference between Average and Median: 460.0
```
