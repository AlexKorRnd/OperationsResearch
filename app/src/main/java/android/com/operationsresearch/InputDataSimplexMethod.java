package android.com.operationsresearch;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import android.text.InputType;

public class InputDataSimplexMethod extends ActionBarActivity {

    final int NUM_VARS = 12;
    final int NUM_EQ = 2;
    final int NUM_COL = 5;

    EditText editTextArray[][] = new EditText[NUM_EQ][NUM_VARS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_simplex_method);

        //setContentView(R.layout.spec_function_layout);

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.linLayout_spec_function);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        HorizontalScrollView linearLayout = (HorizontalScrollView) inflater.inflate(R.layout.spec_function_layout, null);

        //LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linLayout2_spec_function);

        EditText editText = new EditText(InputDataSimplexMethod.this);
        editText.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        linearLayout.addView(editText);

        for (int i=1; i<NUM_VARS; i++){
            TextView textView = new TextView(InputDataSimplexMethod.this);
            textView.setText("x" + i);
            linearLayout.addView(textView);

            TextView textView2 = new TextView(InputDataSimplexMethod.this);
            textView2.setText(" + ");
            linearLayout.addView(textView2);

            EditText editText2 = new EditText(InputDataSimplexMethod.this);
            editText2.setRawInputType(InputType.TYPE_CLASS_NUMBER);
            linearLayout.addView(editText2);
        }

        mainLayout.addView(linearLayout);

    }


    private LinearLayout createViewSpecFunc(Context context,LinearLayout layout, int numVars){

        EditText editText = new EditText(InputDataSimplexMethod.this);
        editText.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(editText);

        for (int i=1; i<numVars; i++){
            TextView textView = new TextView(InputDataSimplexMethod.this);
            textView.setText("x" + i);
            layout.addView(textView);

            textView.setText(" + ");
            layout.addView(textView);

            layout.addView(editText);
        }
        return layout;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input_data_simplex_method, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
