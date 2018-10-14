import java.util.TreeSet;


public class classNode implements Comparable<classNode>{
		private String name;
		private TreeSet<Method> MethodTree;
		private TreeSet<Field> VariableTree;
		
		
		public classNode(String name) {
			this.name = name;
			this.MethodTree = new TreeSet<Method>();
			this.VariableTree = new TreeSet<Field>();
		}
		
		public TreeSet<Field> getVariable() {
			return this.VariableTree;
		}
		
		public TreeSet<Method> getMethod() {
			return this.MethodTree;
		}
		public String getName(){
			return this.name;
		}

		@Override
		public int compareTo(classNode o1) {
			return this.name.compareTo(o1.name);
		}
	}