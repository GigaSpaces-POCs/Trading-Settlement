package com.gigaspaces.util;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;

@SuppressWarnings("rawtypes")
public class GenericComparator implements Comparator {
    String methodName;

    public GenericComparator(String methodName) {
	this.methodName = methodName;
    }

    @SuppressWarnings("unchecked")
	public int compare(Object o1, Object o2) {
	Comparable c1 = null;
	Comparable c2 = null;

	try {
	    o1 = PropertyUtils.getProperty(o1, methodName);
	    o2 = PropertyUtils.getProperty(o2, methodName);

	    c1 = (Comparable) o1;
	    c2 = (Comparable) o2;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	int result = 0;
	if (c1 == null) {
	    result = -1;
	} else if (c2 == null) {
	    result = 1;
	} else if (o1 instanceof String && o2 instanceof String) {
	    result = ((String) c1).compareToIgnoreCase((String) c2);
	} else if (o1 instanceof BigDecimal && o2 instanceof BigDecimal) {
	    result = c1.compareTo(c2);
	} else if (o1 instanceof String && o2 instanceof BigDecimal) {
	    result = c1.compareTo(((BigDecimal) c2).toString());
	} else if (o1 instanceof BigDecimal && o2 instanceof String) {
	    result = (((BigDecimal) c1).toString()).compareTo((String) c2);
	} else if (o1 instanceof Date && o2 instanceof Date) {
	    result = c1.compareTo(c2);
	} else if (o1 instanceof Boolean && o2 instanceof Boolean) {
	    // Added for QC - 9927
	    result = c1.compareTo(c2);
	} else if (o1 instanceof Integer && o2 instanceof Integer) {
	    result = c1.compareTo(c2);
	} else if (o1 instanceof Double && o2 instanceof Double) {
	    result = c1.compareTo(c2);
	}
	else if (o1 instanceof Long && o2 instanceof Long) {
		 // Added for QC - 11049
	    result = c1.compareTo(c2);
	}


	return result;
    }
}