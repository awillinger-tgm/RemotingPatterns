================
RemotingPatterns
================

Aufgabenstellung
~~~~~~~~~~~~~~~~

Das Framework für Remoting Patterns finden sie unter dem Thema "Resources"!

Gruppenarbeit: 2 Mitglieder (Server/Client)

Analysieren Sie in einer Gruppe von 2 Leuten die mitgelieferte Implementation der verteilten LeelaApplikation. Identifizieren Sie dabei alle verwendeten Elemente der "Basic Remoting Patterns" und erstellen Sie UML-Klassendiagramme für die Pakete comm, comm.socket, comm.soap, evs2009 und evs2009.mapping

Schließen Sie die unfertigen Tests ab, und dokumentieren Sie etwaige Schwierigkeiten.

Was ist zu tun?

* UML Klassendiagramm
* Erweitern der Testfälle (mind. einen Testfall erweitern)
* Kritik und Verbesserungsvorschläge

Punkte (16):

Identifikation von Basic Remoting Patterns ... 1Pkt
Beschreibung der Applikation ... 4Pkt
UML-Diagramme ... 3Pkt
Schreiben von einem neuen Testfall ... 2Pkt
konstruktive Verbesserungsvorschläge / Kritikpunkte ... 6Pkt

Beschreibung der Applikation
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Wie Kompilieren?
----------------

Falls noch nicht geschehen, müssen zuerst die Abhängigkeiten aufgeloest & heruntergeladen werden:

.. code:: bash

    ant get-deps

Anschliessend koennen entweder die tests:

.. code:: bash

    ant test

Nur kompilierung:

.. code:: bash

    ant compile

Oder gleich der ganze Ablauf durchgefürt werden (compile -> test -> package -> jar):

.. code:: bash

    ant



UML
~~~

Verwendete Remoting Patterns
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Verbesserungsvorschläge, Kritik
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Testcase schlägt fehl
---------------------

Nach dem entpacken funktioniert das Target ant test/ant nicht(debug output wurde hinzugefügt):

.. code:: bash

    [junit] 70   [main] DEBUG comm.socket.SocketPluginClient  - Got bytes838
    [junit] 70   [main] DEBUG comm.socket.SocketPluginClient  - Read is 838
    [junit] before: Fri Sep 26 09:43:06 CEST 2014
    [junit] after: Fri Sep 26 09:43:06 CEST 2014
    [junit] between: Fri Sep 26 09:43:06 CEST 2014
    [junit] ------------- ---------------- ---------------
    [junit]
    [junit] Testcase: generalTest took 0,21 sec
    [junit]     FAILED
    [junit]
    [junit] junit.framework.AssertionFailedError:
    [junit]     at evs2009.ApplicationTest.check(ApplicationTest.java:91)
    [junit]     at evs2009.ApplicationTest.generalTest(ApplicationTest.java:33)
    [junit]

Um das zu fixen, müssen die Aufrufe von check(..) in den Zeilen 33, 45, 47 in der Datei ApplicationTest.java auskommentiert werden.

Kein ordentliches Exceptionhandling
-----------------------------------

In diversen Dateien, z.B. PeerReader.java findet kein ordentliches Exceptionhandling statt.
Die Exceptions werden zwar abgefangen, der Stacktrace jedoch direkt wieder ausgegeben - keine custom exceptions, kein Logging.

Falls eine Exception auftritt, sollte diese Entweder eine eigene Exception (welche später abgefangen wird) auslösen,
oder ein Logging Tool (z.B. Log4j) verwendet werden.


Dokumentation unvollständig gelöscht
------------------------------------

Die Dokumentations-Files welche wohl hätte von uns verborgen werden sollen
waren noch über die Git-History auffindbar. Mit den folgenden Befehl wären die
Dateien tatsächlich vollständig gelöscht worden:

.. code:: bash

    git filter-branch \
    --index-filter 'git rm --cached --ignore-unmatch \
    README \
    documentation/evs028.odt \
    documentation/evs028.pdf \
    documentation/evsCore.jpg \
    documentation/pkgComm.jpg \
    documentation/pkgMapping.jpg \
    ' d0f074f4a20f6b8b68c0ee80b1646e992d8c09ac..HEAD

``d0f074f4a20f6b8b68c0ee80b1646e992d8c09ac`` ist hierbei der erste commit.

Testcase
--------

Wir haben uns entschieden, die PeerReaderTest Klasse um einen Testfall zu erweitern.
Dieser provoziert eine FileNotFoundException (welche im PeerReader nicht vollständig abgefangen wird - siehe oben).

Um die FNFE auszulösen, erzeugt der Testfall ein neues PeerReader objekt mit leerem String als Dateiname.
Anschließend versucht er auf die Endpoints "test00" zuzugreifen.

Erwartetes Ergebnis: assertEquals = true, da leere Liste
Momentanes Ergebnis: NullPointerException (Endpoint existiert nicht in der Liste, kein Check in der
PeerReader Klasse ob Element überhaupt existiert).
Wenn das ein vom ursprünglichen Ersteller erwartetes Ergebnis ist, so fehlt das in der Dokumentation.

Zeitaufzeichnung
~~~~~~~~~~~~~~~~

================================= ================= ========== ===== ===== =========
Task                              Who               Date       From  To    Duration
================================= ================= ========== ===== ===== =========
Understanding buildfile           Jakob Klepp       2014-09-26 08:10 09:00   00:50
Understanding buildfile           Andreas Willinger 2014-09-26 08:10 09:00   00:50
UML erstellt                      Jakob Klepp       2014-09-26 09:00 10:40   01:40
Kritik: Dokument löschen          Jakob Klepp       2014-09-26 10:40 10:50   00:10
Reparatur Testfall, Dokumentiert  Andreas Willinger 2014-09-26 09:00 10:20   01:20
Ausführinstruktionen, Testfall    Andreas Willinger 2014-09-26 10:20 11:10   00:50
**TOTAL**                                                                  **05:40**
================================= ================= ========== ===== ===== =========
