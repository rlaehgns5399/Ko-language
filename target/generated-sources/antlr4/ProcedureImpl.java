import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class ProcedureImpl implements Procedure{
	Map<String, interfaceNode> interfaceList = new HashMap<>();
	Map<String, classNode> classList = new HashMap<>();
	String currentInterface, currentClass; 
	class interfaceNode {
		private Map<String, methodNode> abstractMethods;
		
		public interfaceNode() {
			this.abstractMethods = new HashMap<>();
		}
		
		public Map<String, methodNode> getAbstractMethods() {
			return this.abstractMethods;
		}
	}
	
	class classNode {
		private Map<String, String> classVariables;
		private Map<String, String> classStaticVariables;
		private Map<String, methodNode> classMethods;
		
		public classNode() {
			this.classMethods = new HashMap<>();
		}
		public Map<String, String> getClassStaticVariables(){
			return this.classStaticVariables;
		}
		public Map<String, String> getClassVariables() {
			return this.classVariables;
		}
		
		public Map<String, methodNode> getClassMethods() {
			return this.classMethods;
		}
	}
	
	class methodNode {
		private String methodName;
		private int numOfParam;
		
		public methodNode(String methodName, int numOfParam) {
			this.methodName = methodName;
			this.numOfParam = numOfParam;
		}
		
		public String getMethodName() {
			return this.methodName;
		}
		
		public int getNumOfParam() {
			return this.numOfParam;
		}
	}
	public Map<String, classNode> getClassList(){
		return this.classList;
	}
	@Override
	public void storeCodesImported(String ctx) {
		ctx = "/" + ctx.replace(".", "/");
		try{
			HelloLexer lexer = new HelloLexer( new ANTLRFileStream(ctx + ".txt"));
			CommonTokenStream tokens = new CommonTokenStream( lexer );
			HelloParser parser = new HelloParser( tokens );
			ParseTree tree = parser.program();
			KoLangVisitor a = new KoLangVisitor();
			a.visit(tree);
			
			Map<String, classNode> import_map = a.procedure.getClassList();
			for(String import_map_className : import_map.keySet()){
				if(this.classList.get(import_map_className) != null){
					// throw new ImportExceptionDuplicateClassName();
				} else {
					this.classList.put(import_map_className, import_map.get(import_map_className));
				}
			}
			
		} catch (IOException e){
			// throw new ImportExceptionFileNotFound();
		}
		
	}

	@Override
	public void initInterfaceInfo(String interfaceName) {
		interfaceNode node = new interfaceNode();
		this.interfaceList.put(interfaceName, node);
		this.setCurrentInterfaceName(interfaceName);
	}

	@Override
	public int getNumOfParamsOfAbstractMethod(HelloParser.Interface_methodContext ctx) {
		if(ctx.params() == null) return 0;
		return ctx.params().param().size();
	}

	@Override
	public String getAbstractMethodName(HelloParser.Interface_methodContext ctx) {
		return ctx.ident().getText();
	}

	@Override
	public void saveAbstractMethodInfo(String abstractMethodName, int numOfParams) {
		interfaceNode list = interfaceList.get(this.currentInterface);
		methodNode method = new methodNode(abstractMethodName, numOfParams);
		if(list.getAbstractMethods().get(abstractMethodName) != null){
			// throw new DuplicatedAbstractMethod();
		} else {
			list.getAbstractMethods().put(abstractMethodName, method);
		}
	}

	@Override
	public boolean check_the_existence_of_class_Interface_to_be_extended(HelloParser.ExtendContext ctx) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void initClassInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getImplementationClassList(HelloParser.ImplementContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean check_the_existence_of_Interface_to_be_implemented(List<String> interfaceList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void saveStaticVariables(HelloParser.Class_static_fieldContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveClassVariables() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNumOfParamsOfClassMethod(HelloParser.Class_methodContext ctx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void saveClassMethodInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValidAssignment(HelloParser.Assignment_stmtContext ctx) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean returnTargetIsValid(HelloParser.Return_stmtContext ctx, Stack<String> stack) {
		String expression = ctx.expression().getText();
		String expr_split[] = expression.split(".");
		
		if(expr_split.length == 1){	// like 변수a
			for(String localVariable : stack){
				// Search localVariableStack first
				if(localVariable.equals(expression)){
					return true;
				}
			}
			
			classNode currentClass = classList.get(getCurrentClassName());
			
			for(String classStaticField : currentClass.getClassStaticVariables().keySet()){
				if(classStaticField.equals(expression)) return true;
			}
			for(String classField : currentClass.getClassVariables().keySet()){
				if(classField.equals(expression)) return true;
			}
			
		} else {
			/*	like a.b.c.d.e;
			 *  but i implement only a.b;
			 */
			classNode referenceClass = classList.get(expr_split[0]);
			for(String classStaticField : referenceClass.getClassStaticVariables().keySet()){
				if(classStaticField.equals(expr_split[expr_split.length-1])) return true;
			}
			for(String classField : referenceClass.getClassVariables().keySet()){
				if(classField.equals(expr_split[expr_split.length-1])) return true;
			}
			
		}
		
		return false;
	}

	@Override
	public boolean conditionIsBooleanValue(HelloParser.If_conditionContext ctx) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isExistingMethod(String currentClassName, String currentMethodName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> String getCurrentMethodName(T ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> String getCurrentClassName(T ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> boolean isExistingClass(T ctx) {
		String className = ctx.toString();
		for(String name : classList.keySet()){
			if(name.equals(className)) return true;
		}
		return false;
	}

	@Override
	public <T> boolean getInterfaceNameIncludingMethod(T ctx) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> boolean isInterface(T ctx) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> boolean isClass(T ctx) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> boolean isExistingVariable(T varName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> boolean isVariableDeclWithValueAssignment(T ctx) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(String name : classList.keySet()){
			sb.append("=====Class List=====\n");
			sb.append(name + "\n");
			classNode classnode = classList.get(name);
			for(String methodName : classnode.classMethods.keySet()){
				sb.append("\tMethod: " + methodName + "\n");
			}
			for(String varName : classnode.classVariables.keySet()){
				sb.append("\tField" + varName + "\n");
			}
			for(String staticVarName : classnode.classStaticVariables.keySet()){
				sb.append("\tStatic Field" + staticVarName + "\n");
			}
		}
		for(String name : interfaceList.keySet()){
			sb.append("=====Interface List=====\n");
			sb.append(name + "\n");
			
			for(String abstractName : interfaceList.get(name).abstractMethods.keySet()){
				sb.append("\tMethod: " + abstractName + "\n");
			}
		}
		return sb.toString();
	}
	@Override
	public void setCurrentInterfaceName(String name) {
		// TODO Auto-generated method stub
		this.currentInterface = name;
	}
	@Override
	public String getCurrentInterfaceName() {
		// TODO Auto-generated method stub
		return this.currentInterface;
	}
	@Override
	public void setCurrentClassName(String name) {
		// TODO Auto-generated method stub
		this.currentClass = name;
	}
	@Override
	public String getCurrentClassName() {
		// TODO Auto-generated method stub
		return this.currentClass;
	}
}