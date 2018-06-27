/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */


    public void submitOrder(View view) {
        EditText nameEditText=(EditText)findViewById(R.id.name_edit_text);
        String name=nameEditText.getText().toString();
        CheckBox whippedCreamCheckbox=(CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream=whippedCreamCheckbox.isChecked();
        CheckBox chocolateCheckbox=(CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate=chocolateCheckbox.isChecked();
        int price=calculatePrice(quantity,hasWhippedCream,hasChocolate);
         String priceMessage=createOrderSummary(name,price,hasWhippedCream,hasChocolate);

         /** intent */
        if(name.equals(null)||name.equals("")){}
        else{
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order from "+name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }}


        // displayMessage(priceMessage);
    }

    public void increment(View view){
        quantity=quantity+1;
        display(quantity);
    }

    public void decrement(View view){
        if(quantity!=1) {
            quantity = quantity - 1;
            display(quantity);
        }
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
     */
    private int calculatePrice(int quantity,boolean hasWhippedCream,boolean hasChocolate){
        int basePrice=40;
        if(hasWhippedCream==true){
            basePrice+=5;
        }
        if(hasChocolate==true){
            basePrice+=10;
        }
        return quantity*basePrice;
    }

    private String createOrderSummary(String name,int price,boolean hasWhippedCream,boolean hasChocolate){
        String message="Name: "+name;
        message+="\nAdd whipped cream? "+hasWhippedCream;
        message+="\nAdd chocolate? "+hasChocolate;
        message+="\nQuantity: "+quantity;
        message+="\nTotal: "+price;
        message+="\nThank you!";
        return message;
    }

    /**
     * This method displays the given text on the screen.

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
     */
}