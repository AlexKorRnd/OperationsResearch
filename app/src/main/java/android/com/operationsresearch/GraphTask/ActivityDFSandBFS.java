package android.com.operationsresearch.GraphTask;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.com.operationsresearch.R;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityDFSandBFS extends ActionBarActivity {

    private static final String TAG_DEBUG_LOAD_GRAPH = "graphTask.loadGraph";
    private static final int TABLE_ROW_MIN_WIDTH = 75;
    private static final int TEXT_SIZE = 18;

    private Graph mGraph;
    private int quantityNodes;
    private int startNode;

    private boolean isEnabledDFS;
    private boolean isEnabledBFS;

    private GraphJSONSerializer mSerializer;

    private DepthFirstSearch mDFS;
    private BreadthFirstSearch mBFS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dfs_and_bfs);

        loadGraph();

        //TODO: сделать нормальное отображение результатов в альбомном режиме
        fillView(ActivityDFSandBFS.this);

    }

    private void loadGraph(){
        quantityNodes = getIntent().getIntExtra(ActivityInputQuantityNodes.TAG_QUANTITY_NODES, 0);
        startNode = getIntent().getIntExtra(ActivityInputAdjacentNodes.TAG_START_NODE, 0);


        mSerializer = new GraphJSONSerializer(ActivityDFSandBFS.this,
                ActivityInputAdjacentNodes.FILENAME);

        try {
            mGraph = mSerializer.loadGraph(quantityNodes);
        } catch (Exception e) {
            Log.e(TAG_DEBUG_LOAD_GRAPH, "Error loading graph: ", e);
            int messageResId = R.string.toast_fail_load_graph;
            Toast.makeText(ActivityDFSandBFS.this, messageResId,
                    Toast.LENGTH_SHORT).show();
        }
    }


    private void fillView(Context context){
        // связываем главный layout с переменной
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.linear_layout_DFS_BFS);

        // выводим заголовок ПВГ
        TextView textViewResultDFS = new TextView(context);
        textViewResultDFS.setText(R.string.text_view_result_depth_first_search);
        textViewResultDFS.setMinWidth(TABLE_ROW_MIN_WIDTH);
        textViewResultDFS.setTextSize(TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE);
        textViewResultDFS.setTypeface(Typeface.SERIF, Typeface.BOLD);
        mainLayout.addView(textViewResultDFS);

        fillTableDFS(context,mainLayout);

        // выводим заголовок ПВШ
        TextView textViewResultBFS = new TextView(context);
        textViewResultBFS.setText(R.string.text_view_result_breadth_first_search);
        textViewResultBFS.setMinWidth(TABLE_ROW_MIN_WIDTH);
        textViewResultBFS.setTextSize(TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE);
        textViewResultBFS.setTypeface(Typeface.SERIF, Typeface.BOLD);
        mainLayout.addView(textViewResultBFS);

        fillTableBFS(context, mainLayout);

    }


    public void fillTableDFS(Context context, LinearLayout mainLayout){
        mDFS = new DepthFirstSearch(mGraph, startNode);

        // таблица для вывода результатов ПВГ
        TableLayout tableLayoutDFS = new TableLayout(context);
        tableLayoutDFS.setGravity(Gravity.CENTER_HORIZONTAL);
        //tableLayoutDFS.setStretchAllColumns(true);
        //tableLayoutDFS.setShrinkAllColumns(true);

        // выводим номера вершин
        fillNumberNodesToTableRow(context, tableLayoutDFS);

        TableRow rowNum = new TableRow(context);
        fillArrayToTableRow(context, rowNum, "Num", mDFS.getNum());
        tableLayoutDFS.addView(rowNum);

        TableRow rowFtr = new TableRow(context);
        fillArrayToTableRow(context, rowFtr, "ftr", mDFS.getFtr());
        tableLayoutDFS.addView(rowFtr);

        TableRow rowTn = new TableRow(context);
        fillArrayToTableRow(context, rowTn, "tn", mDFS.getTn());
        tableLayoutDFS.addView(rowTn);

        TableRow rowTk = new TableRow(context);
        fillArrayToTableRow(context, rowTk, "tk", mDFS.getTk());
        tableLayoutDFS.addView(rowTk);

        mainLayout.addView(tableLayoutDFS);
    }

    public void fillTableBFS(Context context, LinearLayout mainLayout){
        mBFS = new BreadthFirstSearch(mGraph, startNode);
        // таблица для вывода результатов ПВШ
        TableLayout tableLayoutDFS = new TableLayout(context);
        tableLayoutDFS.setGravity(Gravity.CENTER_HORIZONTAL);
        //tableLayoutDFS.setStretchAllColumns(true);
        //tableLayoutDFS.setShrinkAllColumns(true);

        // выводим номера вершин
        fillNumberNodesToTableRow(context, tableLayoutDFS);

        TableRow rowNum = new TableRow(context);
        fillArrayToTableRow(context, rowNum, "Num", mBFS.getNum());
        tableLayoutDFS.addView(rowNum);

        TableRow rowFtr = new TableRow(context);
        fillArrayToTableRow(context, rowFtr, "ftr", mBFS.getFtr());
        tableLayoutDFS.addView(rowFtr);

        mainLayout.addView(tableLayoutDFS);
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

        for (Integer i = 1; i <= mGraph.getQuantityNodes(); i++) {
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
        getMenuInflater().inflate(R.menu.menu_dfsand_bf, menu);
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
