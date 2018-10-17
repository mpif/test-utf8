package org.apache.commons.lang.builder;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MainTest {

	private String str;

	class User {
		int age;
		String name;

		public User(String name, int age) {
			this.name = name;
			this.age = age;

		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		public String toString() {
			return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("name", this.name).toString();
		}
	}

	public User getUser(String name, int age) {
		return new User(name, age);
	}

	public static void main(String args[]) {

		MainTest format = new MainTest();
		User user = format.getUser("jerry", 30);

		System.out.println("toString: " + user);

		System.out.println("-----ToStringStyle.MULTI_LINE_STYLE-----");
		System.out.println("不指定append:" + new ToStringBuilder(user, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println("指定append:" + new ToStringBuilder(user, ToStringStyle.MULTI_LINE_STYLE)
				.append("name", user.getName()).append("age", user.getAge()));

		// System.out.println("-----ToStringStyle.DEFAULT_STYLE-----");
		// System.out.println("不指定append:"
		// + new ToStringBuilder(user, ToStringStyle.DEFAULT_STYLE));
		// System.out.println("指定append:"
		// + new ToStringBuilder(user, ToStringStyle.DEFAULT_STYLE)
		// .append("name", user.getName())
		// .append("age", user.getAge())
		// );
		//
		// System.out.println("-----ToStringStyle.NO_FIELD_NAMES_STYLE-----");
		// System.out.println("不指定append:"
		// + new ToStringBuilder(user, ToStringStyle.NO_FIELD_NAMES_STYLE));
		// System.out.println("指定append:"
		// + new ToStringBuilder(user, ToStringStyle.NO_FIELD_NAMES_STYLE)
		// .append("name", user.getName())
		// .append("age", user.getAge())
		// );
		//
		// format.setStr("hello ");
		// System.out.println(new ToStringBuilder( format ,
		// ToStringStyle.SIMPLE_STYLE ).append( "str" , "fffff" ).toString());

		// List list = new ArrayList();
		// List list2 = null;
		// list.addAll(list2); //if list2 is null, throw nullpointexceptioin

		Integer inte = new Integer(11);
		Integer inte2 = new Integer(11);
		System.out.println("inte.equals(inte2): " + inte.equals(inte2));
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
}
