package books.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookUnavailabilityCause {

    static final String ID_COLUMN = "unavailability_cause_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String cause;

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

}
