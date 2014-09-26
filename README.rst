================
RemotingPatterns
================

Aufgabenstellung
~~~~~~~~~~~~~~~~

Beschreibung der Applikation
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Wie Kompilieren?
----------------

Falls noch nicht geschehen, muessen zuerst die Abhaengigkeiten aufgeloest & heruntergeladen werden:

.. code:: bash

    ant get-deps

Anschliessend koennen entweder die tests:

.. code:: bash

    ant test

Nur kompilierung:

.. code:: bash

    ant compile

Oder gleich der ganze Ablauf durchgefuert werden (compile -> test -> package -> jar):

.. code:: bash

    ant



UML
~~~

Verwendete Remoting Patterns
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Verbesserungsvorschläge/Kritik
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

- ant test/ant laeuft nicht durch (debug output wurde hinzugefuegt):

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

  Temp. Fix: Auskommentieren von check(..) in den Zeilen 33, 45, 47 in der Datei ApplicationTest.java.

- 

Zeitaufzeichnung
~~~~~~~~~~~~~~~~

================================= ================= ========== ===== ===== =========
Task                              Who               Date       From  To    Duration
================================= ================= ========== ===== ===== =========
understanding buildfile           Jakob Klepp       2014-09-22 08:10 09:00   00:50
understanding buildfile           Andreas Willinger 2014-09-22 08:10 09:00   00:50
**TOTAL**                                                                  **01:40**
================================= ================= ========== ===== ===== =========
