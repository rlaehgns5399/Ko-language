public class Field implements Comparable<Field>{
		private String name;
		private boolean isStatic;
		private int arrayLength;
		private Object value;
		
		public Field(String name){
			this.name = name;
			this.isStatic = false;
			this.arrayLength = 0;	// 0 = single var, > 0 = array
			this.value = null;
		}	
		public Field(String name, boolean isStatic, int size, Object value){
			this(name);
			this.isStatic = isStatic;
			this.arrayLength = size;
			this.value = value;
		}
		
		public String getName(){
			return this.name;
		}
		public boolean isStatic(){
			return this.isStatic;
		}
		public int getArrayLength(){
			return arrayLength;
		}
		public Object getValue(){
			return this.value;
		}
		
		@Override
		public int compareTo(Field o1) {
			return this.name.compareTo(o1.name);
		}
	}
