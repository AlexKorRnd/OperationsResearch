package android.com.GraphTask;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import android.com.GraphTask.R;

public class ActivityResultKruskal extends ActionBarActivity {

    private EdgeWeightedGraph mWeightedGraph;
    int edges[][];
    private int quantityNodes;

    Kruskal mKruskal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        LinearLayout main = (LinearLayout) findViewById(R.id.linLayout_result);


        quantityNodes = getIntent().getIntExtra(ActivityInputQuantityNodes.TAG_QUANTITY_NODES, 0);

        mWeightedGraph = new EdgeWeightedGraph(quantityNodes);

        edges = new int[quantityNodes][];


        for (int i = 0; i <quantityNodes ; i++) {
            String tag = ActivityInputWeightEdges.TAG_EDGE_ARRAY+i;
            edges[i] = getIntent().getIntArrayExtra(tag);
        }

        for (int i = 0; i <quantityNodes ; i++) {
            for (int j = i+1; j <quantityNodes ; j++) {
                int weight = edges[i][j];
                if (weight>0){
                    WeightedEdge edge = new WeightedEdge(i, j, weight);
                    mWeightedGraph.addEdge(edge);
                }
            }
        }

        mKruskal = new Kruskal(mWeightedGraph);

        fillView(main, ActivityResultKruskal.this);
    }

    private void fillView(LinearLayout mainLayout, Context context){
        TextView textViewResult = new TextView(context);
        textViewResult.setText("Результат алгоритма Краскала");
        textViewResult.setMinWidth(MyView.TABLE_ROW_MIN_WIDTH);
        textViewResult.setTextSize(TypedValue.COMPLEX_UNIT_DIP, MyView.TEXT_SIZE);
        textViewResult.setTypeface(Typeface.SERIF, Typeface.BOLD);
        mainLayout.addView(textViewResult);

        TableLayout tableLayout = new TableLayout(context);

        MyView.fillNumberNodesToTableRow(context, tableLayout,
                mWeightedGraph.getQuantityNodes());

        MyView.viewArray(context, tableLayout, "ftr", mKruskal.getFtr());

        MyView.viewArray(context, tableLayout, "rnk", mKruskal.getRnk());

        //mainLayout.addView(tableLayout);

        TableRow row = new TableRow(context);

        TextView textViewEdges = new TextView(context);
        textViewEdges.setText("Минимальный остов");
        textViewEdges.setMinWidth(MyView.TABLE_ROW_MIN_WIDTH);
        textViewEdges.setTextSize(TypedValue.COMPLEX_UNIT_DIP, MyView.TEXT_SIZE);
        textViewEdges.setTypeface(Typeface.SERIF, Typeface.BOLD);
        //mainLayout.addView(textViewResult);
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span = quantityNodes+1;

        row.addView(textViewEdges, params);
        tableLayout.addView(row);


        MyQueue<WeightedEdge> UT = new MyQueue<>();
        UT =  mKruskal.getUT();
        while (! UT.isEmpty()){
            Edge edge = UT.pop();

            TableRow rowEdge = new TableRow(context);

            params = new TableRow.LayoutParams();
            params.span = quantityNodes+1;

            TextView edgeTextView = new TextView(context);
            Integer i = edge.either();
            Integer j = edge.other(i);
            edgeTextView.setText("(" + (i+1) + ", " + (j+1) + ")");
            edgeTextView.setTextSize(22);


            rowEdge.addView(edgeTextView);
            tableLayout.addView(rowEdge);
        }

        mainLayout.addView(tableLayout);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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
