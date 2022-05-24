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
public class TvlController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/fines")
	public String fines(Model model, TvlUser user) {

		model.addAttribute("user", user);
		model.addAttribute("users", TvlRepository.userstore);
		model.addAttribute("finestore", TvlRepository.finestore);
		return "fines";
	}

	@GetMapping("/fines")
	public String todos(Model model) {
		model.addAttribute("finestore", TvlRepository.finestore);
		return "fines";
	}

	@GetMapping("/fine")
	public String todo(Model model, @RequestParam String id) {

		int fineId = -1;
		List<String> fine = Arrays.asList("", "", "", "", "", "", "");

		if (!id.equals("")) {
			fineId = Integer.parseInt(id);
			fine = TvlRepository.finestore.get(fineId);
		}

		model.addAttribute("fine", fine);
		return "fine";
	}

	// @RequestParam gets all the values from the form e.g. title = Milk,
	// description = Get the milk
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
		TvlRepository.finestore.add(newTodo);

		// redirect to the GetMapping Todos page so refreshing the page doesn't
		// re-submit the form
		return "redirect:fines";
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
			if (key.equals("id")) {
				editId = Integer.parseInt(paramValue);
			}
		}

		// Add the new ArrayList to the existing TodoRepository ArrayList
		TvlRepository.finestore.set(editId, updatedTodo);

		// redirect to the GetMapping Todos page so refreshing the page doesn't
		// re-submit the form
		return "redirect:fines";
	}

	@GetMapping("/delete")
	public String deleteTodo(Model model, @RequestParam String id) {

		int todoId = Integer.parseInt(id);

		// Remove the item from the ArrayList based on it's index number
		TvlRepository.finestore.remove(todoId);

		model.addAttribute("finestore", TvlRepository.finestore);
		return "fines";
	}

}
