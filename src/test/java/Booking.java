import lombok.Getter;
import lombok.Setter;

public class Booking {
    @Setter @Getter
    private String firstname;
    @Setter @Getter
    private String lastname;
    @Setter @Getter
    private int totalprice;
    @Setter @Getter
    private boolean depositpaid;
    @Setter @Getter
    private BookingDates bookingdates;
    @Setter @Getter
    private String additionalneeds;

    public Booking(String firstname, String lastname, int totalprice, boolean depositpaid, BookingDates bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }
}
