package dd.assistant.backend;

public class CmdSymbo {
	public static class _target{
		public static final String MODEL = "MODEL";
		public static final String TEMPLATE = "TEMPLATE";
		public static final String BRANCH = "BRANCH";
		public static final String MODULE = "MODULE";
		public static final String RELATION = "RELATION";
		public static final String SYSTEM = "SYSTEM";
		public static final String FUZZY = "FUZZY";
	}
	public static class _operate{
		public static final String ADD = "ADD";
		public static final String REMOVE = "REMOVE";
		public static final String QUERY = "QUERY";
		public static final String UPDATE = "UPDATE";
		public static final String SAVE_AS = "SAVE";
		public static final String LOAD_FILE = "LOAD";
	}
	public static class _constraint{
		public static class FILEPATH {
			public static final String KEY ="FILEPATH";
			public static final String V_MANUALLY = "APPOINT";
			public static final String V_DEFAULT = "DEFAULT";
		}
		public static class TEMPLATE_ID{
			public static final String KEY = "T_ID";
		}
		public static class BRANCH_ID{
			public static final String KEY = "B_ID";
		}
		public static class MODULE_ID{
			public static final String KEY = "M_ID";
		}
		public static class RELATION_ID{
			public static final String KEY = "R_ID";
		}
		public static class FUZZY_ID{
			public static final String KEY = "F_ID";
		}
	}
	public static class _supplement{
		public static class FILEPATH {
			public static final String KEY ="FILEPATH";
		}
		public static class TEMPLATE_ID{
			public static final String KEY = "T_ID";
		}
		public static class BRANCH_ID{
			public static final String KEY = "B_ID";
		}
		public static class MODULE_ID{
			public static final String KEY = "M_ID";
		}
		public static class RELATION_ID{
			public static final String KEY = "R_ID";
		}
		public static class NODENAME{
			public static final String KEY = "NAME";
		}
		public static class IS_SIMPLE{
			public static final String KEY = "IS_SIMPLE";
			public static final String V_NO = "NO";
			public static final String V_YES = "YES";
		}
		public static class IS_STATIC{
			public static final String KEY = "IS_STATIC";
			public static final String V_NO = "NO";
			public static final String V_YES = "YES";
		}
		public static class EXTEND{
			public static final String KEY = "EXTEND";
			public static final String V_NONE = "NONE";
		}
		public static class TYPE{
			public static final String KEY = "TYPE";
		}
		public static class TO{
			public static final String KEY = "TO";
		}
		public static class FROM{
			public static final String KEY = "FROM";
		}
		public static class BELONGS{
			public static final String KEY = "BELONGS";
		}
		public static class PORT{
			public static final String KEY = "PORT";
		}
		public static class QUERY{
			public static final String KEY="QUERY";
		}
		public static class INPUT{
			public static final String KEY="INPUT";
		}
		public static class OUTPUT{
			public static final String KEY = "OUTPUT";
		}
		public static class MAIN_BRANCH{
			public static final String KEY="MAIN_BRANCH";
		}
		public static class CONTENT{
			public static final String KEY="CONTENT";
		}
	}

	private static String supplementTidy(String key, String val) {
		return (val==null ? "NONE;" : key + "=" + val + ";");
	}
	private static String constraintTidy(String key, String val) {
		return key+ "=" + val + ",";
	}
	private static String headTidy(String target, String operate) {
		return target + "_" + operate + ":";
	}
	
	/**
   
    RELATION_ADD:		<B_ID=B_ABCD,>		R_ID=R_ABCD;FROM=B_ABCD;TO=B_ABCD;**/
	
