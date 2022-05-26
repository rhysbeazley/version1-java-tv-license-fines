package com.example.springboot;

import java.util.*;
import java.lang.reflect.Array;

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
	public String fines(Model model, TvlUser user, @RequestParam String email) {
		TvlRepository.getByEmail(email);
		model.addAttribute("userfines", TvlRepository.userfines);
		return "fines";
	}

	@GetMapping("/fines")
	public String fines(Model model) {
		model.addAttribute("userfines", TvlRepository.userfines);
		return "fines";
	}

	@GetMapping("/fine")
	public String fine(Model model, @RequestParam String id) {

		// Add a check - Full Amount £130 or £65 if paid within 28 days
		int fineId = Integer.parseInt(id);
		model.addAttribute("fine", TvlRepository.getById(fineId));
		return "fine";

	}

	@PostMapping("/pay")
	public String pay(Model model, @RequestParam String id) {

		// Let's assume every payment is a good payment
		int fineId = Integer.parseInt(id);
		List<String> fine = TvlRepository.getById(fineId);
		fine.set(8, "PAID");

		// redirect to GetMapping page to avoid re-submitting form
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
