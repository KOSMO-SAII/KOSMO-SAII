package com.example.test;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
//		Person a = new Person("이름","성별","번호");
//		Person b = new Person();
	}


}
//
//@Getter
//@Setter
//public class Person() {
//
//	String name;
//	String gender;
//	String number;
//
//	public Person() {  //기본생성자		@NoArgsConstructor
//		this.name = "선미";
//		this.gender = "여성";
//		this.number = "1";
//	}
//
//	public Person(String name, String gender,String number) { //@AllArgsConstructor
//		this.name = name;
//		this.gender = gender;
//		this.number = number;
//	}
//
//	public Person(String name){     //@RequiredArgsConstructor
//		this.name = name;
//		this.gender = "남성";
//		this.number = "0";
//	}
//}