import java.util.TreeSet;

public class Method implements Comparable<Method>{
		private String name;
		private TreeSet<Field> localVariable;
		private int parameterNum = 0;
		public Method(String name){
			this.name = name;
			this.localVariable = new TreeSet<Field>();
		}
		
		public TreeSet<Field> getLocalVariable(){
			return this.localVariable;
		}
		public String getName(){
			return this.name;
		}
		public int getParamterNum(){
			return this.parameterNum;
		}
		public void setParameterNum(int a){
			this.parameterNum = a;
		}
		@Override
		public int compareTo(Method o1) {
			return this.name.compareTo(o1.name);
		}
	}