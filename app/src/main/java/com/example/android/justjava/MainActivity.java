package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        boolean whippedCream = getToppingValue(R.id.whipped_cream_checkbox);
        boolean chocolate = getToppingValue(R.id.chocolate_checkbox);
        int calculatedPrice = calculatePrice(whippedCream, chocolate);
        String customerName = getCustomerName(R.id.customer_name_text);
        String priceMesage = createOrderSummary(calculatedPrice, whippedCream, chocolate, customerName);
//        displayMessage(priceMesage);

        composeEmail("JustJava order for " + customerName, priceMesage);


//        Uri geoLocation = Uri.parse("geo:47.6, -122.3");
//
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(geoLocation);
//        if (intent.resolveActivity(testgetPackageManager()) != null) {
//            startActivity(intent);
//        }
    }

    public void composeEmail(String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "niclas@hotmail.no");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Gets the checked state of the whipped cream checkbox.
     *
     * @return the state of whipped cream checkbox
     * @param stringId
     */
    private boolean getToppingValue(int stringId) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(stringId);
        return whippedCreamCheckBox.isChecked();
    }

    /**
     * Gets the name of the vustomer.
     *
     * @return the name of customer
     * @param stringId
     */
    private String getCustomerName(int stringId) {
        EditText customerNameEdit = (EditText) findViewById(stringId);
        return customerNameEdit.getText().toString();
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     * @param whippedCream
     * @param chocolate
     */
    private int calculatePrice(boolean whippedCream, boolean chocolate) {
        int pricePerCup = 5;

        if (whippedCream) {
            pricePerCup += 1;
        }

        if (chocolate) {
            pricePerCup += 2;
        }

        int totalPrice = quantity * pricePerCup;
        return totalPrice;
    }

    /**
     * Creates the order summary.
     *
     * @return the
     */
    private String createOrderSummary(int price, boolean whippedCream, boolean chocolate, String customerName) {
        return "Name: " + customerName + "\n" +
                "Quantity: " + quantity + "\n"
                + "Add whipped cream? " + whippedCream + "\n"
                + "Add chocolate? " + chocolate + "\n"
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
        if (quantity<100) {
            quantity += 1;
            displayQuantity(quantity);
        } else
            showToastMessage("Your not allowed more than 100 cups of coofee a day!");

    }

    /**
     * This method is called when the + button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 0) {
            quantity -= 1;
            displayQuantity(quantity);
        } else
            showToastMessage("There is no such thing as a negative cup of coffee!");

    }


    public void showToastMessage(String message) {
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

}
