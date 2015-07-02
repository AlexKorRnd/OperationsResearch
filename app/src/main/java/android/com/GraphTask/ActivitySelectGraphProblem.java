package android.com.GraphTask;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.com.GraphTask.R;

public class ActivitySelectGraphProblem extends ActionBarActivity {

    final static String TAG_PROBLEM_DFS_BFS = "graph_problem_DFS_BFS";
    final static String TAG_PROBLEM_KRUSKAL = "graph_problem_Kruskal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_graph_problem);

        Button buttonDFS_BFS = (Button) findViewById(R.id.button_DFS_BFS);
        buttonDFS_BFS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitySelectGraphProblem.this,
                        ActivityInputQuantityNodes.class);
                intent.putExtra(TAG_PROBLEM_DFS_BFS, true);
                intent.putExtra(TAG_PROBLEM_KRUSKAL, false);
                startActivity(intent);
            }
        });

        Button buttonKruskal = (Button) findViewById(R.id.button_Kruskal);
        buttonKruskal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitySelectGraphProblem.this,
                        ActivityInputQuantityNodes.class);
                intent.putExtra(TAG_PROBLEM_DFS_BFS, false);
                intent.putExtra(TAG_PROBLEM_KRUSKAL, true);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_graph_problem, menu);
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
