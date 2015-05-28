package android.com.operationsresearch.GraphTask;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;


public class ActivityInputAdjacentNodes extends ActionBarActivity {

    private static final String TAG = "GraphProblem";
    private static final String TAG_ARRAY_EDIT_TEXT = "GraphProblem_array_edit_text";
    static final String FILENAME = "graph.json";
    static final String TAG_START_NODE = "GraphProblem_StartNode";
    private static final String TAG_GRAPH = "GraphProblem_Graph";

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

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
                parseAdjNodes();
                createNewIntent();
        }
        });

    }

    private void parseAdjNodes(){
        // обрабатываем ввод смежных вершин
        for (int i=0; i<quantityNodes; i++){
            String tmp = mAdjacentNodesEditText[i].getText().toString();
            if (tmp.equals("")){
                continue;
            }
            String[] nodesArray = tmp.split("(\\D)");
            for (int j=0; j<nodesArray.length; j++){
                int node = Integer.parseInt(nodesArray[j]);
                if (node>= 1 && node<=quantityNodes) {
                    // добавляем вершину к вершине i
                    mGraph.addEdge(i, node-1);
                } else{
                    int messageResId = R.string.toast_input_incorrect_node;
                    Toast.makeText(ActivityInputAdjacentNodes.this, messageResId,
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void createNewIntent(){
        Intent intent = new Intent(ActivityInputAdjacentNodes.this,
                ActivityDFSandBFS.class);

        // передаем кол-во вершин в графе
        intent.putExtra(ActivityInputQuantityNodes.TAG_QUANTITY_NODES, quantityNodes);

        if (! mEditTextStartNode.getText().toString().isEmpty()) {
            startNode = Integer.parseInt(mEditTextStartNode.getText().toString());

            if (startNode>= 1 && startNode<=quantityNodes){
                // передаем начальную вершину поиска
                intent.putExtra(TAG_START_NODE, startNode);

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
            else{
                int messageResId = R.string.toast_input_incorrect_start_node;
                Toast.makeText(ActivityInputAdjacentNodes.this, messageResId,
                        Toast.LENGTH_SHORT).show();
            }

        } else{
            int messageResId = R.string.toast_fail_input_start_node;
            Toast.makeText(ActivityInputAdjacentNodes.this, messageResId,
                    Toast.LENGTH_SHORT).show();
        }
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
            mAdjacentNodesEditText[i].setInputType(InputType.TYPE_CLASS_PHONE);
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

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String stringEditText[] = new String[quantityNodes];
        stringEditText = savedInstanceState.getStringArray(TAG_ARRAY_EDIT_TEXT);

        for (int i = 0; i <quantityNodes ; i++) {
            mAdjacentNodesEditText[i].setText(stringEditText[i]);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        String stringEditText[] = new String[quantityNodes];
        for (int i = 0; i <quantityNodes; i++) {
            stringEditText[i] = mAdjacentNodesEditText[i].getText().toString();
        }

        savedInstanceState.putStringArray(TAG_ARRAY_EDIT_TEXT, stringEditText);
    }

    /**
     * Generate a value suitable for use in .
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    public static int generateViewId() {
        for (;;) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }


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
