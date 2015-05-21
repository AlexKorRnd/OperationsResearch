package android.com.operationsresearch.GraphTask;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.com.operationsresearch.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;


public class ActivityInputAdjacentNodes extends ActionBarActivity {

    private static final String TAG = "GraphProblem";
    static final String FILENAME = "graph.json";
    static final String TAG_START_NODE = "GraphProblem_StartNode";
    private static final String TAG_GRAPH = "GraphProblem_Graph";

    private GraphJSONSerializer mSerializer;

    private Graph mGraph;
    private int quantityNodes;
    private int startNode;



    private EditText mAdjacentNodesEditText[];
    private Button mNextButton;
    EditText mEditTextStartNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_adjacent_nodes);

        quantityNodes = getIntent().getIntExtra(ActivityInputQuantityNodes.TAG_QUANTITY_NODES, 0);

        createView(ActivityInputAdjacentNodes.this, quantityNodes);

        // создание графа с numNodes вершинами
        mGraph = new Graph(quantityNodes);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i<quantityNodes; i++){
                    String tmp = mAdjacentNodesEditText[i].getText().toString();
                    if (tmp.equals("")){
                        continue;
                    }
                    String[] nodesArray = tmp.split("(\\D)");
                    for (int j=0; j<nodesArray.length; j++){
                        // добавляем вершину к вершине i
                        mGraph.addEdge(i, Integer.parseInt(nodesArray[j])-1);
                    }
                }


                Intent intent = new Intent(ActivityInputAdjacentNodes.this,
                        ActivityDFSandBFS.class);
                intent.putExtra(ActivityInputQuantityNodes.TAG_QUANTITY_NODES, quantityNodes);
                intent.putExtra(TAG_START_NODE,
                        Integer.parseInt(mEditTextStartNode.getText().toString()));

                // сохраняем граф
                mSerializer = new GraphJSONSerializer(ActivityInputAdjacentNodes.this,
                        FILENAME);
                try {
                    mSerializer.saveGraph(mGraph);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
            }
        });

    }

    private void createView(Context context, int numNodes){
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.linLayout_adjacent_nodes);
        //mainLayout.setOrientation(LinearLayout.VERTICAL);

        mAdjacentNodesEditText = new EditText[numNodes];

        String text = "Введите смежные вершины для ";
        for (int i=0; i<numNodes; i++){
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            TextView textView = new TextView(context);
            textView.setText(text + (i + 1) + "-й вершины");
            linearLayout.addView(textView);

            mAdjacentNodesEditText[i] = new EditText(context);
            linearLayout.addView(mAdjacentNodesEditText[i]);

            mainLayout.addView(linearLayout);
        }

        TextView mTextView = new TextView(context);
        mTextView.setText(R.string.text_view_input_start_node);
        mainLayout.addView(mTextView);

        mEditTextStartNode = new EditText(context);
        mainLayout.addView(mEditTextStartNode);

        mNextButton = new Button(context);
        mNextButton.setText(R.string.button_next);
        mNextButton.setGravity(Gravity.CENTER_HORIZONTAL);
        mainLayout.addView(mNextButton);
    }


    /*private int[] StringToIntArray(String string){

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_input_adjacent_nodes, menu);
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
