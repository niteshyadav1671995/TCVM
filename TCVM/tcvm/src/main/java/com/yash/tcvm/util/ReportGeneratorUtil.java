package com.yash.tcvm.util;

import java.io.FileNotFoundException;
import java.util.List;

import com.yash.tcvm.model.Container;

public class ReportGeneratorUtil {

	public static void generateContainerStatusReport(List<Container> containers){
		int rowsAffected = 0;
		for (Container container : containers) {
			System.out.println(
					"Ingredient : " + container.getIngredient() + " , Max capacity: " + container.getMaxCapacity()
							+ " , Current availability: " + container.getCurrentAvailability() + "");
		}
		rowsAffected = containers.size();
		System.out.println("Container ReFilled Successfully");
	}
}
