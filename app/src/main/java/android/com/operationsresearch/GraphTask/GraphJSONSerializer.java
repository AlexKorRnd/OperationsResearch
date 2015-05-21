package android.com.operationsresearch.GraphTask;

import android.content.Context;
import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алексей on 19.05.2015.
 */
public class GraphJSONSerializer  {

    private Context mContext;
    private String mFilename;


    public GraphJSONSerializer(Context context, String fileName) {
        mContext = context;
        mFilename = fileName;
    }

    public void saveGraph(Graph mGraph) throws JSONException, IOException {


        // Построение массивов в JSON
        JSONArray array[] = new JSONArray[mGraph.getQuantityNodes()];
        for (int i = 0; i <mGraph.getQuantityNodes() ; i++) {
            array[i] = new JSONArray();
            for(int node: mGraph.adj(i)){
                array[i].put(node);
            }
        }

        // Запись файла на диск
        Writer writer = null;
        try {
            OutputStream out = mContext
                    .openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);

            for (int i = 0; i <mGraph.getQuantityNodes() ; i++) {
                writer.write(array[i].toString());
            }
        } finally {
            if (writer != null)
                writer.close();
        }
    }


    public Graph loadGraph(int quantityNodes) throws JSONException, IOException{
        Graph graph = new Graph(quantityNodes);
        BufferedReader reader = null;
        try{
            // Открытие и чтение файла в StringBuilder
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString())
                    .nextValue();



            // Построение массива списков смежных вершин по данным JSONObject
            for (int i = 0; i <quantityNodes ; i++) {
                // Разбор JSON с использованием JSONTokener
                for (int j = 0; j < array.length(); j++) {
                    graph.addEdge(i, array.getInt(j));
                }
            }

        } catch (FileNotFoundException e) {
            // Происходит при начале "с нуля"; не обращайте внимания
        } finally {
            if (reader != null)
                reader.close();
        }
        return graph;
    }

}
