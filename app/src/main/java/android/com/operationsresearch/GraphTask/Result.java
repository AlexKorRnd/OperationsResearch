package android.com.operationsresearch.GraphTask;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.com.operationsresearch.R;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Result extends ActionBarActivity {

    private EdgeWeightedGraph mWeightedGraph;
    int edges[][];
    private int quantityNodes;

    private static final int TABLE_ROW_MIN_WIDTH = 75;
    private static final int TEXT_SIZE = 18;

    Kraskal kraskal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        LinearLayout main = (LinearLayout) findViewById(R.id.linLayout_result);


        quantityNodes = getIntent().getIntExtra(ActivityInputQuantityNodes.TAG_QUANTITY_NODES, 0);

        mWeightedGraph = new EdgeWeightedGraph(quantityNodes);

        edges = new int[quantityNodes][];

        for (int i = 0; i <quantityNodes ; i++) {
            String tag = InputWeightEdges.TAG_EDGE_ARRAY+i;
            edges[i] = getIntent().getIntArrayExtra(tag);
        }
        for (int i = 0; i <quantityNodes ; i++) {
            for (int j = i+1; j <quantityNodes ; j++) {
                int weight = edges[i][j];
                if (weight>0){
                    Edge edge = new Edge(i, j, weight);
                    mWeightedGraph.addEdge(edge);
                }
            }
        }

        kraskal = new Kraskal(mWeightedGraph);

        fillView(main, Result.this);
    }

    private void fillView(LinearLayout mainLayout, Context context){
        /*TextView textViewResult = new TextView(context);
        textViewResult.setText("Результат алгоритма Краскала");
        textViewResult.setMinWidth(TABLE_ROW_MIN_WIDTH);
        textViewResult.setTextSize(TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE);
        textViewResult.setTypeface(Typeface.SERIF, Typeface.BOLD);
        mainLayout.addView(textViewResult);*/

        TableLayout tableLayout = new TableLayout(context);

        fillNumberNodesToTableRow(context, tableLayout);

        TableRow rowFTR = new TableRow(context);
        int ftr[] = kraskal.getFtr();
        fillArrayToTableRow(context, rowFTR, "ftr", ftr);
        tableLayout.addView(rowFTR);

        TableRow rowRNK = new TableRow(context);
        int rnk[] = kraskal.getRnk();
        fillArrayToTableRow(context, rowRNK, "rnk", rnk);
        tableLayout.addView(rowRNK);

        //mainLayout.addView(tableLayout);


        TableRow row = new TableRow(context);

        TextView textViewEdges = new TextView(context);
        textViewEdges.setText("Минимальный остов");
        textViewEdges.setMinWidth(TABLE_ROW_MIN_WIDTH);
        textViewEdges.setTextSize(TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE);
        textViewEdges.setTypeface(Typeface.SERIF, Typeface.BOLD);
        //mainLayout.addView(textViewResult);
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span = quantityNodes+1;

        row.addView(textViewEdges, params);
        tableLayout.addView(row);


        MyQueue<Edge> UT = new MyQueue<>();
        UT =  kraskal.getUT();
        while (! UT.isEmpty()){
            Edge edge = UT.dequeue();

            TableRow rowEdge = new TableRow(context);

            params = new TableRow.LayoutParams();
            params.span = quantityNodes+1;

            TextView edgeTextView = new TextView(context);
            Integer i = edge.either();
            Integer j = edge.other(i);
            edgeTextView.setText("(" + (i+1) + ", " + (j+1) + ")");
            edgeTextView.setTextSize(22);
            //mainLayout.addView(edgeTextView);
            rowEdge.addView(edgeTextView);
            tableLayout.addView(rowEdge);
        }

        mainLayout.addView(tableLayout);
    }

    public void fillNumberNodesToTableRow(Context context, TableLayout tableLayout){
        // строка для вывода номера вершины
        TableRow tableRow = new TableRow(context);
        TextView textView = new TextView(context);
        textView.setText("№");
        textView.setMinWidth(TABLE_ROW_MIN_WIDTH);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE);
        textView.setTypeface(Typeface.SERIF, Typeface.NORMAL);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        tableRow.addView(textView);

        for (Integer i = 1; i <= mWeightedGraph.V(); i++) {
            textView = new TextView(context);
            textView.setText(i.toString());
            textView.setMinWidth(TABLE_ROW_MIN_WIDTH);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE);
            textView.setTypeface(Typeface.SERIF, Typeface.NORMAL);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            tableRow.addView(textView);
        }

        tableLayout.addView(tableRow);

    }

    // процедура для вывода массива в строку таблицу
    public void fillArrayToTableRow(Context context, TableRow tableRow, String title, int arr[]){
        // выводим в 1-ый столбец заголовок
        TextView textView = new TextView(context);
        textView.setText(title);
        textView.setMinWidth(TABLE_ROW_MIN_WIDTH);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE);
        textView.setTypeface(Typeface.SERIF, Typeface.NORMAL);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        tableRow.addView(textView);

        for (int i = 0; i <arr.length; i++) {
            textView = new TextView(context);
            Integer tmp = arr[i];
            if (title.equals("ftr")){
                tmp++;
            }
            textView.setText(tmp.toString());
            textView.setMinWidth(TABLE_ROW_MIN_WIDTH);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE);
            textView.setTypeface(Typeface.SERIF, Typeface.NORMAL);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            tableRow.addView(textView);
        }
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