	//命令生成
	public static class ADD{
		public static class TEMPLATE{
			public static String getCmd(String template_id,String name,String is_simple,String extend) {
				String val = CmdSymbo.headTidy(CmdSymbo._target.TEMPLATE, CmdSymbo._operate.ADD);
				
				val += "<NONE,>";
				val += CmdSymbo.supplementTidy(CmdSymbo._supplement.TEMPLATE_ID.KEY, template_id);
				val += CmdSymbo.supplementTidy(CmdSymbo._supplement.NODENAME.KEY, name);
				val += CmdSymbo.supplementTidy(CmdSymbo._supplement.IS_SIMPLE.KEY, is_simple);
				val += CmdSymbo.supplementTidy(CmdSymbo._supplement.EXTEND.KEY, extend);
				
				return val;
			}
		}
		//BRANCH_ADD:			<T_ID=T_ABCD,>      B_ID=B_ABCD;NAME=BRANCH_1;IS_SIMPLE=NO;IS_STATIC=NO;
		public static class BRANCH{
			public static String getCmd(String template_id,
					String branch_id,String name, String is_simple, String is_static) {
				String val = CmdSymbo.headTidy(CmdSymbo._target.BRANCH, CmdSymbo._operate.ADD);
				
				val += "<";
				val += CmdSymbo.constraintTidy(CmdSymbo._constraint.TEMPLATE_ID.KEY, template_id);
				val += ">";
				
				val += CmdSymbo.supplementTidy(CmdSymbo._supplement.BRANCH_ID.KEY, branch_id);
				val += CmdSymbo.supplementTidy(CmdSymbo._supplement.NODENAME.KEY, name);
				val += CmdSymbo.supplementTidy(CmdSymbo._supplement.IS_SIMPLE.KEY, is_simple);
				val += CmdSymbo.supplementTidy(CmdSymbo._supplement.IS_STATIC.KEY, is_static);
				
				return val;
			}
		}
		public static class MODULE{
			public static String getCmd(String module_id, String name, String type) {
				 //MODULE_ADD:         <NONE,>				M_ID=M_ABCD;NAME=MODULE_1;TYPE=T_ABCD;
				String val = CmdSymbo.headTidy(CmdSymbo._target.MODULE, CmdSymbo._operate.ADD);
				
				val += "<NONE,>";
				
				val += CmdSymbo.supplementTidy(CmdSymbo._supplement.MODULE_ID.KEY, module_id);
				val += CmdSymbo.supplementTidy(CmdSymbo._supplement.NODENAME.KEY, name);
				val += CmdSymbo.supplementTidy(CmdSymbo._supplement.TYPE.KEY, type);
				
				return val;
			}
		}
		public static class RELATION{
			public static String getCmd(String branch_id,
					String relation_id, String from, String to) {
				String val = CmdSymbo.headTidy(CmdSymbo._target.RELATION, CmdSymbo._operate.ADD);
				
				val += "<" + CmdSymbo.constraintTidy(CmdSymbo._constraint.BRANCH_ID.KEY, branch_id) + ">";
				
				val += CmdSymbo.supplementTidy(CmdSymbo._supplement.RELATION_ID.KEY, relation_id);
				val += CmdSymbo.supplementTidy(CmdSymbo._supplement.TO.KEY, to);
				val += CmdSymbo.supplementTidy(CmdSymbo._supplement.FROM.KEY, from);
				
				return val;
			}
		}
	}
	public static class REMOVE{
		public static class FUZZY{
			public static String getCmd(String id) {
				String str = CmdSymbo.headTidy(CmdSymbo._target.FUZZY, CmdSymbo._operate.REMOVE);
				str += "<" + CmdSymbo.constraintTidy(CmdSymbo._constraint.FUZZY_ID.KEY, id) + ">";
				str += "NONE;";
				return str;
			}
		}
	}
	public static class QUERY{
		public static class MODEL{
			public static String getCmd() {
				String str = CmdSymbo.headTidy(CmdSymbo._target.MODEL, CmdSymbo._operate.QUERY);
			
				str += "<NONE,>";
				str += CmdSymbo.supplementTidy(CmdSymbo._supplement.QUERY.KEY, CmdSymbo._supplement.MAIN_BRANCH.KEY);
			
				return str;
			}
		}
		public static class TEMPLATE{
			public static String getCmd(String template_id) {
				return CmdSymbo.headTidy(CmdSymbo._target.TEMPLATE, CmdSymbo._operate.QUERY) + "<"+
						CmdSymbo.constraintTidy(CmdSymbo._constraint.TEMPLATE_ID.KEY, template_id)+">"+
						CmdSymbo.supplementTidy(CmdSymbo._supplement.QUERY.KEY, CmdSymbo._supplement.CONTENT.KEY);
			}
		}
		public static class BRANCH{
			public static String getCmd(String branch_id) {
				return CmdSymbo.headTidy(CmdSymbo._target.BRANCH, CmdSymbo._operate.QUERY) + "<"+
						CmdSymbo.constraintTidy(CmdSymbo._constraint.BRANCH_ID.KEY, branch_id)+">"+
						CmdSymbo.supplementTidy(CmdSymbo._supplement.QUERY.KEY, CmdSymbo._supplement.CONTENT.KEY);
			}
		}
		public static class MODULE{
			public static String getCmd(String module_id) {
				return CmdSymbo.headTidy(CmdSymbo._target.MODULE, CmdSymbo._operate.QUERY) + "<"+
						CmdSymbo.constraintTidy(CmdSymbo._constraint.MODULE_ID.KEY, module_id)+">"+
						CmdSymbo.supplementTidy(CmdSymbo._supplement.QUERY.KEY, CmdSymbo._supplement.CONTENT.KEY);
			}
		}
		public static class RELATION{
			public static String getCmd(String relation_id) {
				return CmdSymbo.headTidy(CmdSymbo._target.RELATION, CmdSymbo._operate.QUERY) + "<"+
						CmdSymbo.constraintTidy(CmdSymbo._constraint.RELATION_ID.KEY, relation_id)+">"+
						CmdSymbo.supplementTidy(CmdSymbo._supplement.QUERY.KEY, CmdSymbo._supplement.CONTENT.KEY);
			}
		}
		public static class FUZZY{
			public static String getCmd(String fuzzy_id) {
				return CmdSymbo.headTidy(CmdSymbo._target.FUZZY, CmdSymbo._operate.QUERY) + "<"+
						CmdSymbo.constraintTidy(CmdSymbo._constraint.FUZZY_ID.KEY, fuzzy_id)+">"+
						CmdSymbo.supplementTidy(CmdSymbo._supplement.QUERY.KEY, CmdSymbo._supplement.CONTENT.KEY);
			}
		}
	}
	public static class UPDATE{
		
	}
	public static class SYSTEM{
		public static class SAVE_AS{
			public static String getCmd(String filepath) {
				String val = CmdSymbo.headTidy(CmdSymbo._target.SYSTEM, CmdSymbo._operate.SAVE_AS);
				val += "<";
				val += CmdSymbo.constraintTidy(CmdSymbo._constraint.FILEPATH.KEY, CmdSymbo._constraint.FILEPATH.V_MANUALLY);
				val += ">";
				
				val += CmdSymbo.supplementTidy(CmdSymbo._supplement.FILEPATH.KEY, filepath);
				
				return val;
			}
		}
		public static class LOAD_FILE{
			public static String getCmd(String appoint_default, String filepath) {
				String val= CmdSymbo.headTidy(CmdSymbo._target.SYSTEM, CmdSymbo._operate.LOAD_FILE);
				
				val += "<";
				val += CmdSymbo.constraintTidy(CmdSymbo._constraint.FILEPATH.KEY, appoint_default);
				val += ">";
				
				val += CmdSymbo.supplementTidy(CmdSymbo._supplement.FILEPATH.KEY, filepath);
				
				return val;
			}
		}
	}
	
	
	

