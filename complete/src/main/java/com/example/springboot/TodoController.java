package com.example.springboot;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam; 

// @RestController
// https://spring.io/guides/gs/handling-form-submission/
@Controller
public class TodoController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/todo")
	public String todo(Model model, @RequestParam String id) {
		
		int todoId = -1;
		String action = "new?id=null";
		String actionButton = "New";
		List<String> datastore = Arrays.asList("", "", "");

		// Check if this is a new todo or an edit of an existing todo
		if(!id.equals("")){
			todoId = Integer.parseInt(id);
			action = "edit?id=" + todoId;
			actionButton = "Edit";
			datastore = TodoRepository.datastore.get(todoId);
		}

		model.addAttribute("action", action);
		model.addAttribute("actionButton", actionButton);
		model.addAttribute("datastore", datastore);
		return "todo";
	}

	@GetMapping("/todos")
	public String todos(Model model) {
		model.addAttribute("datastore", TodoRepository.datastore);
		return "todos";
	}

	// @RequestParam gets all the values from the form e.g. title = Milk, description = Get the milk
	@PostMapping("/new")
	public String newTodo(Model model, @RequestParam Map<String, String> allParams) {

		// Loop over the form parameters and add them to a new ArrayList
		ArrayList<String> newTodo = new ArrayList<String>(); 
		for (String key : allParams.keySet()) {
			String paramKey = key;
			String paramValue = allParams.get(key);
			newTodo.add(paramValue);
			// System.out.println(paramKey + ": " + paramValue);
		}
		// Add the new ArrayList to the existing TodoRepository ArrayList
		TodoRepository.datastore.add(newTodo);
	
		// redirect to the GetMapping Todos page so refreshing the page doesn't re-submit the form
		return "redirect:todos";
	}

	@PostMapping("/edit")
	public String editTodo(Model model, @RequestParam Map<String, String> allParams) {

		int editId = -1;

		// Loop over the form parameters and add them to a new ArrayList
		ArrayList<String> updatedTodo = new ArrayList<String>(); 
		for (String key : allParams.keySet()) {
			String paramKey = key;
			String paramValue = allParams.get(key);
			updatedTodo.add(paramValue);
			if(key.equals("id")){
				editId = Integer.parseInt(paramValue);	
			} 
		}

		// Add the new ArrayList to the existing TodoRepository ArrayList
		TodoRepository.datastore.set(editId, updatedTodo);
	
		// redirect to the GetMapping Todos page so refreshing the page doesn't re-submit the form
		return "redirect:todos";
	}

	@GetMapping("/delete")
	public String deleteTodo(Model model, @RequestParam String id) {

		int todoId = Integer.parseInt(id);

		// Remove the item from the ArrayList based on it's index number
		TodoRepository.datastore.remove(todoId);
		
		model.addAttribute("datastore", TodoRepository.datastore);
		return "todos";
	}

}
