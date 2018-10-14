import java.util.List;
import java.util.Map;
import java.util.Stack;


public interface Procedure {
	public Map getClassList();
	public void storeCodesImported(String name);
	public void initInterfaceInfo(String name);
	public int getNumOfParamsOfAbstractMethod(HelloParser.Interface_methodContext ctx);
	public String getAbstractMethodName(HelloParser.Interface_methodContext ctx);
	public void saveAbstractMethodInfo(String abstractMethodName, int numOfParams);
	public boolean check_the_existence_of_class_Interface_to_be_extended(HelloParser.ExtendContext ctx);
	public void initClassInfo();
	public List<String> getImplementationClassList(HelloParser.ImplementContext ctx);
	public boolean check_the_existence_of_Interface_to_be_implemented(List<String> interfaceList);
	public void saveStaticVariables(HelloParser.Class_static_fieldContext ctx);
	public void saveClassVariables();
	public int getNumOfParamsOfClassMethod(HelloParser.Class_methodContext ctx);
	public <T> String getCurrentClassName(T ctx);
	public <T> String getCurrentMethodName(T ctx);
	public void saveClassMethodInfo();
	public boolean isValidAssignment(HelloParser.Assignment_stmtContext ctx);
	public boolean returnTargetIsValid(HelloParser.Return_stmtContext ctx, Stack<String> stack);
	public boolean conditionIsBooleanValue(HelloParser.If_conditionContext ctx);
	public boolean isExistingMethod(String currentClassName, String currentMethodName);
	public <T> boolean isExistingClass(T ctx);
	public <T> boolean getInterfaceNameIncludingMethod(T ctx);
	public <T> boolean isInterface(T ctx);
	public <T> boolean isClass(T ctx);
	public <T> boolean isExistingVariable(T varName);
	public <T> boolean isVariableDeclWithValueAssignment(T ctx);
	public void setCurrentInterfaceName(String name);
	public String getCurrentInterfaceName();
	public void setCurrentClassName(String name);
	public String getCurrentClassName();
}