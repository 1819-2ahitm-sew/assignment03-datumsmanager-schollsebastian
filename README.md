# Übung Datumsverwaltung

## Beurteilung

4/4 Punkte (Feedback im Sourcecode)

## Lehrziel

- Modellieren einer einzelnen Java Klasse
- Konstruktor
- Member-Variablen

## Aufgabenstellung

Der Benutzer gibt wiederholt ein Datum in der Form `<Tag>.<Monat>.<Jahr>` ein. Das Programm zeigt daraufhin immer das jüngste / älteste aller bisher eingegebenen Datumswerte an.
Dieser Vorgang kann abgebrochen werden, indem der Benutzer das Wort "`quit`" (beenden) eingibt.


## Hinweis

Erstelle einen abstrakten Datentyp für ein Datum.
Für den Variablenzugriff soll es keine Setter-Methoden geben. Die Werte der Member-Variablen werden ausschließlich über einen Konstruktor zugewiesen, welcher die Gültigkeit des Datums überprüft.

Ein Datum soll folgende Funktionalitäten besitzen:

- __Initialisierung__ mit einem Konstruktor, welcher als Parameter eine Zeichenkette der Form `<Tag>.<Monat>.<Jahr>` übernimmt. Stellt sich das Datum als ungültig heraus, so werden keine Wertzuweisungen durchgeführt.

- __Ermittlung des Wochentages__

  Das kann leicht über folgende Formel ermittelt werden:

  ~~~java
  int weekday = ((d + (int)(2.6 * ((m + 9) % 12 + 1) - 0.2)
                + y % 100 + (int)(y % 100 / 4) + (int)(y / 400)
                - 2 * (int)(y / 100) - 1) % 7 + 7) % 7 + 1;
  ~~~
  Ist der Monat ein Jänner oder Februar, so muss beim Jahr 1 subtrahiert werden.

  Das Ergebnis ist eine ganze Zahl mit folgender Bedeutung:

  0 ... Montag, 1 ... Dienstag, usw.

  Quelle: [Wikipedia](https://de.wikipedia.org/wiki/Wochentagsberechnung#Programmierung)
  


- __Vergleich mit einem anderen Datum__

- __Ermitteln einer Datumsdarstellung als Zeichenkette__
  `<Wochentag>, <Tag>.<Monatsname> <Jahr>`
  
- __Unit-Tests__ sind auskommentiert und können zur Funktionsprüfung verwendet werden.
  
## Ablaufbeispiel

```
Date-Manager
============

1. Datum: 1.10.2018
Jüngstes Datum: Montag, 1. Oktober 2018
2. Datum: 30.9.2018
Jüngstes Datum: Montag, 1. Oktober 2018
3. Datum: 2.10.2018
Jüngstes Datum: Dienstag, 2. Oktober 2018
4. Datum: quit
```

## Erweiterung 
Verwenden Sie für die Wochentage eine Enumeration `Weekday`