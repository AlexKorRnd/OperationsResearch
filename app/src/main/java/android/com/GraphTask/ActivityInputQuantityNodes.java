package android.com.GraphTask;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.com.GraphTask.R;

public class ActivityInputQuantityNodes extends ActionBarActivity {

    EditText mQuantityNodesEditText;
    Button mNextButton;

    int quantity;

    public static final String TAG_QUANTITY_NODES = "android.com.android.com.GraphTask.QUANTITY_NODES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_quantity_nodes);


        mQuantityNodesEditText = (EditText) findViewById(R.id.quantityNodesEditText);



        mNextButton = (Button) findViewById(R.id.button_next);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isProblemDFS_BFS = getIntent().
                        getBooleanExtra(ActivitySelectGraphProblem.TAG_PROBLEM_DFS_BFS, false);
                boolean isProblemKruskal = getIntent().
                        getBooleanExtra(ActivitySelectGraphProblem.TAG_PROBLEM_KRUSKAL, false);

                Intent intent = null;
                if (isProblemDFS_BFS){
                    intent = new Intent(ActivityInputQuantityNodes.this,
                            ActivityInputAdjacentNodes.class);
                }
                else {
                    intent = new Intent(ActivityInputQuantityNodes.this,
                            ActivityInputWeightEdges.class);
                }

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
