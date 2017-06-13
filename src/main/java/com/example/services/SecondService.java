package com.example.services;

import org.springframework.stereotype.Service;

@Service
public class SecondService {
	
	public void methodTwo()
	{
		
		System.out.println("seconmd method");
		throw new RuntimeException();
		
	}

}
