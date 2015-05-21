package android.com.operationsresearch.GraphTask;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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

    private Graph mGraph;
    private int quantityNodes;
    private int startNode;

    private GraphJSONSerializer mSerializer;

    private DepthFirstSearch mDepthFirstSearch;
    private BreadthFirstSearch mBreadthFirstSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dfs_and_bfs);

        loadGraph();



        fillView(ActivityDFSandBFS.this);

    }

    private void loadGraph(){
        quantityNodes = getIntent().getIntExtra(ActivityInputQuantityNodes.TAG_QUANTITY_NODES, 0);
        startNode = getIntent().getIntExtra(ActivityInputAdjacentNodes.TAG_START_NODE, 0);

        // загружаем граф
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
        mDepthFirstSearch = new DepthFirstSearch(mGraph);
        mBreadthFirstSearch = new BreadthFirstSearch(mGraph);

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.linear_layout_DFS_BFS);

        TextView textViewResultDFS = new TextView(context);
        textViewResultDFS.setText(R.string.text_view_result_depth_first_search);
        mainLayout.addView(textViewResultDFS);

        TableLayout tableDFS = new TableLayout(context);

        TableRow tableRowDFS = new TableRow(context);
        TextView textView = new TextView(context);

        int[] tmpArray = mDepthFirstSearch.getFtr();
        textView.setText("num");
        tableRowDFS.addView(textView);
        /*for (int i = 0; i <quantityNodes ; i++) {
            te
        }*/

        tableDFS.addView(tableRowDFS);
        mainLayout.addView(tableDFS);
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
