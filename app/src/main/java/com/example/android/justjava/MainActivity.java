package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        int calculatedPrice = calculatePrice();
        String priceMesage = createOrderSummary(calculatedPrice);
        displayMessage(priceMesage);
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice() {
        int pricePerCup = 5;
        int totalPrice = quantity * pricePerCup;
        return totalPrice;
    }

    /**
     * Creates the order summary.
     *
     * @return the
     */
    private String createOrderSummary(int price) {
        return "Name: Niclas\nQuantity: " + quantity + "\n"
                + "Total: $" + (price) + "\nThank you!";
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        quantity += 1;
        displayQuantity(quantity);
        // displayPrice(numberOfCoffees * price);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void decrement(View view) {
        quantity -= 1;
        displayQuantity(quantity);
        // displayPrice(numberOfCoffees * price);
    }

}
