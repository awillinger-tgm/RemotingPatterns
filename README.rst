================
RemotingPatterns
================

Aufgabenstellung
----------------

Beschreibung der Applikation
----------------------------

UML
---

Verwendete Remoting Patterns
----------------------------

Verbesserungsvorschläge/Kritik
------------------------------

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

  Fix: Auskommentieren von check(..) in den Zeilen 33, 45, 47 in der Datei ApplicationTest.java.

Zeitaufzeichnung
----------------
