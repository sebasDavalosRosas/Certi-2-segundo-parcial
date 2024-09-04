import lombok.Getter;
import lombok.Setter;

public class BookingDates {
    @Setter @Getter
    private String checkin;
    @Setter @Getter
    private String checkout;

    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }
}

