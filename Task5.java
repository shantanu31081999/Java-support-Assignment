import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task5 {

    // FIX: Added SLF4J logger instead of using printStackTrace
    private static final Logger logger =
            LoggerFactory.getLogger(Task5.class);

    public ValidationResult validate(Document doc) {

        try {

            if (doc == null) {

                throw new RuntimeException("Document is null");

             
            }

            String content = doc.extractContent();

            if (content.isEmpty()) {

                 throw new RuntimeException("Empty content");

                
            }

            return runValidationRules(content);

        } catch (IllegalArgumentException e) {

            // e.printStackTrace();                    // issue 1

            // FIX: Log expected validation failures as warning instead of stack trace
            logger.warn("Validation failed: {}", e.getMessage());

            // return null;                            // issue 2

            // FIX: Return safe ValidationResult instead of null
            return new ValidationResult(false);

        } catch (Exception e) {

            // FIX: Log unexpected runtime exceptions with stack trace
            logger.error("Unexpected error during validation", e);

            return new ValidationResult(false);
        }
    }

    public void validateBatch(List<Document> docs) {

        for (Document doc : docs) {

            try {

                ValidationResult r = validate(doc);

                if (r != null && r.isValid()) {      // issue 3

                    // FIX: Added null check to avoid NullPointerException
                    saveResult(r);
                }

            } catch (Exception e) {

                // silent — swallowed completely    // issue 4

                // FIX: Log batch processing exceptions instead of silently swallowing
                logger.error("Error while validating batch document", e);
            }
        }
    }

    private ValidationResult runValidationRules(String content) {

        // Existing business logic
        return new ValidationResult(true);
    }

    private void saveResult(ValidationResult r) {

        // Existing business logic
    }
}