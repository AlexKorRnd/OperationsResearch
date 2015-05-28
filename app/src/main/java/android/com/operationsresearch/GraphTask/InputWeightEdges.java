package android.com.operationsresearch.GraphTask;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.com.operationsresearch.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class InputWeightEdges extends ActionBarActivity {

    public static final String TAG_EDGE_ARRAY = "Graph_problem.EDGE_ARRAY";

    final int TEXT_SIZE = 16;
    final int MIN_WIDTH = 80;


    //private Graph mGraph;
    private EdgeWeightedGraph mWeightedGraph;
    private int quantityNodes;
    //private int startNode;

    private int edges[][];

    //private GraphJSONSerializer mSerializer;

    private EditText mEdgeEditText[][];
    private Button mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_weight_edges);

        quantityNodes = getIntent().getIntExtra(ActivityInputQuantityNodes.TAG_QUANTITY_NODES, 0);

        //LinearLayout main = (LinearLayout) findViewById(R.id.linear_layout_input_weight_edges);
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout_input_weight_edges);

        TableRow rowTitle = new TableRow(InputWeightEdges.this);
        TextView textView = new TextView(InputWeightEdges.this);

        rowTitle.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        textView.setText("Ввод взвешенного графа(если ребра нет можете оставить поле пустым)");
        textView.setTextSize(TEXT_SIZE * TypedValue.COMPLEX_UNIT_DIP);

        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span = quantityNodes;

        rowTitle.addView(textView,params);
        tableLayout.addView(rowTitle);

        fillView(tableLayout, InputWeightEdges.this);

        mWeightedGraph = new EdgeWeightedGraph(quantityNodes);
        edges = new int[quantityNodes][quantityNodes];

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <quantityNodes ; i++) {
                    for (int j = i+1; j <quantityNodes ; j++) {
                        String tmpWeight = mEdgeEditText[i][j].getText().toString();
                        if (!tmpWeight.isEmpty()){
                            int weight = Integer.parseInt(tmpWeight);
                            edges[i][j] = weight;
                            Edge edge = new Edge(i, j, weight);
                            mWeightedGraph.addEdge(edge);
                        }
                    }
                }
                Intent intent = new Intent(InputWeightEdges.this, Result.class);

                for (int i = 0; i <quantityNodes ; i++) {
                    String tag = TAG_EDGE_ARRAY+i;
                    int tmp[] = edges[i];
                    intent.putExtra(tag, tmp);
                }
                intent.putExtra(ActivityInputQuantityNodes.TAG_QUANTITY_NODES, quantityNodes);

                startActivity(intent);

            }
        });
    }

    public void fillView(TableLayout tableLayout, Context context){
        //TableLayout tableLayout = new TableLayout(context);

        mEdgeEditText = new EditText[quantityNodes][quantityNodes];

        TableRow rowIndex = new TableRow(context);
        TextView textView1 = new TextView(context);
        textView1.setText("№");
        rowIndex.addView(textView1);
        for (Integer i = 1; i <=quantityNodes; i++) {
            TextView textView = new TextView(context);
            textView.setText(i.toString());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            rowIndex.addView(textView);
        }
        tableLayout.addView(rowIndex);

        for (int i = 0; i <quantityNodes; i++) {
            TableRow tableRow = new TableRow(context);
            TextView textView = new TextView(context);
            Integer n = i+1;
            textView.setText(n.toString());
            textView.setGravity(Gravity.CENTER_VERTICAL);
            tableRow.addView(textView);
            for (int j = 0; j <quantityNodes ; j++) {
                mEdgeEditText[i][j] = new EditText(context);
                mEdgeEditText[i][j].setMinWidth(MIN_WIDTH);
                tableRow.addView(mEdgeEditText[i][j]);
            }
            tableLayout.addView(tableRow);
        }

        //main.addView(tableLayout);

        TableRow row = new TableRow(context);
        mNextButton = new Button(context);
        mNextButton.setText("Дальше");

        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span = quantityNodes;

        row.addView(mNextButton, params);
        tableLayout.addView(row);
        //main.addView(mNextButton);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input_weight_edges, menu);
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
