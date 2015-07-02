package android.com.GraphTask;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import android.com.GraphTask.R;

public class ActivityInputWeightEdges extends ActionBarActivity {

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

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.linear_layout_input_weight_edges);

        fillView(mainLayout, ActivityInputWeightEdges.this);

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
                            mWeightedGraph.addEdge(i, j, weight);
                        }
                    }
                }
                Intent intent = new Intent(ActivityInputWeightEdges.this,
                        ActivityResultKruskal.class);

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

    private void fillView(LinearLayout mainLayout, Context context){

        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this);

        TableLayout tableLayout = new TableLayout(this);
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(ViewGroup.
                LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        tableLayout.setLayoutParams(params);
        tableLayout.setGravity(Gravity.CENTER);

        //TableLayout tableLayout = new TableLayout(context);

        mEdgeEditText = new EditText[quantityNodes][quantityNodes];

        /*TableRow rowIndex = new TableRow(context);
        TextView textView1 = new TextView(context);
        MyView.setText(textView1, "№");
        rowIndex.addView(textView1);
        for (Integer i = 1; i <=quantityNodes; i++) {
            TextView textView = new TextView(context);
            MyView.setText(textView, i.toString());
            rowIndex.addView(textView);
        }
        tableLayout.addView(rowIndex);*/

        MyView.fillNumberNodesToTableRow(this, tableLayout, quantityNodes);

        for (int i = 0; i <quantityNodes; i++) {
            TableRow tableRow = new TableRow(context);
            TextView textView = new TextView(context);
            Integer n = i+1;
            MyView.setText(textView, n.toString());
            tableRow.addView(textView);
            for (int j = 0; j <quantityNodes ; j++) {
                mEdgeEditText[i][j] = new EditText(context);
                mEdgeEditText[i][j].setMinWidth(MIN_WIDTH);
                tableRow.addView(mEdgeEditText[i][j]);
            }
            tableLayout.addView(tableRow);
        }

        horizontalScrollView.addView(tableLayout);

        mainLayout.addView(horizontalScrollView);

        //TableRow row = new TableRow(context);
        mNextButton = new Button(context);
        mNextButton.setText("Дальше");
        mainLayout.addView(mNextButton);

        //TableRow.LayoutParams params = new TableRow.LayoutParams();
        //params.span = quantityNodes;

        //row.addView(mNextButton, params);
        //tableLayout.addView(row);
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
