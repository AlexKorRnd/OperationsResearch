package android.com.operationsresearch;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class InputDataSimplexMethod extends ActionBarActivity {

    final int NUM_VARS = 12;
    final int NUM_EQUALS = 2;
    final int NUM_COL = 5;
    final int TEXT_SIZE = 16;
    final int MIN_WIDTH = 24;

    EditText editTextArray[][] = new EditText[NUM_EQUALS][NUM_VARS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_simplex_method);

        createViewDinamicRows(InputDataSimplexMethod.this, NUM_VARS);



    }


    private void createViewDinamicRows(Context context, int numVars){
        // Настраиваем динамически изменяемое кол-во столбцов
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout_spec_function);

        TableRow titleRowSpecFunc = new TableRow(context);
        TextView titleSpecFunc = new TextView(context);
        titleSpecFunc.setText(R.string.text_view_input_coef_obj_func);
        titleSpecFunc.setTextSize(TEXT_SIZE * TypedValue.COMPLEX_UNIT_DIP);
        titleRowSpecFunc.addView(titleSpecFunc);
        tableLayout.addView(titleRowSpecFunc);

        TableRow rowSpecFunc = new TableRow(context);
        for (int i=0; i<NUM_VARS; i++){
            EditText editText = new EditText(context);
            editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL |
                    InputType.TYPE_NUMBER_FLAG_SIGNED);
            editText.setMinWidth(MIN_WIDTH);
            rowSpecFunc.addView(editText);

            TextView textView = new TextView(context);
            textView.setTextSize(TEXT_SIZE*TypedValue.COMPLEX_UNIT_DIP);
            String curVar ="x" + (i+1);
            if (i<NUM_VARS-1){
                textView.setText(curVar + " + ");
                rowSpecFunc.addView(textView);
            }
            else{
                textView.setText(curVar + " --> ");
                rowSpecFunc.addView(textView);

                Spinner spinner = new Spinner(context);
                // Настраиваем адаптер
                ArrayAdapter<?> adapter =
                        ArrayAdapter.createFromResource(this, R.array.type_objective_func, R.layout.spinner);
                adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

                // Вызываем адаптер
                spinner.setAdapter(adapter);

                rowSpecFunc.addView(spinner);
            }
        }
        tableLayout.addView(rowSpecFunc);

        // Растягиваем textView на все столбцы
        //TextView textView = (TextView) findViewById(R.id.titleSpecFunct_TextView);
        TableRow.LayoutParams params = (TableRow.LayoutParams)titleSpecFunc.getLayoutParams();
        params.span = NUM_VARS;
        titleSpecFunc.setLayoutParams(params);

        // Создаем заголовок для ввода системы ограничений
        TableRow rowTitleLimited = new TableRow(context);
        TextView titleLimited = new TextView(context);
        titleLimited.setText(R.string.text_view_title_limited);
        titleLimited.setTextSize(TEXT_SIZE*TypedValue.COMPLEX_UNIT_DIP);
        rowTitleLimited.addView(titleLimited);
        tableLayout.addView(rowTitleLimited);
        titleLimited.setLayoutParams(params);


        // Создаем поля для ввода ограничений
        for (int i=0; i<NUM_EQUALS; i++){
            TableRow curRowLimited = new TableRow(context);
            for(int j=0; j<NUM_VARS; j++){
                EditText editText = new EditText(context);
                editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL |
                        InputType.TYPE_NUMBER_FLAG_SIGNED);
                editText.setMinWidth(MIN_WIDTH);
                curRowLimited.addView(editText);

                TextView textView = new TextView(context);
                textView.setTextSize(TEXT_SIZE*TypedValue.COMPLEX_UNIT_DIP);
                String curVar ="x" + (j+1);
                if (j<NUM_VARS-1){
                    textView.setText(curVar + " + ");
                    curRowLimited.addView(textView);
                }
                else{
                    textView.setText(curVar);
                    curRowLimited.addView(textView);

                    Spinner spinner = new Spinner(context);
                    // Настраиваем адаптер
                    ArrayAdapter<?> adapter =
                            ArrayAdapter.createFromResource(this, R.array.type_of_equals, R.layout.spinner);
                    adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

                    // Вызываем адаптер
                    spinner.setAdapter(adapter);

                    curRowLimited.addView(spinner);

                    EditText editText2 = new EditText(context);
                    editText2.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL |
                            InputType.TYPE_NUMBER_FLAG_SIGNED);
                    editText2.setMinWidth(2*MIN_WIDTH);
                    curRowLimited.addView(editText2);
                }
            }
            tableLayout.addView(curRowLimited);
        }

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
