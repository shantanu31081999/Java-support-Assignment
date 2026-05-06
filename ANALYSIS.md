Task2: Diagnose: ConcurrentModificationException


ERROR 2024-04-10 09:14:33 [http-nio-8080-exec-23]
  c.s.dlp.service.StatementProcessorService - Processing failed
 
java.util.ConcurrentModificationException
  at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:911)
  at java.util.ArrayList$Itr.next(ArrayList.java:861)
  at c.s.dlp.service.StatementProcessorService
       .filterTransactions(StatementProcessorService.java:142)
  at c.s.dlp.service.StatementProcessorService
       .processStatement(StatementProcessorService.java:98)
  at c.s.dlp.controller.StatementController
       .upload(StatementController.java:67)

       
1.	What is the exact cause of ConcurrentModificationException in Java?
Ans: It occurs when a collection is modified during iteration. We can avoid it by using ConcurrentHashMap which is a example of fail safe iterator.


2.	What code pattern at line 142 most likely triggered this error?
Ans: This error was most likely caused by modifying an ArrayList while iterating over it using an for loop

Sample Code Snippet:
for (Transaction txn : transactions) {
    if (txn.isInvalid()) {
        transactions.remove(txn);
    }
}

3.	Provide the minimal code change (one or two lines) that resolves this safely.
Ans: The issue can be resolved safely by using Iterator.remove() instead of removing elements directly from the collection during iteration.
  