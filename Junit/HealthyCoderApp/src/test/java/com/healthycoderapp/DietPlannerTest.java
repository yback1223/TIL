package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

class DietPlannerTest {
	
	private DietPlanner dietPlanner;
	
	@BeforeEach //BeforeEach�� ���� �޼���� �׽�Ʈ �޼��� ���� ������ ����
	void setup() {
		this.dietPlanner = new DietPlanner(20, 30, 50);
	}
	
	@AfterEach //AfterEach�� ���� �޼���� �׽�Ʈ �޼��� ���� ���Ŀ� ����
	void afterEach() {
		System.out.println("A unit test was finished!");
	}
	

	@RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME) //test�� 10�� �ݺ��Ѵٴ� �ǹ��� ������̼�, value = �ݺ�Ƚ�� name = �ݺ��Ҷ����� �տ� ���� �̸�
	void test() {
		//given
		Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
		DietPlan expected = new DietPlan(2202, 110, 73, 275);
		
		//when
		DietPlan actual = dietPlanner.calculateDiet(coder);
		
		//then
		//assertEquals(expected, actual); // �ƿ� ���� ��ü���� �����ȴ�.
		assertAll(
				() -> assertEquals(expected.getCalories(), actual.getCalories()),
				() -> assertEquals(expected.getProtein(), actual.getProtein()),
				() -> assertEquals(expected.getFat(), actual.getFat()),
				() -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate())
			);
	}

}