	public static void main(String[] args) {
		System.out.println("=====系统操作========================================================");
		System.out.println(CmdSymbo.SYSTEM.SAVE_AS.getCmd("real_filepath"));
		System.out.println(CmdSymbo.SYSTEM.LOAD_FILE.getCmd(CmdSymbo._constraint.FILEPATH.V_MANUALLY, "real_filepath"));
		System.out.println(CmdSymbo.SYSTEM.LOAD_FILE.getCmd(CmdSymbo._constraint.FILEPATH.V_DEFAULT, "void"));
		
		System.out.println("\n\n=====增加命令========================================================");
		System.out.println(CmdSymbo.ADD.TEMPLATE.getCmd("T_ABCD", "T_NAME", CmdSymbo._supplement.IS_SIMPLE.V_NO,
				CmdSymbo._supplement.EXTEND.V_NONE));
		System.out.println(CmdSymbo.ADD.BRANCH.getCmd("T_ABCD", "B_ABCD", "B_NAME", 
				CmdSymbo._supplement.IS_SIMPLE.V_NO,CmdSymbo._supplement.IS_STATIC.V_NO));
		System.out.println(CmdSymbo.ADD.MODULE.getCmd("M_ABCD", "M_NAME", "T_ABCD"));
		System.out.println(CmdSymbo.ADD.RELATION.getCmd("B_ABCD", "R_ABCD", "B_ABCD", "B_ABCD"));
		
		System.out.println("\n\n=====查询命令========================================================");
		System.out.println(CmdSymbo.QUERY.TEMPLATE.getCmd("T_ABCD"));
		System.out.println(CmdSymbo.QUERY.BRANCH.getCmd("B_ABCD"));
		System.out.println(CmdSymbo.QUERY.MODULE.getCmd("M_ABCD"));
		System.out.println(CmdSymbo.QUERY.RELATION.getCmd("R_ABCD"));
		System.out.println(CmdSymbo.QUERY.MODEL.getCmd());
		System.out.println(CmdSymbo.QUERY.FUZZY.getCmd("F_ABCD"));
		
		
	}

}
