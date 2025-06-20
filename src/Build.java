import java.util.*;

public class Build {

  /**
   * Prints words that are reachable from the given vertex and are strictly shorter than k characters.
   * If the vertex is null or no reachable words meet the criteria, prints nothing.
   *
   * @param vertex the starting vertex
   * @param k the maximum word length (exclusive)
   */
  public static void printShortWords(Vertex<String> vertex, int k) {

    if (vertex == null) return;

    Set<Vertex<String>> visited = new HashSet<>();
    Stack<Vertex<String>> stack = new Stack<>();
    stack.push(vertex);

    while (!stack.isEmpty()) {
      Vertex<String> current = stack.pop();

      if (visited.contains(current)) continue;

      visited.add(current);

      if (current.data !=null && current.data.length()< k) {

        System.out.println(current.data);

      }//end if

      for (Vertex<String> neighbor : current.neighbors) {
        if (!visited.contains(neighbor)) {

          stack.push(neighbor);

        }//end if
      }//end for
    }//end while
}//end printShortWords


  /**
   * Returns the longest word reachable from the given vertex, including its own value.
   *
   * @param vertex the starting vertex
   * @return the longest reachable word, or an empty string if the vertex is null
   */
  public static String longestWord(Vertex<String> vertex) {
    if (vertex == null) return "";

    Set<Vertex<String>> visited = new HashSet<>();
    Stack<Vertex<String>> longestStack = new Stack<>();
    longestStack.push(vertex);

    String longest = "";

    while (!longestStack.isEmpty()) {
      Vertex<String> current = longestStack.pop();

      if (visited.contains(current)) continue;

      visited.add(current);

      if (current.data !=null && current.data.length() > longest.length()) {

        longest = current.data;

      }//end if

      for (Vertex<String> neighbor : current.neighbors) {
        if (!visited.contains(neighbor)) {

          longestStack.push(neighbor);

        }//end if
      }//end for
    }//end while

    return longest;
}//end longestWord

  /**
   * Prints the values of all vertices that are reachable from the given vertex and 
   * have themself as a neighbor.
   *
   * @param vertex the starting vertex
   * @param <T> the type of values stored in the vertices
   */
  public static <T> void printSelfLoopers(Vertex<T> vertex) {
    if (vertex == null) return;

    Set<Vertex<T>> visited = new HashSet<>();
    Stack<Vertex<T>> selfStack = new Stack<>();

    selfStack.push(vertex);

    while (!selfStack.isEmpty()) {
      Vertex<T> current = selfStack.pop();

      if (visited.contains(current)) continue;

      visited.add(current);

      if (current.neighbors.contains(current)) {

        System.out.println(current.data);

      }//end if

      for (Vertex<T> neighbor : current.neighbors) {
        if (!visited.contains(neighbor)) {

          selfStack.push(neighbor);

        }//end if
      }//end for
    }//end while
}//end printSelfLoopers

  /**
   * Determines whether it is possible to reach the destination airport through a series of flights
   * starting from the given airport. If the start and destination airports are the same, returns true.
   *
   * @param start the starting airport
   * @param destination the destination airport
   * @return true if the destination is reachable from the start, false otherwise
   */
  public static boolean canReach(Airport start, Airport destination) {

    boolean flag = false;

    if (start == null || destination == null) return flag;

    if (start == destination) {

      flag = true;
      return flag;

    }//end if

    Set<Airport> visited = new HashSet<>();
    Queue<Airport> reachedQueue = new LinkedList<>();

    reachedQueue.add(start);

    while (!reachedQueue.isEmpty()) {
      Airport current = reachedQueue.poll();

      if (visited.contains(current)) continue;

      visited.add(current);

      if (current == destination) {

        flag = true;
        return flag;

      }//end if

      for (Airport next : current.getOutboundFlights()) {
        if (!visited.contains(next)) {

          reachedQueue.add(next);

        }//end if
      }//end for
    }//end while

    return flag;

  }//end canReach

  /**
   * Returns the set of all values in the graph that cannot be reached from the given starting value.
   * The graph is represented as a map where each vertex is associated with a list of its neighboring values.
   *
   * @param graph the graph represented as a map of vertices to neighbors
   * @param starting the starting value
   * @param <T> the type of values stored in the graph
   * @return a set of values that cannot be reached from the starting value
   */
  public static <T> Set<T> unreachable(Map<T, List<T>> graph, T starting) {

  Set<T> visited = new HashSet<>();
  Stack<T> unreachableStack = new Stack<>();

  unreachableStack.push(starting);

  while (!unreachableStack.isEmpty()) {
    T current = unreachableStack.pop();
    if (visited.contains(current)) continue;
    visited.add(current);

    List<T> neighbors = graph.get(current);

    if (neighbors != null) {
      for (T neighbor : neighbors) {
        if (!visited.contains(neighbor)) {

          unreachableStack.push(neighbor);

        }//end if visited doesn't contain neighbor
      }//end for
    }//end if not null
  }//end while

  Set<T> unreachableSet = new HashSet<>(graph.keySet());
  unreachableSet.removeAll(visited);
  return unreachableSet;

  }//end unreachable




}//end file
