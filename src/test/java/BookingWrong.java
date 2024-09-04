import lombok.Getter;
import lombok.Setter;

public class BookingWrong {
    @Setter @Getter
    private Integer firstname;
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

    public BookingWrong(Integer firstname, String lastname, int totalprice, boolean depositpaid, BookingDates bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }
}