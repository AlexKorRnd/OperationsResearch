package android.com.GraphTask;

import android.content.Context;
import android.util.JsonReader;
import android.util.JsonWriter;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Сохранения графа в формате JSON
 */
public class GraphJSONSerializer  {

    private Context mContext;
    private String mFilename;


    public GraphJSONSerializer(Context context, String fileName) {
        mContext = context;
        mFilename = fileName;
    }

    public void saveGraph(Graph mGraph) throws JSONException, IOException {

        JsonWriter writer = null;
        try {
            OutputStream out = mContext
                    .openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));

            writer.beginObject();

            for (Integer i = 0; i <mGraph.getQuantityNodes(); i++) {
                String name = i.toString();
                writer.name(name);
                writeIntegersArray(writer, mGraph.adj[i]);

            }
            writer.endObject();
        } finally {
            if (writer != null){
                //writer.endObject();
                writer.close();
            }

        }
    }

    private void writeIntegersArray(JsonWriter writer, Iterable<Edge> nodes) throws IOException {
        writer.beginArray();
        for (Edge edge : nodes) {
            writer.value(edge.either());
        }
        writer.endArray();
    }

    public Graph loadGraph(int quantityNodes) throws JSONException, IOException{
        JsonReader reader = null;
        Graph graph = new Graph(quantityNodes);
        try {
            InputStream in = mContext.openFileInput(mFilename);
            reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

            reader.beginObject();

            for (Integer i = 0; i < graph.getQuantityNodes() ; i++) {
                String name = reader.nextName();
                if (name.equals(i.toString())){
                    addNodes(reader, graph, i);
                }
            }
            reader.endObject();

            return graph;

        } finally {
            if (reader != null) {
                //reader.endObject();
                reader.close();
            }
        }

    }


    private void addNodes(JsonReader reader, Graph graph, int indexNode) throws IOException {
        //List doubles = new ArrayList();

        reader.beginArray();
        while (reader.hasNext()) {
            int i = reader.nextInt();
            graph.addEdge(indexNode, i);
        }
        reader.endArray();
    }
}
