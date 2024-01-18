import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static HashMap<Character, Node> head = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        Trie trie = new Trie();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            String result = trie.insert(str);
            System.out.println(result);
        }
    }
}

class Node {
    HashMap<Character, Node> child = new HashMap<>();
    int isEnd = 0;
}

class Trie {
    Node root = new Node();

    String insert(String in) {
        Node node = root;
        StringBuilder result = new StringBuilder();
        boolean endFlag = false;
        for(int i = 0; i < in.length(); i++) {
            if(node.child.containsKey(in.charAt(i))) {
                node = node.child.get(in.charAt(i));
                result.append(in.charAt(i));
            }
            else {
                Node next = new Node();
                node.child.put(in.charAt(i), next);
                node = next;
                if(!endFlag) {
                    result.append(in.charAt(i));
                    endFlag = true;
                }
            }
        }
        if(node.isEnd == 0) {
            node.isEnd = 1;
            return result.toString();
        }
        else {
            node.isEnd += 1;
            return result.append(node.isEnd).toString();
        }
    }
}
