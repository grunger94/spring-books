package books.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookStatus {

    public static final String PATH = "bookstatus";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String description;

    public String getDescription() {
        return description;
    }

    public void getDescription(String description) {
        this.description = description;
    }

}
