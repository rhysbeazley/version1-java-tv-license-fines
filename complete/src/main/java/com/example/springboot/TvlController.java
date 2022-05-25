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
	public String fines(Model model) {
		model.addAttribute("finestore", TvlRepository.finestore);
		return "fines";
	}

	@GetMapping("/fine")
	public String fine(Model model, @RequestParam String id) {

		int fineId = -1;
		List<String> fine = Arrays.asList("", "", "", "", "", "", "", "", "");
		// Add a check - Full Amount £130 or £65 if paid within 28 days

		// Find the fine by it's Id number
		if (!id.equals("")) {
			fineId = Integer.parseInt(id);
			for (int x = 0; x < TvlRepository.finestore.size(); x++) {
				List<String> row = TvlRepository.finestore.get(x);
				for (int y = 0; y < row.size(); y++) {
					if (fineId == Integer.parseInt(row.get(0))) {
						fine = TvlRepository.finestore.get(x);
					}
				}
			}
		}

		model.addAttribute("fine", fine);
		return "fine";

	}

	@PostMapping("/pay")
	public String pay(Model model, @RequestParam Map<String, String> allParams) {

		// Loop over the form parameters and add them to a new ArrayList
		ArrayList<String> payment = new ArrayList<String>();
		for (String key : allParams.keySet()) {
			String paramKey = key;
			String paramValue = allParams.get(key);
			// payment.add(paramValue);
			System.out.println(paramKey + ": " + paramValue);
		}
		// Add the new ArrayList to the existing TodoRepository ArrayList
		// TvlRepository.finestore.add(newTodo);

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
