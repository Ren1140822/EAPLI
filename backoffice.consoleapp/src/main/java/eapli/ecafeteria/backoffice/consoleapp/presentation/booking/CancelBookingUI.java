/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.booking;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class CancelBookingUI extends AbstractUI {

    private final CancelBookingController theController = new CancelBookingController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {

        final Iterable<Booking> bookingsDone = this.theController.listBookings();
        if (!bookingsDone.iterator().hasNext()) {
            System.out.println("There are no bookings done.");
        } else {
            final SelectWidget<Booking> selector = new SelectWidget<>(listBookings, new BookingPrinter());
            selector.show();
            final Booking updtBooking = selector.selectedElement();
            try {
                this.theController.changeBookingState(updtBooking);
            } catch (DataConcurrencyException ex) {
                System.out.println("It is not possible to change the booking state because it was changed by another user.");
            } catch (DataIntegrityViolationException ex) {
                //should not happen!
                Logger.getLogger(CancelBookingUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Cancel Booking";
    }

}
