import java.util.TreeSet;

public class VariableInterface {

	private TreeSet<classNode> ClassTree = new TreeSet<classNode>(); 
	//private TreeSet<interfaceNode> InterfaceTree = new TreeSet<interfaceNode>(); 
	
	public TreeSet<classNode> getClassTree(){
		return this.ClassTree;
	}
//	public TreeSet<interfaceNode> getInterfaceTree(){
//		return this.InterfaceTree;
//	}
//	public class interfaceNode {
//		
//	}
	
	public classNode classSearch(String className){
		classNode result = null;
		for(classNode iterator : ClassTree){
			if(iterator.getName().equals(className)){
				return iterator;
			}
		}
		
		//System.err.println("class: " + className + " 을(를) 찾을 수 없습니다.");
		
		return null;
	}
	public Field variableSearch(String className, String var){
		classNode find_class = classSearch(className);
		
		for(Field want : find_class.getVariable()){
			if(want.getName().equals(var)){
				return want;
			}
		}
		
		//System.err.println("class: " + className + "에서 변수: " + var +" 을(를) 찾을 수 없습니다.");
		
		return null;
	}
	public Method methodSearch(String class_name, String method_name){
		classNode class_element = classSearch(class_name);
		
		for(Method method : class_element.getMethod()){
			if(method.getName().equals(method_name)){
				return method;
			}
		}
		
		//System.err.println("class: " + class_name + "에서 메서드: " + method_name +" 을(를) 찾을 수 없습니다.");
		
		return null;
	}
	@Override
	public String toString(){
		for(classNode a : ClassTree){
			System.out.println("Class: " + a.getName());
			for(Field b : a.getVariable()){
				System.out.println("\tField: " + b.getName() + "(" + b.getValue() + ", " + b.isStatic() + ", " + b.getArrayLength() + ")");
			}
			for(Method c : a.getMethod()){
				System.out.println("\tMethod: " + c.getName());
			}
		}
		return "";
	}
}