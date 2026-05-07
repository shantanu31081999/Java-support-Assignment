import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class Task3 {
		// private int processedCount = 0;
	
    // FIX: replaced int with AtomicInteger to make counter thread-safe
    private AtomicInteger processedCount = new AtomicInteger(0);

    public void process(List<StatementRecord> records) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (StatementRecord record : records) {

            executor.submit(() -> {

                processRecord(record);
                //  processedCount++;   
                // FIX: Atomic increment prevents race condition in multi-threaded environment
                processedCount.incrementAndGet();
            });
        }

        executor.shutdown();

        executor.awaitTermination(5, TimeUnit.MINUTES);
    }

    public int getProcessedCount() {
    	// return processedCount;
        // FIX: Return integer value from AtomicInteger
        return processedCount.get();
    }

    private void processRecord(StatementRecord record) {

        // Existing business logic
    }
}