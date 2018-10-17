package com.codefans.java.classes;

import java.lang.reflect.Field;
import java.util.HashMap;

public class ObjectTransform {

	public static void main(String[] args) {
		ObjectTransform tf = new ObjectTransform();
		Company cp = new Company();
		cp.setCompanyId(100001);
		cp.setCompanyName("mycompany");
		CompanyDomain cd = new CompanyDomain();
		cd.setId(200002);
		cd.setDomain("new2010.local");
		cp.setCompanyDomain(cd);

		HashMap map = new HashMap();
		map.put("username", "11111");
		map.put("password", "22222");
		cp.setMap(map);

		MyCompany cpny = new MyCompany();
		cpny.setMyCompanyDomain(new MyCompanyDomain());
		tf.transform(cp, cpny);

		// System.out.println("companyId:" + cpny.getCompanyId());
		System.out.println("companyName:" + cpny.getCompanyName());
		System.out.println("companyDomain:" + cpny.getMyCompanyDomain().getDomain());
		System.out.println("username:" + cpny.getMap().get("username"));
		System.out.println("password:" + cpny.getMap().get("password"));
	}

	public void transform(Company company, MyCompany mc) {
		try {
			Class source = company.getClass();

			Field[] fields = source.getDeclaredFields();
			Field field = null;

			String fieldName = "";
			Object fieldValue = "";
			Class fieldClass = null;

			Class dest = mc.getClass();
			Field[] fds = dest.getDeclaredFields();
			Field fd = null;
			String fn = "";
			Object fv = "";
			Class fc = null;
			for (int i = 0; i < fields.length; i++) {
				field = fields[i];
				fieldValue = field.get(company);

				fieldClass = field.getType();
				fieldName = field.getName();

				for (int j = 0; j < fds.length; j++) {
					fd = fds[j];
					fv = fd.get(mc);

					fc = fd.getType();
					System.out.println("---------" + fd.getDeclaringClass());
					System.out.println("=========" + fc);
					if (fieldClass.equals(CompanyDomain.class) && fc.equals(MyCompanyDomain.class)) {
						transform((CompanyDomain) fieldValue, (MyCompanyDomain) fv);
					}
					fn = fd.getName();
					if (fieldName.equalsIgnoreCase(fn)) {
						fd.set(mc, fieldValue);
						break;
					}
				}
				// if(fieldName.equals("companyName")) {
				// field.set(company, "changeValue");
				// }
				// System.out.println(fieldName + ":" + fieldValue);
			}

			// System.out.println("companyName: " + company.getCompanyName());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void transform(CompanyDomain companyDomain, MyCompanyDomain myCompanyDomain) {
		try {
			Class source = companyDomain.getClass();

			Field[] fields = source.getDeclaredFields();
			Field field = null;

			String fieldName = "";
			Object fieldValue = "";

			Class dest = myCompanyDomain.getClass();
			Field[] fds = dest.getDeclaredFields();
			Field fd = null;
			String fn = "";
			Object fv = "";
			for (int i = 0; i < fields.length; i++) {
				field = fields[i];
				fieldName = field.getName();
				fieldValue = field.get(companyDomain);
				for (int j = 0; j < fds.length; j++) {
					fd = fds[j];
					fn = fd.getName();
					fv = fd.get(myCompanyDomain);
					if (fieldName.equalsIgnoreCase(fn)) {
						fd.set(myCompanyDomain, fieldValue);
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
