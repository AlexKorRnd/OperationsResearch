package android.com.operationsresearch.GraphTask;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.com.operationsresearch.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityInputQuantityNodes extends ActionBarActivity {

    EditText mQuantityNodesEditText;
    Button mNextButton;

    int quantity;

    public static final String TAG_QUANTITY_NODES = "GraphTask.QUANTITY_NODES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_quantity_nodes);



        mQuantityNodesEditText = (EditText) findViewById(R.id.quantityNodesEditText);
        mQuantityNodesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //quantity = Integer.parseInt(mQuantityNodesEditText.getText().toString());
            }
        });



        //final Graph graph = new Graph(quantity);

        mNextButton = (Button) findViewById(R.id.button_next);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityInputQuantityNodes.this,
                        InputWeightEdges.class);
                if (! mQuantityNodesEditText.getText().toString().isEmpty()){
                    quantity = Integer.parseInt(mQuantityNodesEditText.getText().toString());
                    intent.putExtra(TAG_QUANTITY_NODES, quantity);
                    startActivity(intent);
                }
                else{
                    int messageResId = R.string.toast_fail_input_quantity_nodes;
                    Toast.makeText(ActivityInputQuantityNodes.this, messageResId,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_input_quantity_nodes, menu);
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
