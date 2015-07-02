package android.com.GraphTask;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.com.GraphTask.R;

public class ActivityResultDFSandBFS extends ActionBarActivity {

    private static final String TAG_DEBUG_LOAD_GRAPH = "graphTask.loadGraph";
    /*private static final int TABLE_ROW_MIN_WIDTH = 75;
    private static final int TEXT_SIZE = 18;*/

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

        isEnabledDFS = getIntent().getBooleanExtra(ActivityInputAdjacentNodes.TAG_IS_ENABLED_DFS,
                false);
        isEnabledBFS = getIntent().getBooleanExtra(ActivityInputAdjacentNodes.TAG_IS_ENABLED_BFS,
                false);

        mGraph = loadGraph();

        if (isEnabledDFS){
            mDFS = new DepthFirstSearch(mGraph, startNode-1);
        }

        if (isEnabledBFS){
            mBFS = new BreadthFirstSearch(mGraph, startNode-1);
        }

        //TODO: сделать нормальное отображение результатов в альбомном режиме
        fillView(ActivityResultDFSandBFS.this);

    }

    private Graph loadGraph(){
        quantityNodes = getIntent().getIntExtra(ActivityInputQuantityNodes.TAG_QUANTITY_NODES, 0);
        startNode = getIntent().getIntExtra(ActivityInputAdjacentNodes.TAG_START_NODE, 0);


        mSerializer = new GraphJSONSerializer(ActivityResultDFSandBFS.this,
                ActivityInputAdjacentNodes.FILENAME);

        try {
            return mSerializer.loadGraph(quantityNodes);
        } catch (Exception e) {
            Log.e(TAG_DEBUG_LOAD_GRAPH, "Error loading graph: ", e);
            int messageResId = R.string.toast_fail_load_graph;
            Toast.makeText(ActivityResultDFSandBFS.this, messageResId,
                    Toast.LENGTH_SHORT).show();
        }
        return null;
    }


    private void fillView(Context context){
        // связываем главный layout с переменной
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.linear_layout_DFS_BFS);

        if (isEnabledDFS){
            // выводим заголовок ПВГ
            TextView textViewResultDFS = new TextView(context);
            textViewResultDFS.setText(R.string.text_view_result_depth_first_search);
            textViewResultDFS.setMinWidth(MyView.TABLE_ROW_MIN_WIDTH);
            textViewResultDFS.setTextSize(TypedValue.COMPLEX_UNIT_DIP, MyView.TEXT_SIZE);
            textViewResultDFS.setTypeface(Typeface.SERIF, Typeface.BOLD);
            mainLayout.addView(textViewResultDFS);

            fillTableDFS(context, mainLayout);
        }


        if(isEnabledBFS){
            // выводим заголовок ПВШ
            TextView textViewResultBFS = new TextView(context);
            textViewResultBFS.setText(R.string.text_view_result_breadth_first_search);
            textViewResultBFS.setMinWidth(MyView.TABLE_ROW_MIN_WIDTH);
            textViewResultBFS.setTextSize(TypedValue.COMPLEX_UNIT_DIP, MyView.TEXT_SIZE);
            textViewResultBFS.setTypeface(Typeface.SERIF, Typeface.BOLD);
            mainLayout.addView(textViewResultBFS);

            fillTableBFS(context, mainLayout);
        }

    }

    public void fillTableDFS(Context context, LinearLayout mainLayout){


        // таблица для вывода результатов ПВГ
        TableLayout tableLayoutDFS = new TableLayout(context);
        tableLayoutDFS.setGravity(Gravity.CENTER_HORIZONTAL);

        // выводим номера вершин
        MyView.fillNumberNodesToTableRow(context, tableLayoutDFS, mGraph.getQuantityNodes());

        MyView.viewArray(context, tableLayoutDFS, "num", mDFS.getNum());

        MyView.viewArray(context, tableLayoutDFS, "ftr", mDFS.getFtr());

        MyView.viewArray(context, tableLayoutDFS, "tn", mDFS.getTn());

        MyView.viewArray(context, tableLayoutDFS, "tk", mDFS.getTk());

        mainLayout.addView(tableLayoutDFS);
    }

    public void fillTableBFS(Context context, LinearLayout mainLayout){


        // таблица для вывода результатов ПВШ
        TableLayout tableLayoutDFS = new TableLayout(context);
        tableLayoutDFS.setGravity(Gravity.CENTER_HORIZONTAL);

        // выводим номера вершин
        MyView.fillNumberNodesToTableRow(context, tableLayoutDFS, mGraph.getQuantityNodes());

        MyView.viewArray(context, tableLayoutDFS, "num", mBFS.getNum());

        MyView.viewArray(context, tableLayoutDFS, "ftr", mBFS.getFtr());

        mainLayout.addView(tableLayoutDFS);
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
